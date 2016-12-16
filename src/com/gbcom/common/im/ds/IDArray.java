/**
 * @(#)IDArray.java       07/11
 *
 * Copyright (c) 2007 GBCom Communication Technology Co.Ltd
 * All right reserved.
 */
package com.gbcom.common.im.ds;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

/**
 * 根据ID构成的数组，用于存储信息模型、显示模型生成的对象
 * 用id来计算hashcode，对于具有相同hashcode的对象，采用数组来保存，这样搜索快，插入慢
 * 
 * @author fengjing
 * @version 2.0
 */
public class IDArray implements IIDNameArray {
	/**
	 * 序列化版本ID
	 */
	private static final long serialVersionUID = -5083605736191830542L;

	/**
	 * The hash table data.
	 */
	private transient IIDNameStruct[][] table;

	/**
	 * The total number of entries in the hash table.
	 */
	private int count;

	/**
	 * The table is rehashed when its size exceeds this threshold. (The value of
	 * this field is (int)(capacity * loadFactor).)
	 * 
	 * @serial
	 */
	private int threshold;

	/**
	 * The load factor for the hashtable.
	 * 
	 * @serial
	 */
	private float loadFactor;

	/**
	 * 构造函数
	 */
	public IDArray() {
		this(11, 0.75f);
	}

	/**
	 * 构造函数
	 * 
	 * @param initialCapacity
	 *            初始容量
	 * @param factor
	 *            装载因素
	 */
	public IDArray(int initialCapacity, float factor) {
		this.table = new IIDNameStruct[initialCapacity][];
		this.loadFactor = factor;
		threshold = (int) (initialCapacity * loadFactor);
	}

	/**
	 * 构造函数
	 * 
	 * @param datas
	 *            要装载的数据
	 */
	public IDArray(ArrayList<IIDNameStruct> datas) {
		loadFactor = 0.75f;
		count = datas.size();
		int initialCapacity = 11;
		while (count > (threshold = (int) (loadFactor * initialCapacity))) {
			initialCapacity = initialCapacity * 2 + 1;
		}

		this.table = new IIDNameStruct[initialCapacity][];
		Vector[] temp = new Vector[initialCapacity];

		for (int i = 0; i < count; i++) {
			IIDNameStruct e = datas.get(i);

			int index = (e.getID() & 0x7FFFFFFF) % initialCapacity;
			Vector<IIDNameStruct> v = temp[index];
			if (v == null) {
				v = new Vector();
				temp[index] = v;
			}
			v.add(e);
		}

		for (int i = 0; i < initialCapacity; i++) {
			table[i] = IDNameStructUtil.sort(temp[i]);
		}
	}

	/**
	 * 根据oid得到IMO
	 * 
	 * @param id
	 *            int 待查对象oid
	 * @return IMO 找到的对象，或空
	 */
	public IIDNameStruct get(int id) {
		int index = (id & 0x7FFFFFFF) % table.length;
		IIDNameStruct[] array = table[index];
		index = IDNameStructUtil.binarySearch(array, id);
		if (index != -1) {
			return array[index];
		}
		return null;
	}

	/**
	 * 找到具有相同rdn操作对象
	 * 
	 * @param name
	 *            String 待找对象的rdn
	 * @return IIDNameStruct[] 找到的数组
	 */
	public IIDNameStruct[] get(String name) {
		Vector<IIDNameStruct> result = new Vector();
		for (int i = 0; i < table.length; i++) {
			IIDNameStruct[] e = table[i];
			if (e != null) {
				for (int j = 0; j < e.length; j++) {
					if (name.equals(e[j].getName())) {
						result.add(e[j]);
					}
				}
			}
		}

		if (result.size() == 0) {
			return null;
		}

		IIDNameStruct[] r = new IIDNameStruct[result.size()];
		result.toArray(r);
		return r;
	}

	@Override
	public IIDNameStruct findFirst(String name) {
		for (int i = 0; i < table.length; i++) {
			IIDNameStruct[] e = table[i];
			if (e != null) {
				for (int j = 0; j < e.length; j++) {
					if (name.equals(e[j].getName())) {
						return e[j];
					}
				}
			}
		}
		return null;
	}

	/**
	 * Increases the capacity of and internally reorganizes this hashtable, in
	 * order to accommodate and access its entries more efficiently. This method
	 * is called automatically when the number of keys in the hashtable exceeds
	 * this hashtable's capacity and load factor.
	 */
	protected void rehash() {
		int oldCapacity = table.length;
		IIDNameStruct[][] oldMap = table;

		int newCapacity = oldCapacity * 2 + 1;
		IIDNameStruct[][] newMap = new IIDNameStruct[newCapacity][];
		Vector[] temp = new Vector[newCapacity];

		threshold = (int) (newCapacity * loadFactor);
		table = newMap;

		for (int i = oldCapacity; i-- > 0;) {
			IIDNameStruct[] old = oldMap[i];
			if (old != null) {
				for (int j = old.length - 1; j > -1; j--) {
					IIDNameStruct e = old[j];

					int index = e.getID() % newCapacity;
					Vector<IIDNameStruct> v = temp[index];
					if (v == null) {
						v = new Vector();
						temp[index] = v;
					}
					v.add(e);
				}
			}
		}

		for (int i = 0; i < newCapacity; i++) {
			newMap[i] = IDNameStructUtil.sort(temp[i]);
		}
	}

	/**
	 * 返回当前map拥有的所有操作对象
	 * 
	 * @return IMO[] 操作对象数组
	 */
	public IIDNameStruct[] get() {
		IIDNameStruct[] result = new IIDNameStruct[count];
		int index = 0;
		for (int i = 0; i < table.length; i++) {
			IIDNameStruct[] entry = table[i];
			if (entry != null) {
				for (int j = entry.length - 1; j > -1; j--) {
					result[index++] = entry[j];
				}
			}
		}

		return result;
	}

	/**
	 * 向当前map添加操作对象。put方法同rehash方法都有一个排序插入过程，考虑到速度方面的因素，故没有放入独立的函数中
	 * 
	 * @param data
	 *            待加操作对象
	 */
	public void put(IIDNameStruct data) {
		if (count >= threshold) {
			// Rehash the table if the threshold is exceeded
			rehash();
		}
		count++;
		int index = (data.getID() & 0x7FFFFFFF) % table.length;
		table[index] = IDNameStructUtil.add(table[index], data);
	}

	@Override
	public void remove(IIDNameStruct in) {
		int index = (in.getID() & 0x7FFFFFFF) % table.length;
		int length = table[index] == null ? 0 : table[index].length;
		table[index] = IDNameStructUtil.remove(table[index], in);
		count = count
				- (length - (table[index] == null ? 0 : table[index].length));
	}

	@Override
	public int size() {
		return count;
	}

	@Override
	public Iterator iterator() {
		return new Enumerator();
	}

	/**
	 * A hashtable enumerator class. This class implements both the Enumeration
	 * and Iterator interfaces, but individual instances can be created with the
	 * Iterator methods disabled. This is necessary to avoid unintentionally
	 * increasing the capabilities granted a user by passing an Enumeration.
	 */
	private class Enumerator implements Iterator {
		int index = 0;

		int elementIndex = 0;

		/**
		 * 构造函数
		 */
		Enumerator() {
			for (index = 0; index < table.length; index++) {
				if (table[index] != null && table[index].length > 0) {
					break;
				}
			}
		}

		/**
		 * 枚举容器中是否还有别的对象
		 * 
		 * @return 容器中是否有其他对象，是则返回true，否则返回false
		 */
		public boolean hasMoreElements() {
			if (index >= table.length) {
				return false;
			}

			if (table[index].length > elementIndex) {
				return true;
			}

			elementIndex = 0;
			for (index++; index < table.length; index++) {
				if (table[index] != null && table[index].length > 0) {
					return true;
				}
			}

			return false;
		}

		/**
		 * 获得容器中的下一个对象
		 * 
		 * @return 返回容器中的下一个对象
		 */
		public Object nextElement() {
			if (hasMoreElements()) {
				return table[index][elementIndex++];
			}

			return null;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Iterator#hasNext()
		 */
		public boolean hasNext() {
			return hasMoreElements();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Iterator#next()
		 */
		public Object next() {
			return nextElement();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Iterator#remove()
		 */
		public void remove() {
			int tempIndex = index;
			int tempElement = elementIndex;
			BAD: {
				if (--tempElement >= 0) {
					break BAD;
				} else {
					while (tempIndex-- >= 0) {
						if (table[tempIndex] != null
								&& table[tempIndex].length > 0) {
							tempElement = table[tempIndex].length - 1;
							break BAD;
						}
					}
				}
				return;
			}

			IIDNameStruct[] datas = table[tempIndex];
			IIDNameStruct[] newDatas = new IIDNameStruct[datas.length - 1];
			System.arraycopy(datas, 0, newDatas, 0, tempElement);
			System.arraycopy(datas, tempElement + 1, newDatas, tempElement,
					newDatas.length - tempElement);
			table[tempIndex] = newDatas;
			index = tempIndex;
			elementIndex = tempElement;

		}
	}

	/**
	 * 
	 * @param s
	 * @throws java.io.IOException
	 */
	private synchronized void writeObject(ObjectOutputStream s)
			throws IOException {
		s.defaultWriteObject();
		s.writeInt(table.length);
		for (int i = 0; i < table.length; i++) {
			IIDNameStruct[] temp = table[i];
			s.writeInt(temp == null ? 0 : temp.length);
			if (temp != null) {
				for (int j = 0; j < temp.length; j++) {
					s.writeObject(temp[j]);
				}
			}
		}
	}

	/**
	 * @param s
	 * @throws java.io.IOException
	 * @throws ClassNotFoundException
	 */
	private synchronized void readObject(ObjectInputStream s)
			throws IOException, ClassNotFoundException {
		s.defaultReadObject();
		int length = s.readInt();
		table = new IIDNameStruct[length][];
		for (int i = 0; i < table.length; i++) {
			length = s.readInt();
			if (length != 0) {
				IIDNameStruct[] temp = new IIDNameStruct[length];
				for (int j = 0; j < temp.length; j++) {
					temp[j] = (IIDNameStruct) s.readObject();
				}
				table[i] = temp;
			}
		}
	}
}

/**
 * @(#)IDNameStructImpl.java       07/11
 *
 * Copyright (c) 2007 GBCom Communication Technology Co.Ltd
 * All right reserved.
 */
package com.gbcom.common.im.ds;

import java.util.List;

/**
 * IDNameStruct的工具类，实现一些基本的操作
 * 
 * @author fengjing
 * @version 2.0
 */
public class IDNameStructUtil {
	/**
	 * 对象数组
	 */
	public static final IIDNameStruct[] MO_ARRAY_TYPE = new IIDNameStruct[0];

	/**
	 * 在数组中二分法查找
	 * 
	 * @param a
	 *            目标数组
	 * @param key
	 *            要查找的关键字
	 * 
	 * @return 结果对应的数组下标
	 */
	public static int binarySearch(IIDNameStruct[] a, int key) {
		if (a == null) {
			return -1;
		}

		// key not found.
		return binarySearch(a, key, 0, a.length);
	}

	/**
	 * 在数组中二分法查找
	 * 
	 * @param a
	 *            目标数组
	 * @param key
	 *            查找的关键字
	 * @param offset
	 *            偏移量
	 * @param length
	 *            搜索范围
	 * @return 结果对应的数组下标
	 */
	public static int binarySearch(IIDNameStruct[] a, int key, int offset,
			int length) {
		if (length == 0) {
			return -1;
		}

		int off = offset;
		int high = off + length - 1;
		if (key < a[off].getID() || key > a[high].getID()) {
			return -1;
		}

		while (off <= high) {
			int mid = (off + high) >> 1;

			// cant be equal
			int cmp = -1;
			if (a[mid].getID() == key) {
				return mid;
			} else if (a[mid].getID() > key) {
				// Neither val is NaN, thisVal is larger
				cmp = 1;
			}

			if (cmp < 0) {
				off = mid + 1;
			} else if (cmp > 0) {
				high = mid - 1;
			}
		}

		// key not found.
		return -1;
	}

	/**
	 * 交换数组中两个对象的位置
	 * 
	 * @param x
	 *            目标数组
	 * @param a
	 *            对象a的数组下标
	 * @param b
	 *            对象b的数组下标
	 */
	private static void swap(IIDNameStruct[] x, int a, int b) {
		IIDNameStruct t = x[a];
		x[a] = x[b];
		x[b] = t;
	}

	/**
	 * 交换数组中两段对象的位置
	 * 
	 * @param x
	 *            目标数组
	 * @param p1
	 *            a段对象的开始下标
	 * @param p2
	 *            b段对象的开始下标
	 * @param n
	 *            交换对象的个数
	 */
	private static void vecswap(IIDNameStruct[] x, int p1, int p2, int n) {
		int a = p1;
		int b = p2;
		for (int i = 0; i < n; i++, a++, b++) {
			swap(x, a, b);
		}
	}

	/**
	 * 比较数组中三个对象的ID，返回中间值的数组下标
	 * 
	 * @param x
	 *            目标数组
	 * @param a
	 *            对象a的数组下标
	 * @param b
	 *            对象b的数组下标
	 * @param c
	 *            对象c的数组下标
	 * @return 中间值的数组下标
	 */
	private static int med3(IIDNameStruct[] x, int a, int b, int c) {
		return (x[a].getID() < x[b].getID() ? (x[b].getID() < x[c].getID() ? b
				: x[a].getID() < x[c].getID() ? c : a) : (x[b].getID() > x[c]
				.getID() ? b : x[a].getID() > x[c].getID() ? c : a));
	}

	/**
	 * 在数组中排序
	 * 
	 * @param x
	 *            目标数组
	 * @param off
	 *            偏移量
	 * @param len
	 *            要排序的对象个数
	 */
	public static void sort(IIDNameStruct[] x, int off, int len) {
		// Insertion sort on smallest arrays
		if (len < 7) {
			for (int i = off; i < len + off; i++) {
				for (int j = i; j > off && x[j - 1].getID() > x[j].getID(); j--) {
					swap(x, j, j - 1);
				}
			}
			return;
		}

		// Choose a partition element, v
		// Small arrays, middle element
		int m = off + (len >> 1);
		if (len > 7) {
			int l = off;
			int n = off + len - 1;
			if (len > 40) {
				// Big arrays, pseudomedian of 9
				int s = len / 8;
				l = med3(x, l, l + s, l + 2 * s);
				m = med3(x, m - s, m, m + s);
				n = med3(x, n - 2 * s, n - s, n);
			}

			// Mid-size, med of 3
			m = med3(x, l, m, n);
		}

		IIDNameStruct v = x[m];

		// Establish Invariant: v* (<v)* (>v)* v*
		int a = off, b = a, c = off + len - 1, d = c;
		while (true) {
			while (b <= c && x[b].getID() <= v.getID()) {
				if (x[b] == v) {
					swap(x, a++, b);
				}
				b++;
			}

			while (c >= b && x[c].getID() >= v.getID()) {
				if (x[c] == v) {
					swap(x, c, d--);
				}
				c--;
			}

			if (b > c) {
				break;
			}

			swap(x, b++, c--);
		}

		int s, n = off + len;
		s = Math.min(a - off, b - a);
		vecswap(x, off, b - s, s);
		s = Math.min(d - c, n - d - 1);
		vecswap(x, b, n - s, s);

		// Recursively sort non-partition-elements
		//off checkstyle
		if ((s = b - a) > 1) {
			sort(x, off, s);
		}
		//off checkstyle
		if ((s = d - c) > 1) {
			sort(x, n - s, s);
		}
	}

	/**
	 * 在数组中排序
	 * 
	 * @param x
	 *            目标数组
	 * @return 返回排序后的数组
	 */
	public static IIDNameStruct[] sort(IIDNameStruct[] x) {
		if (x == null) {
			return null;
		}

		sort(x, 0, x.length);
		return x;
	}

	/**
	 * 在队列中排序
	 * 
	 * @param a
	 *            目标队列
	 * @return 返回排序后的数组
	 */
	public static IIDNameStruct[] sort(List<IIDNameStruct> a) {
		if (a == null || a.size() == 0) {
			return null;
		}

		IIDNameStruct[] result = (IIDNameStruct[]) a.toArray(MO_ARRAY_TYPE);
		sort(result, 0, result.length);
		return result;
	}

	/**
	 * 在数组中插入对象
	 * 
	 * @param a
	 *            目标数组
	 * @param data
	 *            待插入的对象
	 * @return 返回对象插入后的数组
	 */
	public static IIDNameStruct[] add(IIDNameStruct[] a, IIDNameStruct data) {
		IIDNameStruct[] result;
		if (a == null || a.length == 0) {
			result = a == null ? (IIDNameStruct[]) java.lang.reflect.Array
					.newInstance(data.getClass(), 1)
					: (IIDNameStruct[]) java.lang.reflect.Array.newInstance(a
							.getClass().getComponentType(), 1);
			result[0] = data;
			return result;
		}

		result = (IIDNameStruct[]) java.lang.reflect.Array.newInstance(a
				.getClass().getComponentType(), a.length + 1);
		int index = indexOf(a, data);
		System.arraycopy(a, 0, result, 0, index);
		result[index] = data;
		System.arraycopy(a, index, result, index + 1, a.length - index);
		return result; // key not found.
	}

	/**
	 * 在数组中删除对象
	 * 
	 * @param a
	 *            目标数组
	 * @param data
	 *            待删除的对象
	 * @return 返回删除对象后的数组
	 */
	public static IIDNameStruct[] remove(IIDNameStruct[] a, IIDNameStruct data) {
		if (a == null || a.length == 0) {
			return null;
		}

		return remove(a, binarySearch(a, data.getID()));
	}

	/**
	 * 在数组中删除对象
	 * 
	 * @param a
	 *            目标数组
	 * @param index
	 *            指定删除的数组下标
	 * @return 返回删除对象后的数组
	 */
	public static IIDNameStruct[] remove(IIDNameStruct[] a, int index) {
		if (index == -1) {
			return a;
		}

		if (a.length == 1) {
			return null;
		}

		IIDNameStruct[] result = (IIDNameStruct[]) java.lang.reflect.Array
				.newInstance(a.getClass().getComponentType(), a.length - 1);
		System.arraycopy(a, 0, result, 0, index);
		System.arraycopy(a, index + 1, result, index, a.length - index - 1);
		return result; // key not found.
	}

	/**
	 * 获得某一对象在数组中的下标
	 * 
	 * @param a
	 *            目标数组
	 * @param data
	 *            待查找对象
	 * @return 返回该对象在数组中的下标
	 */
	public static int indexOf(IIDNameStruct[] a, IIDNameStruct data) {
		return indexOf(a, data, 0, a.length);
	}

	/**
	 * 获得某一对象在数组中的下标
	 * 
	 * @param a
	 *            目标数组
	 * @param data
	 *            待查找对象
	 * @param offset
	 *            偏移量
	 * @param length
	 *            查找的长度
	 * @return 返回该对象在数组中的下标
	 */
	public static int indexOf(IIDNameStruct[] a, IIDNameStruct data,
			int offset, int length) {
		// int highest = off + length - 1;
		int off = offset;
		int high = off + length - 1;// highest;
		int mid = 0;

		while (off <= high) {
			mid = (off + high) >> 1;

			// cant be equal
			int cmp = -1;
			if (a[mid].getID() > data.getID()) {
				// Neither val is NaN, thisVal is larger
				cmp = 1;
			}

			if (cmp < 0) {
				off = mid + 1;
			} else if (cmp > 0) {
				high = mid - 1;
			}
		}

		// if (mid > highest || a[mid].id > data.id)
		if (mid > high) {
			return mid;
		}

		return mid + 1;
	}

	/**
	 * 在数组中确定某一对象应插入的位置
	 * 
	 * @param a
	 *            目标数组
	 * @param id
	 *            对象ID
	 * @return 该对象在数组中应插入的位置
	 */
	public static int bestIndexOf(IIDNameStruct[] a, int id) {
		return bestIndexOf(a, id, 0, a.length);
	}

	/**
	 * 在数组中确定某一对象应插入的位置
	 * 
	 * @param a
	 *            目标数组
	 * @param id
	 *            对象ID
	 * @param offset
	 *            偏移量
	 * @param length
	 *            查找长度
	 * @return 该对象在数组中应插入的位置
	 */
	public static int bestIndexOf(IIDNameStruct[] a, int id, int offset,
			int length) {
		// int highest = off + length - 1;
		int off = offset;
		int high = off + length - 1;// highest;
		int mid = 0;

		while (off <= high) {
			mid = (off + high) >> 1;
			// cant be equal
			int cmp = -1;
			if (a[mid].getID() > id) {
				cmp = 1; // Neither val is NaN, thisVal is larger
			} else if (a[mid].getID() == id) {
				break;
			}

			if (cmp < 0) {
				off = mid + 1;
			} else if (cmp > 0) {
				high = mid - 1;
			}
		}

		// if (mid > highest || a[mid].id > id)
		if (mid > high) {
			return mid;
		}

		return mid + 1;
	}

	/**
	 * 生成ID的数组
	 * 
	 * @param a
	 *            目标数组
	 * @return 返回生成的ID数组
	 */
	public static int[] toIDArray(IIDNameStruct[] a) {
		if (a != null) {
			int[] result = new int[a.length];
			for (int i = 0; i < result.length; i++) {
				result[i] = a[i].getID();
			}

			return result;
		}
		return null;
	}

	/**
	 * 生成名称的数组
	 * 
	 * @param a
	 *            目标数组
	 * @return 返回生成的名称数组
	 */
	public static String[] toNameArray(IIDNameStruct[] a) {
		if (a != null) {
			String[] result = new String[a.length];
			for (int i = 0; i < result.length; i++) {
				result[i] = a[i].getName();
			}

			return result;
		}
		return null;
	}
}

/**
 * @(#)EnumAttributeDesc.java       07/11
 *
 * Copyright (c) 2007 GBCom Communication Technology Co.Ltd
 * All right reserved.
 */
package com.gbcom.common.im.desc;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 枚举属性描述，继承AttributeDesc类
 * 
 * @author fengjing
 * @version 2.0
 * 
 */
public class EnumAttributeDesc extends AttributeDesc implements IEnumAttribute {
	/**
	 * 序列化版本ID
	 */
	private static final long serialVersionUID = -8211635415581627L;

	/**
	 * 记录日志
	 */
	private Logger logger = LoggerFactory.getLogger(EnumAttributeDesc.class);

	/**
	 * 实体类列表
	 */
	private ArrayList<Entry> entries = new ArrayList<Entry>();

	/**
	 * 索引组名
	 */
	private HashMap<Integer, String> groups = new HashMap<Integer, String>();

	/**
	 *
	 */
	public EnumAttributeDesc() {
		super();
	}

	/**
	 * 
	 * @param attrID int
	 * @param name String
	 * @param enumValue int[]
	 * @param enumName String[]
	 */
	public EnumAttributeDesc(int attrID, String name, int[] enumValue,
			String[] enumName) {
		super();

		setAliasName(name);
		setAttrID(attrID);
		setControl("6"); // ComboBox Editor
		setData("5");
		setDefault("");
		setLength("2");
		setMask("56");
		setName(name);

		for (int i = 0; i < enumValue.length && i < enumName.length; i++) {
			addEntry(0, "enum", "", enumValue[i], enumName[i]);
		}
	}

	/**
	 * 添加entry
	 * @param group String
	 * @param groupName String
	 * @param attrName String
	 * @param attrValue String
	 * @param enumName String 
	 */
	public void addEntry(final String group, final String groupName,
			final String attrName, final String attrValue, final String enumName) {
		try {
			int groupID = Integer.parseInt(group);
			int value = Integer.parseInt(attrValue);

			addEntry(groupID, groupName, attrName, value, enumName);
		} catch (NumberFormatException e) {
			logger.error("EnumAttributeDesc.addEntry", e);
		}
	}

	/**
	 * 添加entry
	 * @param groupID INT
	 * @param groupName String
	 * @param attrName String
	 * @param attrValue String
	 * @param enumName String
	 */
	public void addEntry(final int groupID, final String groupName,
			final String attrName, final int attrValue, final String enumName) {
		try {
			if (!groups.containsValue(groupName)) {
				groups.put(Integer.valueOf(groupID), groupName);
			}

			Entry newEntry = new Entry(groupID, attrName, attrValue, enumName);
			entries.add(newEntry);
		} catch (NumberFormatException e) {
			logger.error("EnumAttributeDesc.addEntry", e);
		}
	}

	/**
	 * 添加组
	 * @param group String
	 * @param groupName String
	 */
	public void addGroup(final String group, final String groupName) {
		try {
			int groupID = Integer.parseInt(group);
			if (!groups.containsValue(groupName)) {
				groups.put(Integer.valueOf(groupID), groupName);
			}
		} catch (NumberFormatException e) {
			logger.error("EnumAttributeDesc.addGroup", e);
		}
	}

	/**
	 * 
	 */
	public void removeAllEntries() {
		groups.clear();
		entries.clear();
	}

	/**
	 * 删除entrys
	 * @param groupIds int[]
	 */
	public void removeEntries(int[] groupIds) {
		ArrayList<Entry> entries = new ArrayList<Entry>();

		int length = this.entries.size();
		for (int i = 0; i < length;) {
			Entry e = this.entries.get(i);

			int j = 0;
			for (; j < groupIds.length; j++) {
				if (e.groupID == groupIds[j]) {
					entries.remove(e);
					break;
				}
			}

			if (j == groupIds.length) {
				i++;
			}
		}
	}

	@Override
	public String getEnumName(int groupId, int value, boolean isBitSet) {
		if (isBitSet) {
			for (int i = 0; i < entries.size(); i++) {
				Entry e = (Entry) entries.get(i);
				if ((e.groupID == groupId)) {
					if (e.groupID == 0) {
						// 如果group id为0则代表是普通枚举型
						if (e.value == value) {
							return e.uIDisplay;
						}
					} else {
						// 如果group id为非0则代表是bitset枚举型

						if (e.value == (value & groupId)) {
							return e.uIDisplay;
						}
					}
				}
			}
			// 主要是针对存在可以自行输入值的一些配置项，
			return "" + value;
			// return null;
		} else {
			for (int i = 0; i < entries.size(); i++) {
				Entry e = (Entry) entries.get(i);
				if ((e.groupID == groupId)) {
					if (e.value == value) {
						return e.uIDisplay;
					}
				}
			}

			// 主要是针对存在可以自行输入值的一些配置项，
			return "" + value;
			// return null;
		}
	}

	@Override
	public int getGroupValue(int groupId, int value, boolean isBitSet) {
		if (isBitSet) {
			for (int i = 0; i < entries.size(); i++) {
				Entry e = (Entry) entries.get(i);
				if ((e.groupID == groupId)) {
					if (e.groupID == 0) {
						// 如果group id为0则代表是普通枚举型
						return value;
					} else {
						// 如果group id为非0则代表是bitset枚举型
						return (value & groupId);
					}
				}
			}
			return -1;
		} else {
			for (int i = 0; i < entries.size(); i++) {
				Entry e = (Entry) entries.get(i);
				if ((e.groupID == groupId)) {
					return value;
				}
			}
			return -1;
		}
	}

	@Override
	public String[] getAllDis(int groupId) {
		int length = this.entries.size();
		ArrayList<String> temp = new ArrayList<String>();
		for (int i = 0; i < length; i++) {
			if (this.entries.get(i).groupID == groupId) {
				temp.add(this.entries.get(i).uIDisplay);
			}
		}
		return temp.toArray(new String[temp.size()]);
	}

	/**
	 * 获取所有枚举值
	 * 
	 * @return Integer[]
	 */
	public Integer[] getAllEnumVars() {
		int length = this.entries.size();
		ArrayList<Integer> temp = new ArrayList<Integer>();
		for (int i = 0; i < length; i++) {
			temp.add(this.entries.get(i).value);
		}
		return temp.toArray(new Integer[temp.size()]);
	}

	@Override
	public int[] getAllGroup() {
		if (this.groups.isEmpty()) {
			return null;
		}

		int[] result = new int[this.groups.keySet().size()];
		int i = 0;
		Iterator<Integer> it = this.groups.keySet().iterator();
		while (it.hasNext()) {
			result[i++] = it.next().intValue();
		}

		// 增加排序后返回
		int temp;
		for (int j = 0; j < result.length; j++) {
			for (int k = 0; k < result.length; k++) {
				if (result[j] < result[k]) {
					temp = result[j];
					result[j] = result[k];
					result[k] = temp;
				}
			}
		}
		return result;
	}

	@Override
	public int getEnumValue(int groupId, String dis) {
		int length = this.entries.size();
		for (int i = 0; i < length; i++) {
			Entry e = this.entries.get(i);
			if (e.groupID == groupId && e.uIDisplay.equals(dis)) {
				return e.value;
			}
		}
		// 主要是针对存在可以自行输入值的一些配置项，
		return Integer.parseInt(dis);
		// return -1;
	}

	@Override
	public int getGroupValue(int groupId, int value) {
		return this.getGroupValue(groupId, value, true);
	}

	@Override
	public String getEnumName(int groupId, int value) {
		return this.getEnumName(groupId, value, true);
	}

	@Override
	public String getGroupName(int groupId) {
		if (this.groups.containsKey(Integer.valueOf(groupId))) {
			return this.groups.get(Integer.valueOf(groupId));
		}
		return null;
	}

	/**
	 * 获取entrys
	 * @param groupId int
	 * @return ArrayList<Entry>
	 */
	public ArrayList<Entry> getEntries(int groupId) {
		ArrayList<Entry> entries = new ArrayList<Entry>();
		int length = this.entries.size();
		for (int i = 0; i < length; i++) {
			Entry e = this.entries.get(i);
			if (e.groupID == groupId) {
				entries.add(e);
			}
		}
		return entries;
	}

	@Override
	public AttributeDesc clone() {
		EnumAttributeDesc attrDescClone = new EnumAttributeDesc();
		attrDescClone.aid = this.aid;
		attrDescClone.aliasName = this.aliasName;
		attrDescClone.attrName = this.attrName;
		attrDescClone.controlType = this.controlType;
		attrDescClone.defaultValue = this.defaultValue;
		attrDescClone.dataType = this.dataType;
		attrDescClone.length = this.length;
		attrDescClone.mask = this.mask;
		attrDescClone.maxValue = this.maxValue;
		attrDescClone.minValue = this.minValue;

		attrDescClone.atCmd = this.atCmd;
		attrDescClone.rspParser = this.rspParser;

		attrDescClone.entries = (ArrayList<Entry>) this.entries.clone();
		attrDescClone.groups = (HashMap<Integer, String>) this.groups.clone();

		return attrDescClone;
	}

	/**
     *
     */
	public class Entry implements Serializable {
		private static final long serialVersionUID = 6540321210684306803L;

		/**
		 * 实体对应的Index
		 */
		private int groupID;

		/**
		 * 实体对应的名
		 */
		private String name;

		/**
		 * 实体对应的值
		 */
		private int value;

		/**
		 * 实体对应的界面显示 这里，其实是对应各个枚举值设置请求的AT指令
		 */
		private String uIDisplay;

		/**
		 * 构造函数
		 * @param name String
		 * @param groupId
		 *            对应的groupId
		 * @param value
		 *            对应的属性值
		 * @param enumName
		 *            对应的界面显示
		 */
		protected Entry(int groupId, String name, int value, String enumName) {
			this.groupID = groupId;
			this.name = name;
			this.value = value;
			this.uIDisplay = enumName;
		}

		/**
		 * 
		 * @return String
		 */
		public String getUIDisplay() {
			return uIDisplay;
		}

		/**
		 * 
		 * @return int
		 */
		public int getValue() {
			return value;
		}

	}

}

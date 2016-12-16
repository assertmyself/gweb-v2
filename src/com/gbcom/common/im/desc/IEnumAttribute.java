/**
 * @(#)IEnumAttribute.java       07/11
 *
 * Copyright (c) 2007 GBCom Communication Technology Co.Ltd
 * All right reserved.
 */
package com.gbcom.common.im.desc;

/**
 * 枚举类型的操作接口 本类的部分成员方法可能本版本中用不着，预留，先不删除
 * 
 * 
 * @author fengjing
 * @version 2.0
 * 
 */
public interface IEnumAttribute {
	/**
	 * 获得某一索引下的所有界面显示
	 * 
	 * @param group
	 *            指定的索引
	 * @return 返回该索引下的所有界面显示
	 */
	public String[] getAllDis(int group);

	/**
	 * 获得该枚举属性的所有BitSet的group值
	 * 
	 * @return 返回该枚举属性的所有BitSet的group值
	 */
	public int[] getAllGroup();

	/**
	 * 获得某一索引下某一界面显示的值 通过groupId判断是否bitset型枚举,groupId大于0则是bitset型枚举
	 * @param group int
	 * @param dis
	 *            指定的界面显示
	 * @return 对应的属性值
	 */
	public int getEnumValue(int group, String dis);

	/**
	 * 获得某一groupId值对应的界面显示<br>
	 * 通过groupId判断是否bitset型枚举,groupId大于0则是bitset型枚举
	 * @param group int
	 * @param value
	 *            指定的属性值
	 * @return 对应的界面显示
	 */
	public String getEnumName(int group, int value);

	/**
	 * 获得某一groupId值对应的界面显示<br>
	 * 
	 * @param group int
	 * @param value int
	 * @param isBitSet boolean
	 * @return String
	 */
	public String getEnumName(int group, int value, boolean isBitSet);

	/**
	 * 传入属性的指和groupid，获取该groupid的值<br>
	 * 通过groupId判断是否bitset型枚举,groupId大于0则是bitset型枚举
	 * 
	 * @param groupId
	 *            该属性下的某一个组
	 * @param value
	 *            该属性的value
	 * @return int
	 */
	public int getGroupValue(int groupId, int value);

	/**
	 * 传入属性的指和groupid，获取该groupid的值
	 * 
	 * @param groupId
	 *            索引组号
	 * @param value int
	 * @param isBitSet boolean 
	 * @return int 
	 */
	public int getGroupValue(int groupId, int value, boolean isBitSet);

	/**
	 * 获得某一组索引对应的索引组名
	 * 
	 * @param group
	 *            索引组号
	 * @return 索引组名
	 */
	public String getGroupName(int group);
}

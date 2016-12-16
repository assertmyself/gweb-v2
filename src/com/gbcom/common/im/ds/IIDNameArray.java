/**
 * @(#)IIDNameArray.java       07/11
 *
 * Copyright (c) 2007 GBCom Communication Technology Co.Ltd
 * All right reserved.
 */
package com.gbcom.common.im.ds;

import java.io.Serializable;
import java.util.Iterator;

/**
 * 对象数组，用于存储信息模型、显示模型生成的对象
 * 
 * @author fengjing
 * @version 2.0
 */
public interface IIDNameArray extends Serializable {
	/**
	 * 根据oid得到IMO
	 * 
	 * @param id
	 *            int 待查对象oid
	 * @return IMO 找到的对象，或空
	 */
	public IIDNameStruct get(int id);

	/**
	 * 找到具有相同rdn操作对象
	 * 
	 * @param name
	 *            String 待找对象的rdn
	 * @return IIDNameStruct[] 找到的数组
	 */
	public IIDNameStruct[] get(String name);

	/**
	 * 返回第一个同名字相符的对象
	 * 
	 * @param name
	 *            String
	 * @return IDNameStruct 返回第一个同名字相符的对象
	 */
	public IIDNameStruct findFirst(String name);

	/**
	 * 返回当前map拥有的所有操作对象
	 * 
	 * @return IMO[] 操作对象数组
	 */
	public IIDNameStruct[] get();

	/**
	 * 向当前map添加操作对象
	 * 
	 * @param data
	 *            IIDNameStruct 待加操作对象
	 */
	public void put(IIDNameStruct data);

	/**
	 * 从当前列表中删除指定对象
	 * 
	 * @param mo
	 *            IIDNameStruct 待删除对象
	 */
	public void remove(IIDNameStruct mo);

	/**
	 * 返回当前map的数量
	 * 
	 * @return int 返回当前map的数量
	 */
	public int size();

	/**
	 * 将数组构成迭代器
	 * 
	 * @return 生成的迭代器
	 */
	public Iterator iterator();
}

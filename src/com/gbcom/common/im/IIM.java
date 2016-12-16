/**
 * @(#)IIM.java      07/11
 *
 * Copyright (c) 2007 GBCom Communication Technology Co.Ltd
 * All right reserved.
 */

package com.gbcom.common.im;

import com.gbcom.common.im.desc.IClassDesc;

import java.io.Serializable;

/**
 * 信息模型接口
 * 
 * @author fengjing
 * @version 2.0
 * 
 */
public interface IIM extends Serializable {
	/**
	 * 根据类的cid查找该类的描述
	 * 
	 * @param cid
	 *            某一类的ID
	 * @return 返回该cid的类描述接口
	 */
	public IClassDesc getClassDesc(int cid);

	/**
	 * 根据类的名字返回该类的描述
	 * 
	 * @param className
	 *            某一类的名字
	 * @return 返回该名字的类描述接口
	 */
	public IClassDesc getClassDesc(String className);

	/**
	 * 根据Table Name返回classDesc
	 *  
	 * @param tableName String
	 * @return IClassDesc
	 */
	public IClassDesc getClassDescByTableName(String tableName);

	/**
	 * 获取该信息模型的版本号
	 * 
	 * @return 返回该模型的版本号
	 */
	public String getVersion();

	/**
	 * 获取该信息模型的网元类型
	 * 
	 * @return 返回该模型的网元类型
	 */
	public String getType();

	/**
	 * 返回时间戳
	 * 
	 * @return 返回该模型的时间戳
	 */
	public long getTimeStamp();

	/**
	 * 返回该网元子树根节点
	 * 
	 * @return 返回该模型的根类描述
	 */
	public IClassDesc getRoot();
}

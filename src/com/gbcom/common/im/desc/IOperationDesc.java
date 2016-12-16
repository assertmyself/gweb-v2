/**
 * @(#)IOperationDesc.java       07/11
 *
 * Copyright (c) 2007 GBCom Communication Technology Co.Ltd
 * All right reserved.
 */

package com.gbcom.common.im.desc;

import java.io.Serializable;

/**
 * 操作描述类，继承自Serializable
 * 
 * @author fengjing
 * @version 2.0
 * 
 */
public interface IOperationDesc extends Serializable {
	/**
	 * OPT，这个静态变量和IM中的冗余
	 */
	String OPT = "opt";
	/**
	 * GROUP
	 */
	String GROUP = "group";
	/**
	 * ID
	 */
	String ID = "id";
	/**
	 * NAME
	 */
	String NAME = "name";
	/**
	 * AT_CMD
	 */
	String AT_CMD = "atCmd";
	/**
	 * RSP_PARSER
	 */
	String RSP_PARSER = "rspParser";
	/**
	 * ENTRY
	 */
	String ENTRY = "entry";
	/**
	 * KEY
	 */
	String KEY = "key";
	/**
	 * LEVEL
	 */
	String LEVEL = "level";

	/**
	 * 该方法用于获取对象类型CID。
	 * 
	 * @return 返回对象类型CID
	 */
	public int getCID();

	/**
	 * 该方法用于获取操作组ID。
	 * 
	 * @return 返回操作组ID
	 */
	public int getOperGroup();

	/**
	 * 该方法用于获取指定管理对象类的指定操作的操作名称。
	 * 
	 * 
	 * @return 返回参数指定的管理对象类的操作的操作名称
	 */
	public String getOperName();

	/**
	 * 该方法用于获取指定管理对象类的指定操作的操作级别。
	 * 
	 * @return 返回参数指定的管理对象类的操作的操作级别
	 */
	public int getOperLevel();

	/**
	 * 该方法用于获取指定对象类的指定操作类型的操作ID。
	 * 
	 * @return int
	 */
	public int getOperID();

	/**
	 * getAtCmd
	 * @return String
	 */
	public String getAtCmd();

	/**
	 * getRspParserClassName  syj
	 * @return String
	 */
	public String getRspParserClassName();

}

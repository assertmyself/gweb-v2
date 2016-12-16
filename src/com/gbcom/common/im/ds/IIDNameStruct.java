/**
 * @(#)IIDNameStruct.java       07/11
 *
 * Copyright (c) 2007 GBCom Communication Technology Co.Ltd
 * All right reserved.
 */
package com.gbcom.common.im.ds;

import java.io.Serializable;

/**
 * 接口，用于信息模型、显示模型生成的对象
 * 
 * @author fengjing
 * @version 2.0
 */
public interface IIDNameStruct extends Serializable {
	/**
	 * 获得对象ID
	 * 
	 * @return 返回对象的ID
	 */
	public int getID();

	/**
	 * 获得对象名
	 * 
	 * @return 返回对象名
	 */
	public String getName();
}

/**
 * @(#)RootClassDesc.java       07/11
 *
 * Copyright (c) 2007 GBCom Communication Technology Co.Ltd
 * All right reserved.
 */
package com.gbcom.common.im.desc;

/**
 * @author fengjing
 * @version 2.0
 * 
 */
public class RootClassDesc extends ClassDesc {
	/**
	 * 序列化版本ID
	 */
	private static final long serialVersionUID = 3465065732137533228L;

	/**
	 * 获取网元类型
	 */
	private String type;

	/**
	 * 获取网元版本号
	 */
	private String version;

	/**
	 * 构造函数
	 * 
	 * @param classID int
	 * @param className String
	 */
	public RootClassDesc(int classID, String className) {
		super(classID, className);
	}

	/**
	 * 构造函数
	 * 
	 * @param classID String
	 * @param className String
	 */
	public RootClassDesc(String classID, String className) {
		super(classID, className);
	}

	/**
	 * @return Returns the type.
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * @param type
	 *            The type to set.
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return Returns the version.
	 */
	public String getVersion() {
		return this.version;
	}

	/**
	 * @param version
	 *            The version to set.
	 */
	public void setVersion(String version) {
		this.version = version;
	}

}

/**
 * @(#)VIMInitialException.java      07/11
 *
 * Copyright (c) 2007 GBCom Communication Technology Co.Ltd
 * All right reserved.
 */
package com.gbcom.common.im.exception;

/**
 * 信息模型、显示模型初始化异常，继承自Exception
 * 
 * @author fengjing
 * @version 2.0
 * 
 */
public class IMInitialException extends Exception {
	/**
	 * 序列化版本号
	 */
	private static final long serialVersionUID = -8211665498632081627L;

	/**
	 * 构造函数
	 */
	public IMInitialException() {
		super("信息模型初始化失败");
	}

	/**
	 * 构造函数
	 * 
	 * @param ex
	 *            异常信息
	 */
	public IMInitialException(String ex) {
		super(ex);
	}

	/**
	 * IMInitialException
	 * @param ex String
	 * @param e Throwable
	 */
	public IMInitialException(String ex, Throwable e) {
		super(ex, e);
	}
}

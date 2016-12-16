/**
 * @(#)VIMInitialException.java      07/11
 *
 * Copyright (c) 2007 GBCom Communication Technology Co.Ltd
 * All right reserved.
 */
package com.gbcom.common.im.exception;

/**
 * 解析MCU响应异常，继承自Exception
 * 
 * @author fengjing
 * @version 2.0
 * 
 */
public class McuResponseParserException extends Exception {
	/**
	 * 序列化版本号
	 */
	private static final long serialVersionUID = -8211665498632081627L;

	/**
	 * 构造函数
	 */
	public McuResponseParserException() {
		super("解析MCU响应异常");
	}

	/**
	 * 构造函数
	 * 
	 * @param ex
	 *            异常信息
	 */
	public McuResponseParserException(String ex) {
		super(ex);
	}

	/**
	 * McuResponseParserException
	 * @param ex String
	 * @param e Throwable
	 */
	public McuResponseParserException(String ex, Throwable e) {
		super(ex, e);
	}
}

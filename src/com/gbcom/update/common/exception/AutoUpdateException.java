package com.gbcom.update.common.exception;

/**
 * 自动升级异常
 * 
 * @author doujun
 * 
 */
public class AutoUpdateException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1234578122222L;

	/**
	 * 无参构造器
	 */
	public AutoUpdateException() {

	}

	/**
	 * @param message
	 *            异常信息
	 * @param cause
	 *            异常
	 */
	public AutoUpdateException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 *            异常信息
	 */
	public AutoUpdateException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 *            异常
	 */
	public AutoUpdateException(Throwable cause) {
		super(cause);
	}

}

/**
 * @(#)IllegalCmArgumentException.java       07/11
 *
 * Copyright (c) 2007 GBCom Communication Technology Co.Ltd
 * All right reserved.
 */
package com.gbcom.common.im.exception;

/**
 * 参数输入错误抛出异常
 * 
 * @author fengjing
 * @version 2.0
 * 
 */
public class IllegalCmArgumentException extends IllegalArgumentException {
	/**
	 * 序列化版本ID
	 */
	private static final long serialVersionUID = -5555554470754667797L;

	/**
	 * Constructs an <code>ParameterWrongException</code> with <code>null</code>
	 * as its error detail message.
	 */
	public IllegalCmArgumentException() {
		super("参数输入错误");
	}

	/**
	 * Constructs an <code>ParameterWrongException</code> with the specified
	 * detail message. The error message string <code>s</code> can later be
	 * retrieved by the <code>{@link Throwable#getMessage}</code> method of
	 * class <code>java.lang.Throwable</code>.
	 * 
	 * @param s
	 *            the detail message.
	 */
	public IllegalCmArgumentException(String s) {
		super(s);
	}

	/**
	 * Constructs a new exception with the specified detail message and cause.
	 * 
	 * <p>
	 * Note that the detail message associated with <code>cause</code> is
	 * <i>not</i> automatically incorporated in this exception's detail message.
	 * 
	 * @param message
	 *            the detail message (which is saved for later retrieval by the
	 *            {@link Throwable#getMessage()} method).
	 * @param cause
	 *            the cause (which is saved for later retrieval by the
	 *            {@link Throwable#getCause()} method). (A <tt>null</tt> value
	 *            is permitted, and indicates that the cause is nonexistent or
	 *            unknown.)
	 * @since 1.5
	 */
	public IllegalCmArgumentException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructs a new exception with the specified cause and a detail message
	 * of <tt>(cause==null ? null : cause.toString())</tt> (which typically
	 * contains the class and detail message of <tt>cause</tt>). This
	 * constructor is useful for exceptions that are little more than wrappers
	 * for other throwables (for example,
	 * {@link java.security.PrivilegedActionException}).
	 * 
	 * @param cause
	 *            the cause (which is saved for later retrieval by the
	 *            {@link Throwable#getCause()} method). (A <tt>null</tt> value
	 *            is permitted, and indicates that the cause is nonexistent or
	 *            unknown.)
	 * @since 1.5
	 */
	public IllegalCmArgumentException(Throwable cause) {
		super(cause);
	}

}

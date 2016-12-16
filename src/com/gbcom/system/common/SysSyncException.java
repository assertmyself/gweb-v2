package com.gbcom.system.common;
/**
 * 同步错误。
 * 
 * @author SunYanzheng
 * @date 2014-12-24,下午04:54:36
 * @version v1.0.0
 * @see com.gbcom.system.common.SysSyncException
 */
public class SysSyncException extends Exception{

	private static final long serialVersionUID = 1L;

	/**
	 * SysSyncException
	 */
	public SysSyncException(){
		super("SysSyncException");
	}
	
	/**
	 * SysSyncException
	 * @param msg String
	 */ 
	public SysSyncException(String msg){
		super(msg);
	}
	/**
	 * SysSyncException
	 * @param msg String
	 * @param t Throwable
	 */ 
	public SysSyncException(String msg,Throwable t){
		super(msg, t);
	}
}

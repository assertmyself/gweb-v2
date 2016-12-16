//off checkstyle
package com.gbcom.system.aop;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.gbcom.system.utils.Constants;

/**
 * 业务日志的元注释,用来向切面中传递消息
 * 
 * @author syz
 * @date Nov 27, 2015 10:18:31 AM
 * @version 1.0.0
 * @since com.gbcom.system.aop.UserLog
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface UserLog {
	public static final String USERLOG_EVENTTYPE_QUERY="QUERY";
	public static final String USERLOG_EVENTTYPE_VIEW="VIEW";
	public static final String USERLOG_EVENTTYPE_ADD="ADD";
	public static final String USERLOG_EVENTTYPE_MODIFY="MODIFY";
	public static final String USERLOG_EVENTTYPE_DELETE="DELETE";
	public static final String USERLOG_EVENTTYPE_EXPORT="EXPORT";
	public static final String USERLOG_EVENTTYPE_IMPORT="IMPORT";
	public static final String USERLOG_EVENTTYPE_NEOPER="NEOPER";
	public static final String USERLOG_EVENTTYPE_UNKNOW="UNKNOW";
	//仅用于版本自动更新
	public static final String USERLOG_EVENTTYPE_UPDATE="UPDATE";
	
	
	public static final String USERLOG_RESULT_SUCCESS="SUCCESS";
	public static final String USERLOG_RESULT_FAILED="FAILED";
	
	public static final String USERLOG_LOGTYPE_LOGIN=Constants.LOG_TYPE_LOGIN;
	public static final String USERLOG_LOGTYPE_BUSSINESS=Constants.LOG_TYPE_BUSSINESS;
	public static final String USERLOG_LOGTYPE_WS=Constants.LOG_TYPE_WS;
	public static final String USERLOG_LOGTYPE_LOGOUT=Constants.LOG_TYPE_LOGOUT;
	/**
	 * 日志类型：2
	 */
	String LogType() default USERLOG_LOGTYPE_BUSSINESS;

	/**
	 * 事件类型,默认为内置general类型
	 */
	String eventType() default "";

	/**
	 * 操作模块
	 */
	String moudle() default "";
	
	/**
	 * 当业务操作成功时的日志信息
	 */
	String result() default "SUCCESS";

	/**
	 * 额外信息;对应message
	 */
	String description()default "";

	/**
	 * 如果为false等同于不再上面 添加注解
	 * @return
	 */
	boolean include()default true;
}

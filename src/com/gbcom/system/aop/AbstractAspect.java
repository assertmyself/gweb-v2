package com.gbcom.system.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * zing定义AOP切面接口，
 * 使用aspect
 * @author syz
 * @date Nov 27, 2015 10:04:17 AM
 * @version 1.0.0
 * @since com.gbcom.system.aop.AbstractAspect
 */
public abstract class AbstractAspect {
	/**
	 * 日志记录器
	 */
	protected static Logger logger = LoggerFactory
			.getLogger(AbstractAspect.class);
	/**
	 * 前置处理，
	 * @param joinPoint ProceedingJoinPoint
	 * @throws  Throwable Throwable
	 */
	public abstract void doBefore(JoinPoint joinPoint) throws Throwable ;

	/**
	 * 后置处理
	 * @param joinPoint ProceedingJoinPoint
	 * @throws  Throwable Throwable
	 */
	public abstract void doAfter(JoinPoint joinPoint) throws Throwable ;

	/**
	 * 拦截用户请求的切面,在该切面中将做日志的处理
	 * @param joinPoint ProceedingJoinPoint
	 * @return Object
	 * @throws  Throwable Throwable
	 */
	public abstract Object around(ProceedingJoinPoint joinPoint) throws Throwable ;
	
}

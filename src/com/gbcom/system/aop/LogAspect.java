package com.gbcom.system.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.gbcom.system.domain.SysLog;
import com.hc.core.utils.DateTimeHelper;

/**
 * 日志处理切面，AbstractLogAspect的默认父类，该类将从EJBContext中取出Authentication的信息，并将其信息填充到
 * 日志中，同时对业务操作的开始时间和结束时间也会设置。
 * 
 * @author SunYanzheng
 * @date 下午5:39:58
 * @version v1.0.0
 * @see LogAspect
 */
@Aspect
@Component
public class LogAspect extends BaseLogAspect {

	// private static final String EDP =
	// "execution(* com.gbcom.acs.common.server..*.*(..))";
	/**
	 * 例如定义切入点表达式 execution(* com.sample.service.impl..*.*(..))
	 * execution()是最常用的切点函数，其语法如下所示： 整个表达式可以分为五个部分： 1、execution(): 表达式主体。
	 * 2、第一个*号：表示返回类型，*号表示所有的类型。
	 * 3、包名：表示需要拦截的包名，后面的两个句点表示当前包和当前包的所有子包，com.sample.service.impl包、子孙包下所有类的方法。
	 * 4、第二个*号：表示类名，*号表示所有的类。
	 * 5、*(..):最后这个星号表示方法名，*号表示所有的方法，后面括弧里面表示方法的参数，两个句点表示任何参数。
	 */
	private static final String EDP = "execution(* com.gbcom.*.controller.*Controller.*(..))";

	/**
	 * 注解切点，: (LogAspect.controllerAspect)
	 *
	 */
	@Pointcut("@annotation(com.gbcom.system.aop.UserLog)")
	public void controllerAspect() {
	}

	// /@Before(EDP)
	@Override
	public void doAfter(JoinPoint joinPoint) throws Throwable {
		logger.info("doAfter");

	}

	// /@Before(EDP)
	@Override
	public void doBefore(JoinPoint joinPoint) throws Throwable {
		logger.info("doBefore");
	}

	/**
	 * 拦截用户请求的切面,在该切面中将做日志的处理
	 * 
	 * @param joinPoint
	 *            joinPoint
	 * @return Object 业务方法的返回值
	 * @throws Throwable
	 *             throwable
	 */
	// @Around(EDP)
	@Around("controllerAspect()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {// NOPMD
		Object obj = null;
		UserLog userLog = null;
		SysLog sysLog = new SysLog();
		// 获取连接点的方法签名对象
		try {
			MethodSignature joinPointObject = (MethodSignature) joinPoint
					.getSignature();
			// 连接点对象的方法
			Method method = joinPointObject.getMethod();
			if (method.isAnnotationPresent(UserLog.class)) {
				userLog = method.getAnnotation(UserLog.class);
				super.buildClassMethod(joinPoint, sysLog);
				super.buildUserLog(userLog, sysLog);
				super.buildSession(
						((ServletRequestAttributes) RequestContextHolder
								.getRequestAttributes()).getRequest(), sysLog);
			}
		} catch (Exception e) {
			logger.info("building logItem error", e);
		}
		try {
			sysLog.setEnterTime(DateTimeHelper.getTimestamp());
			obj = joinPoint.proceed(joinPoint.getArgs());
			sysLog.setOutTime(DateTimeHelper.getTimestamp());
		} catch (Throwable e) {
			if (sysLog != null) {
				sysLog.setResult("failed");
			}
			throw e;
		} finally {
			try {
				if (userLog.include()) {
					super.log(sysLog);
				}
			} catch (Exception e) {
				logger.info("record log  failed!! the exception not throws", e);
			}
		}
		return obj;
	}
}

package com.gbcom.system.aop;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;

import com.gbcom.system.daoservice.SysLogService;
import com.gbcom.system.domain.SysLog;
import com.gbcom.system.domain.SysUser;
import com.gbcom.system.manager.SysUserManager;


/**
 * 日志切面的基类
 * 
 * @author syz
 * @date Nov 27, 2015 10:07:32 AM
 * @version 1.0.0
 * @since com.gbcom.system.aop.BaseLogAspect
 */
public abstract class BaseLogAspect extends AbstractAspect{
	@Autowired
	SysLogService sysLogService;
	@Autowired
	private SysUserManager sysUserManager;
	/**
	 * 构建元数据：通过反射
	 * @param joinPoint ProceedingJoinPoint
	 * @param sysLog SysLog
	 */
	public void buildClassMethod(ProceedingJoinPoint joinPoint, SysLog sysLog) {
		String targetName = joinPoint.getTarget().getClass().getSimpleName().replace("Controller", "");
		char[] chars = targetName.toCharArray();
		chars[0] = (char) (chars[0]+32);
		targetName = new String(chars);
		String methodName = joinPoint.getSignature().getName();
		if(methodName.toLowerCase().contains("grid")||methodName.toLowerCase().contains("init")){
			methodName = UserLog.USERLOG_EVENTTYPE_QUERY;
		}
		else if(methodName.toLowerCase().contains("modify")||methodName.toLowerCase().contains("save")){
			methodName=UserLog.USERLOG_EVENTTYPE_MODIFY;
		}
		else if(methodName.toLowerCase().contains("view")){
			methodName=UserLog.USERLOG_EVENTTYPE_VIEW;
		}
		else if(methodName.toLowerCase().contains("delete")){
			methodName=UserLog.USERLOG_EVENTTYPE_DELETE;
		}
		else if(methodName.toLowerCase().contains("add")){
			methodName=UserLog.USERLOG_EVENTTYPE_ADD;
		}
		else if(methodName.toLowerCase().contains("export")){
			methodName=UserLog.USERLOG_EVENTTYPE_EXPORT;
		}
		else if(methodName.toLowerCase().contains("import")){
			methodName=UserLog.USERLOG_EVENTTYPE_IMPORT;
		}else{
			methodName=UserLog.USERLOG_EVENTTYPE_UNKNOW;
		}
		sysLog.setMoudle(targetName);
		sysLog.setEventType(methodName);
	}

	/**
	 * buildUserLog
	 * @param userLog UserLog
	 * @param sysLog SysLog
	 */
	public void buildUserLog(UserLog userLog, SysLog sysLog) {
		if (userLog == null) {
			return;
		}
		if(!StringUtils.isEmpty(userLog.eventType())){
			sysLog.setEventType(userLog.eventType());
		}
		if(!StringUtils.isEmpty(userLog.moudle())){
			sysLog.setMoudle(userLog.moudle());
		}

		sysLog.setLogType(userLog.LogType());
		sysLog.setMessage(userLog.description());
		sysLog.setResult(userLog.result());//the result can be override
	}

	/**
	 * 
	 * @param request HttpServletRequest
	 * @param entity SysLog
	 */
	public void buildSession(HttpServletRequest request, SysLog entity) {
		Enumeration headers = request.getHeaderNames();
		SysUser sysUser = sysUserManager.getSysUser();
		if (sysUser != null) {
			entity.setUser(sysUser);
			entity.setIpAddress(request.getRemoteHost());
			entity.setSessionid(request.getRequestedSessionId());
			entity.setIeVersion(request.getHeader("User-Agent"));
			// 设置登入和登出时间
			entity.setPageUrl(request.getRequestURI());
		}
	}
	
	/**
	 * 调用{@link Log}接口的实现类记录单条日志
	 * 
	 * @param sysLog
	 *            单条日志
	 */
	public void log(SysLog sysLog) {
		try {
			sysLogService.save(sysLog);
		} catch (Exception e) {
			logger.error("record log error!!!",e);
		}
	}

}

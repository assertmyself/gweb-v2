package com.gbcom.system.manager;

import com.gbcom.system.daoservice.SysLogService;
import com.gbcom.system.daoservice.SysUserService;
import com.gbcom.system.domain.SysLog;
import com.gbcom.system.domain.SysUser;
import com.gbcom.system.utils.Constants;
import com.hc.core.security.count.UserLogger;
import com.hc.core.utils.DateTimeHelper;
import com.hc.core.utils.FormatUtils;
import com.hc.core.webservice.security.WSLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * User: Intellj Date: 12-6-12
 */
@Service("userLogger")
public class SysLogManager implements UserLogger, WSLogService {
	@Autowired
	private SysLogService sysLogService;

	@Autowired
	private SysUserService sysUserService;

	@Autowired
	private SysUserManager sysUserManager;

	@Autowired
	private SysCodeManager sysCodeManager;

	/**
	 * 保存日志
	 * 
	 * @param bean
	 *            SysLog
	 */
	public void save(SysLog bean) {
		// bean.setLogTime(new Timestamp(System.currentTimeMillis()));

		sysLogService.save(bean);
	}

	/**
	 * 保存日志信息
	 * 
	 * @param id
	 *            用户ID
	 * @param ipAddress
	 *            ip地址
	 * @param logType
	 *            日志类型
	 */
	public void save(Long id, String ipAddress, String logType) {
		SysUser user = sysUserService.get(id);

		this.save(user, ipAddress, logType);
	}

	/**
	 * 保存日志
	 * 
	 * @param loginUser
	 *            登录用户
	 * @param ipAddress
	 *            用户地址
	 * @param logType
	 *            日志类型
	 */
	public void save(SysUser loginUser, String ipAddress, String logType) {
		SysLog bean = new SysLog();

		bean.setIpAddress(ipAddress);
//		bean.setLogType(sysCodeManager.getCodeDetailByCode(
//				Constants.LOG_TYPE_CODE, logType));
		bean.setLogType(logType);
		bean.setUser(loginUser);

		if (logType.equals(Constants.LOG_TYPE_LOGIN)) {
			bean.setEnterTime(DateTimeHelper.getTimestamp());
		} else if (logType.equals(Constants.LOG_TYPE_LOGOUT)) {
			bean.setOutTime(DateTimeHelper.getTimestamp());
		}

		this.save(bean);
	}

	/**
	 * @param id
	 *            Long
	 * @param ipAddress
	 *            String
	 * @param logType
	 *            String
	 */
	public void log(Long id, String ipAddress, String logType) {
		save(id, ipAddress, logType);
	}

	/**
	 * 记录日志
	 * 
	 * @param username
	 *            String
	 * @param pageUrl
	 *            String
	 * @param userIp
	 *            String
	 * @param sessionId
	 *            String
	 * @param logType
	 *            String
	 * @param moudle
	 *            String
	 * @param eventType
	 *            String
	 * @param message
	 *            String
	 */
	@Override
	public void log(String username, String pageUrl, String userIp,
			String sessionId, String logType,String moudle,String eventType,String message) {
		SysUser user = sysUserManager.getSysUser(username);

		SysLog bean = new SysLog();

		bean.setPageUrl(pageUrl);
		bean.setSessionid(sessionId);
		bean.setIpAddress(userIp);
		bean.setEnterTime(DateTimeHelper.getTimestamp());

		bean.setLogType(logType);
		bean.setUser(user);
		bean.setEventType(eventType);
		bean.setMoudle(moudle);
		bean.setMessage(message);
		this.save(bean);
	}

	/**
	 * 记录日志：无类型
	 * 
	 * @param username
	 *            String
	 * @param pageUrl
	 *            String
	 */
	public void log(String username, String pageUrl) {
		this.log(username, pageUrl, null, null);
	}

	/**
	 * 记录Web Service日志
	 * 
	 * @param username
	 *            String
	 * @param pageUrl
	 *            String
	 * @param userIp
	 *            String
	 * @param sessionId
	 *            String
	 */
	public void log(String username, String pageUrl, String userIp,
			String sessionId) {
		this.log(username, pageUrl, userIp, sessionId,
				Constants.LOG_TYPE_WS,null,null,null);
	}

	/**
	 * 删除用户日志
	 * 
	 * @param userId
	 *            Long
	 */
	public void deleteLog(Long userId) {
		String hql = "from SysLog where user.id={0}";
		List<SysLog> sysLogs = sysLogService.findByQuery(FormatUtils.format(
				hql, userId));
		for (SysLog sysLog : sysLogs) {
			sysLogService.delete(sysLog);
		}
	}

	/**
	 * 用户指定时间点之后登陆日志列表
	 * 
	 * @param time
	 *            String
	 * @return 用户指定时间点之后登陆日志列表
	 */
	public List<SysLog> getTodayLoginCount(String time) {
		Long userId = sysUserManager.getSysUser().getId();
		String hql = "from SysLog where enterTime >= '" + time
				+ "' and user.id = " + userId;
		return sysLogService.findByQuery(hql);
	}


}

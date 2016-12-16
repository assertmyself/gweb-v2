package com.gbcom.system.utils;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gbcom.system.domain.SysUser;
import com.gbcom.system.manager.SysAreaManager;
import com.gbcom.system.manager.UserSessionManager;
import com.hc.core.utils.SpringUtils;

/**
 * 用户session工具类，可以获取登录用户信息，提供对session管理的支持。
 * 
 * <p>
 * @author syz
 * <p>
 * @date 2015-9-11,下午02:41:51
 * <p>
 * @version v1.0.0
 * <p>
 * @see com.gbcom.system.utils.UserSessionUtils
 */
public class UserSessionUtils {
	private static final Logger log = LoggerFactory
			.getLogger(UserSessionUtils.class);

	/** 有效 */
	public static final String USER_STATUS_VALID = "1";

	/** 无效*/
	public static final String USER_STATUS_INVALID = "0";

	/** 锁定*/
	public static final String USER_STATUS_LOCKED = "2";

	private static UserSessionUtils instance = null;

	/**
	 * 构造函数
	 */
	private UserSessionUtils() {

	}

	/**
	 * 取得UserSessionUtils的实例
	 * 
	 * @return UserSessionUtils实例
	 */
	public static UserSessionUtils getInstance() {
		if (instance == null) {
			instance = new UserSessionUtils();
		}

		return instance;
	}

	/**
	 * 获取UserSession bean
	 * 
	 * @return the userSession
	 */
	public UserSessionManager getUserSession() {
		try {
			// return (UserSessionManager)
			// getApplicationContext().getBean("userSessionManager");
			return (UserSessionManager) SpringUtils
					.getBean("userSessionManager");
		} catch (Exception e) {
			log.error("error", e);
		}

		return null;
	}

	/**
	 * 取得当前登录用户Id
	 * 
	 * @return 登录用户id
	 */
	public Long getLoginedUserId() {
		return getUserSession().getLoginedUserId();
	}

	/**
	 * 取得当前登录的SysUser对象
	 * 
	 * @return 当前登录用户
	 */
	public SysUser getLoginedUser() {
		return getUserSession().getLoginedUser();
	}

	/**
	 * 用户是否有效,
	 * 
	 * @param user 系统用户
	 * @return Boolean
	 */
	public Boolean isUserValid(SysUser user) {
		if (StringUtils.equals(user.getStatus(), this.USER_STATUS_VALID)) {
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	/**
	 * 用户是否无效
	 * 
	 * @param user SysUser
	 * @return Boolean
	 */
	public Boolean isUserInvalid(SysUser user) {
		if (StringUtils.equals(user.getStatus(), this.USER_STATUS_INVALID)) {
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	/**
	 * 用户是否锁定
	 * 
	 * @param user SysUser
	 * @return Boolean
	 */
	public Boolean isUserLocked(SysUser user) {
		if (StringUtils.equals(user.getStatus(), this.USER_STATUS_LOCKED)) {
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	/**
	 * 添加管理域到session 与具体业务有关
	 */
	public void initDevDomain() {
		initDevDomain(getLoginedUser());
	}

	/**
	 * 添加管理域到session 与具体业务有关 涉及到ccsv3业务，未独立出来， 根据role不同 读取热点表的不同字段
	 * 
	 * @param user
	 *            系统管理员
	 */
	public void initDevDomain(SysUser user) {/*
		ServerContext.getSession().setAttribute("hasLogin", true);

		String manageDev = "";
		int groupType = 0;
		for (SysUserRole userRole : user.getSysUserRoles()) {
			groupType = Integer.valueOf(userRole.getRole().getId().toString());
			break;
		}
		switch (groupType) {

		case 1:
		case 2:
		case 3:
		case 4:
			//
			manageDev = "ALL,";
			break;
		case 5:
			manageDev = getManageDev(GUserType.CUSTOMER, user.getLoginName());
			break;
		case 6:
			manageDev = getManageDev(GUserType.AGENT, user.getLoginName());
			break;
		case 7:
			manageDev = getManageDev(GUserType.CGMERATE, user.getLoginName());
			break;
		case 8:
		case 9:
			manageDev = getManageDev();
			break;
		default:
			manageDev = "ALL,";
		}
		String res = manageDev.substring(0, manageDev.length() - 1);
		ServerContext.getSession().setAttribute("domain", res);

	*/}
	/*
	private String getManageDev(GUserType type, String name) {
		String manageDev = "";
		HotspotService service = (HotspotService) SpringUtils
				.getBean("hotspotService");
		List<Hotspot> list = service.getListByUser(type, name);
		if (list == null || list.isEmpty()) {
			manageDev = ",";
		} else {
			for (Hotspot ht : list) {
				manageDev += ht.getGwID() + ",";
			}
		}
		return manageDev;
	}*/

	// 分组用户
	private String getManageDev() {
		SysAreaManager sysAreaManager = (SysAreaManager) SpringUtils
				.getBean("sysAreaManager");

		List<String> neIds = sysAreaManager.sysAreaHotsUnderSysArea();

		String keys = "";
		for (String ne : neIds) {
			keys += ne + ",";
		}
		return keys.contains(",") ? keys : ",";

	}

	/**
	 * 从session 获取管理域
	 * 
	 * @return String
	 */
	public String getDevDomain() {
		HttpSession session = ServerContext.getSession();
		if (session == null) {
			return "NoSession";// exception
		}
		String domain = (String) session.getAttribute("domain");
		return domain;
	}
}
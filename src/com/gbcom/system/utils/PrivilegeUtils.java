package com.gbcom.system.utils;

import com.gbcom.system.domain.*;
import com.hc.core.utils.Constant;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 数据授权处理工具类
 * </p>
 * <p/>
 * 
 * <p>
 * @author syz
 * <p>
 * @date 2015-9-11,下午02:36:10
 * <p>
 * @version v1.0.0
 * <p>
 * @see com.gbcom.system.utils.PrivilegeUtils
 */
public class PrivilegeUtils {
	/**
	 * 取得项目数据授权过滤条件
	 * 
	 * @param prefix String
	 * @return 过滤条件
	 */
	public static String getProjectCondition(String prefix) {
		boolean isTag0 = false;
		boolean isTag1 = false;

		SysUser loginUser = UserSessionUtils.getInstance().getLoginedUser();

		Iterator<SysUserRole> roles = null; // loginUser.getSysUserRoles().iterator();

		// 根据角色名称进行判断
		while (roles.hasNext()) {
			SysRole role = roles.next().getRole();
			String code = StringUtils.defaultIfEmpty(role.getCode(), "");

			if (code.equals(Constant.ROLE_PROJECT_TAG_0)) {
				isTag0 = true;
			} else if (code.equals(Constant.ROLE_PROJECT_TAG_1)) {
				isTag1 = true;
			}
		}

		if (isTag0 && isTag1) {
			return " ";
		} else if (isTag0) {
			return " and " + prefix + ".isTag = 0 ";
		} else if (isTag1) {
			return " and " + prefix + ".isTag = 1 ";
		} else {
			return " and 1<>1 ";
		}
	}

	/**
	 * 取得项目数据授权过滤条件
	 * 
	 * @return 过滤条件
	 */
	public static String getProjectCondition() {
		return getProjectCondition("");
	}

	/**
	 * 将权限List转成Map
	 * 
	 * @param list
	 *            权限List
	 * @return 权限Map
	 */
	public static Map<Long, SysPrivilege> listToMap(List<SysPrivilege> list) {
		Map<Long, SysPrivilege> ret = new HashMap<Long, SysPrivilege>();

		if (list != null && list.size() > 0) {
			for (SysPrivilege bean : list) {
				ret.put(bean.getId(), bean);
			}
		}

		return ret;
	}

	/**
	 * 将权限List转成Map
	 * 
	 * @param list
	 *            权限List
	 * @return 权限Map
	 */
	public static Map<Long, SysUserPrivilege> userPrivilegeListToMap(
			List<SysUserPrivilege> list) {
		Map<Long, SysUserPrivilege> ret = new HashMap<Long, SysUserPrivilege>();

		if (list != null && list.size() > 0) {
			for (SysUserPrivilege bean : list) {
				ret.put(bean.getPrivilege().getId(), bean);
			}
		}

		return ret;
	}
}

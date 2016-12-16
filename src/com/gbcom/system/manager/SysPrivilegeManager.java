package com.gbcom.system.manager;

import com.gbcom.system.daoservice.SysPrivilegeService;
import com.gbcom.system.domain.SysPrivilege;
import com.hc.core.security.privilege.Privilege;
import com.hc.core.security.util.SpringSecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Project:bcscmis
 * <p/>
 * <p>
 * 权限管理逻辑处理类
 * </p>
 * <p/>
 * Create On 2010-1-16 下午12:49:24
 * 
 * @author <a href="mailto:hzxia2002@gmail.com">XiaHongzhong</a>
 * @version 1.0
 */
@Service
public class SysPrivilegeManager {
	@Autowired
	private SysPrivilegeService sysPrivilegeService;

	/**
	 * 取得所有的权限定义信息
	 * 
	 * @return 权限列表
	 */
	public List<SysPrivilege> getAllPrivileges() {
		String hql = "from SysPrivilege order by treeId asc";
		return sysPrivilegeService.find(hql);
	}

	/**
	 * 取得所有的按钮权限
	 * 
	 * @param gridId
	 *            gridId
	 * @return 权限Map
	 */
	public Map<String, Privilege> getButtonPrivileges(String gridId) {
		List<Privilege> list = SpringSecurityUtils.getButtonPrivileges();

		Map<String, Privilege> ret = new HashMap<String, Privilege>();

		if (list != null && list.size() > 0) {
			for (Privilege p : list) {
				if (p.getCode().toLowerCase().indexOf(
						gridId.toLowerCase() + ".") != -1) {
					ret.put(p.getCode(), p);
				}
			}
		}

		return ret;
	}

	/**
	 * 取得用户按钮权限
	 * 
	 * @param gridId
	 *            gridId
	 * @return 用户按钮权限列表
	 */
	public Map<String, Privilege> getUserButtonPrivileges(String gridId) {
		List<Privilege> list = SpringSecurityUtils.getUserButtonPrivileges();

		Map<String, Privilege> ret = new HashMap<String, Privilege>();

		if (list != null && list.size() > 0) {
			for (Privilege p : list) {
				if (p.getCode().toLowerCase().indexOf(
						gridId.toLowerCase() + ".") != -1) {
					ret.put(p.getCode(), p);
				}
			}
		}

		return ret;
	}

	/**
	 * 取得菜单权限
	 * 
	 * @return 菜单权限Map
	 */
	public Map<String, Privilege> getMenuPrivilegesMap() {
		Map<String, Privilege> ret = SpringSecurityUtils.getMenuPrivilegesMap();

		return ret;
	}

	/**
	 * 取得菜单权限
	 * 
	 * @return 菜单权限List
	 */
	public List<Privilege> getMenuPrivileges() {
		return SpringSecurityUtils.getMenuPrivileges();
	}

	/**
	 * 取得用户菜单权限
	 * 
	 * @return 菜单权限Map
	 */
	public Map<String, Privilege> getUserMenuPrivilegesMap() {
		return SpringSecurityUtils.getUserMenuPrivilegesMap();
	}

	/**
	 * 通过权限代码取得权限
	 * 
	 * @param code
	 *            权限代码
	 * @return SysPrivilege
	 */
	public SysPrivilege getPrivilegesByCode(String code) {
		String hql = "from SysPrivilege t where t.code = '" + code + "'";

		List<SysPrivilege> list = sysPrivilegeService.find(hql);

		if (list != null && list.size() > 0) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * 判断用户是否有该权限
	 * 
	 * @param privilegeCode
	 *            String
	 * @return boolean
	 */
	public boolean hasPrivilegeByCode(String privilegeCode) {
		Collection<Privilege> privileges = SpringSecurityUtils
				.getUserAllPrivileges();
		for (Privilege privilege : privileges) {
			if (privilege.getCode().equals(privilegeCode)) {
				return true;
			}
		}
		return false;
	}
}
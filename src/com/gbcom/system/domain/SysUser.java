//off checkstyle
package com.gbcom.system.domain;

import com.gbcom.system.domain.base.BaseSysUser;

import java.util.Set;


public class SysUser extends BaseSysUser {
    private static final long serialVersionUID = 1L;

    /*[CONSTRUCTOR MARKER BEGIN]*/
    public SysUser() {
        super();
    }

    /**
     * Constructor for primary key
     */
    public SysUser(java.lang.Long id) {
        super(id);
    }

    /**
     * Constructor for required fields
     */
    public SysUser(
            java.lang.Long id,
            java.lang.String loginName) {

        super(
                id,
                loginName);
    }

/*[CONSTRUCTOR MARKER END]*/

    /**
     * 获取用户拥有的角色
     *
     * @return
     */
    public String getRoleNames() {
        String roleNames = "";
        Set<SysUserRole> userRoles = getSysUserRoles();
        for (SysUserRole userRole : userRoles) {
            roleNames += "," + userRole.getRole().getRoleName();
        }
        if (!"".equals(roleNames)) {
            roleNames = roleNames.substring(1);
        }
        return roleNames;
    }
}
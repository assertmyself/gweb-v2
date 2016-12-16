//off checkstyle
package com.gbcom.system.domain;

import com.gbcom.system.domain.base.BaseSysArea;

/**
 * 地区信息。
 * 
 * <p>
 * @author syz
 * <p>
 * @date 2015-5-14,下午06:11:37
 * <p>
 * @version v1.0.0
 * <p>
 * @see com.gbcom.system.domain.SysArea
 */
public class SysArea extends BaseSysArea {
    private static final long serialVersionUID = 1L;

    /*[CONSTRUCTOR MARKER BEGIN]*/
    public SysArea() {
        super();
    }

    /**
     * Constructor for primary key
     */
    public SysArea(java.lang.Long id) {
        super(id);
    }



   /* public String getRoleNames() {
        String roleNames = "";
        Set<SysUserRole> userRoles = getSysUserRoles();
        for (SysUserRole userRole : userRoles) {
            roleNames += "," + userRole.getRole().getRoleName();
        }
        if (!"".equals(roleNames)) {
            roleNames = roleNames.substring(1);
        }
        return roleNames;
    }*/
}
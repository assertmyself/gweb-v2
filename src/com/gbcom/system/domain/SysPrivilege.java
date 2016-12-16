//off checkstyle
package com.gbcom.system.domain;

import com.gbcom.system.domain.base.BaseSysPrivilege;
import com.hc.core.security.privilege.Privilege;


public class SysPrivilege extends BaseSysPrivilege implements Privilege {
    private static final long serialVersionUID = 1L;

    /*[CONSTRUCTOR MARKER BEGIN]*/
    public SysPrivilege() {
        super();
    }

    /**
     * Constructor for primary key
     */
    public SysPrivilege(java.lang.Long id) {
        super(id);
    }

    /**
     * Constructor for required fields
     */
    public SysPrivilege(
            java.lang.Long id,
            java.lang.String code) {

        super(
                id,
                code);
    }

/*[CONSTRUCTOR MARKER END]*/

    private String privilegeType;  //仅提供给权限验证使用

    /**
     * 取得权限类型
     *
     * @return
     */
    public String getPrivilegeType() {
        try {
            if (this.privilegeType == null || this.privilegeType.equals("")) {
                setPrivilegeType();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.privilegeType;
    }

    /**
     * 设置权限类型
     */
    public void setPrivilegeType() {
        if (this.getType() != null) {
            this.privilegeType = this.getType().getCode().toUpperCase();
        }
    }
}
//off checkstyle
package com.gbcom.system.domain;

import com.gbcom.system.domain.base.BaseSysUserPrivilege;


public class SysUserPrivilege extends BaseSysUserPrivilege {
    private static final long serialVersionUID = 1L;

    /*[CONSTRUCTOR MARKER BEGIN]*/
    public SysUserPrivilege() {
        super();
    }

    /**
     * Constructor for primary key
     */
    public SysUserPrivilege(java.lang.Long id) {
        super(id);
    }

    /**
     * Constructor for required fields
     */
    public SysUserPrivilege(
            java.lang.Long id,
            com.gbcom.system.domain.SysPrivilege privilege,
            SysUser user) {

        super(
                id,
                user,
                privilege);
    }

/*[CONSTRUCTOR MARKER END]*/


}
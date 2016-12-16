//off checkstyle
package com.gbcom.system.domain;

import com.gbcom.system.domain.base.BaseSysPersonDept;


public class SysPersonDept extends BaseSysPersonDept {
    private static final long serialVersionUID = 1L;

    /*[CONSTRUCTOR MARKER BEGIN]*/
    public SysPersonDept() {
        super();
    }

    /**
     * Constructor for primary key
     */
    public SysPersonDept(java.lang.Long id) {
        super(id);
    }

    /**
     * Constructor for required fields
     */
    public SysPersonDept(
            java.lang.Long id,
            com.gbcom.system.domain.SysDept dept,
            com.gbcom.system.domain.SysPerson person) {

        super(
                id,
                dept,
                person);
    }

/*[CONSTRUCTOR MARKER END]*/


}
//off checkstyle
package com.gbcom.system.domain;

import com.gbcom.system.domain.base.BaseSysPerson;

import java.util.Set;


public class SysPerson extends BaseSysPerson {
    private static final long serialVersionUID = 1L;

    /*[CONSTRUCTOR MARKER BEGIN]*/
    public SysPerson() {
        super();
    }

    /**
     * Constructor for primary key
     */
    public SysPerson(java.lang.Long id) {
        super(id);
    }

/*[CONSTRUCTOR MARKER END]*/

    /**
     * 获取单位部门名称(支持多个)
     *
     * @return 。
     */
    public SysDept getCompany() {
        SysDept dept = getDept();
        if (dept != null) {
            if (dept.getIsCompany()) {
                return dept;
            } else {
                return getParentCompany(dept);
            }
        }
        return null;
    }

    private SysDept getParentCompany(SysDept dept) {
        if (dept.getParent() != null) {
            return getParentCompany(dept.getParent());
        } else {
            return dept;
        }
    }

    /**
     * 所属部门
     *
     * @return 。
     */
    public SysDept getDept() {
        Set<SysPersonDept> sysPersonDepts = getSysPersonDepts();
        if (sysPersonDepts.size() > 0) {
            SysPersonDept next = sysPersonDepts.iterator().next();
            return next.getDept();
        }
        return null;
    }

    /**
     * 所在部门
     *
     * @return 。
     */
    public SysPersonDept getPersonDept() {
        Set<SysPersonDept> sysPersonDepts = getSysPersonDepts();
        if (sysPersonDepts.size() > 0) {
            return sysPersonDepts.iterator().next();
        }
        return null;
    }
}
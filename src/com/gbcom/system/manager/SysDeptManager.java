package com.gbcom.system.manager;

import com.gbcom.system.domain.SysDept;
import org.springframework.stereotype.Service;

/**
 * 单位、部门
 */
@Service
public class SysDeptManager {
    /**
     * 获取单位部门名称(支持多个)
     *
     * @param dept .
     * @return company
     */
    public SysDept getSysUnitBySysDept(SysDept dept) {
        SysDept company = null;
        if (dept != null) {
            company = getParentCompany(dept);
        }
        return company;
    }

    private SysDept getParentCompany(SysDept dept) {
        if (dept.getParent() != null) {
            return getParentCompany(dept.getParent());
        } else {
            return dept;
        }
    }
}

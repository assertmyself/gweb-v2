package com.gbcom.system.manager;

import com.hc.core.security.privilege.PrivilegeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <p>
 * </p>
 * User: <a href="mailto:hzxia2002@gmail.com">Jackie</a> Date: 2009-11-21 Time:
 * 20:38:17 Version: 1.0
 */
@SuppressWarnings("unchecked")
public class PrivilegeServiceImpl implements PrivilegeService {
    @Autowired
    private SysPrivilegeManager sysPrivilegeManager;

    /**
     * 返回所有的权限
     *
     * @return 权限列表
     */
    public List getAllPrivileges() {
        return sysPrivilegeManager.getAllPrivileges();
    }
}

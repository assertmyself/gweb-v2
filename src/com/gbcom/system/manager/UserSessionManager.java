package com.gbcom.system.manager;

import com.gbcom.system.daoservice.SysUserService;
import com.gbcom.system.domain.SysPerson;
import com.gbcom.system.domain.SysUser;
import com.hc.core.security.user.BaseUser;
import com.hc.core.security.util.SpringSecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Project:bcscmis
 * <p/>
 * <p>
 * </p>
 * <p/>
 * Create On 2009-12-25 下午05:49:23
 *
 * @author <a href="mailto:hzxia2002@gmail.com">XiaHongzhong</a>
 * @version 1.0
 */
@Service
public class UserSessionManager {
    @Autowired
    private SysUserService sysUserService;

    /**
     * 取得当前登录用户Id
     *
     * @return 登录用户id
     */
    public Long getLoginedUserId() {
        BaseUser loginUser = SpringSecurityUtils.getCurrentUser();

        if (loginUser != null) {
            return loginUser.getId();
        }

        return null;
    }

    /**
     * 取得当前登录的SysUser对象
     *
     * @return 当前登录用户
     */
    public SysUser getLoginedUser() {
        Long userId = getLoginedUserId();

        if (userId != null) {
            return sysUserService.get(userId);
        }

        return null;
    }

    /**
     * 获取当前登录对象的人员
     *
     * @return 人员
     */
    public SysPerson getSysPerson() {
        SysUser sysUser = getLoginedUser();
        if (sysUser != null) {
            return sysUser.getPerson();
        }
        return null;
    }
}

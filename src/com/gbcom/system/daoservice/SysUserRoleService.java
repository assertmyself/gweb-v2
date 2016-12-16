package com.gbcom.system.daoservice;

import com.gbcom.system.domain.SysUserRole;
import com.hc.core.orm.hibernate.EntityService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * author:system
 * 注：此文件内容不需要修改
 */
@Service
public class SysUserRoleService extends EntityService<SysUserRole, Long> {
	/**
	 * SysUserRole初始化方法
	 * @param sessionFactory SessionFactory
	 */
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        initDao(sessionFactory, SysUserRole.class);
    }
}
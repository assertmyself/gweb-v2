package com.gbcom.system.daoservice;

import com.gbcom.system.domain.SysOperationTableLog;
import com.hc.core.orm.hibernate.EntityService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * author:system
 * 注：此文件内容不需要修改
 */
@Service
public class SysOperationTableLogService extends EntityService<SysOperationTableLog, Long> {
	/**
	 * SysOperationTableLog初始化方法
	 * @param sessionFactory SessionFactory
	 */
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        initDao(sessionFactory, SysOperationTableLog.class);
    }
}
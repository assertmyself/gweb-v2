package com.gbcom.system.daoservice;

import com.gbcom.system.domain.SysMessage;
import com.hc.core.orm.hibernate.EntityService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * SysMessageService
 * @author huanghaiyun
 * @createTime 2016-12-9下午01:59:03
 */
@Service
public class SysMessageService extends EntityService<SysMessage, Long> {
	/**
	 * SysDept初始化方法
	 * @param sessionFactory SessionFactory
	 */
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        initDao(sessionFactory, SysMessage.class);
    }
}
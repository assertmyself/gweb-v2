package com.gbcom.system.daoservice;

import com.gbcom.system.domain.TimePlan;
import com.hc.core.orm.hibernate.EntityService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-7-10
 * Time: 下午3:33
 * To change this template use File | Settings | File Templates.
 */
@Service
public class TimePlanService extends EntityService<TimePlan, Long> {
	/**
	 * TimePlan初始化方法
	 * @param sessionFactory SessionFactory
	 */
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        initDao(sessionFactory, TimePlan.class);
    }
}

package com.gbcom.system.daoservice;

import com.gbcom.system.domain.AppVersion;
import com.hc.core.orm.hibernate.EntityService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: fengjing
 * Date: 14-7-9
 * Time: 上午10:19
 * To change this template use File | Settings | File Templates.
 */
@Service
public class AppVersionService extends EntityService<AppVersion, Long> {
	/**
	 * AppVersion初始化工作
	 * @param sessionFactory SessionFactory
	 */
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        initDao(sessionFactory, AppVersion.class);
    }
}

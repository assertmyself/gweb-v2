package com.gbcom.demo.daoservice;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gbcom.demo.domain.Hello;
import com.hc.core.orm.hibernate.EntityService;
/**
 * service
 * 
 * <p>
 * @author syz
 * <p>
 * @date 2015-12-28,下午03:36:42
 * <p>
 * @version v1.0.0
 * <p>
 * @see com.gbcom.demo.daoservice.HelloService
 */
@Service
public class HelloService extends EntityService<Hello, Long> {
	/**
	 * : (HelloService.setSessionFactory)
	 * @param sessionFactory SessionFactory
	 */
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        initDao(sessionFactory, Hello.class);
    }
}

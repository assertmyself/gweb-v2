package com.gbcom.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
/**
 * 
 * spring bean factory -- autoaware
 * 
 * @author SunYanzheng
 * @date 2014-10-9,上午10:58:24
 * @version v1.0.0
 * @see com.gbcom.spring.ServiceLocatorBAK
 */
public class ServiceLocatorBAK implements BeanFactoryAware {

	private static BeanFactory beanFactory = null;
	
	/**
	 * @param beanFactory beanFactory 
	 * @throws BeansException BeansException
	 */
	@SuppressWarnings("static-access")
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;
	}
	/**
	 * 
	 * @return beanFactory
	 */
	public BeanFactory getBeanFactory() {
		return beanFactory;
	}	
	/**
	 * 
	 * @param beanName beanName
	 * @return Object
	 */
	public static Object getBean(String beanName) {
		return beanFactory.getBean(beanName);
	}
	
	/**
	 * 
	 * @param beanName beanName
	 * @param cla cla
	 * @return Object
	 */
	public static Object getBean(String beanName, Class<?> cla) {
	   return beanFactory.getBean(beanName, cla);
	}
}

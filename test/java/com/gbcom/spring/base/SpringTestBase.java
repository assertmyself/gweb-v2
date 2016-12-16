package com.gbcom.spring.base;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 测试基类 <li>
 * 利用spring测试框架，编写junit测试基类，spring内部维护了一static类型的JVM级别的bean缓存，这样无需手动用
 * <code>ctx.getBean()</code>的方式获取bean实例，而是spring自动注入； <li>
 * 使用方法：<li>  <b>（未使用）</b>
 *    继承该类 不再需要额外的注解
 * 
 * @author SunYanzheng
 * @date 2015-2-27,上午11:05:53
 * @version v1.0.0
 * @see com.gbcom.spring.base.SpringTestBase
 */
@RunWith(SpringJUnit4ClassRunner.class)//选择测试框架
@ContextConfiguration(inheritLocations = true, locations = "classpath:/context/applicationContext.xml")
public class SpringTestBase  extends
		AbstractTransactionalJUnit4SpringContextTests {
	/**
	 * 构造参数返回和数据传送,并设置标识位表示此bean来源于测试方法，不需要切面拦截
	 */
}
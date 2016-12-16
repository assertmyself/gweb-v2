package com.gbcom.common.drools;

import java.util.List;

/**
 * 
 * @param <T>
 * <p>
 * @author syz
 * <p>
 * @date 2015-7-6,上午11:32:55
 * <p>
 * @version v1.0.0
 * <p>
 * @see com.gbcom.common.drools.DroolsRuleEngine
 */
public interface DroolsRuleEngine {
	
	/**
	 * 初始化规则引擎
	 */
	//public void initEngine();
	
	/**
	 * 刷新规则引擎中的规则
	 */
	public void refreshEnginRule();
	
	/**
	 * 执行规则引擎
	 * @param domain Fact
	 */
	public void executeRuleEngine(final List<Object> domain);
}
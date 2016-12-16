package com.gbcom.common.drools;

/**
 *  drools 引擎 工厂类
 * 
 * <p>
 * @author syz
 * <p>
 * @date 2015-7-6,下午01:14:27
 * <p>
 * @version v1.0.0
 * <p>
 * @see com.gbcom.common.drools.DroolsRuleEngineFactory
 */


public class DroolsEngineFactory  {
	/**
	 * ENGINE_TYPE_DB
	 */
	public static final int ENGINE_TYPE_DB = 1;
	/**
	 * ENGINE_TYPE_FILE
	 */
	public static final int ENGINE_TYPE_FILE = 2;






	/**
	 * dbEngine
	 * @return DroolsRuleEngine
	 */
	public static DroolsRuleEngine dbEngine(){
		return DroolsRuleEngineDBImpl.getInstance();
	}
	
	/**
	 * fileEngine
	 * @return DroolsRuleEngine
	 */
	public static DroolsRuleEngine fileEngine(){
		return DroolsRuleEngineImpl.getInstance();
	}

}

package com.gbcom.common.drools;

import org.drools.RuleBase;
import org.drools.RuleBaseFactory;

/**
 * BASE role  FACTORY.
 * 
 * 
 * <p>
 * @author syz
 * <p>
 * @date 2015-7-7,下午01:55:59
 * <p>
 * @version v1.0.0
 * <p>
 * @see com.gbcom.common.drools.RuleBaseFacatory
 */
public class RuleBaseFacatory {
	private static RuleBase ruleBase;
	/**
	 * getRuleBase
	 * @return RuleBase
	 */
	public static RuleBase getRuleBase(){
		return null != ruleBase ? ruleBase : RuleBaseFactory.newRuleBase();
	}
}
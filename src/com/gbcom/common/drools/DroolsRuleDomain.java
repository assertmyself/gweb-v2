//off checkstyle
package com.gbcom.common.drools;


/**
 * 保存数据库 匹配规则。
 * 
 * <p>
 * @author syz
 * <p>
 * @date 2015-7-2,下午05:38:36
 * <p>
 * @version v1.0.0
 * <p>
 * @see com.gbcom.drools.demo.DroolsRuleDomain
 */
public class DroolsRuleDomain {
	/** 数据库记录ID */
	private long id;
	/** 规则名称 */
	private String ruleName;
	/** 规则正文  */
	private String ruleContext;
	/** 规则版本 */
	private int version;
	/** 规则脚本状态 */
	private int status;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public String getRuleContext() {
		return ruleContext;
	}

	public void setRuleContext(String ruleContext) {
		this.ruleContext = ruleContext;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
}

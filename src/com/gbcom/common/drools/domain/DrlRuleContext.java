//off checkstyle
package com.gbcom.common.drools.domain;

/**
 * 具体条件 内容
 * 
 * <p>
 * @author syz
 * <p>
 * @date 2015-7-6,下午03:03:02
 * <p>
 * @version v1.0.0
 * <p>
 * @see com.gbcom.DrlRuleContext.domain.RuleContext
 */
public class DrlRuleContext {
	//type
	public static final int RULE_CONTEXT_TYPE_EQ = 0; // = 
	public static final int RULE_CONTEXT_TYPE_GE = 1; // >=
	public static final int RULE_CONTEXT_TYPE_LE = 2; //<=
	public static final int RULE_CONTEXT_TYPE_NOT = 3; //!=
	
	private Long id;
	private String item;// 版本  、mac
	private int type = RULE_CONTEXT_TYPE_EQ;
	private String context;
	
	private DrlRule drl;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public void setDrl(DrlRule drl) {
		this.drl = drl;
	}
	public DrlRule getDrl() {
		return drl;
	}
	
	
}

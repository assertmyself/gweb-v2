//off checkstyle
package com.gbcom.common.drools.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * 
 * drl 匹配规则
 * <p>
 * @author syz
 * <p>
 * @date 2015-7-6,下午03:08:02
 * <p>
 * @version v1.0.0
 * <p>
 * @see com.gbcom.ccsv3.domain.DrlRule
 */
public class DrlRule implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	//策略名：用户使用 主键
	private String name;
	//最好是枚举  0：全局 1： 热点 2：ap： 3：cpe   保留字段
	private int scope;

	
	private String clz;
	private Set<DrlRuleContext> rules;
	/** 规则正文  */
	private String ruleContext;
	
	/**设备类型，OEM之后设备类型*/
	private String sysmodel;

	private String version;
	//升级完成是否重启   //保留字段  默认为false，即升级不需要重启。
	private boolean needReboot = false;
	/**
	 * 计划所处状态 1,2,3,4
	 */
	private int state = 1;
	
	private Date beginTime;
	private Date endTime;
	
	/**
	 * 升级时间点
	 * @return
	 */
	private int trigerTime;
		
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public boolean isNeedReboot() {
		return needReboot;
	}
	public void setNeedReboot(boolean needReboot) {
		this.needReboot = needReboot;
	}
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public int getScope() {
		return scope;
	}
	public void setScope(int scope) {
		this.scope = scope;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "DrlRule [name=" + name + ", scope=" + scope + ", ruleContext="
				+ ruleContext + ", version=" + version+ "]";
	}
	public String getSysmodel() {
		return sysmodel;
	}
	public void setSysmodel(String sysmodel) {
		this.sysmodel = sysmodel;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public Long getId() {
		return id;
	}
	public String getRuleContext() {
		return ruleContext;
	}
	public void setRuleContext(String ruleContext) {
		this.ruleContext = ruleContext;
	}
	public void setClz(String clz) {
		this.clz = clz;
	}
	public String getClz() {
		return clz;
	}
	public void setRules(Set<DrlRuleContext> rules) {
		this.rules = rules;
	}
	public Set<DrlRuleContext> getRules() {
		return rules;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public int getTrigerTime() {
		return trigerTime;
	}
	public void setTrigerTime(int trigerTime) {
		this.trigerTime = trigerTime;
	}

}

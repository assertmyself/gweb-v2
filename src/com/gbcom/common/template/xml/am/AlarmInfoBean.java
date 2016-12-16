/**
 * @(#)AlarmInfoBean.java       07/11
 *
 * Copyright (c) 2007 GBCom Communication Technolofy Co.Ltd
 * All right reserved.
 */
package com.gbcom.common.template.xml.am;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

/**
 * 告警信息实体类
 * 
 * @author xuyin
 * @version 1.0
 */
public class AlarmInfoBean implements Serializable {

	/** Comment for <code>serialVersionUID</code> */
	private static final long serialVersionUID = -2086651523044071291L;

	/**
	 * 告警码
	 */
	private int alarmCode;

	/**
	 * 告警名称
	 */
	private String alarmName;

	/**
	 * 告警类型
	 */
	private String alarmType;

	/**
	 * 告警节点
	 */
	private String alarmOid;

	/**
	 * 是否可以清除
	 */
	private boolean canClear;

	/**
	 * 告警清除节点
	 */
	private String clearOid;

	/**
	 * 告警对象
	 */
	private String alarmObject;

	/**
	 * 告警细节描述
	 */
	private String alarmDetailDesc;

	/**
	 * 告警级别
	 */
	private int alarmLevel;

	/**
	 * 告警附加信息
	 */
	private Map<String, String> alarmExtInfo = new HashMap<String, String>();

	/**
	 * 告警产生条件
	 */
	private String alarmRaiseCondition;

	/**
	 * 告警清除条件
	 */
	private String alarmClearCondition;

	/**
	 * 告警影响范围
	 */
	private String alarmAffectRange;

	/**
	 * 告警建议恢复
	 */
	private String alarmProposedRepairAction;

	/**
	 * 是否重启告警
	 */
	private int alarmRebootClear;

	/**
	 * 告警原因信息
	 */
	private List<AlarmReasonBean> reasons = new ArrayList<AlarmReasonBean>();

	/**
	 * 告警原因信息
	 */
	private Map<Integer, AlarmReasonBean> alarmReasons = new HashMap<Integer, AlarmReasonBean>();

	/**
	 * 告警是否邮件通知
	 * 如果需要编辑，不推荐使用 boolean类型。
	 */
	private boolean mail;


	/**
	 * 处理方法
	 */
	private String process;

	/**
	 * @return the alarmCode
	 */
	public int getAlarmCode() {
		return alarmCode;
	}

	/**
	 * @param alarmCode
	 *            the alarmCode to set
	 */
	public void setAlarmCode(int alarmCode) {
		this.alarmCode = alarmCode;
	}

	/**
	 * @return the alarmName
	 */
	public String getAlarmName() {
		return alarmName;
	}

	/**
	 * @param alarmName
	 *            the alarmName to set
	 */
	public void setAlarmName(String alarmName) {
		this.alarmName = alarmName;
	}

	/**
	 * @return the alarmType
	 */
	public String getAlarmType() {
		return alarmType;
	}

	/**
	 * @param alarmType
	 *            the alarmType to set
	 */
	public void setAlarmType(String alarmType) {
		this.alarmType = alarmType;
	}

	/**
	 * @return the alarmOid
	 */
	public String getAlarmOid() {
		return alarmOid;
	}

	/**
	 * @param alarmOid
	 *            the alarmOid to set
	 */
	public void setAlarmOid(String alarmOid) {
		this.alarmOid = alarmOid;
	}

	/**
	 * @return the canClear
	 */
	public boolean isCanClear() {
		return canClear;
	}

	/**
	 * @param canClear
	 *            the canClear to set
	 */
	public void setCanClear(boolean canClear) {
		this.canClear = canClear;
	}

	/**
	 * @return the clearOid
	 */
	public String getClearOid() {
		return clearOid;
	}

	/**
	 * @param clearOid
	 *            the clearOid to set
	 */
	public void setClearOid(String clearOid) {
		this.clearOid = clearOid;
	}

	/**
	 * @return the alarmObject
	 */
	public String getAlarmObject() {
		return alarmObject;
	}

	/**
	 * @param alarmObject
	 *            the alarmObject to set
	 */
	public void setAlarmObject(String alarmObject) {
		this.alarmObject = alarmObject;
	}

	/**
	 * @return the alarmDetailDesc
	 */
	public String getAlarmDetailDesc() {
		return alarmDetailDesc;
	}

	/**
	 * @param alarmDetailDesc
	 *            the alarmDetailDesc to set
	 */
	public void setAlarmDetailDesc(String alarmDetailDesc) {
		this.alarmDetailDesc = alarmDetailDesc;
	}

	/**
	 * @return the alarmLevel
	 */
	public int getAlarmLevel() {
		return alarmLevel;
	}

	/**
	 * @param alarmLevel
	 *            the alarmLevel to set
	 */
	public void setAlarmLevel(int alarmLevel) {
		this.alarmLevel = alarmLevel;
	}

	/**
	 * @return the alarmExtInfo
	 */
	public Map<String, String> getAlarmExtInfo() {
		return alarmExtInfo;
	}

	/**
	 * @param alarmExtInfo
	 *            the alarmExtInfo to set
	 */
	public void setAlarmExtInfo(Map<String, String> alarmExtInfo) {
		this.alarmExtInfo = alarmExtInfo;
	}

	/**
	 * @return the alarmRaiseCondition
	 */
	public String getAlarmRaiseCondition() {
		return alarmRaiseCondition;
	}

	/**
	 * @param alarmRaiseCondition
	 *            the alarmRaiseCondition to set
	 */
	public void setAlarmRaiseCondition(String alarmRaiseCondition) {
		this.alarmRaiseCondition = alarmRaiseCondition;
	}

	/**
	 * @return the alarmClearCondition
	 */
	public String getAlarmClearCondition() {
		return alarmClearCondition;
	}

	/**
	 * @param alarmClearCondition
	 *            the alarmClearCondition to set
	 */
	public void setAlarmClearCondition(String alarmClearCondition) {
		this.alarmClearCondition = alarmClearCondition;
	}

	/**
	 * @return the alarmAffectRange
	 */
	public String getAlarmAffectRange() {
		return alarmAffectRange;
	}

	/**
	 * @param alarmAffectRange
	 *            the alarmAffectRange to set
	 */
	public void setAlarmAffectRange(String alarmAffectRange) {
		this.alarmAffectRange = alarmAffectRange;
	}

	/**
	 * @return the alarmProposedRepairAction
	 */
	public String getAlarmProposedRepairAction() {
		return alarmProposedRepairAction;
	}

	/**
	 * @param alarmProposedRepairAction
	 *            the alarmProposedRepairAction to set
	 */
	public void setAlarmProposedRepairAction(String alarmProposedRepairAction) {
		this.alarmProposedRepairAction = alarmProposedRepairAction;
	}

	/**
	 * @return the alarmRebootClear
	 */
	public int getAlarmRebootClear() {
		return alarmRebootClear;
	}

	/**
	 * @param alarmRebootClear
	 *            the alarmRebootClear to set
	 */
	public void setAlarmRebootClear(int alarmRebootClear) {
		this.alarmRebootClear = alarmRebootClear;
	}

	/**
	 * @return the reasons
	 */
	@XmlElementWrapper(name = "reasons")
	@XmlElement(name = "reason")
	public List<AlarmReasonBean> getReasons() {
		return reasons;
	}

	/**
	 * @param reasons
	 *            the reasons to set
	 */
	public void setReasons(List<AlarmReasonBean> reasons) {
		this.reasons = reasons;

	}

	/**
	 * @return the alarmReasons
	 */
	public Map<Integer, AlarmReasonBean> getAlarmReasons() {
		return alarmReasons;
	}

	/**
	 * @param alarmReasons
	 *            the alarmReasons to set
	 */
	public void setAlarmReasons(Map<Integer, AlarmReasonBean> alarmReasons) {
		this.alarmReasons = alarmReasons;
	}

	/**
	 * 初始化告警原因Map
	 */
	public void initAlarmReasonMap() {
		for (AlarmReasonBean alarmReasonBean : reasons) {
			alarmReasons.put(alarmReasonBean.getCode(), alarmReasonBean);
		}
	}

	/**
	 * @return the process
	 */
	public String getProcess() {
		return process;
	}

	/**
	 * @param process
	 *            the process to set
	 */
	public void setProcess(String process) {
		this.process = process;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AlarmInfoBean [alarmAffectRange=" + alarmAffectRange
				+ ", alarmClearCondition=" + alarmClearCondition
				+ ", alarmCode=" + alarmCode + ", alarmDetailDesc="
				+ alarmDetailDesc + ", alarmExtInfo=" + alarmExtInfo
				+ ", alarmLevel=" + alarmLevel + ", alarmName=" + alarmName
				+ ", alarmObject=" + alarmObject + ", alarmOid=" + alarmOid
				+ ", alarmProposedRepairAction=" + alarmProposedRepairAction
				+ ", alarmRaiseCondition=" + alarmRaiseCondition
				+ ", alarmReasons=" + alarmReasons + ", alarmRebootClear="
				+ alarmRebootClear + ", alarmType=" + alarmType + ", canClear="
				+ canClear + ", clearOid=" + clearOid 
				+ ", mail=" + mail + ", process=" + process + ", reasons="
				+ reasons + "]";
	}

	/**
	 * : (AlarmInfoBean.setMail)
	 * @param mail boolean
	 */
	public void setMail(boolean mail) {
		this.mail = mail;
	}

	/**
	 * : (AlarmInfoBean.isMail)
	 * @return boolean
	 */
	public boolean isMail() {
		return mail;
	}
	
}

package com.gbcom.common.template.xml.am;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * 告警知识库bean
 *
 */
public class AlarmTplBean implements Serializable{ /**
	 *  Comment for <code>serialVersionUID</code> 
	 */
	private static final long serialVersionUID = 1L;

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
	private String alarmLevel;

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
	 */
	private String isMail;
	
	/**
	 * 处理方法
	 */
	private String process;

	/**
	 * @return int
	 */
	public int getAlarmCode() {
		return alarmCode;
	}

	/**
	 * @param alarmCode int
	 */
	public void setAlarmCode(int alarmCode) {
		this.alarmCode = alarmCode;
	}

	/**
	 * @return String
	 */
	public String getAlarmName() {
		return alarmName;
	}

	/**
	 * @param alarmName String
	 */
	public void setAlarmName(String alarmName) {
		this.alarmName = alarmName;
	}

	/**
	 * @return String
	 */
	public String getAlarmType() {
		return alarmType;
	}

	/**
	 * @param alarmType String
	 */
	public void setAlarmType(String alarmType) {
		this.alarmType = alarmType;
	}

	/**
	 * @return String
	 */
	public String getAlarmOid() {
		return alarmOid;
	}

	/**
	 * @param alarmOid String
	 */
	public void setAlarmOid(String alarmOid) {
		this.alarmOid = alarmOid;
	}

	/**
	 * @return boolean
	 */
	public boolean isCanClear() {
		return canClear;
	}

	/**
	 * @param canClear boolean
	 */
	public void setCanClear(boolean canClear) {
		this.canClear = canClear;
	}

	/**
	 * @return String
	 */
	public String getClearOid() {
		return clearOid;
	}

	/**
	 * @param clearOid String
	 */
	public void setClearOid(String clearOid) {
		this.clearOid = clearOid;
	}

	/**
	 * @return String
	 */
	public String getAlarmObject() {
		return alarmObject;
	}

	/**
	 * @param alarmObject String
	 */
	public void setAlarmObject(String alarmObject) {
		this.alarmObject = alarmObject;
	}

	/**
	 * @return String
	 */
	public String getAlarmDetailDesc() {
		return alarmDetailDesc;
	}

	/** 
	 * @param alarmDetailDesc String
	 */
	public void setAlarmDetailDesc(String alarmDetailDesc) {
		this.alarmDetailDesc = alarmDetailDesc;
	}

	/**
	 * @return String
	 */
	public String getAlarmLevel() {
		return alarmLevel;
	}

	/**
	 * @param alarmLevel String
	 */
	public void setAlarmLevel(String alarmLevel) {
		this.alarmLevel = alarmLevel;
	}

	/**
	 * @return  Map<String, String>
	 */
	public Map<String, String> getAlarmExtInfo() {
		return alarmExtInfo;
	}

	/**
	 * @param alarmExtInfo Map<String, String>
	 */ 
	public void setAlarmExtInfo(Map<String, String> alarmExtInfo) {
		this.alarmExtInfo = alarmExtInfo;
	}

	/**
	 * @return String
	 */
	public String getAlarmRaiseCondition() {
		return alarmRaiseCondition;
	}

	/**
	 * @param alarmRaiseCondition String
	 */
	public void setAlarmRaiseCondition(String alarmRaiseCondition) {
		this.alarmRaiseCondition = alarmRaiseCondition;
	}

	/**
	 * @return String
	 */
	public String getAlarmClearCondition() {
		return alarmClearCondition;
	}

	/**
	 * @param alarmClearCondition String
	 */
	public void setAlarmClearCondition(String alarmClearCondition) {
		this.alarmClearCondition = alarmClearCondition;
	}

	/**
	 * @return String
	 */
	public String getAlarmAffectRange() {
		return alarmAffectRange;
	}

	/**
	 * @param alarmAffectRange String
	 */
	public void setAlarmAffectRange(String alarmAffectRange) {
		this.alarmAffectRange = alarmAffectRange;
	}

	/**
	 * @return String
	 */
	public String getAlarmProposedRepairAction() {
		return alarmProposedRepairAction;
	}

	/**
	 * @param alarmProposedRepairAction String
	 */
	public void setAlarmProposedRepairAction(String alarmProposedRepairAction) {
		this.alarmProposedRepairAction = alarmProposedRepairAction;
	}

	/**
	 * @return int
	 */
	public int getAlarmRebootClear() {
		return alarmRebootClear;
	}

	/**
	 * @param alarmRebootClear int
	 */
	public void setAlarmRebootClear(int alarmRebootClear) {
		this.alarmRebootClear = alarmRebootClear;
	}

	/** 
	 * @return List<AlarmReasonBean>
	 */
	public List<AlarmReasonBean> getReasons() {
		return reasons;
	}

	/**
	 * @param reasons List<AlarmReasonBean>
	 */
	public void setReasons(List<AlarmReasonBean> reasons) {
		this.reasons = reasons;
	}

	/**
	 * @return Map<Integer, AlarmReasonBean>
	 */
	public Map<Integer, AlarmReasonBean> getAlarmReasons() {
		return alarmReasons;
	}

	/**
	 * @param alarmReasons Map<Integer, AlarmReasonBean>
	 */
	public void setAlarmReasons(Map<Integer, AlarmReasonBean> alarmReasons) {
		this.alarmReasons = alarmReasons;
	}

	/**
	 * @return String
	 */
	public String getIsMail() {
		return isMail;
	}

	/**
	 * @param isMail String
	 */
	public void setIsMail(String isMail) {
		this.isMail = isMail;
	}

	/**
	 * @return String
	 */
	public String getProcess() {
		return process;
	}

	/**
	 * @param process String
	 */
	public void setProcess(String process) {
		this.process = process;
	}

	/**
	 * @return long
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}

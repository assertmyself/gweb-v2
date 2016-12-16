/*******************************************************************************
 * Copyright (c) GBCOM
 * All rights reserved. This program and the accompanying materials are belong 
 * to Shanghai GBCOM Communication Technology Co.Ltd
 * http://www.gbcom.com.cn
/*******************************************************************************/
package com.gbcom.common.template.xml.am;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlType;

/**
 * 上报告警的模板
 * 
 * @author xuyin
 * @version 1.0
 */
@XmlType(propOrder = { "vendor","sysTime","alarmOid", "alarmSn", "alarmNeName",
		"alarmLevel", "alarmType", "alarmReasonID", "alarmReason",
		"alarmRaiseTime", "alarmStatus", "alarmTitle", "alarmInfo" })
public class AlarmReportTemplet implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String vendor;
	private String sysTime;
	private String alarmOid;
	private String alarmSn;
	private String alarmNeName;
	private String alarmLevel;
	private String alarmType;
	private String alarmReasonID;
	private String alarmReason;
	private String alarmRaiseTime;
	private String alarmStatus;
	private String alarmTitle;
	private String alarmInfo;

	/**
	 * @return the vendor
	 */
	public String getVendor() {
		return vendor;
	}

	/**
	 * @param vendor
	 *            the vendor to set
	 */
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	/**
	 * @return the sysTime
	 */
	public String getSysTime() {
		return sysTime;
	}

	/**
	 * @param sysTime
	 *            the sysTime to set
	 */
	public void setSysTime(String sysTime) {
		this.sysTime = sysTime;
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
	 * @return the alarmSn
	 */
	public String getAlarmSn() {
		return alarmSn;
	}

	/**
	 * @param alarmSn
	 *            the alarmSn to set
	 */
	public void setAlarmSn(String alarmSn) {
		this.alarmSn = alarmSn;
	}

	/**
	 * @return the alarmNeName
	 */
	public String getAlarmNeName() {
		return alarmNeName;
	}

	/**
	 * @param alarmNeName
	 *            the alarmNeName to set
	 */
	public void setAlarmNeName(String alarmNeName) {
		this.alarmNeName = alarmNeName;
	}

	/**
	 * @return the alarmLevel
	 */
	public String getAlarmLevel() {
		return alarmLevel;
	}

	/**
	 * @param alarmLevel
	 *            the alarmLevel to set
	 */
	public void setAlarmLevel(String alarmLevel) {
		this.alarmLevel = alarmLevel;
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
	 * @return the alarmReasonID
	 */
	public String getAlarmReasonID() {
		return alarmReasonID;
	}

	/**
	 * @param alarmReasonID
	 *            the alarmReasonID to set
	 */
	public void setAlarmReasonID(String alarmReasonID) {
		this.alarmReasonID = alarmReasonID;
	}

	/**
	 * @return the alarmReason
	 */
	public String getAlarmReason() {
		return alarmReason;
	}

	/**
	 * @param alarmReason
	 *            the alarmReason to set
	 */
	public void setAlarmReason(String alarmReason) {
		this.alarmReason = alarmReason;
	}

	/**
	 * @return the alarmRaiseTime
	 */
	public String getAlarmRaiseTime() {
		return alarmRaiseTime;
	}

	/**
	 * @param alarmRaiseTime
	 *            the alarmRaiseTime to set
	 */
	public void setAlarmRaiseTime(String alarmRaiseTime) {
		this.alarmRaiseTime = alarmRaiseTime;
	}

	/**
	 * @return the alarmStatus
	 */
	public String getAlarmStatus() {
		return alarmStatus;
	}

	/**
	 * @param alarmStatus
	 *            the alarmStatus to set
	 */
	public void setAlarmStatus(String alarmStatus) {
		this.alarmStatus = alarmStatus;
	}

	/**
	 * @return the alarmTitle
	 */
	public String getAlarmTitle() {
		return alarmTitle;
	}

	/**
	 * @param alarmTitle
	 *            the alarmTitle to set
	 */
	public void setAlarmTitle(String alarmTitle) {
		this.alarmTitle = alarmTitle;
	}

	/**
	 * @return the alarmInfo
	 */
	public String getAlarmInfo() {
		return alarmInfo;
	}

	/**
	 * @param alarmInfo
	 *            the alarmInfo to set
	 */
	public void setAlarmInfo(String alarmInfo) {
		this.alarmInfo = alarmInfo;
	}

}

/*******************************************************************************
 * Copyright (c) GBCOM
 * All rights reserved. This program and the accompanying materials are belong 
 * to Shanghai GBCOM Communication Technology Co.Ltd
 * http://www.gbcom.com.cn
/*******************************************************************************/
package com.gbcom.common.template.xml.am;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 上报告警的模板
 * 
 * @author xuyin
 * @version 1.0
 */
@XmlRootElement(name = "alarms")
public class AlarmReportTempletBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 告警模板bean
	 */
	private List<AlarmReportTemplet> alarmReportTemplets = new ArrayList<AlarmReportTemplet>();

	/**
	 * @return the alarmReportTemplets
	 */
	@XmlElement(name = "alarm")
	public List<AlarmReportTemplet> getAlarmReportTemplets() {
		return alarmReportTemplets;
	}

	/**
	 * @param alarmReportTemplets
	 *            the alarmReportTemplets to set
	 */
	public void setAlarmReportTemplets(
			List<AlarmReportTemplet> alarmReportTemplets) {
		this.alarmReportTemplets = alarmReportTemplets;
	}

}

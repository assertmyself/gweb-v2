/**
 * @(#)AlarmTempletBean.java       07/11
 *
 * Copyright (c) 2008 GBCom Communication Technology Co.Ltd
 * All right reserved.
 */
package com.gbcom.common.template.xml.am;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 告警模板集合
 * 
 * @author xuyin
 */
@XmlRootElement(name = "alarms")
public class AlarmTempletBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String vendor;

	private List<AlarmInfoBean> alarmInfoBeans = new ArrayList<AlarmInfoBean>();

	private Map<Integer, AlarmInfoBean> alarmInfos = new HashMap<Integer, AlarmInfoBean>();

	private Map<String, AlarmInfoBean> alarmInfoOids = new HashMap<String, AlarmInfoBean>();

	/**
	 * 构造方法
	 */
	public AlarmTempletBean() {

	}

	/**
	 * @return the vendor
	 */
	@XmlElement(name = "vendor")
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
	 * @return the alarmInfoBeans
	 */
	/**
	 * @return the vendor
	 */
	@XmlElement(name = "alarmInfo")
	public List<AlarmInfoBean> getAlarmInfoBeans() {
		return alarmInfoBeans;
	}

	/**
	 * @param alarmInfoBeans
	 *            the alarmInfoBeans to set
	 */
	public void setAlarmInfoBeans(List<AlarmInfoBean> alarmInfoBeans) {
		this.alarmInfoBeans = alarmInfoBeans;
	}

	/**
	 * addAlarmInfo
	 * 
	 * @param alarmCode
	 *            alarmCode
	 * @param alarmInfoBean
	 *            alarmInfoBean
	 */
	public void addAlarmInfo(int alarmCode, AlarmInfoBean alarmInfoBean) {
		if (alarmInfos == null) {
			alarmInfos = new HashMap<Integer, AlarmInfoBean>();
		}
//		synchronized (alarmInfoBeans){
//			alarmInfoBeans.add(alarmInfoBean);
//		}
		synchronized (alarmInfos) {
			alarmInfos.put(alarmCode, alarmInfoBean);
		}
		synchronized (alarmInfoOids) {
			alarmInfoOids.put(alarmInfoBean.getAlarmOid(), alarmInfoBean);
			alarmInfoOids.put(alarmInfoBean.getClearOid(), alarmInfoBean);
		}
	}

	/**
	 * addAlarmInfo
	 * 
	 * @param alarmCode
	 *            alarmCode
	 * @return AlarmInfoBean
	 */
	public AlarmInfoBean getAlarmInfo(int alarmCode) {
		return alarmInfos.get(alarmCode);
	}

	/**
	 * addAlarmInfo
	 * 
	 * @param oid
	 *            oid
	 * @return AlarmInfoBean
	 */
	public AlarmInfoBean getAlarmInfoByOid(String oid) {
		return alarmInfoOids.get(oid);
	}
}

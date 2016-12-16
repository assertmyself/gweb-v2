/**
 * @(#)AlarmVersionTemplet.java       07/11
 *
 * Copyright (c) 2008 GBCom Communication Technology Co.Ltd
 * All right reserved.
 */
package com.gbcom.common.template.xml.am;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 所有告警池
 * 
 * @author xuyin
 * @version 1.0
 */
public final class AlarmTemplet implements Serializable {
	/**
	 * @description
	 */
	private static final long serialVersionUID = -4309386425675803981L;

	/**
	 * OMC侧告警模板,CODE为key
	 */
	private Map<String, AlarmTempletBean> alarmTempletMap = new HashMap<String, AlarmTempletBean>();
	
	/**
	 * OMC侧告警模板 OID为key
	 */
	private Map<String, AlarmTempletBean> alarmTempletOidMap = new HashMap<String, AlarmTempletBean>();

	/**
     * 上报告警模板
     */
	private Map<String, AlarmReportTemplet> alarmReportTempletMap = new HashMap<String, AlarmReportTemplet>();
	

	/**
	 * 单例对象
	 */
	private static AlarmTemplet instance = null;

	/**
	 * 
	 * TODO description here
	 */
	private AlarmTemplet() {

	}

	/**
	 * 
	 * TODO description here
	 * 
	 * @return AlarmTemplet
	 */
	public static AlarmTemplet getInstance() {
		synchronized (AlarmTemplet.class) {
			if (instance == null) {
				instance = new AlarmTemplet();
			}
			return instance;
		}
	}

	/**
	 * @return the alarmTempletMap
	 */
	public Map<String, AlarmTempletBean> getAlarmTempletMap() {
		return alarmTempletMap;
	}

	/**
	 * @param alarmTempletMap
	 *            the alarmTempletMap to set
	 */
	public void setAlarmTempletMap(Map<String, AlarmTempletBean> alarmTempletMap) {
		this.alarmTempletMap = alarmTempletMap;
	}

	/**
	 * @return the alarmReportTempletMap
	 */
	public Map<String, AlarmReportTemplet> getAlarmReportTempletMap() {
		return alarmReportTempletMap;
	}

	/**
	 * @param alarmReportTempletMap
	 *            the alarmReportTempletMap to set
	 */
	public void setAlarmReportTempletMap(
			Map<String, AlarmReportTemplet> alarmReportTempletMap) {
		this.alarmReportTempletMap = alarmReportTempletMap;
	}
	

	/**
	 * @return the alarmTempletOidMap
	 */
	public Map<String, AlarmTempletBean> getAlarmTempletOidMap() {
		return alarmTempletOidMap;
	}

	/**
	 * @param alarmTempletOidMap the alarmTempletOidMap to set
	 */
	public void setAlarmTempletOidMap(
			Map<String, AlarmTempletBean> alarmTempletOidMap) {
		this.alarmTempletOidMap = alarmTempletOidMap;
	}

	/**
	 * 
	 * @param vendor
	 *            vendor
	 * @param alarmTempletBean
	 *            alarmTempletBean
	 */
	public void addAlarmTemplet(String vendor, AlarmTempletBean alarmTempletBean) {
		synchronized (vendor) {
			alarmTempletMap.put(vendor, alarmTempletBean);
		}
	}

	/**
	 * 
	 * @param vendor
	 *            vendor
	 * @param alarmReportTemplet
	 *            alarmReportTemplet
	 */
	public void addAlarmReportTemplet(String vendor,
			AlarmReportTemplet alarmReportTemplet) {
		synchronized (vendor) {
			alarmReportTempletMap.put(vendor, alarmReportTemplet);
		}
	}
	

	/**
	 * 
	 * @param vendor
	 *            vendor
	 * @return AlarmTempletBean
	 */
	public AlarmTempletBean getAlarmTemplet(String vendor) {
		return alarmTempletMap.get(vendor);
	}

	/**
	 * 
	 * @param vendor
	 *            vendor
	 * @return AlarmReportTemplet
	 */
	public AlarmReportTemplet getAlarmReportTemplet(String vendor) {
		return alarmReportTempletMap.get(vendor);
	}
	
	/**
	 * 
	 * @param oid
	 *            oid
	 * @return AlarmTempletBean
	 */
	public AlarmTempletBean getAlarmTempletByOid(String oid) {
		return alarmTempletMap.get(oid);
	}
	
	

}

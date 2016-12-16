package com.gbcom.common.template.xml.snmp;

import org.apache.log4j.Logger;

import com.gbcom.op.util.xml.XStreamUtil;

/**
 * snmp基本配置 + 接入上报 oid 工厂类
 * 
 * @author SunYanzheng
 * @date 下午2:10:54
 * @version v1.0.0
 * @see SnmpTempManager
 */
public final class SnmpTempManager {
	private static final Logger LOG = Logger.getLogger(SnmpTempManager.class);
	private static final String HEART_REPORT_PATH = "config/snmp/heart_report_templet.xml";
	private static final String ACCESS_REPORT_PATH = "config/snmp/access_report_templet.xml";
	private static final String SNMP_CONFIG_PATH = "config/snmp/snmp_config.xml";
	private static final String UPGRADE_REPORT_PATH = "config/snmp/filetrans_report_templet.xml";
	private static final String L2TP_REPORT_PATH = "config/snmp/l2tp_report_templet.xml";

	

	private static SnmpTempManager instance = new SnmpTempManager();
	/**
	 * 接入上报 模板
	 */
	private AccessReportTemplate accessReport = null;
	private HeartReportTemplate heartReport = null;
	private L2tpReportTemplate l2tpReport = null;

	


	/**
	 * 升级模板
	 */
	private FileTransReportTemplate upgradeReport;

	/**
	 * snmp基本配置模板
	 */
	private SnmpContextTemplate snmpContext;

	private SnmpTempManager() {

	}

	/**
	 * 获取单例
	 * 
	 * @return SnmpTemplateFactory
	 */
	public static SnmpTempManager getInstance() {
		return instance;
	}

	/**
	 * 初始化
	 * 
	 * @throws Exception
	 *             Exception
	 */
	public void init() throws Exception {
		loadSnmpConfig();
		loadAccessReport();
		loadUpgradeReport();
		loadHeartReport();
		loadL2tpReport();

	}

	private void loadAccessReport() throws Exception {
		Class<?>[] classContext = { AccessReportTemplate.class };
		accessReport = XStreamUtil.fromXML(AccessReportTemplate.class,
				ACCESS_REPORT_PATH, classContext);
		if (accessReport == null) {
			LOG.error("LOAD FILE FAILED");
			throw new Exception();
		}
	}

	private void loadUpgradeReport() throws Exception {
		Class<?>[] classContext = { FileTransReportTemplate.class };
		upgradeReport = XStreamUtil.fromXML(FileTransReportTemplate.class,
				UPGRADE_REPORT_PATH, classContext);
		if (upgradeReport == null) {
			LOG.error("LOAD upgradeReport  FILE FAILED");
			throw new Exception();
		}
	}
	
	private void loadHeartReport() throws Exception {
		Class<?>[] classContext = { HeartReportTemplate.class };
		heartReport = XStreamUtil.fromXML(HeartReportTemplate.class,
				HEART_REPORT_PATH, classContext);
		if (heartReport == null) {
			LOG.error("LOAD FILE FAILED");
			throw new Exception();
		}
	}

	private void loadSnmpConfig() throws Exception {
		Class<?>[] classContext = { SnmpContextTemplate.class,
				SnmpConfigTemplate.class };
		snmpContext = XStreamUtil.fromXML(SnmpContextTemplate.class,
				SNMP_CONFIG_PATH, classContext);
		if (snmpContext == null) {
			LOG.error("LOAD FILE FAILED");
			throw new Exception();
		}
	}

	private void loadL2tpReport() throws Exception {
		Class<?>[] classContext = { L2tpReportTemplate.class };
		l2tpReport = XStreamUtil.fromXML(L2tpReportTemplate.class,
				L2TP_REPORT_PATH, classContext);
		if (l2tpReport == null) {
			LOG.error("LOAD FILE FAILED");
			throw new Exception();
		}
	}



	// private void loadFileReport() throws Exception {
	// Class<?>[] classContext = { FileReportTemplate.class };
	// fileReport = XStreamUtil.fromXML(FileReportTemplate.class,
	// FILE_REPORT_PATH, classContext);
	// if (fileReport == null) {
	// LOG.error("LOAD FileReport  FILE FAILED");
	// throw new Exception();
	// }
	// }

	/**
	 * 获取心跳模板
	 * 
	 * @return AccessReportTemplate
	 */
	public HeartReportTemplate getHeartReportTemplate() {
		return heartReport;
	}

	/**
	 * 获取接入模板
	 * 
	 * @return AccessReportTemplate
	 */
	public AccessReportTemplate getAccessReportTemplate() {
		return accessReport;
	}

	/**
	 * 获取升级模板
	 * 
	 * @return UpgradeReportTemplate
	 */
	public FileTransReportTemplate getUpgradeReportTemplate() {

		return upgradeReport;
	}

	/**
	 * 获取接入模板
	 * 
	 * @return AccessReportTemplate
	 */
	public L2tpReportTemplate getL2tpReportTemplate() {
		return l2tpReport;
	}

	
	

	// /**
	// * 获取接入模板
	// *
	// * @return AccessReportTemplate
	// */
	// public FileReportTemplate getFileReportTemplate() {
	// return fileReport;
	// }

	/**
	 * 获取SNMP配置
	 * 
	 * @return SnmpContextTemplate
	 */
	public SnmpContextTemplate getSnmpContext() {
		return snmpContext;
	}

	/**
	 * 默认取第一个为snmp配置
	 * 
	 * @return SnmpConfigTemplate
	 */
	public SnmpConfigTemplate getDefaultSnmpConfig() {
		return getSnmpContext().getList().get(0);
	}
}

/**
 * @(#)AlarmTemplet.java       07/11
 *
 * Copyright (c) 2008 GBCom Communication Technology Co.Ltd
 * All right reserved.
 */
package com.gbcom.common.template.xml.am;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.apache.log4j.Logger;
import org.w3c.dom.NodeList;
import com.gbcom.omc.platform.da.xml.IXmlParser;
import com.gbcom.omc.platform.da.xml.MyXMLException;
import com.gbcom.omc.platform.da.xml.XmlFactory;
import com.gbcom.system.utils.XmlFileUtil;

/**
 * 告警模板，加载告警知识库信息，包括{@link AlarmReportTempletBean} 和{@link AlarmTempletBean}
 * 
 * @author SunYanzheng
 * @date 上午10:27:40
 * @version v1.0.0
 * @see AlarmTempletFactory
 */
public final class AlarmTempletFactory implements IAlarmTemplet, Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger
			.getLogger(AlarmTempletFactory.class);
	/**
	 * 单例对象
	 */
	private static AlarmTempletFactory instance = new AlarmTempletFactory();
	private AlarmTempletBean alarmTempletBean = new AlarmTempletBean();
	private AlarmReportTempletBean alarmReportTempletBean = new AlarmReportTempletBean();
	private Map<String, List<Integer>> alarmFilterMap = new HashMap<String, List<Integer>>();

	/**
	 * 是否已加载的标志
	 */
	private boolean isLoad = false;

	/**
	 * 模板文件路径
	 */
	private static final String MODLE_FILE_PATH = "config/am/alarm_config.xml";

	/**
	 * private Constructor
	 */
	private AlarmTempletFactory() {

	}

	/**
	 * 获取单例对象
	 * 
	 * @return AlarmTempletFactory
	 */
	public static AlarmTempletFactory getInstance() {
		return instance;
	}

	/**
	 * 
	 * TODO description here
	 * 
	 * @return
	 * @throws MyXMLException
	 */
	private String[] getAlarmTempletFiles() throws MyXMLException {
		final String file = Thread.currentThread().getContextClassLoader()
				.getResource(MODLE_FILE_PATH).getFile();
		final IXmlParser xmlParser = XmlFactory.getXmlParser();
		xmlParser.loadFile(file);
		final NodeList nodeList = (NodeList) xmlParser
				.getNodeList("/conf/templetFile/fileName");
		final int size = nodeList.getLength();
		String[] templetFiles = new String[size];
		for (int i = 0; i < size; i++) {
			templetFiles[i] = nodeList.item(i).getTextContent();
		}
		return templetFiles;
	}

	/**
	 * 
	 * 读取配置文件
	 * 
	 * @param file
	 *            file
	 * @throws MyXMLException
	 */
	private void loadAlarmTemplet(final String file) throws MyXMLException {
		final File xml = new File(file);
		if (xml.exists()) {
			final AlarmTempletBean templetBean = XmlFileUtil
					.unmarshallerObjectFromXml(AlarmTempletBean.class, xml);
			final List<AlarmInfoBean> alarmInfoBeans = templetBean
					.getAlarmInfoBeans();
			for (AlarmInfoBean alarmInfoBean : alarmInfoBeans) {
				alarmInfoBean.initAlarmReasonMap();
				final int alarmCode = alarmInfoBean.getAlarmCode();
				templetBean.addAlarmInfo(alarmCode, alarmInfoBean);
			}
			AlarmTemplet.getInstance().addAlarmTemplet(templetBean.getVendor(),
					templetBean);
		}

	}

	/**
	 * 
	 * 读取上报告警配置文件
	 * 
	 * @param file
	 *            file
	 * @throws MyXMLException
	 */
	private void loadAlarmReportTemplate(final String file)
			throws MyXMLException {
		final File xml = new File(file);
		if (xml.exists()) {
			alarmReportTempletBean = XmlFileUtil.unmarshallerObjectFromXml(
					AlarmReportTempletBean.class, xml);
			final List<AlarmReportTemplet> alarmReportTemplets = alarmReportTempletBean
					.getAlarmReportTemplets();
			for (AlarmReportTemplet alarmReportTemplet : alarmReportTemplets) {
				AlarmTemplet.getInstance().addAlarmReportTemplet(
						alarmReportTemplet.getVendor(), alarmReportTemplet);
			}
		}
	}

	/**
	 * 更新模板文件
	 * 
	 * @throws MyXMLException
	 *             MyXMLException
	 */
	public void updateAlarmTemplate() throws MyXMLException {
		final Set<String> vendors = AlarmTemplet.getInstance()
				.getAlarmTempletMap().keySet();
		// 获取有多少个模板文件，在配置文件alarm_config.xml中描述
		final String language = Locale.getDefault().getLanguage();
		for (String vendor : vendors) {
			// 读取各版本的模版
			final String file = Thread.currentThread().getContextClassLoader()
					.getResource(
							"config/am/alarm_templet_" + vendor + "_"
									+ language + ".xml").getPath();
			final File xml = new File(file);
			if (xml.exists()) {
				XmlFileUtil.marshallerObjectToXml(AlarmTemplet.getInstance()
						.getAlarmTemplet(vendor), AlarmTempletBean.class, xml);
			}
		}

	}

	/**
	 * 更新指定模板文件
	 * 
	 * @param vendor
	 *            vendor
	 * @throws MyXMLException
	 *             MyXMLException
	 */
	public void updateAlarmTemplate(final String vendor) throws MyXMLException {
		// 获取有多少个模板文件，在配置文件alarm_config.xml中描述
		final String language = Locale.getDefault().getLanguage();
		// 读取各版本的模版
		final String file = Thread.currentThread().getContextClassLoader()
				.getResource(
						"config/am/alarm_templet_" + vendor + "_" + language
								+ ".xml").getPath();
		final File xml = new File(file);
		if (xml.exists()) {
			XmlFileUtil.marshallerObjectToXml(AlarmTemplet.getInstance()
					.getAlarmTemplet(vendor), AlarmTempletBean.class, xml);
		}

	}

	/**
	 * 
	 * 加载配置文件
	 * 
	 * @throws MyXMLException
	 *             MyXMLException
	 */
	public void load() throws MyXMLException {
		if (isLoad) {
			return;
		}
		// 获取有多少个模板文件，在配置文件alarm_config.xml中描述
		final String[] files = getAlarmTempletFiles();
		if (files.length == 0) {
			return;
		}
		final String language = Locale.getDefault().getLanguage();
		// 读取各版本的模版
		for (int i = 0; i < files.length; i++) {
			final String file = Thread.currentThread().getContextClassLoader()
					.getResource(
							"config/am/" + files[i] + "_" + language + ".xml")
					.getPath();
			loadAlarmTemplet(file);
		}

		loadAlarmReportTemplate(Thread.currentThread().getContextClassLoader()
				.getResource(
						"config/am/alarm_report_templet_" + language + ".xml")
				.getPath());
		isLoad = true;
		LOG.info("****load Alarm Templet ok!***");
	}

	/**
	 * 获取alarmTempletBean
	 * 
	 * @return alarmTempletBean
	 */
	@Override
	public AlarmTempletBean getAlarmTemplet() {
		return alarmTempletBean;
	}

	/**
	 * 
	 * @return getAlarmTempletFiles
	 * @throws MyXMLException
	 *             MyXMLException
	 */
	public String[] getAlarmTemplets() throws MyXMLException {
		return getAlarmTempletFiles();
	}

	/**
	 * 告警文件 Reload
	 * 
	 * @throws MyXMLException
	 *             MyXMLException
	 */
	public synchronized void reLoad() throws MyXMLException {
		final String[] files = getAlarmTempletFiles();
		final String language = Locale.getDefault().getLanguage();
		if (files.length != 0) {
			AlarmTemplet.getInstance().getAlarmTempletMap().clear();
			for (int i = 0; i < files.length; i++) {
				String file = Thread.currentThread().getContextClassLoader()
						.getResource(
								"config/am/" + files[i] + "_" + language
										+ ".xml").getFile();
				loadAlarmTemplet(file);
			}
			LOG.info("*reload Alarm Templet ok!*");
		}
	}

	/**
	 * @return the alarmFilterMap
	 */
	public Map<String, List<Integer>> getAlarmFilterMap() {
		return alarmFilterMap;
	}

	/**
	 * @param alarmFilterMap
	 *            the alarmFilterMap to set
	 */
	public void setAlarmFilterMap(
			final Map<String, List<Integer>> alarmFilterMap) {
		this.alarmFilterMap = alarmFilterMap;
	}

	/**
	 * 
	 * @param alarmTempletBean
	 *            alarmTempletBean
	 */
	public void setAlarmTempletBean(final AlarmTempletBean alarmTempletBean) {
		this.alarmTempletBean = alarmTempletBean;
	}

	/**
	 * @return 告警上报模板
	 */
	public AlarmReportTempletBean getAlarmReportTemplateBean() {
		return alarmReportTempletBean;
	}

	/**
	 * main-test
	 * 
	 * @param args
	 *            String
	 * @throws MyXMLException
	 *             MyXMLException
	 */
	public static void main(final String[] args) throws MyXMLException {
		AlarmTempletFactory.getInstance().load();
	}

}

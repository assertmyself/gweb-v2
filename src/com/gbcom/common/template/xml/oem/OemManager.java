/*******************************************************************************
 * Copyright (c) GBCOM
 * All rights reserved. This program and the accompanying materials are belong 
 * to Shanghai GBCOM Communication Technology Co.Ltd
 * http://www.gbcom.com.cn
/*******************************************************************************/
package com.gbcom.common.template.xml.oem;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gbcom.system.utils.XmlFileUtil;

/**
 * OEM工厂, {@link config/oem/oem.xml }
 * 
 * @author xuyin
 * @version 1.0
 * @date 2013-4-3
 */
public class OemManager implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 日志
	 */
	private static final Logger LOG = LoggerFactory.getLogger(OemManager.class);

	/**
	 * 单例对象
	 */
	private static OemManager instance = new OemManager();

	private static final String VENDOR_FILE = "config/oem/oem.xml";

	private static final String VENDOR_FILE_PATH = "config/oem/";

	private static final String FILE_TYPE_XML = ".xml";
	private static final String FILE_LOCALE_SEPARATOR = "/";

	private Oem oem = null;

	// 缓存设备型号，方便查询
	private Map<String, Map<String, String>> codeMap = new HashMap<String, Map<String, String>>();

	// key:sysmodel value:oemvendor
	private Map<String, String> vendorModels = new LinkedHashMap<String, String>();

	/**
	 * 
	 * TODO description here
	 */
	private OemManager() {
		initOem();
	}

	/**
	 * 
	 * TODO description here
	 * 
	 * @return OemFactory
	 */
	public static OemManager getInstance() {
		return instance;
	}

	/**
	 * 初始化OEM信息 {@link}
	 */
	private void initOem() {
		parseFile();
		/**
		 * 设置语言环境 {@link config/oem/oem.xml} #<locale>zh_CN</locale>
		 */
		initLocale();
		LOG
				.info("parse oem/vendor file Success!  /n Set JVM Default Locale Sucess");
	}

	private void initLocale() {
		if (oem.getLocale() != null && !oem.getLocale().equals("")) {
			String[] lanCoun = oem.getLocale().split("_");
			if (lanCoun.length == 1) {
				Locale.setDefault(new Locale(lanCoun[0]));
			} else if (lanCoun.length == 2) {
				Locale.setDefault(new Locale(lanCoun[0], lanCoun[1]));
			}
		}
	}

	/**
	 * 解析文件
	 */
	private void parseFile() {
		loadOemFile();
		loadVendorFile();
	}

	/**
	 * 解析厂商文件
	 * 
	 * <pre>
	 * 对应文件名  oemName+"_"+Locale+".xml"  {@link #oem}
	 * @version 2.0.0
	 */
	private void loadVendorFile() {
		String filePath = null;
		if (oem.getPline() != null && !oem.getPline().trim().equals("")) {
			filePath = this.getClass().getClassLoader().getResource(
					VENDOR_FILE_PATH + oem.getLocale() + FILE_LOCALE_SEPARATOR
							+ oem.getName() + "_" + oem.getPline()
							+ FILE_TYPE_XML).getFile();
		}else{
			filePath = this.getClass().getClassLoader().getResource(
					VENDOR_FILE_PATH + oem.getLocale() + FILE_LOCALE_SEPARATOR
							+ oem.getName() + FILE_TYPE_XML).getFile();
		}
		File file = new File(filePath);
		if (file.exists()) {
			try {
				Vendor vendor = XmlFileUtil.unmarshallerObjectFromXml(
						Vendor.class, file);

				for (VendorModel model : vendor.getModels()) {
					vendorModels.put(model.getSysModel(), model.getVenModel());
				}
				oem.setVendor(vendor);
				/**
				 * 	<build>v3.0.8</build>
	<date>20151218</date>
	<version>v3.0.8</version>
				 */
				//oem.setBuild(vendor.ge)
				
			} catch (Exception e) {
				LOG.error("failed to parse xml, file=" + filePath, e);
				System.exit(0);
			}
		} else {
			LOG.error("filePath can't be found <" + filePath + ">");
			System.exit(0);
		}
	}

	private void loadOemFile() {
		String filePath = this.getClass().getClassLoader().getResource(
				VENDOR_FILE).getFile();
		File file = new File(filePath);
		if (file.exists()) {
			try {
				oem = XmlFileUtil.unmarshallerObjectFromXml(Oem.class, file);
			} catch (Exception e) {
				LOG.error("failed to parse vendor.xml", e);
				e.printStackTrace();
				System.exit(0);
			}
		} else {
			LOG.error("oem.xml can't be found");
			System.exit(0);
		}

	}

	/**
	 * @return the oem
	 */
	public Oem getOem() {
		return oem;
	}

	/**
	 * 获取vendorModels
	 * 
	 * @return vendorModels
	 */
	public Map<String, String> getVendorModels() {
		return vendorModels;
	}

	/**
	 * 获取sysModel
	 * 
	 * @param sysModel
	 *            String
	 * @return String
	 */
	public String getVenModel(String sysModel) {
		return vendorModels.get(sysModel);
	}

	/**
	 * 获取厂商外部编号
	 * 
	 * @param vedor
	 *            厂商 名称
	 * @param code
	 *            内部编号
	 * @return outer code
	 */
	public String getOuterCode(String vedor, String code) {

		if (codeMap.get(vedor) != null) {
			return codeMap.get(vedor).get(code);
		}
		return null;

	}

	/**
	 * 获取与厂商外部编号相对应的内部型号
	 * 
	 * @param vedor
	 *            厂商 名称
	 * @param outercode
	 *            外部编号
	 * @return inner code
	 */
	public String getInnerCode(String vedor, String outercode) {

		if (codeMap.get(vedor) != null) {
			return codeMap.get(vedor).get(outercode);
		}
		return null;

	}

	/**
	 * 将内存中的内容同步到文件中
	 */
	public void save() {
		saveOem();
		saveVendor();
	}

	/**
	 * 将内容中oem信息同步到oem.xml文件中
	 */
	private void saveOem() {
		String filePath = this.getClass().getClassLoader().getResource(
				VENDOR_FILE).getFile();
		System.out.println(filePath);
		File file = new File(filePath);
		if (file.exists()) {
			try {
				XmlFileUtil.marshallerObjectToXml(oem, Oem.class, file);
			} catch (Exception e) {
				LOG.error("failed to parse oem.xml", e);
				e.printStackTrace();
			}
		} else {
			LOG.error("oem.xml can't be found");
		}
	}

	/**
	 * 将内存中vendor信息同步到vendor.xml文件中
	 */
	private void saveVendor() {
		String filePath = this.getClass().getClassLoader().getResource(
				VENDOR_FILE_PATH + oem.getLocale() + FILE_LOCALE_SEPARATOR
						+ oem.getName() + FILE_TYPE_XML).getFile();
		File file = new File(filePath);
		if (file.exists()) {
			try {
				XmlFileUtil.marshallerObjectToXml(oem.getVendor(),
						Vendor.class, file);
			} catch (Exception e) {
				LOG.error("failed to parse vendor.xml", e);
				e.printStackTrace();
			}
		} else {
			LOG.error("vendor.xml can't be found");
		}
	}

	/**
	 * 测试方法
	 * 
	 * @param args
	 *            String[]
	 */
	public static void main(String[] args) {
		Oem oem = OemManager.getInstance().getOem();
		System.out.println(oem.getVendor().getVersion());
		OemManager.getInstance().getOem().getVendor().setVersion("v3.0.0.7");
		OemManager.getInstance().save();
		System.out.println(OemManager.getInstance().getOem().getVendor().getVersion());
	}
}

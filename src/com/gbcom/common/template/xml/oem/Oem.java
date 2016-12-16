package com.gbcom.common.template.xml.oem;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 供应商
 * 
 * @author xuyin
 * @version 1.0
 * @date 2013-4-3
 */
@XmlRootElement(name = "oem")
public class Oem implements Serializable {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 供应商名称:与 vendor保持一致，
	 */
	private String name;
	/**
	 * 厂商信息，待扩展。
	 */
	private Vendor vendor;
	// DW:网监 DE：默认的版本 或者没有
	private String pline;

	private String locale;
//	// 内部版本号
//	private String build;
//	private String date;

	/**
	 * 获取name
	 * 
	 * @return the name
	 */
	@XmlElement(name = "name")
	public String getName() {
		return name;
	}

	/**
	 * 设置name
	 * 
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取vendor
	 * 
	 * @return the vendor
	 */
	public Vendor getVendor() {
		return vendor;
	}

	/**
	 * 设置vender
	 * 
	 * @param vendor
	 *            the vendor to set
	 */
	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	/**
	 * 获取locale
	 * 
	 * @return the locale
	 */
	@XmlElement(name = "locale")
	public String getLocale() {
		return locale;
	}

	/**
	 * 设置locale
	 * 
	 * @param locale
	 *            the locale to set
	 */
	public void setLocale(String locale) {
		this.locale = locale;
	}

	/**
	 * 获取pline
	 * 
	 * @return pline
	 */
	public String getPline() {
		return pline;
	}

	/**
	 * 设置pline
	 * 
	 * @param pline
	 *            String
	 */
	public void setPline(String pline) {
		this.pline = pline;
	}


}

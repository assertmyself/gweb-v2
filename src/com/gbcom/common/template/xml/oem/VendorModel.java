package com.gbcom.common.template.xml.oem;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * 
 * 厂商型号
 */
@XmlRootElement(name = "model")
public class VendorModel {
	private String sysModel;
	private String venModel;
	/**
	 * @return sysModel
	 */
	@XmlAttribute
	public String getSysModel() {
		return sysModel;
	}
	/**
	 * @param sysModel String
	 */
	public void setSysModel(String sysModel) {
		this.sysModel = sysModel;
	}
	/**
	 * @return venModel
	 */
	@XmlAttribute
	public String getVenModel() {
		return venModel;
	}
	/**
	 * @param venModel String
	 */
	public void setVenModel(String venModel) {
		this.venModel = venModel;
	}

}

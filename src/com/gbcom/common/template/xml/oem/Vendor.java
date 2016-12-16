/*******************************************************************************
* Copyright (c) GBCOM
* All rights reserved. This program and the accompanying materials are belong 
* to Shanghai GBCOM Communication Technology Co.Ltd
* http://www.gbcom.com.cn
/*******************************************************************************/
package com.gbcom.common.template.xml.oem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 厂商信息。
 * 
 * <p>
 * @author syz
 * <p>
 * @date 2015-9-1,上午09:21:27
 * <p>
 * @version v1.0.0
 * <p>
 * @see com.gbcom.ccsv3.template.xml.oem.Vendor
 */
@XmlRootElement(name = "vendor")
public class Vendor implements Serializable{

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	//保留
	private String alias;
	//保留
	private String vendorCode;
	
	//oem版本号
	private String version;
	//标志产品
	private String product="CCSV3";
	//oem 图片路径
	private String imgPath="nooem";
	private boolean lit;



	private List<VendorModel> models = new ArrayList<VendorModel>();
	//开通的service
	private List<String> services = new ArrayList<String>();

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the alias
	 */
	public String getAlias() {
		return alias;
	}

	/**
	 * @param alias the alias to set
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}

	

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * @return the services
	 */
	@XmlElementWrapper(name = "services")
	@XmlElement(name = "service")
	public List<String> getServices() {
		return services;
	}

	/**
	 * @param services the services to set
	 */
	public void setServices(List<String> services) {
		this.services = services;
	}

	/**
	 * 设置vendorCode
	 * @param vendorCode String
	 */
	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}

	/**
	 * 设置vendorCode
	 * @return vendorCode
	 */
	public String getVendorCode() {
		return vendorCode;
	}

	/**
	 * 获取models
	 * @return models
	 */
	@XmlElementWrapper(name = "models")
	@XmlElement(name = "model")
	public List<VendorModel> getModels() {
		return models;
	}

	/**
	 * 设置models 
	 * @param models List<VendorModel>
	 */
	public void setModels(List<VendorModel> models) {
		this.models = models;
	}

	/**
	 * 设置product
	 * @param product String
	 */
	public void setProduct(String product) {
		this.product = product;
	}

	/**
	 * 获取product
	 * @return product
	 */
	public String getProduct() {
		return product;
	}

	/**
	 * 设置imgPath
	 * @param imgPath String
	 */
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	/**
	 * 获取imgPath
	 * @return imgPath
	 */
	public String getImgPath() {
		return imgPath;
	}

	/**
	 * 设置lit
	 * @param lit boolean
	 */
	public void setLit(boolean lit) {
		this.lit = lit;
	}

	/**
	 * 获取lit
	 * @return lit
	 */
	public boolean isLit() {
		return lit;
	}
}

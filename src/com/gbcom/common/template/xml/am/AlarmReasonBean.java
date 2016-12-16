/*******************************************************************************
 * Copyright (c) GBCOM
 * All rights reserved. This program and the accompanying materials are belong 
 * to Shanghai GBCOM Communication Technology Co.Ltd
 * http://www.gbcom.com.cn
/*******************************************************************************/
package com.gbcom.common.template.xml.am;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

/**
 * @author xuyin
 * @version 1.0
 */
public class AlarmReasonBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int code;
	
	private String info;

	/**
	 * @return the code
	 */
	@XmlElement(name = "code")
	public int getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * @return the info
	 */
	@XmlElement(name = "info")
	public String getInfo() {
		return info;
	}

	/**
	 * @param info
	 *            the info to set
	 */
	public void setInfo(String info) {
		this.info = info;
	}

}

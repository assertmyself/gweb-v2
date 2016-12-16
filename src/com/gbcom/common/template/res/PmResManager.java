/*******************************************************************************
 * Copyright (c) GBCOM
 * All rights reserved. This program and the accompanying materials are belong 
 * to Shanghai GBCOM Communication Technology Co.Ltd
 * http://www.gbcom.com.cn
/*******************************************************************************/
package com.gbcom.common.template.res;

import java.util.ResourceBundle;

/**
 * 性能模块资源文件
 * 
 * @author SunYanzheng
 * @date 2013-1-5
 * @version v1.0.0
 */
public final class PmResManager {

	/**
	 * constants,资源文件的路径
	 */
	private static final String PROPERTIES_FILE_PATH = "resource/resource_pm";

	/**
	 * Resource bundle
	 */
	private static ResourceBundle bundle = ResourceBundle
			.getBundle(PROPERTIES_FILE_PATH);

	/**
	 * 构造函数
	 * 
	 */
	private PmResManager() {
	}

	/**
	 * 获取中英文资源字符串
	 * 
	 * @param key
	 *            需要查找的资源key
	 * @return 找到的资源字符串
	 */
	public static String getString(String key) {
		String resourceValue = bundle.getString(key);
		if (resourceValue == null || resourceValue.equals("")) {
			return key;
		} else {
			return resourceValue;
		}
	}
}

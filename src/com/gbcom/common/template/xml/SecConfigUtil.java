/**
 * Copyright 2009 GBCOM Co.,Ltd. All rights reserved.
 * GB PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gbcom.common.template.xml;

import com.gbcom.op.properties.Config;
import com.gbcom.op.properties.SimpleConfig;

/**
 * 读取配置文件config\\sec\\sec.properties的类
 * 
 * @author duanxiongwen
 * @version 1.50.00, 2010-4-9
 */
public final class SecConfigUtil {
	/**
	 * 读取sec.properties到config
	 */
	private static Config config;

	/**
	 *单例
	 */
	private static SecConfigUtil instance = new SecConfigUtil();

	/**
	 *获取单例
	 * 
	 * @return 返回单例
	 */
	public static SecConfigUtil getInstance() {
		return instance;
	}

	/**
	 * 配合单例模式，私有化构造方法
	 */
	private SecConfigUtil() {
		// 配合单例模式，私有化构造方法
		config = new SimpleConfig("config/sec/sec.properties");
	}

	// ------------------sec----------------

	/**
	 * 取得authClass的值
	 * 
	 * @return authClass
	 */
	public static String getAuthClass() {
		return getConfig().getString("authClass");
	}


	/**
	 * 取得securityTaskTimeGap的值
	 * 
	 * @return securityTaskTimeGap
	 */
	public static int getSecurityTaskTimeGap() {
		return getConfig().getInt("securityTaskTimeGap");
	}

	/**
	 * @return 配置文件对应的Config
	 */
	private static Config getConfig() {
		if (config == null) {
			getInstance();
		}
		return config;
	}

}
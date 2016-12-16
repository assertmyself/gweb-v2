/**
 * Copyright 2009 GBCOM Co.,Ltd. All rights reserved.
 * GB PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gbcom.common.template.xml;

import com.gbcom.op.properties.Config;
import com.gbcom.op.properties.SimpleConfig;

/**
 * 读取配置文件config\\log\\log.properties的类
 * 
 * @author duanxiongwen
 * @version 1.50.00, 2010-4-9
 */
public final class LogConfigUtil {
	/**
	 * 读取log.properties到config
	 */
	private static Config config;

	/**
	 *单例
	 */
	private static LogConfigUtil instance = new LogConfigUtil();

	/**
	 *获取单例
	 * 
	 * @return 返回单例
	 */
	public static LogConfigUtil getInstance() {
		return instance;
	}

	/**
	 * 配合单例模式，私有化构造方法
	 */
	private LogConfigUtil() {
		// 配合单例模式，私有化构造方法
		config = new SimpleConfig("config/log/log.properties");
	}

	// ---------log--------------
	/**
	 * 取得eachdayhour的值
	 * 
	 * @return eachdayhour
	 */
	public static int getEachDayHour() {
		return getConfig().getInt("eachdayhour");
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
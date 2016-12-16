package com.gbcom.common.template.res;

import java.util.ResourceBundle;

/**
 * 国际化资源管理<br>包含多个模块。
 * 涉及 <code>Util,Common,AM,SEC,SW 等模块
 * 
 * @author SunYanzheng
 * @date 下午2:36:40
 * @version v1.0.0
 * @see AmSecSwResourceManager
 */
public final class AmSecSwResourceManager {
	/**
	 * constants,资源文件的路径	 */
	public static final String PROPERTIES_FILE_PATH = "resource/resource_amsecsw";

	/**
	 * Resource bundle
	 */
	private static ResourceBundle bundle = ResourceBundle
			.getBundle(PROPERTIES_FILE_PATH);

	/**
	 * 构造方法
	 */
	private AmSecSwResourceManager() {

	}

	/**
	 * 获取中英文资源字符串
	 * 
	 * @param key
	 *            需要查找的资源key
	 * @return 找到的资源字符串
	 */
	public static String getString(String key) {
		String value = bundle.getString(key);
		if (value == null || value.equals("")) {
			return key;
		} else {
			return value;
		}
	}
}

package com.gbcom.common.template.res;

import java.util.ResourceBundle;

/**
 * Basic模块资源管理器
 * 
 * @author SunYanzheng
 * @date 下午2:10:09
 * @version v1.0.0
 * @see BasicResManager
 */
public class BasicResManager {
	// 文件路径：
	private static final String PROPERTIES_FILE_PATH = "resource/resource_basic";
	// 获取JAVA配置资源文件ResourceBundle
	private static ResourceBundle bundle = ResourceBundle
			.getBundle(PROPERTIES_FILE_PATH);

	
	/**
	 * // 提供静态方法：非单例形式
	 * @param key String
	 * @return String
	 */
	public static String getString(String key) {
		if (bundle == null) {
			bundle = ResourceBundle.getBundle(PROPERTIES_FILE_PATH);
		}
		String value;
		try {
			value = bundle.getString(key);
		} catch (Exception e) {
			e.printStackTrace();
			return key;
		}
		return (value == null || value.equals("")) ? key : value;
	}

}

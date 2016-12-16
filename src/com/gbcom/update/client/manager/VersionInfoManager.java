package com.gbcom.update.client.manager;

import java.util.HashMap;
import java.util.Map;
import com.gbcom.update.common.VersionInfo;

/**
 * 用于缓存从服务器端获取的最新版本信息
 * 
 * @author 版本信息管理器
 * 
 */
public class VersionInfoManager {

	/**
	 * 缓存map，key : 产品 ，value : 产品详细信息
	 */
	private static Map<String, VersionInfo> versionCache = null;

	/**
	 * 单例
	 */
	private static VersionInfoManager instance = new VersionInfoManager();

	/**
	 * 私有构造器
	 */
	private VersionInfoManager() {
		init();
	}

	/**
	 * 单例模式
	 * 
	 * @return VersionInfoManager
	 */
	public static synchronized VersionInfoManager getInstance() {
		if (instance == null) {
			instance = new VersionInfoManager();
		}
		return instance;
	}

	private void init() {
		if (versionCache == null) {
			versionCache = new HashMap<String, VersionInfo>();
		}
	}

	/**
	 * @param product
	 *            产品
	 * @param info
	 *            版本信息
	 */
	public void put(String product, VersionInfo info) {
		versionCache.put(product, info);
	}

	/**
	 * @param product
	 *            产品
	 * @return 版本信息
	 */
	public VersionInfo get(String product) {
		return versionCache.get(product);
	}
}

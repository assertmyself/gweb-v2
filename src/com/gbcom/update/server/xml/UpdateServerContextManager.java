package com.gbcom.update.server.xml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import com.gbcom.op.util.xml.XStreamUtil;
import com.gbcom.update.common.VersionInfo;
import com.hc.core.utils.ReflectionUtils;

/**
 * 服务器端产品信息控制器
 * 
 * @author doujun
 * 
 */
/**
 * @author Administrator
 * 
 */
public class UpdateServerContextManager {
	/**
	 * 日志记录器
	 */
	private static final Logger LOG = Logger
			.getLogger(UpdateServerContextManager.class);
	/**
	 * key : 版本号 value : 版本信息
	 */
	private Map<String, VersionInfo> versionInfoMap = null;
	/**
	 * key : 版本号 value ：详细版本信息
	 */
	private Map<String, UpdateServer> updateServerMap = null;
	/**
	 * context
	 */
	private UpdateServerContext context = null;
	/**
	 * 服务器端版本信息配置文件相对地址
	 */
	private static final String SERVER_SITE_PATH = "config/update/server_site.xml";
	/**
	 * 单例
	 */
	private static UpdateServerContextManager instance = new UpdateServerContextManager();

	
	
	/**
	 * 私有构造器
	 */
	private UpdateServerContextManager() {
		init();
	}

	/**
	 * 返回单例
	 * 
	 * @return 单例
	 */
	public static synchronized UpdateServerContextManager getInstance() {
		if (instance == null) {
			instance = new UpdateServerContextManager();
		}
		return instance;
	}

	/**
	 * 初始化到缓存中
	 */
	private void init() {
		try {
			if (versionInfoMap == null) {
				versionInfoMap = new HashMap<String, VersionInfo>();
			}
			if (updateServerMap == null) {
				updateServerMap = new HashMap<String, UpdateServer>();
			}
			Class<?>[] classContext = new Class<?>[] {
					UpdateServerContext.class, UpdateServer.class,
					FilterRule.class, ZipRule.class };
			context = XStreamUtil.fromXML(
					UpdateServerContext.class, SERVER_SITE_PATH, classContext);
			if (context != null) {
				List<UpdateServer> updateServerList = context.getList();
				for (int i = 0; i < updateServerList.size(); i++) {
					UpdateServer updateServer = updateServerList.get(i);
					// 这里可以考虑直接使用UpdateServer,而不使用VersionInfo
					VersionInfo versionInfo = new VersionInfo();
					ReflectionUtils.copyBean(updateServer, versionInfo,
							new String[] { "name", "product", "version", "no",
									"date", "method" });
					versionInfoMap.put(updateServer.getProduct(), versionInfo);
					updateServerMap
							.put(updateServer.getProduct(), updateServer);
				}
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
	}

	/**
	 * 根据版本号获取版本信息bean
	 * 
	 * @param product
	 *            版本号
	 * @return 版本信息
	 */
	public VersionInfo getVersionInfo(String product) {
		return versionInfoMap.get(product);
	}

	/**
	 * 根据版本号获取服务器端详细版本信息
	 * 
	 * @param product
	 *            版本号
	 * @return 版本信息
	 */
	public UpdateServer getUpdateServer(String product) {
		return updateServerMap.get(product);
	}

	/**
	 * 返回版本信息map
	 * 
	 * @return 版本信息map
	 */
	public Map<String, VersionInfo> getVersionInfoMap() {
		return versionInfoMap;
	}

	/**
	 * 返回详细版本信息map
	 * 
	 * @return 详细版本信息map
	 */
	public Map<String, UpdateServer> getUpdateServerMap() {
		return updateServerMap;
	}
	/**
	 * 获取上下文
	 * @return context
	 */
	public UpdateServerContext getUpdateServerContext(){
		return context;
	}

	/**
	 * 返回所有的版本号
	 * 
	 * @return 版本号集合
	 */
	public List<String> getProducts() {
		return new ArrayList<String>(versionInfoMap.keySet());
	}

	/**
	 * 获取压缩规则
	 * 
	 * @param product
	 *            产品
	 * @return 规则集合
	 */
	public List<ZipRule> getZipRule(String product) {
		UpdateServer update = getUpdateServer(product);
		if (update == null) {
			return new ArrayList<ZipRule>();
		}
		return update.getZipRules();
	}

	/**
	 * 获取压缩规则
	 * 
	 * @param product
	 *            产品
	 * @return 规则集合
	 */
	public List<String> getExcludes(String product) {
		List<ZipRule> zipRules = getZipRule(product);
		List<String> rules = new ArrayList<String>();
		for (ZipRule zipRule : zipRules) {
			if (zipRule.getExclude() == null || zipRule.getExclude().equals("")) {
				continue;
			}
			rules.add(zipRule.getExclude());
		}
		return rules;
	}

	/**
	 * 获取压缩规则
	 * 
	 * @param product
	 *            产品
	 * @return 规则集合
	 */
	public List<String> getIncludes(String product) {
		List<ZipRule> zipRules = getZipRule(product);
		List<String> rules = new ArrayList<String>();
		for (ZipRule zipRule : zipRules) {
			if (zipRule.getInclude() == null || zipRule.getInclude().equals("")) {
				continue;
			}
			rules.add(zipRule.getInclude());
		}
		return rules;
	}

	/**
	 * 获取过滤规则
	 * 
	 * @param product
	 *            产品
	 * @return 规则集合
	 */
	public List<FilterRule> getFilterRules(String product) {
		UpdateServer update = getUpdateServer(product);
		if (update == null) {
			return new ArrayList<FilterRule>();
		}
		return update.getFilterRules();
	}
}

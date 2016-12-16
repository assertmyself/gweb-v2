package com.gbcom.update.client.xml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.gbcom.op.util.xml.XStreamUtil;
import com.gbcom.update.common.VersionInfo;
import com.hc.core.utils.ReflectionUtils;

/**
 * 客户端产品信息管理器
 * 
 * @author doujun
 * @date 2015/11/16
 * 
 */
public class UpdateClientContextManager {
	/**
	 * 日志记录器
	 */
	private static final Logger LOG = Logger
			.getLogger(UpdateClientContextManager.class);
	/**
	 * 版本信息map
	 * 
	 * key ： 产品 value : 版本信息
	 */
	private static Map<String, VersionInfo> versionInfoMap = null;
	/**
	 * 详细版本信息map
	 * 
	 * key : 产品 value : 版本信息
	 */
	private static Map<String, UpdateClient> updateClientMap = null;
	/**
	 * 文件过滤 key: 产品 value: 过滤条件
	 */
	private static Map<String, List<FileFilter>> fileFilterMap = null;

	private UpdateClientContext updateClientContext = null;
	private static final String CLIENT_SITE_PATH = "config/update/client_site.xml";
	/**
	 * 单例
	 */
	private static UpdateClientContextManager instance = new UpdateClientContextManager();

	/**
	 * 私有构造器
	 */
	private UpdateClientContextManager() {
		init();
	}

	/**
	 * 获取单例
	 * 
	 * @return 单例
	 */
	public static synchronized UpdateClientContextManager getInstance() {
		if (instance == null) {
			instance = new UpdateClientContextManager();
		}
		return instance;
	}

	/**
	 * 初始化
	 */
	private void init() {
		try {
			if (versionInfoMap == null) {
				versionInfoMap = new HashMap<String, VersionInfo>();
			}
			if (updateClientMap == null) {
				updateClientMap = new HashMap<String, UpdateClient>();
			}
			if (fileFilterMap == null) {
				fileFilterMap = new HashMap<String, List<FileFilter>>();
			}
			Class<?>[] classContext = new Class<?>[] {
					UpdateClientContext.class, UpdateClient.class,
					FileFilter.class };
			updateClientContext = XStreamUtil.fromXML(
					UpdateClientContext.class, CLIENT_SITE_PATH, classContext);
			if (updateClientContext != null) {
				UpdateClient updateClient = updateClientContext
						.getUpdateClient();
				VersionInfo versionInfo = new VersionInfo();
				ReflectionUtils.copyBean(updateClient, versionInfo,
						new String[] { "name", "product", "version", "no",
								"date", "method" });
				LOG.info(versionInfo.toString());
				versionInfoMap.put(updateClient.getProduct(), versionInfo);
				updateClientMap.put(updateClient.getProduct(), updateClient);
				fileFilterMap.put(updateClient.getProduct(), updateClient
						.getFileFilters());
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
	}

	/**
	 * 获取版本信息
	 * 
	 * @param product
	 *            String
	 * @return 版本信息
	 */
	public VersionInfo getVersionInfo(String product) {
		return versionInfoMap.get(product);
	}

	/**
	 * 获取详细版本信息
	 * 
	 * @param product
	 *            String
	 * @return 详细版本信息
	 */
	public UpdateClient getupdateClient(String product) {
		return updateClientMap.get(product);
	}

	/**
	 * 获取版本信息map
	 * 
	 * @return 版本信息map
	 */
	public Map<String, VersionInfo> getVersionInfoMap() {
		return versionInfoMap;
	}

	/**
	 * 获取详细版本信息map
	 * 
	 * @return 详细版本信息map
	 */
	public Map<String, UpdateClient> getUpdateClientMap() {
		return updateClientMap;
	}

	/**
	 * 获取产品名称
	 * 
	 * @return 产品名称
	 */
	public String getUpdateProduct() {
		List<String> products = new ArrayList<String>(versionInfoMap.keySet());
		if (products.size() > 0) {
			return products.get(0);
		}
		return "";
	}

	/**
	 * 获取产品过滤条件
	 * 
	 * @param product
	 *            产品
	 * @return 文件过滤条件集合
	 */
	public List<FileFilter> getFileFilter(String product) {
		return fileFilterMap.get(product);
	}

	/**
	 * 获取client_site.xml配置文件信息
	 * 
	 * @return UpdateClientContext
	 */
	public UpdateClientContext getUpdateClientContext() {
		return updateClientContext;
	}

	/**
	 * 将内存中的信息同步到文件中
	 * 
	 */
	public void save() {
		try {
			Class<?>[] classContext = new Class<?>[] {
					UpdateClientContext.class, UpdateClient.class,
					FileFilter.class };
			String filePath = this.getClass().getClassLoader().getResource(
					CLIENT_SITE_PATH).getPath();
			LOG.info("client_xml path :" + filePath);
			XStreamUtil.toXML(updateClientContext, filePath, classContext);
		} catch (Exception e) {
			LOG.error("client_xml parse failed !", e);
			e.printStackTrace();
		}
	}

	/**
	 * 测试
	 * 
	 * @param args String[]
	 */
	public static void main(String[] args) {
		System.out.println(UpdateClientContextManager.getInstance()
				.getUpdateClientContext().getUpdateClient().getDate());
		UpdateClientContextManager.getInstance().getUpdateClientContext()
				.getUpdateClient().setDate("20151125");
		UpdateClientContextManager.getInstance().save();
	}

}

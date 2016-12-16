package com.gbcom.system.manager;

/**
 * 系统配置 User: Intellj Date: 12-3-21
 */
public class ConfigManager {
	private String siteName; // 系统名称
	private String filePath; // 附件存储路径

	private String baiduApiKey; // 百度云推送key
	private String baiduSecretKey; // 百度云推送key

	private String dbFilePath; // DB备份文件路径
	private String appFilePath; // APP版本文件路径

	/**
	 * @return siteName
	 */
	public String getSiteName() {
		return siteName;
	}

	/**
	 * @param siteName
	 *            String
	 */
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	/**
	 * @return filePath
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * @param filePath
	 *            String
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * @return baiduApiKey
	 */
	public String getBaiduApiKey() {
		return baiduApiKey;
	}

	/**
	 * @param baiduApiKey
	 *            String
	 */
	public void setBaiduApiKey(String baiduApiKey) {
		this.baiduApiKey = baiduApiKey;
	}

	/**
	 * @return baiduSecretKey
	 */
	public String getBaiduSecretKey() {
		return baiduSecretKey;
	}

	/**
	 * @param baiduSecretKey
	 *            String
	 */
	public void setBaiduSecretKey(String baiduSecretKey) {
		this.baiduSecretKey = baiduSecretKey;
	}

	/**
	 * 
	 * @return dbFilePath
	 */
	public String getDbFilePath() {
		return this.dbFilePath;
	}

	/**
	 * 
	 * @param dbFilePath
	 *            String
	 */
	public void setDbFilePath(String dbFilePath) {
		this.dbFilePath = dbFilePath;
	}

	/**
	 * 
	 * @return appFilePath
	 */
	public String getAppFilePath() {
		return this.appFilePath;
	}

	/**
	 * 
	 * @param appFilePath
	 *            String
	 */
	public void setAppFilePath(String appFilePath) {
		this.appFilePath = appFilePath;
	}

}

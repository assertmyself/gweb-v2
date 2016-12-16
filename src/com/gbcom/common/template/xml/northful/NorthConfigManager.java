package com.gbcom.common.template.xml.northful;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gbcom.system.utils.XmlFileUtil;

/**
 * 北向配置管理类
 * 
 * 入口判断
 * 
 * @author qinyeju
 * @date 2015-9-29
 */
public class NorthConfigManager {
	/**
	 * 日志
	 */
	private static final Logger LOG = LoggerFactory.getLogger(NorthConfigManager.class);
	/**
	 * 单例对象
	 */
	private static NorthConfigManager instance = new NorthConfigManager();
	
	private NorthConfig northConfig;
	
	/**
	 * 获取单例
	 * @return NorthConfigManager
	 */
	public static NorthConfigManager getInstance() {
		return instance;
	}
	
	private NorthConfigManager(){
		loadNorthConfigFile();
	}
	
	/**
	 * 解析文件
	 */
	private void loadNorthConfigFile() {
		String filePath = this.getClass().getClassLoader()
				.getResource("/config/north/north_config.xml").getFile();
		File file = new File(filePath);
		if (file.exists()) {
			try {
				northConfig = XmlFileUtil.unmarshallerObjectFromXml(NorthConfig.class, file);
			} catch (Exception e) {
				LOG.error("failed to parse north_config.xml",e);
				System.exit(0);
			}
		} else {
			LOG.error("north_config.xml can't be found");
			System.exit(0);
		}

	}
	/**
	 * 获取配置信息
	 * @return NorthConfig
	 */
	public NorthConfig getNorthConfig(){
		return northConfig;
	}
	
	
	/**
	 * : (NorthConfigManager.isContains)
	 * @param vendor String
	 * @return boolean
	 */
	public boolean isContains(String vendor){
		return northConfig.getList().contains(vendor);
	}
}

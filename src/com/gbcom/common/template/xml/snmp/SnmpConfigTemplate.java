package com.gbcom.common.template.xml.snmp;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * SNMP基本配置信息
 * 
 * @author SunYanzheng
 * @date 下午3:14:18
 * @version v1.0.0
 * @see SnmpConfigTemplate
 */
@XStreamAlias("SnmpConfig")
public class SnmpConfigTemplate {
	/**
	 * vendor
	 */
	@XStreamAlias("vendor")
	private String vendor = "gbcom";
	/**
	 * 
	 */
	@XStreamAlias("defaultMib")
	private String defaultMib = "defaultMib";
	/**
	 * 
	 */
	@XStreamAlias("heartCacheKey")
	private String heartCacheKey = "1.3.6.1.4.1.28723.12.10.1.11";

	/**
	 * rootOID
	 */
	@XStreamAlias("rootOID")
	private String rootOID="1.3.6.1.4.1.28723";

	/**
	 * community
	 */
	@XStreamAlias("community")
	private String community ="public";
	
	/**
	 * @return vendor
	 */
	public String getVendor() {
		return vendor;
	}

	/**
	 * @param vendor
	 *            String
	 */
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	/**
	 * @return rootOID
	 */
	public String getRootOID() {
		return rootOID;
	}

	/**
	 * @param rootOID
	 *            String
	 */
	public void setRootOID(String rootOID) {
		this.rootOID = rootOID;
	}

	/**
	 * @return community
	 */
	public String getCommunity() {
		return community;
	}

	/**
	 * @param community
	 *            String
	 */
	public void setCommunity(String community) {
		this.community = community;
	}

	/**
	 * : (SnmpConfigTemplate.setDefaultMib)
	 * @param defaultMib String
	 */
	public void setDefaultMib(String defaultMib) {
		this.defaultMib = defaultMib;
	}

	/**
	 * : (SnmpConfigTemplate.getDefaultMib)
	 * @return String
	 */
	public String getDefaultMib() {
		return defaultMib;
	}

	/**
	 * : (SnmpConfigTemplate.setHeartCacheKey)
	 * @param heartCacheKey String
	 */
	public void setHeartCacheKey(String heartCacheKey) {
		this.heartCacheKey = heartCacheKey;
	}

	/**
	 * : (SnmpConfigTemplate.getHeartCacheKey)
	 * @return String
	 */
	public String getHeartCacheKey() {
		return heartCacheKey;
	}

}

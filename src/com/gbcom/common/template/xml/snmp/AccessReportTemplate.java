package com.gbcom.common.template.xml.snmp;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * AP上报oid 模板
 * 
 * @author SunYanzheng
 * @date 下午1:35:17
 * @version v1.0.0
 * @see AccessReportTemplate
 */
@XStreamAlias("AccessReportTemplate")
public class AccessReportTemplate extends BaseReportTemplate {

	/**
	 * 发现类型
	 */
	@XStreamAlias("sysMacAddress")
	public String sysMacAddress;
	/**
	 * CPE设备工作模式： 1:bridge 2:route
	 */
	@XStreamAlias("sysIpAddress")
	public String sysIpAddress;

	/**
	 * 管理状态
	 */
	@XStreamAlias("sysManageState")
	public String sysManageState;

	/**
	 * 设备型号，内部ID
	 */
	@XStreamAlias("modelId")
	public String modelId;
	/**
	 * 设备型号内部名称
	 */
	@XStreamAlias("modelName")
	public String modelName;
	/**
	 * 设备型号（oem之后）
	 */
	@XStreamAlias("sysModel")
	public String sysModel;
	/**
	 * CPE设备类型
	 */
	@XStreamAlias("hardwareType")
	public String hardwareType;
	/**
	 * CPE设备类型
	 */
	@XStreamAlias("boardVersion")
	public String boardVersion;

	/**
	 * 真实软件版本
	 */
	@XStreamAlias("softwareVersion")
	public String softwareVersion;
	/**
	 * 真实软件版本
	 */
	@XStreamAlias("oemSwVersion")
	public String oemSwVersion;

	/**
	 * 关联MAC地址
	 */
	@XStreamAlias("hardwareVersion")
	public String hardwareVersion;

	/**
	 * AP信号强度：dbm
	 */
	@XStreamAlias("assocRssi")
	public String assocRssi;
	/**
	 * CPE所在信道
	 */
	@XStreamAlias("radioChannelUsing")
	public String radioChannelUsing;
	/**
	 * devType
	 */
	@XStreamAlias("devType")
	public String devType;
	/**
	 * sysHotId
	 */
	@XStreamAlias("sysHotId")
	public String sysHotId;
	/**
	 * sysName
	 */
	@XStreamAlias("sysName")
	public String sysName;

	/**
	 * 配置号
	 */
	@XStreamAlias("tplSequence")
	public String tplSequence;

	/**
	 * 工作模式
	 */
	@XStreamAlias("workMode")
	public String workMode;
}

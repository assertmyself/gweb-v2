package com.gbcom.common.template.xml.snmp;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 文件传输结果trap
 * @author SunYanzheng
 * @date 下午1:35:17
 * @version v1.0.0
 * @see FileTransReportTemplate
 */
@XStreamAlias("FileTransReportTemplate")
public class FileTransReportTemplate extends BaseReportTemplate{
	
	/**
	 * 上报MAC
	 */
	@XStreamAlias("sysMacAddress")
	public String sysMacAddress;
	
	/**
	 * 软件版本
	 */
	@XStreamAlias("softwareVersion")
	public String softwareVersion;
	/**
	 * 软件版本
	 */
	@XStreamAlias("targetVersion")
	public String targetVersion;


	
	/**
	 * 升级状态  :1: success(1) 
 	2: get-file-fail(2) 
 	3: file-wrong(3) 
 	4: flash-error(4) 
	 */
	@XStreamAlias("transStatus")
	public String transStatus;
	/**
	 * 文件类型   1：ver 2：config 3：diagnose 4：other
	 */
	@XStreamAlias("apFileOperType")
	public String apFileOperType;
	

}

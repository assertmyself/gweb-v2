//off checkstyle
package com.gbcom.common.template.xml.sys;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
/**
 * 设备型号映射：oem设备型号为key
 * @author SunYanzheng
 * @date 2014-5-29
 * @version v1.0.0
 * @see com.gbcom.omc.ccsv2.common.template.config.ApSysModel
 */
@XStreamAlias("ApSysModel")
public class ApSysModel {
	@XStreamAsAttribute
	private String sysModel;
	//key
	@XStreamAsAttribute
	private String modelId;
    
	@XStreamAsAttribute
    private int hardwareType;
	@XStreamAsAttribute
    private int boardVersion;
	@XStreamAsAttribute
	private boolean dev5g;
	@XStreamAsAttribute
	private String desc;
	public int getHardwareType() {
		return hardwareType;
	}
	public void setHardwareType(int hardwareType) {
		this.hardwareType = hardwareType;
	}
	public int getBoardVersion() {
		return boardVersion;
	}
	public void setBoardVersion(int boardVersion) {
		this.boardVersion = boardVersion;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getSysModel() {
		return sysModel;
	}
	public void setSysModel(String sysModel) {
		this.sysModel = sysModel;
	}
	public String getModelId() {
		return modelId;
	}
	public void setModelId(String modelID) {
		this.modelId = modelID;
	}
	public boolean isDev5g() {
		return dev5g;
	}
	public void setDev5g(boolean dev5g) {
		this.dev5g = dev5g;
	}
    


}

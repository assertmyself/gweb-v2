//off checkstyle
package com.gbcom.common.template.xml.sys;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * 每个类型 都有目标型号
 * 
 * @author SunYanzheng
 * @date 上午11:03:03
 * @version v1.0.0
 * @see TargetVer
 */
@XStreamAlias("TargetVer")
public class TargetVer {
	/**
	 * 设备类型号：CPE上报为准
	 *        1.CPE-2.4G
	 *        2.CPE-5G
	 */
	@XStreamAsAttribute
	private String deType;
	
	/**
	 * 软件平台类型
	 *        1.cpe-ta40
	 *        2.cpe-ta20
	 */
	@XStreamAsAttribute
	private String verType;
	@XStreamAsAttribute
	private String tarVer;
	@XStreamAsAttribute
	private String desc;
	
	public String getDeType() {
		return deType;
	}
	public void setDeType(String deType) {
		this.deType = deType;
	}
	public String getVerType() {
		return verType;
	}
	public void setVerType(String verType) {
		this.verType = verType;
	}
	public String getTarVer() {
		return tarVer;
	}
	public void setTarVer(String tarVer) {
		this.tarVer = tarVer;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
}

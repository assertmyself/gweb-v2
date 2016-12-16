package com.gbcom.common.template.xml.snmp;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * BaseReportTemplate，上报模板基类
 * @author SunYanzheng
 * @date 2014-7-24
 * @version v1.0.0
 * @see com.gbcom.omc.lte.common.bean.template.snmp.BaseReportTemplate
 */
public class BaseReportTemplate {
	/**
	 * 关联状态：内部属性
	 */
	@XStreamAlias("relative")
	public boolean relative;
	/**
	 * isRelative
	 * @return boolean
	 */
	public boolean isRelative() {
		return relative;
	}
	/**
	 * setRelative
	 * @param relative boolean
	 */
	public void setRelative(boolean relative) {
		this.relative = relative;
	}
	
}

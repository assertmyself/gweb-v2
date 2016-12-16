package com.gbcom.common.template.xml.snmp;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * SNMP基本配置信息列表
 * @author SunYanzheng
 * @date 下午3:14:18
 * @version v1.0.0
 * @see SnmpContextTemplate
 */
@XStreamAlias("SnmpContext")
public class SnmpContextTemplate {
	private List<SnmpConfigTemplate> list =null;
	/**
	 * 获取snmp配置列表
	 * @return List<SnmpConfigTemplate>
	 */
	public List<SnmpConfigTemplate> getList() {
		return list;
	}
	/**
	 * 设置snmp配置列表
	 * @param list List<SnmpConfigTemplate>
	 */
	public void setList(List<SnmpConfigTemplate> list) {
		this.list = list;
	}
	
}

package com.gbcom.common.template.xml.snmp;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * L2TP{@docRoot 是一种工业标准的Internet隧道协议}上报模板 {@link com.gbcom.omc.lte.server.service.template.SnmpTemplateFactory}
 * <manageMac>.14.2.1.4.2</manageMac>
	<l2tpClientIp>.14.2.4.2</l2tpClientIp>
 * 
 * @author SunYanzheng
 * @date 下午1:35:17
 * @version v1.0.0
 * @see L2tpReportTemplate
 */
@XStreamAlias("L2tpReportTemplate")
public class L2tpReportTemplate extends BaseReportTemplate  {
	/**
	 * 管理Mac
	 */
	@XStreamAlias("manageMac")
	public String manageMac;
	/**
	 * 服务器ip地址
	 */
	@XStreamAlias("l2tpServerIp")
	public String l2tpServerIp;
	
	/**
	 * 用户名
	 */
	@XStreamAlias("l2tpUsrName")
	public String l2tpUsrName;
	
	/**
	 * 密码
	 */
	@XStreamAlias("l2tpUsrPass")
	public String l2tpUsrPass;
	
	/**
	 * 客户端ip地址
	 */
	@XStreamAlias("l2tpClientIp")
	public String l2tpClientIp;
	/**
	 * 客户端状态
	 */
	@XStreamAlias("l2tpClientStatue")
	public String l2tpClientStatue;
	/**
	 * 客户端信息
	 */
	@XStreamAlias("l2tpClientMsg")
	public String l2tpClientMsg;

}

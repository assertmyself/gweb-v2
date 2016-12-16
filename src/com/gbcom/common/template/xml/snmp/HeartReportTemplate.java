package com.gbcom.common.template.xml.snmp;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 心跳上报模板{@link com.gbcom.ccsv3.template.xml.snmp.SnmpTempManager.server.service.template.SnmpTemplateFactory}
 * <manageMac>.14.2.1.4.2</manageMac>
	<lteSignalRssi>.14.2.2.2.4</lteSignalRssi>
	<lteSignalRsrp>.14.2.2.2.5</lteSignalRsrp>
	<lteSignalRsrq>.14.2.2.2.6</lteSignalRsrq>
	<lteSignalSinr>.14.2.2.2.7</lteSignalSinr>
 * 
 * @author SunYanzheng
 * @date 下午1:35:17
 * @version v1.0.0
 * @see HeartReportTemplate
 */
@XStreamAlias("HeartReportTemplate")
public class HeartReportTemplate extends BaseReportTemplate{
	/**
	 * 管理Mac
	 */
	@XStreamAlias("sysMacAddress")
	public String sysMacAddress;
	/**
	 * 
	 */
	@XStreamAlias("channelUsing")
	public String channelUsing;

	/**
	 * 
	 */
	@XStreamAlias("transmitPower")
	public String transmitPower;

	/**
	 * 
	 */
	@XStreamAlias("wifi5ChannelUsing")
	public String wifi5ChannelUsing;

	/**
	 * 
	 */
	@XStreamAlias("wifi5TransmitPower")
	public String wifi5TransmitPower;
	/**
	 * 
	 */
	@XStreamAlias("tplSequence")
	public String tplSequence;
	
}

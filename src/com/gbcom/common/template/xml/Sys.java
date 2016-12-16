package com.gbcom.common.template.xml;

import com.gbcom.common.template.xml.guide.DeviceInfoCenter;
import com.gbcom.common.template.xml.jobm.JobWrapperManager;
import com.gbcom.common.template.xml.mail.MailSender;
import com.gbcom.common.template.xml.msg.MsgSender;
import com.gbcom.common.template.xml.oem.OemManager;
import com.gbcom.common.template.xml.snmp.MibNodeManager;
import com.gbcom.common.template.xml.snmp.SnmpTempManager;
import com.gbcom.common.template.xml.sys.CfgListManager;
import com.gbcom.common.template.xml.sys.SysConfigManager;
import com.gbcom.common.template.xml.tpl.TplInfoManager;
import com.gbcom.update.client.xml.UpdateClientContextManager;
import com.gbcom.update.server.xml.UpdateServerContextManager;

/**
 * 获取配置单例对象。。
 * 
 * 需要在初始化之后使用。。。。
 * 
 * @author SunYanzheng
 * @date 2014-10-9,下午04:08:21
 * @version v1.0.0
 * @see com.gbcom.ccsv3.template.xml.Sys
 */
public class Sys {

	/**
	 * 动态信息采集中心
	 * 
	 * @return DeviceInfoCenter
	 */
	public static DeviceInfoCenter guideCenter() {
		return DeviceInfoCenter.getInstance();
	}

	/**
	 * 系统配置 sac-config
	 * 
	 * @return SysConfigManager
	 */
	public static SysConfigManager sysCfgM() {
		return SysConfigManager.getInstance();
	}

	/**
	 * 下发列表 cfg-list
	 * 
	 * @return CfgListManager
	 */
	public static CfgListManager cfgListM() {
		return CfgListManager.getInstance();
	}

	/**
	 * job.xml
	 * 
	 * @return JobWrapperManager
	 */
	public static JobWrapperManager jobM() {
		return JobWrapperManager.getInstance();
	}

	/**
	 * mail.xml
	 * 
	 * @return MailSender
	 */
	public static MailSender mailS() {
		return MailSender.getInstance();
	}

	/**
	 * msg.xml
	 * 
	 * @return MsgSender
	 */
	public static MsgSender msgS() {
		return MsgSender.getInstance();
	}

	/**
	 * oem.xml
	 * 
	 * @return OemManager
	 */
	public static OemManager oemM() {
		return OemManager.getInstance();
	}

	/**
	 * snmp相关
	 * 
	 * @return SnmpTempManager
	 */
	public static SnmpTempManager snmpM() {
		return SnmpTempManager.getInstance();
	}

	/**
	 * mib.node
	 * 
	 * @return MibNodeManager
	 */
	public static MibNodeManager mibNodeM() {
		return MibNodeManager.getInstance();
	}

	/**
	 * log.xml
	 * 
	 * @return LogConfigUtil
	 */
	public static LogConfigUtil logU() {
		return LogConfigUtil.getInstance();
	}

	/**
	 * sec-operator
	 * 
	 * @return SecConfigUtil
	 */
	public static SecConfigUtil secU() {
		return SecConfigUtil.getInstance();
	}

	/**
	 * @return TplInfoManager
	 */
	public static TplInfoManager tplM() {
		return TplInfoManager.getInstance();
	}
	
	/**
	 * update-server.site
	 * @return UpdateServerContextManager
	 */
	public static UpdateServerContextManager updateSM(){
		return UpdateServerContextManager.getInstance();
	}
	
	/**
	 * 
	 * @return UpdateClientContextManager
	 */
	public static UpdateClientContextManager updateCM(){
		return UpdateClientContextManager.getInstance();
	}
}

//off checkstyle
package com.gbcom.common.template.xml.sys;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
/**
 * CMS基本配置
 * 
 * @author SunYanzheng
 * @date 上午11:10:20
 * @version v1.0.0
 * @see SacConfig
 */
@XStreamAlias("SacConfig")
public class SacConfig {
    // 标记为节点属性
    @XStreamAsAttribute
    protected String xmlns = "http://s3.amazonaws.com/doc/2006-03-01";

    @XStreamAlias("Port")
    private String port;
    /**
     * 心跳时间
     */
    @XStreamAlias("Heart")
    private int heart;
    /**
     * 心跳时间
     */
    @XStreamAlias("ActThreshold")
    private int actThreshold;
    /**
     * 心跳时间
     */
    @XStreamAlias("ClearThreshold")
    private int clearThreshold;
    
    /**
     * 接入数目
     */
    @XStreamAlias("Number")
    private int number;
    
    /**
     * 心跳时间
     */
    @XStreamAlias("ReportInterval")
    private int reportInterval;
    /**
     * 离线门限，，即连续离线多少次 就显示为离线
     */
    @XStreamAlias("OffLineThreshold")
    private int offLineThreshold;
    /**
     * 信号质量获取间隔，，即多少次心跳获取信号质量
     */
    @XStreamAlias("LQInterval")
    private int lqInterval;
    
    /**
     * snmp Ip
     */
    @XStreamAlias("SnmpIp")
    private String snmpIp;
    
    /**
     * 指标不清晰，可以该在流程中处理
     * 
     * @XStreamAlias("AccessRule")
     * public String accessRule;
     */
    /**
     * 内部告警
     */
    @XStreamAlias("InnerAlarm")
    private boolean innerAlarm;
    /**
     * tr069
     */
    @XStreamAlias("Tr069Switch")
    private boolean tr069Switch;
    /**
     * tr069
     */
    @XStreamAlias("MultiDiscover")
    private boolean multiDiscover;
    
    @XStreamAlias("Ftp")
    private FtpConfig ftp;
    @XStreamAlias("L2tp")
    private L2tpConfig L2tp;
    


	public FtpConfig getFtp() {
		return ftp;
	}

	public void setFtp(FtpConfig ftp) {
		this.ftp = ftp;
	}

	@XStreamAlias("AutoUpgrade")
    private boolean autoUpgrade;
	@XStreamAlias("AutoConfig")
	private boolean autoConfig;
    
    @XStreamAlias("TargetVerList")
    private List<TargetVer> targetVers;

    

    
    /**
     * 可管理类型
     */
    @XStreamAlias("ApSysModelList")
    private List<ApSysModel> devices ;

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public int getHeart() {
		return heart;
	}

	public void setHeart(int heart) {
		this.heart = heart;
	}


	public List<TargetVer> getTargetVers() {
		return targetVers;
	}

	public void setTargetVers(List<TargetVer> targetVer) {
		this.targetVers = targetVer;
	}



	public boolean isAutoUpgrade() {
		return autoUpgrade;
	}

	public void setAutoUpgrade(boolean autoUpgrade) {
		this.autoUpgrade = autoUpgrade;
	}

	public List<ApSysModel> getDevices() {
		return devices;
	}

	public void setDevices(List<ApSysModel> devices) {
		this.devices = devices;
	}

	public int getReportInterval() {
		return reportInterval;
	}

	public void setReportInterval(int reportInterval) {
		this.reportInterval = reportInterval;
	}

	/**
	 * @return the number
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(int number) {
		this.number = number;
	}

	/**
	 * @return the innerAlarm
	 */
	public boolean isInnerAlarm() {
		return innerAlarm;
	}

	/**
	 * @param innerAlarm the innerAlarm to set
	 */
	public void setInnerAlarm(boolean innerAlarm) {
		this.innerAlarm = innerAlarm;
	}

	public String getSnmpIp() {
		return snmpIp;
	}

	public void setSnmpIp(String snmpIp) {
		this.snmpIp = snmpIp;
	}

	/**
	 * 
	 * @return
	 */
	public int getActThreshold() {
		return actThreshold;
	}

	/**
	 * 
	 * @param actThreshold
	 */
	public void setActThreshold(int actThreshold) {
		this.actThreshold = actThreshold;
	}

	/**
	 * 
	 * @return
	 */
	public int getClearThreshold() {
		return clearThreshold;
	}

	/**
	 * 
	 * @param clearThreshold
	 */
	public void setClearThreshold(int clearThreshold) {
		this.clearThreshold = clearThreshold;
	}

	public int getLqInterval() {
		return lqInterval;
	}

	public void setLqInterval(int lqInterval) {
		this.lqInterval = lqInterval;
	}

	public int getOffLineThreshold() {
		return offLineThreshold;
	}

	public void setOffLineThreshold(int offLineThreshold) {
		this.offLineThreshold = offLineThreshold;
	}

	public void setTr069Switch(boolean tr069Switch) {
		this.tr069Switch = tr069Switch;
	}

	public boolean isTr069Switch() {
		return tr069Switch;
	}

	public void setAutoConfig(boolean autoConfig) {
		this.autoConfig = autoConfig;
	}

	public boolean isAutoConfig() {
		return autoConfig;
	}

	public void setMultiDiscover(boolean multiDiscover) {
		this.multiDiscover = multiDiscover;
	}

	public boolean isMultiDiscover() {
		return multiDiscover;
	}

	public void setL2tp(L2tpConfig l2tp) {
		L2tp = l2tp;
	}

	public L2tpConfig getL2tp() {
		return L2tp;
	}


}

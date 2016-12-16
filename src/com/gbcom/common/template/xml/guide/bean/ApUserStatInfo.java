package com.gbcom.common.template.xml.guide.bean;

import com.gbcom.common.template.xml.guide.DeviceInfo;

/**
 * AP用户配置
 * 
 * <node name="staID" oid=".1.3.1.1.110.13.105.10.1.1"></node> <node
 * name="bssid" oid=".1.3.1.1.110.13.105.10.1.2"></node> <node name="ssid"
 * oid=".1.3.1.1.110.13.105.10.1.3"></node> <node name="staVlanId"
 * oid=".1.3.1.1.110.13.105.10.1.4"></node> <node name="staVlanEnable"
 * oid=".1.3.1.1.110.13.105.10.1.5"></node> <node name="staMacAddress"
 * oid=".1.3.1.1.110.13.105.10.1.6"></node> <node name="staDevType"
 * oid=".1.3.1.1.110.13.105.10.1.7"></node> <node name="staConnectTime"
 * oid=".1.3.1.1.110.13.105.10.1.8"></node> <node name="staUplinkOctets"
 * oid=".1.3.1.1.110.13.105.10.1.9"></node> <node name="staDownlinkOctets"
 * oid=".1.3.1.1.110.13.105.10.1.10"></node> <node name="rssi"
 * oid=".1.3.1.1.110.13.105.10.1.11"></node> <node name="frequency"
 * oid=".1.3.1.1.110.13.105.10.1.12"></node> <node name="snr"
 * oid=".1.3.1.1.110.13.105.10.1.13"></node> <node name="txRate"
 * oid=".1.3.1.1.110.13.105.10.1.14"></node> <node name="errRate"
 * oid=".1.3.1.1.110.13.105.10.1.15"></node> <node name="assoRate"
 * oid=".1.3.1.1.110.13.105.10.1.16"></node> <node name="staIp"
 * oid=".1.3.1.1.110.13.105.10.1.17"></node>
 * 
 * 
 * @author SunYanzheng
 * @date 2014-5-5
 * @version v1.0.0
 * @see com.gbcom.omc.ccsv2.common.bean.ap.DHCPConfigEntity
 */
public class ApUserStatInfo extends DeviceInfo {

	/**
	 * 序列哈
	 */
	private static final long serialVersionUID = 1L;
	private int staID;
	private String bssid;
	private String ssid;
	private int staVlanId;
	private int staVlanEnable;
	private String staMacAddress;
	private String staIp;
	private String staDevType;
	private String staConnectTime;
	private long staUplinkOctets;
	private long staDownlinkOctets;
	private int rssi;
	private String frequency;
	private int snr;
	// mbps
	private String txRate;
	// 1/10000
	private int errRate;
	private int assoRate;

	/**
	 * 获取staID
	 * 
	 * @return staID
	 */
	public int getStaID() {
		return staID;
	}

	/**
	 * 设置staID
	 * 
	 * @param staID
	 *            int
	 */
	public void setStaID(int staID) {
		this.staID = staID;
	}

	/**
	 * 获取bssid
	 * 
	 * @return bssid
	 */
	public String getBssid() {
		return bssid;
	}

	/**
	 * 设置bssid
	 * 
	 * @param bssid
	 *            String
	 */
	public void setBssid(String bssid) {
		this.bssid = bssid;
	}

	/**
	 * 获取ssid
	 * 
	 * @return ssid
	 */
	public String getSsid() {
		return ssid;
	}

	/**
	 * 设置ssid
	 * 
	 * @param ssid
	 *            String
	 */
	public void setSsid(String ssid) {
		this.ssid = ssid;
	}

	/**
	 * 获取staVlanId
	 * 
	 * @return staVlanId
	 */
	public int getStaVlanId() {
		return staVlanId;
	}

	/**
	 * 设置staVlanId
	 * 
	 * @param staVlanId
	 *            int
	 */
	public void setStaVlanId(int staVlanId) {
		this.staVlanId = staVlanId;
	}

	/**
	 * 获取staVlanEnable
	 * 
	 * @return staVlanEnable
	 */
	public int getStaVlanEnable() {
		return staVlanEnable;
	}

	/**
	 * 设置staVlanEnable
	 * 
	 * @param staVlanEnable
	 *            int
	 */
	public void setStaVlanEnable(int staVlanEnable) {
		this.staVlanEnable = staVlanEnable;
	}

	/**
	 * 获取staMacAddress
	 * 
	 * @return staMacAddress
	 */
	public String getStaMacAddress() {
		return staMacAddress;
	}

	/**
	 * 设置staMacAddress
	 * 
	 * @param staMacAddress
	 *            String
	 */
	public void setStaMacAddress(String staMacAddress) {
		this.staMacAddress = staMacAddress;
	}

	/**
	 * 获取staIp
	 * 
	 * @return staIp
	 */
	public String getStaIp() {
		return staIp;
	}

	/**
	 * 设置staIp
	 * 
	 * @param staIp
	 *            String
	 */
	public void setStaIp(String staIp) {
		this.staIp = staIp;
	}

	/**
	 * 获取staDevType
	 * 
	 * @return staDevType
	 */
	public String getStaDevType() {
		return staDevType;
	}

	/**
	 * 设置staDevType
	 * 
	 * @param staDevType
	 *            String
	 */
	public void setStaDevType(String staDevType) {
		this.staDevType = staDevType;
	}

	/**
	 * 获取staConnectTime
	 * 
	 * @return staConnectTime
	 */
	public String getStaConnectTime() {
		return staConnectTime;
	}

	/**
	 * 设置staConnectTime
	 * 
	 * @param staConnectTime
	 *            String
	 */
	public void setStaConnectTime(String staConnectTime) {
		this.staConnectTime = staConnectTime;
	}

	/**
	 * 获取staUplinkOctets
	 * 
	 * @return staUplinkOctets
	 */
	public long getStaUplinkOctets() {
		return staUplinkOctets;
	}

	/**
	 * 设置staUplinkOctets
	 * 
	 * @param staUplinkOctets
	 *            long
	 */
	public void setStaUplinkOctets(long staUplinkOctets) {
		this.staUplinkOctets = staUplinkOctets;
	}

	/**
	 * 获取staDownlinkOctets
	 * 
	 * @return staDownlinkOctets
	 */
	public long getStaDownlinkOctets() {
		return staDownlinkOctets;
	}

	/**
	 * 设置staDownlinkOctets
	 * 
	 * @param staDownlinkOctets
	 *            long
	 */
	public void setStaDownlinkOctets(long staDownlinkOctets) {
		this.staDownlinkOctets = staDownlinkOctets;
	}

	/**
	 * 获取rssi
	 * 
	 * @return rssi
	 */
	public int getRssi() {
		return rssi;
	}

	/**
	 * 设置rssi
	 * 
	 * @param rssi
	 *            int
	 */
	public void setRssi(int rssi) {
		this.rssi = rssi;
	}

	/**
	 * 获取frequency
	 * 
	 * @return frequency
	 */
	public String getFrequency() {
		return frequency;
	}

	/**
	 * 设置frequency
	 * 
	 * @param frequency
	 *            String
	 */
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	/**
	 * 获取snr
	 * 
	 * @return snr
	 */
	public int getSnr() {
		return snr;
	}

	/**
	 * 设置snr
	 * 
	 * @param snr
	 *            int
	 */
	public void setSnr(int snr) {
		this.snr = snr;
	}

	/**
	 * 获取txRate
	 * 
	 * @return txRate
	 */
	public String getTxRate() {
		return txRate;
	}

	/**
	 * 设置txRate
	 * 
	 * @param txRate
	 *            String
	 */
	public void setTxRate(String txRate) {
		this.txRate = txRate;
	}

	/**
	 * 获取errRate
	 * 
	 * @return errRate
	 */
	public int getErrRate() {
		return errRate;
	}

	/**
	 * 设置errRate
	 * 
	 * @param errRate
	 *            int
	 */
	public void setErrRate(int errRate) {
		this.errRate = errRate;
	}

	/**
	 * 获取assoRate
	 * 
	 * @return assoRate
	 */
	public int getAssoRate() {
		return assoRate;
	}

	/**
	 * 设置assoRate
	 * 
	 * @param assoRate
	 *            int
	 */
	public void setAssoRate(int assoRate) {
		this.assoRate = assoRate;
	}

}

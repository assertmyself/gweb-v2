//off checkstyle
package com.gbcom.common.template.xml.sys;

import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * l2tpServerIp="127.0.0.1" l2tpUsrName="gbcom" l2tpUsrPass="gbcom"
 * 
 * @author SunYanzheng
 * @date 下午2:21:51
 * @version v1.0.0
 * @see L2tpConfig
 */
public class L2tpConfig {
	@XStreamAsAttribute
	private String l2tpServerIp;
	@XStreamAsAttribute
	private String l2tpUsrName;

	@XStreamAsAttribute
	private String l2tpUsrPass;

	public String getL2tpServerIp() {
		return l2tpServerIp;
	}

	public void setL2tpServerIp(String serverIp) {
		l2tpServerIp = serverIp;
	}

	public String getL2tpUsrName() {
		return l2tpUsrName;
	}

	public void setL2tpUsrName(String usrName) {
		l2tpUsrName = usrName;
	}

	public String getL2tpUsrPass() {
		return l2tpUsrPass;
	}

	public void setL2tpUsrPass(String usrPass) {
		l2tpUsrPass = usrPass;
	}

}

package com.gbcom.common.template.xml.mail;

import java.util.Set;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * JOB封装POJO, 包括<code>triggerList</code>,<code>jobParamList</code>
 * 
 * @author SunYanzheng
 * @date 上午9:52:08
 * @version v1.0.0
 * @see MailContext
 */
@XStreamAlias("MailContext")
public class MailContext {
	@XStreamAsAttribute
	private String senderAddr;
	@XStreamAsAttribute
	private String senderPwd;
	@XStreamAsAttribute
	private String senderName;
	@XStreamAsAttribute
	private String host;
	@XStreamAsAttribute
	private String port;
	@XStreamAsAttribute
	private String protocol;
	@XStreamAsAttribute
	private String auth;

	@XStreamAlias("receivers")
	private Set<MailReceiver> receivers;

	/**
	 * 获取senderAddr
	 * 
	 * @return senderAddr
	 */
	public String getSenderAddr() {
		return senderAddr;
	}

	/**
	 * 设置senderAddr
	 * 
	 * @param senderAddr
	 *            String
	 */
	public void setSenderAddr(String senderAddr) {
		this.senderAddr = senderAddr;
	}

	/**
	 * 获取senderPwd
	 * 
	 * @return senderPwd
	 */
	public String getSenderPwd() {
		return senderPwd;
	}

	/**
	 * 设置senderPwd
	 * 
	 * @param senderPwd String
	 */
	public void setSenderPwd(String senderPwd) {
		this.senderPwd = senderPwd;
	}

	/**
	 * 获取senderName
	 * 
	 * @return senderName
	 */
	public String getSenderName() {
		return senderName;
	}

	/**
	 * 设置senderName
	 * 
	 * @param senderName
	 *            String
	 */
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	/**
	 * 获取host
	 * 
	 * @return host
	 */
	public String getHost() {
		return host;
	}

	/**
	 * 设置host
	 * 
	 * @param host
	 *            String
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * 获取port
	 * 
	 * @return port
	 */
	public String getPort() {
		return port;
	}

	/**
	 * 设置port
	 * 
	 * @param port
	 *            String
	 */
	public void setPort(String port) {
		this.port = port;
	}

	/**
	 * 获取protocol
	 * 
	 * @return protocol
	 */
	public String getProtocol() {
		return protocol;
	}

	/**
	 * 设置protocol
	 * 
	 * @param protocol
	 *            String
	 */
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	/**
	 * 获取auth
	 * 
	 * @return auth
	 */
	public String getAuth() {
		return auth;
	}

	/**
	 * 设置auth
	 * 
	 * @param auth
	 *            String
	 */
	public void setAuth(String auth) {
		this.auth = auth;
	}

	/**
	 * 获取receivers
	 * 
	 * @return receivers
	 */
	public Set<MailReceiver> getReceivers() {
		return receivers;
	}

	/**
	 * 设置receivers
	 * 
	 * @param receivers
	 *            Set<MailReceiver>
	 */
	public void setReceivers(Set<MailReceiver> receivers) {
		this.receivers = receivers;
	}
}

package com.gbcom.common.template.xml.mail;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * JOB 触发器配置
 * 
 * @author SunYanzheng
 * @date 上午9:48:34
 * @version v1.0.0
 * @see MailReceiver
 */
@XStreamAlias("MailReceiver")
public class MailReceiver {
	@XStreamAsAttribute
	private String receiverAddr;
	@XStreamAsAttribute
	private String receiverType;

	/**
	 * 获取receiverAddr
	 * 
	 * @return receiverAddr
	 */
	public String getReceiverAddr() {
		return receiverAddr;
	}

	/**
	 * 设置receiverAddr
	 * 
	 * @param receiverAddr
	 *            String
	 */
	public void setReceiverAddr(String receiverAddr) {
		this.receiverAddr = receiverAddr;
	}

	/**
	 * 获取receiverType
	 * 
	 * @return receiverType
	 */
	public String getReceiverType() {
		return receiverType;
	}

	/**
	 * 设置receiverType
	 * 
	 * @param receiverType
	 *            String
	 */
	public void setReceiverType(String receiverType) {
		this.receiverType = receiverType;
	}

}

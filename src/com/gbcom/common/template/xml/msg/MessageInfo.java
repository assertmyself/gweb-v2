package com.gbcom.common.template.xml.msg;


import java.io.Serializable;
import java.util.Date;

/**
 * 异步消息框架，消息。
 * 
 * <p>
 * @author syz
 * <p>
 * @date 2015-6-4,下午02:40:34
 * <p>
 * @version v1.0.0
 * <p>
 * @see com.gbcom.ccsv3.template.xml.msg.MessageInfo
 */
public class MessageInfo implements Serializable{
	/**
	 * MessageInfo.java
	 */
	private static final long serialVersionUID = 5227789988217685539L;
	//消息id
	private int messageID;
	private Date time = new Date();
	//消息主体
	private Serializable msgBody;
	/**
	 * @return the messageID
	 */
	public int getMessageID() {
		return messageID;
	}
	/**
	 * @param messageID the messageID to set
	 */
	public void setMessageID(int messageID) {
		this.messageID = messageID;
	}
	/**
	 * @return the msgBody
	 */
	public Serializable getMsgBody() {
		return msgBody;
	}
	/**
	 * @param msgBody the msgBody to set
	 */
	public void setMsgBody(Serializable msgBody) {
		this.msgBody = msgBody;
	}
	/**
	 * 覆盖父类的toString方法
	 * @return String
	 */
	@Override
	public String toString() {
		return "MessageInfo [messageID=" + messageID + ", msgBody=" + msgBody
				 + "]";
	}
	/**
	 * @return the time
	 */
	public Date getTime() {
		return time;
	}
	/**
	 * @param time the time to set
	 */
	public void setTime(Date time) {
		this.time = time;
	}

}

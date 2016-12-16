package com.gbcom.common.template.xml.msg;


import java.util.Date;

import org.apache.log4j.Logger;

/**
 * 内部告警分发器,当有内部告警产生时则添加到内部告警队列中
 * 
 * @author caomin
 */
public class MsgSender {
	private static final Logger LOG = Logger.getLogger(MsgSender.class);
	private static MsgSender instance = new MsgSender();
	private MessageDispatcher msgDispatcher = null;
	
	private MsgSender() {
		init();
	}
	private void init(){
		msgDispatcher = new MessageDispatcher();
		msgDispatcher.start();
		LOG.info("build and start message dispatcher  success!  time  ="+new Date()); 
	}

	/**
	 * 获取消息发送器
	 * @return MessageSender
	 */
	public static MsgSender getInstance() {
		return instance;
	}

	/**
	 * 发送消息
	 * @param message 消息
	 */
	public void send(MessageInfo message) {
		if (!msgDispatcher.isRunning()) {
			LOG.error("msgDispatcher is not running,so the message will not be process !!! (Thread problem or other)");
			//off checkstyle
			synchronized (instance) {
				if(!msgDispatcher.isRunning()){
					if(msgDispatcher!=null){
						LOG.warn("MSG:: stop msgDispatcher");
						msgDispatcher.stop();
						msgDispatcher = null;
					}
					init();
					LOG.warn("MsgSender :: reBuild msgDispatcher object again   .....");
				}
			}
		}
		msgDispatcher.addMessage(message);
	}
}

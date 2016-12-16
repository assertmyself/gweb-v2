package com.gbcom.common.template.xml.msg.process;

import org.apache.log4j.Logger;

import com.gbcom.common.template.xml.msg.MessageInfo;

/**
 * test msg
 * 
 * @author SunYanzheng
 * @date 下午4:13:10
 * @version v1.0.0
 * @see TestMsgProcess
 */
public class TestMsgProcess implements IMessageProcess {
	private static final Logger LOG = Logger.getLogger(TestMsgProcess.class);

	/**
	 * 消息处理
	 * 
	 * @param msg
	 *            MessageInfo
	 */
	@Override
	public void processMsg(MessageInfo msg) {
		LOG.info(msg);
	}
}

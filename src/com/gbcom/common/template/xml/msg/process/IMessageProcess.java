package com.gbcom.common.template.xml.msg.process;

import com.gbcom.common.template.xml.msg.MessageInfo;

/**
 * 消息处理接口,非线程安全对象。
 * @author SunYanzheng
 * @date 下午6:11:41
 * @version v1.0.0
 * @see IMessageProcess
 */
public interface IMessageProcess {

	/**
	 * 消息处理接口
	 * @param msg 消息
	 */
	public void processMsg(MessageInfo msg);
}

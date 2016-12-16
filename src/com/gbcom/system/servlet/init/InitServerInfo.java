package com.gbcom.system.servlet.init;

import java.util.HashSet;
import java.util.Set;

import com.gbcom.common.template.res.BasicResManager;

/**
 * 初始化信息,报错服务器启动时的一些异常
 * @author SunYanzheng
 * @date 下午2:29:40
 * @version v1.0.0
 * @see InitServerInfo
 */
public final class InitServerInfo {
	private static InitServerInfo instance = new InitServerInfo();
	private Set<String> messages=null;
	boolean result = false;
	private InitServerInfo(){
		messages = new HashSet<String>();
	}
	/**
	 *  获取单例对象
	 * @return InitServerInfo
	 */
	public static InitServerInfo getInstance(){
		if(instance ==null){
			instance = new InitServerInfo();
		}
		return instance;
	}
	
	/**
	 * 初始化状态，登录
	 * @return statue
	 */
	public boolean getInitStatue(){
		return messages.isEmpty();
	}
	
	/**
	 * 添加错误消息
	 * @param e String
	 */
	public void addMessage(String e){
		messages.add(e);
	}

	/**
	 * 获取初始化结果
	 * @return String
	 */
	public String getMessage(){
		if(messages.isEmpty()){
			return ""+BasicResManager.getString("Basic_application_load_success");
		}
		String msg = "";
		for(String m : messages){
			msg += m+"\n";
		}
		return msg.substring(0, msg.lastIndexOf("\n"));
	}
}

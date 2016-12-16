package com.gbcom.common.template.xml.msg;


import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.gbcom.common.template.xml.msg.process.IMessageProcess;
import com.gbcom.op.util.xml.XStreamUtil;
/**
 * 消息路由管理
 * @author SunYanzheng
 * @date 下午5:53:24
 * @version v1.0.0
 * @see RouteInfoManager
 */
public class RouteInfoManager {
	private RouteInfoContext routeContext;
	private Map<Integer, IMessageProcess> msgProcess = new 
			HashMap<Integer, IMessageProcess>();
	private static RouteInfoManager instance = new RouteInfoManager();
	private RouteInfoManager(){
		load();
	}
	/**
	 *  获取资源类，且对应的资源已经构建完成
	 * @return RouteInfoManager
	 */
	public static RouteInfoManager getInstance(){
		return instance;
	}
	@SuppressWarnings("unchecked")
	private void load(){
		final Class<?>[] classContext = { RouteInfoContext.class,RouteInfo.class};
		final URL url=Thread.currentThread().getContextClassLoader().getResource("config/msg/routeinfo.xml");
		routeContext=XStreamUtil.fromXML(RouteInfoContext.class, url.getFile(),
				classContext);
		for(RouteInfo routeInfoRoute:routeContext.getRouteList()){
		    int msgID=routeInfoRoute.getMsgid();
		    String process=routeInfoRoute.getProcessor();
			try {
				Class<IMessageProcess> tmp = (Class<IMessageProcess>) Class
						.forName(process);
				msgProcess.put(msgID, tmp.newInstance());
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch(Exception e){
				
			}
		}
	}
	
	/**
	 * 获取消息处理器
	 * @param id MSG id
	 * @return IMessageProcess
	 */
	public  IMessageProcess getMsgProcess(int id){
		return msgProcess.get(id);
	}

}

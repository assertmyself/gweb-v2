package com.gbcom.common.template.xml.sys;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.gbcom.op.util.xml.XStreamUtil;
/**
 * Ap配置项 管理器。所有SAC提供配置的Item，作为同步或者下发的依据
 * 
 * @author SunYanzheng
 * @date 下午5:53:24
 * @version v1.0.0
 * @see CfgListManager
 */
public class CfgListManager {
	private CfgListContext context;
	private Map<String, CfgItem> cfgMap = new 
			HashMap<String, CfgItem>();
	private static CfgListManager instance = new CfgListManager();
	private CfgListManager(){
		load();
	}
	/**
	 *  获取资源类，且对应的资源已经构建完成
	 * @return RouteInfoManager
	 */
	public static CfgListManager getInstance(){
		return instance;
	}
	private void load(){
		final Class<?>[] classContext = { CfgListContext.class,CfgItem.class};
		final URL url=Thread.currentThread().getContextClassLoader().getResource("config/sys/cfg_list.xml");
		context=XStreamUtil.fromXML(CfgListContext.class, url.getFile(),
				classContext);
		for(CfgItem item: context.getList()){
			String name = item.getName();
			cfgMap.put(name, item);
		}
	}

	/**
	 * 
	 * @param name String
	 * @return ConfigItem
	 */
	public  CfgItem getItem(String name ){
		return cfgMap.get(name);
	}

	/**
	 * 获取配置MAP
	 * 
	 * @return  Map<String, ConfigItem>
	 */
	public Map<String, CfgItem> getCfgMap(){
		return cfgMap;
	}
	/**
	 * 
	 * @return Set<String>
	 */
	public Set<String> getKeySet(){
		return cfgMap.keySet();
	}
	/**
	 * test
	 * @param args params
	 */
	public static void main(String[] args){
		CfgItem item = CfgListManager.getInstance().getItem("DHCPConfigEntity");
		System.out.println(item.getDesc());
	}
}

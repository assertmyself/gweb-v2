package com.gbcom.common.template.xml.guide;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import com.gbcom.op.util.xml.XStreamUtil;

/**
 * Ap配置项 管理器。所有SAC提供配置的Item，作为同步或者下发的依据
 * 包括两部分内容：
 * 1.加载动态信息采集模板信息
 * 2.保存采集数据
 * \
 * 设备缓存框架 扩展。
 * 
 * 设备信息扩展。
 * 
 * @author SunYanzheng
 * @date 下午5:53:24
 * @version v1.0.0
 * @see CfgListManager
 */
public class DeviceInfoCenter {
	private static final Logger logger = Logger.getLogger(DeviceInfoCenter.class);
	//模板
	private Map<String, DevInfoGuide> guideMap = new HashMap<String, DevInfoGuide>();
	//数据
	private Map<String,Map<Object,List<DeviceInfo>>> datas = new HashMap<String,Map<Object,List<DeviceInfo>>>();
	
	/**
	 * 获取实例
	 * @return DeviceInfoCenter
	 */
	public static DeviceInfoCenter getInstance(){
		return DevInfoCenterHolder.instance;
	}
	
	private static class DevInfoCenterHolder{
		private static DeviceInfoCenter instance = new DeviceInfoCenter();
	}
	private DeviceInfoCenter(){
		load();
	}
	private void load(){
		logger.info("build device info guide and  datas information  begin");
		final Class<?>[] classContext = { DevInfoContext.class,DevInfoGuide.class};
		final URL url=Thread.currentThread().getContextClassLoader().getResource("config/guide/devinfo_guide.xml");
		DevInfoContext context=XStreamUtil.fromXML(DevInfoContext.class, url.getFile(),
				classContext);
		for(DevInfoGuide item: context.getList()){
			String code = item.getCode();
			guideMap.put(code, item);
			//数据基本
			datas.put(code, new HashMap<Object,List<DeviceInfo>>());
		}
		logger.info("build device guidemap and datas success!");
	}
	
	/**
	 * 获取指标
	 * @param id
	 * @return IMessageProcess
	 */
	public Map<String, DevInfoGuide> getGuideMap(){
		return guideMap;
	}
	
	/**
	 * 获取采集指标
	 * @param code String
	 * @return DevInfoGuide
	 */
	public DevInfoGuide getGuideByCode(String code){
		return guideMap.get(code);
	}
	/**
	 * 获取数据
	 * @return Map<String,Map<ApDeviceBean,List<DeviceInfo>>>
	 */
	public Map<String,Map<Object,List<DeviceInfo>>>  getDatas(){
		return datas;
	}
	
	/**
	 * 获取某个指标的数据<code>Code</code>
	 * @param code  指标CODE
	 * @return Map<ApDeviceBean,List<DeviceInfo>>
	 */
	public Map<Object,List<DeviceInfo>>  getDatasByGuide(String code){
		return datas.get(code);
	}
	/**
	 * 添加
	 * @param code String
	 * @param data Map<ApDeviceBean,List<DeviceInfo>>
	 */
	public void pupEntry(String code,Map<Object,List<DeviceInfo>> data){
		datas.put(code, data);
	}
	
	/**
	 * 清空所有缓存
	 */
	public void clearAll(){
		guideMap.clear();
		datas.clear();
	}
	/**
	 * 清空数据
	 */
	public void clearData(){
		guideMap.clear();
	}
	/**
	 * test
	 * @param args params
	 */
	public static void main(String[] args){
		DevInfoGuide info = DeviceInfoCenter.getInstance().getGuideByCode("10001");
		System.out.println(info.getName());
	}
}

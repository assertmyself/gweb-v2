package com.gbcom.common.template.xml.prefilter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.gbcom.op.util.xml.XStreamUtil;

/**
 * DevFilterCondition管理器
 * 
 * @author doujun
 * 
 */
public class PreFilterConditionManager {
	private static final Logger LOG = Logger
			.getLogger(PreFilterConditionManager.class);
	private static final String FILEPATH = "config/prefilter/PreFilterCondition.xml";
	private static Map<String, PreFilterCondition> conditionMap = null;
	private static PreFilterConditionManager instance = null;
	private boolean deprecated = true;//注销整个模块
	/**
	 * 私有构造器
	 */
	
	private PreFilterConditionManager() {
		init();
	}

	/**
	 * 获取实例
	 * 
	 * @return DevFilterConditionManager
	 */
	public static PreFilterConditionManager getInstance() {
		if (instance == null) {
			instance = new PreFilterConditionManager();
		}
		return instance;
	}

	/**
	 * 初始化方法
	 */
	private void init() {
		try {
			if (conditionMap == null) {
				conditionMap = new HashMap<String, PreFilterCondition>();
			}
			Class<?>[] classContext = { PreFilterConditionContext.class,
					PreFilterCondition.class };
			PreFilterConditionContext devConditionContext = XStreamUtil.fromXML(
					PreFilterConditionContext.class, FILEPATH, classContext);
			List<PreFilterCondition> conList = devConditionContext.getList();
			for (PreFilterCondition devFilterCondition : conList) {
				conditionMap.put(devFilterCondition.getSysmodel(),
						devFilterCondition);
			}
			
			deprecated = !devConditionContext.isFilterSwitch();
		} catch (Exception e) {
			LOG.error("INIT FAILE",e);
			deprecated = true;
		}
	}

	/**
	 * 根据设备信息和操作ID，判断操作是否支持
	 * 
	 * @param apDevice
	 *            设备信息
	 * @param operId
	 *            操作ID
	 * @return Boolean true if success ,,false if failed!
	 */
	public boolean filter(Object apDevice, String operId) {
		if(deprecated){
			return deprecated;
		}
		try {
			//must replace
			//implement  object.match
			PreFilterCondition devFilterCondition = conditionMap.get(apDevice.toString());
			if (devFilterCondition == null) {
				return true; //如果没有配置该型号，返回true
			}
			String[] regexs = devFilterCondition.getVersion().split(",");
			for (String regex : regexs) {
				if (regex.equals("*")) {
					regex = ".*";
				}
				boolean isMatch = apDevice.toString().matches(regex);
				if (isMatch) {
					String[] values = devFilterCondition.getValue().split(",");
					List<String> valueList = Arrays.asList(values);
					boolean isContain = valueList.contains(operId);
					return isContain;
				} else {
					return false;
				}
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return false;
		}
		return true;
	}
}

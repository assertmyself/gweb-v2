//off checkstyle
package com.gbcom.system.common.hchart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * 静态工具类
 * 提供HChartPoints转化的方法
 * @author syz
 * @date 2015-12-8 下午03:12:56
 * @version 1.0.0
 * @since com.gbcom.ccsv3.common.hchart.HChartPoints
 */
public class HChartPoints {
	private static final Logger logger = Logger.getLogger(HChartPoints.class);
	/**
	 * 转化成ponit
	 * @param name
	 * @param series
	 * @return
	 */
	public static List<HChartPoint> toHChartPoint(Map<String,Object> series){
		List<HChartPoint> list= new ArrayList<HChartPoint>();
		for(Map.Entry<String,Object> each : series.entrySet()){
			HChartPoint one = new HChartPoint(each.getKey(),each.getValue().toString());
			list.add(one);
		}
		return list;
	}
	/**
	 * 转化成ponit
	 * @param name
	 * @param series
	 * @return
	 */
	public static List<HChartPoint> toHChartPoint(List<Object[]> series){
		List<HChartPoint> list= new ArrayList<HChartPoint>();
		for(Object[] each : series){
			if(each.length<2) continue;
			HChartPoint one = new HChartPoint(each[0].toString(),each[1].toString());
			list.add(one);
		}
		return list;
	}
	
	/**
	 * 将map中 的点 转化为  HChartPoint
	 * map 方式
	 * series == map
	 * @param series
	 * @return
	 */
	public static Map<String,List<HChartPoint>> transferSeries(String name,Map<String,Object> series){
		if(name == null) name = "entry"; 
		if(series == null) return new HashMap<String,List<HChartPoint>>(); 
		Map<String,List<HChartPoint>> serm = new HashMap<String,List<HChartPoint>>();
		serm.put(name, toHChartPoint(series));
		return serm;
	}
	/**
	 * 数组方式：
	 * series == map
	 * @param map
	 * @return
	 */
	public static Map<String,List<HChartPoint>> transferSeries(String name,List<Object[]> series){
		if(name == null) name = "entry"; 
		if(series == null) return new HashMap<String,List<HChartPoint>>(); 
		Map<String,List<HChartPoint>> serm = new HashMap<String,List<HChartPoint>>(); 
		serm.put(name, toHChartPoint(series));
		return serm;
	}
	
	/**
	 * 多个 serise
	 * @param names
	 * @param serieses List<List<Object[]>> or List<Map<String,Object>>
	 * @return
	 */
	public static Map<String,List<HChartPoint>> transferVectorSeries(List<String> names,List<Object> serieses){
		Map<String,List<HChartPoint>> serm = new HashMap<String,List<HChartPoint>>(); 
		if(serieses == null ||serieses.size() ==0) return new HashMap<String,List<HChartPoint>>();
		for(int i=0;i<serieses.size();i++){
			Object each = serieses.get(i);

			String name = names.get(i);
			if(name == null) name = "entry"+i; 
			if(each instanceof List){
				serm.put(name, toHChartPoint((List)each));
			}else if(each instanceof Map){
				serm.put(name, toHChartPoint((Map)each));
			}else{
				logger.warn("the serieses is not List<Object[] or Map<String,Object>> each= "+each);
			}
		}
		return serm;
	}
	
	
	

}

package com.gbcom.system.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

/**
 * 集合工具类，待扩展
 * 
 * <p>
 * @author syz
 * <p>
 * @date 2015-9-15,下午01:10:38
 * <p>
 * @version v1.0.0
 * <p>
 * @see com.gbcom.system.utils.CollectionUtil
 */
public class CollectionUtil {
	/***
	 * BROWSER_NAME
	 */
	public static final String[] BROWSER_NAME = new String[]{"MSIE","Netscape","Opera","Firefox","Chrome"};

	/**
	 * 获取第一个元素
	 * @param <T> <T> T
	 * @param collection Collection<T>
	 * @return <T> T
	 */
    public static <T> T getFirstEle(Collection<T> collection) {
        if (collection == null || collection.size() == 0) {
            return null;
        }
        return collection.iterator().next();
    }
    
    /**
     * 将list用","分割成字符串
     * @param list List
     * @return String
     */
    public static String listToString(List list){
    	if (list==null || list.size()==0) {
            return "''";
        }
        StringBuilder result=new StringBuilder();
        boolean flag=false;
        for (Object string : list) {
            if (flag) {
                result.append(",");
            }else {
                flag=true;
            }
            result.append("'"+string.toString()+"'");
        }
        return result.toString();
    }
    /**
     * 将list用","分割成字符串
     * @param set Set
     * @return String
     */
    public static String listToString(Set set){
    	if (set==null || set.size()==0) {
            return "''";
        }
        StringBuilder result=new StringBuilder();
        boolean flag=false;
        for (Object string : set) {
            if (flag) {
                result.append(",");
            }else {
                flag=true;
            }
            result.append(string.toString());
        }
        return result.toString();
    }
    
    /**
     * @param agent 浏览器标识
     * @return 浏览器缩写
     * @throws Exception Exception
     */
    public static String browserTypeCheck(String agent) throws Exception{
    	if(agent == null){
    		throw new Exception("browser agent is null");
    	}
    	String browser = "";
    	for(int i = 0; i < BROWSER_NAME.length; i++){
    		browser = BROWSER_NAME[i];
    		if(agent.toUpperCase().indexOf(browser.toUpperCase()) == -1){
    			browser = "其它";
    		}else{
    			if(browser.equals("MSIE")){
    				browser = "IE";
    			}
    			break;
    		}
    	}
    	return browser;
    }
    
    /**
	 * map排序
     * @param map 需要排序的map 
     * @param num int
     * @return 排序后的map
     */
    @SuppressWarnings("unchecked")
	public static Map mapSort(Map map,int num){
    	List<Map.Entry<Object, Object>> entries = new ArrayList<Map.Entry<Object, Object>>(
    			map.entrySet());
		Collections.sort(entries, new Comparator<Map.Entry<Object, Object>>() {
			public int compare(Map.Entry<Object, Object> obj1,
					Map.Entry<Object, Object> obj2) {
				if(obj1.getValue() instanceof List<?> && obj2.getValue() instanceof List<?>){
					return ((List<?>)obj2.getValue()).size() - ((List<?>)obj1.getValue()).size(); 
				}else{
					return Integer.parseInt(obj2.getValue().toString())
						- Integer.parseInt(obj1.getValue().toString());
				}
			}
		});
		Map<Object, Object> sortMap = new LinkedHashMap<Object, Object>();
		if(num == 0 || entries.size() < num){
			for (int i = 0; i < entries.size(); i++) {
				Entry<Object, Object> entry = entries.get(i);
				sortMap.put(entry.getKey(), entry.getValue());
			}
		}else{
			for (int i = 0; i < num; i++) {
				Entry<Object, Object> entry = entries.get(i);
				sortMap.put(entry.getKey(), entry.getValue());
			}
		}
		return sortMap;
    }
}

package com.gbcom.common.hibernate;

import com.hc.core.orm.hibernate.Page;
import com.hc.core.ui.UIHelper;
import com.hc.core.utils.StringHelper;
import flexjson.JSONSerializer;
import flexjson.transformer.DateTransformer;
import org.apache.commons.beanutils.NestedNullException;
import org.apache.commons.beanutils.PropertyUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * jqGRID支持 ，用于将sql查询结果返回json
 * 
 * 分组了 jqgrid和hibernate查询等相关的条件和数据转json等操作，
 * 作为controller的核心部分，
 * 
 * <p>
 * @author syz
 * <p>
 * @date 2015-9-15,下午01:15:45
 * <p>
 * @version v1.0.0
 * <p>
 * @see com.gbcom.common.hibernate.GridJq
 */
public class GridJq {

    /**
     * 根据列名和page转json
     *
     * @param columnNames .
     * @param page        .
     * @return .
     * @throws Exception .
     */
    @SuppressWarnings("unchecked")
    public static String toJSON(String columnNames, Page page) throws Exception {
        List<Map> list = getGridValue(page.getRows(), columnNames);
        return toJSON(list, page);
    }
    

    /**
     * 添加jqGrid所必需的页面信息
     *
     * @param list .
     * @param page .
     * @return .
     */
    public static Map addPageInfo(List list, Page page) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("total", page.getTotalPages());
        map.put("page", page.getPage());
        map.put("records", page.getRecords());
        map.put("rows", list);
        return map;
    }

    /**
     * 转化json 
     * @param list List
     * @param page Page
     * @return String
     */
    public static String toJSON(List list, Page page) {
        return new JSONSerializer().exclude("*.class").include("rows").transform(new DateTransformer("yyyy-MM-dd"), Date.class)
                .serialize(addPageInfo(list, page));
    }

    /**
     * 
     * @param col Collection
     * @param columnNames String
     * @return List<Map>
     * @throws Exception Exception
     */
    @SuppressWarnings("unchecked")
    public static List<Map> getGridValue(Collection col, String columnNames)
            throws Exception {
        String[] colNames = UIHelper.string2Array(columnNames);
        List<Map> valueList = new ArrayList<Map>();
        for (Object aList : col) {
            valueList.add(getRowValue(aList, colNames));
        }
        return valueList;
    }

    /**
     * 获得obj属性columnName的值.
     *
     * @param obj Object
     * @param columnName String
     * @return String
     */
    public static String getColumnValue(Object obj, String columnName) {
        String rel = "";
        columnName = columnName.trim();

        Object value = null;
        try {
            value = PropertyUtils.getNestedProperty(obj, columnName);
        } catch (IllegalAccessException e) {
//            e.printStackTrace();
        } catch (InvocationTargetException e) {
//            e.printStackTrace();
        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
        } catch (NestedNullException e) {
            value = null;
        }
        if (value != null) {
            rel = value.toString();
        }

        //注意以下这段是与ext的显示有关系
        if (!StringHelper.isEmpty(rel)) {
            //把回车换行做一个替换，到页面要plugin再替换过来
            rel = rel.replaceAll("\r\n", "<br/>");
            rel = rel.replaceAll("\r", "<br/>");
            rel = rel.replaceAll("\n", "<br/>");
            //把数据里的单引号替换成反斜杠加单引号，这样ext可以直接显示为单引号
            rel = StringHelper.findAndReplace(rel, "\'", "\\\'");
        }
        return rel;
    }

    /**
     * getRowValue
     * @param obj .
     * @param columnNames .
     * @return Map<String, String>
     */
    public static Map<String, String> getRowValue(Object obj, String[] columnNames) {
    	if(obj instanceof Map){
    		return (Map<String, String>) obj;
    	}
        Map<String, String> map = new LinkedHashMap<String, String>();
        for (String columnName : columnNames) {
            map.put(columnName, getColumnValue(obj, columnName));
        }
        return map;
    }
    
	/**
	 * toJSON: (GridJq.toJSON)
	 * @param list List 
	 * @param columnNames String
	 * @return String 
	 * @throws Exception Exception
	 */ 
    public static String toJSON(List list,String columnNames) throws Exception{
    	List<Map> mapList = getGridValue(list, columnNames);
    	return new JSONSerializer().serialize(mapList);
    }
}

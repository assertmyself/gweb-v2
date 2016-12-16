//off checkstyle
package com.gbcom.system.common.hchart;

import java.util.List;
import java.util.Map;

/**
 * HchartTransfer 工具类
 * @author syz
 * @date Nov 30, 2015 4:07:48 PM
 * @version 1.0.0
 * @since com.gbcom.ccsv3.common.hchart.HChartTransfer
 */
public class HChartTransfer {

	public HChartResult transferHchartData(List<Map<String,Object>> rows){
		
		HChartResult rst  = new HChartResult();
		rst.setTotal(1);
//		rst.setData();//fill it from rows  
		rst.setErrmsg("success"); 
		rst.setErrno(0);
		return null;
	}
	public HChartResult transferHchartData(Map<String,Object> rows){
		return null;
	}
	
}

//off checkstyle
package com.gbcom.system.common.hchart;

import java.util.List;
import java.util.Map;


/**
 * HCHART JIEGUO .
 * 
 * <p>
 * @author syz
 * <p>
 * @date 2015-3-20,下午02:31:40
 * <p>
 * @version v1.0.0
 * <p>
 * @see com.gbcom.ccsv3.northful.bblink.BblinkResult
 */
public class HChartResult<T> {
	
	public static final int RST_OK = 0;
	public static final int RST_FAILED = -1;
	
	private int errno = 0;
	private int total = 0;
	private String errmsg = "success";
	/**
	 * key : series - name
	 * value : point array;
	 */
	private List<T> grid = null;
	private Map<String,List<HChartPoint>> data= null;
	public int getErrno() {
		return errno;
	}
	public void setErrno(int errno) {
		this.errno = errno;
		if(errno !=0){
			this.errmsg= "failed";
			this.total = 0;
		}
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public String getErrmsg() {
		return errmsg;
	}
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
	public void setData(Map<String,List<HChartPoint>> data) {
		this.data = data;
	}
	public Map<String,List<HChartPoint>> getData() {
		return data;
	}
	public void setGrid(List<T> grid) {
		this.grid = grid;
	}
	public List<T> getGrid() {
		return grid;
	}
	
	
	public void add(String name,List<HChartPoint> each){
		if(each == null) return;
		if(name == null) name = "entry";
		
		data.put(name, each);
	}
	
	/**
	 * 静态的 构造方法
	 * @param map
	 * @return
	 */
	public static HChartResult valueOf(Map<String,List<HChartPoint>> map){
		HChartResult rst = new HChartResult();
		rst.setData(map);
		rst.setTotal(map.size());
		return rst;
	}


}

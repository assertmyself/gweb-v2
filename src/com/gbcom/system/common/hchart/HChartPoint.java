//off checkstyle
package com.gbcom.system.common.hchart;

/**
 * HichartPoint
 * 
 * <p>
 * @author syz
 * <p>
 * @date 2015-5-19,下午06:55:37
 * <p>
 * @version v1.0.0
 * <p>
 * @see com.gbcom.system.common.hchart.HChartPoint
 */
public class HChartPoint {
	
	
	public HChartPoint(String x,String y){
		this.pointY = y;
		this.ponitX = x;
	}
	private String ponitX;
	private String pointY;
	public String getPonitX() {
		return ponitX;
	}
	public void setPonitX(String ponitX) {
		this.ponitX = ponitX;
	}
	public String getPointY() {
		return pointY;
	}
	public void setPointY(String pointY) {
		this.pointY = pointY;
	}
	
	

}

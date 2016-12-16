package com.gbcom.common.template.xml.guide;

import java.io.Serializable;
import java.util.Date;
/**
 * 配置实体基类
 * @author SunYanzheng
 * @date 2014-4-30
 * @version v1.0.0
 * @see com.gbcom.omc.ccsv2.common.bean.ap.ConfigEntity
 */
public abstract class DeviceInfo implements Serializable {

	/**
	 * ConfigEntity.java
	 */
	private static final long serialVersionUID = -3412362038125492333L;
	//内部ID
	protected int id = 0;
	protected Date time;
	
	/**
	 * 获取id
	 * @return int
	 */
	public int getId() {
		return id;
	}

	/**
	 * 设置id
	 * @param iid id
	 */
	public void setId(int iid) {
		this.id = iid;
	}

	/**
	 * 
	 * 获取时间
	 * @return time
	 */
	public Date getTime() {
		return time;
	}

	/**
	 * 设置时间
	 * @param time Date
	 */
	public void setTime(Date time) {
		this.time = time;
	}

	
}

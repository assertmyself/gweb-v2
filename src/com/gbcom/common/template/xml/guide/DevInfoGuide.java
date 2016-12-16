package com.gbcom.common.template.xml.guide;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * 设备动态信息采集列表
 * 
 * @author SunYanzheng
 * @date 2014-6-23
 * @version v1.0.0
 * @see com.gbcom.omc.ccsv2.common.bean.dynamic.DevInfoGuide
 */
@XStreamAlias("DevInfoGuide")
public class DevInfoGuide {
	@XStreamAsAttribute
	private String code;
	@XStreamAsAttribute
	private String name;
	@XStreamAsAttribute
	private String desc;
	@XStreamAsAttribute
	private String mibNode;
	@XStreamAsAttribute
	private String entityClass;

	@XStreamAsAttribute
	private boolean multi;
	@XStreamAsAttribute
	private boolean autoJob;

	/**
	 * 设置name
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置name
	 * 
	 * @param name
	 *            String
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取desc
	 * 
	 * @return desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * 设置desc
	 * 
	 * @param desc
	 *            String
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * 获取
	 * 
	 * @return multi
	 */
	public boolean isMulti() {
		return multi;
	}

	/**
	 * 设置multi
	 * 
	 * @param multi
	 *            boolean
	 */
	public void setMulti(boolean multi) {
		this.multi = multi;
	}

	/**
	 * 获取code
	 * 
	 * @return code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 设置code
	 * 
	 * @param code
	 *            String
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 获取mibNode
	 * 
	 * @return mibNode
	 */
	public String getMibNode() {
		return mibNode;
	}

	/**
	 * 设置minNode
	 * 
	 * @param mibNode
	 *            String
	 */
	public void setMibNode(String mibNode) {
		this.mibNode = mibNode;
	}

	/**
	 * 获取setEntityClass
	 * 
	 * @return setEntityClass
	 */
	public String getEntityClass() {
		return entityClass;
	}

	/**
	 * 设置setEntityClass
	 * 
	 * @param entityClass
	 *            String
	 */
	public void setEntityClass(String entityClass) {
		this.entityClass = entityClass;
	}

	/**
	 * 获取autoJob
	 * 
	 * @return autoJob
	 */
	public boolean isAutoJob() {
		return autoJob;
	}

	/**
	 * 设置autoJob
	 * 
	 * @param autoJob
	 *            boolean
	 */
	public void setAutoJob(boolean autoJob) {
		this.autoJob = autoJob;
	}

}

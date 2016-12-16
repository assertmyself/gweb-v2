package com.gbcom.common.template.xml.sys;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("ConfigItem")
public class CfgItem {
	@XStreamAsAttribute
	private String name;
	@XStreamAsAttribute
	private String desc;
	@XStreamAsAttribute
	private String clxName;
	@XStreamAsAttribute
	private String priority;
	@XStreamAsAttribute
	private boolean multi;
	@XStreamAsAttribute
	private boolean autoSync;

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            String
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @param desc
	 *            String
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * @return priority
	 */
	public String getPriority() {
		return priority;
	}

	/**
	 * @param priority
	 *            String
	 */
	public void setPriority(String priority) {
		this.priority = priority;
	}

	/**
	 * @return clxName
	 */
	public String getClxName() {
		return clxName;
	}

	/**
	 * @param clxName
	 *            String
	 */
	public void setClxName(String clxName) {
		this.clxName = clxName;
	}

	/**
	 * @return multi
	 */
	public boolean isMulti() {
		return multi;
	}

	/**
	 * @param multi
	 *            boolean
	 */
	public void setMulti(boolean multi) {
		this.multi = multi;
	}

	/**
	 * @return autoSync
	 */
	public boolean isAutoSync() {
		return autoSync;
	}

	/**
	 * @param autoSync
	 *            boolean
	 */
	public void setAutoSync(boolean autoSync) {
		this.autoSync = autoSync;
	}

}

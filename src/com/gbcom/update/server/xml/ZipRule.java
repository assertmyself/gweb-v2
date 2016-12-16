package com.gbcom.update.server.xml;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * 压缩规则
 * 
 * @author doujun
 * 
 */
@XStreamAlias("ZipRule")
public class ZipRule {
	/**
	 * 压缩规则名称
	 */
	@XStreamAsAttribute
	private String name;
	/**
	 * 包含规则
	 */
	@XStreamAsAttribute
	private String include;
	/**
	 * 过滤规则
	 */
	@XStreamAsAttribute
	private String exclude;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the include
	 */
	public String getInclude() {
		return include;
	}

	/**
	 * @param include
	 *            the include to set
	 */
	public void setInclude(String include) {
		this.include = include;
	}

	/**
	 * @return the exclude
	 */
	public String getExclude() {
		return exclude;
	}

	/**
	 * @param exclude
	 *            the exclude to set
	 */
	public void setExclude(String exclude) {
		this.exclude = exclude;
	}
}

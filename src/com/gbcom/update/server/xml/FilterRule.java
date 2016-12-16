package com.gbcom.update.server.xml;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * 过滤规则
 * 
 * @author doujun
 * 
 */
@XStreamAlias("FilterRule")
public class FilterRule {
	/**
	 * 过滤规则名称
	 */
	@XStreamAsAttribute
	private String name;
	/**
	 * 过滤条件
	 */
	@XStreamAsAttribute
	private String filterConst;
	/**
	 * 过滤内容
	 */
	@XStreamAsAttribute
	private String filterContent;

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
	 * @return the filterConst
	 */
	public String getFilterConst() {
		return filterConst;
	}

	/**
	 * @param filterConst
	 *            the filterConst to set
	 */
	public void setFilterConst(String filterConst) {
		this.filterConst = filterConst;
	}

	/**
	 * @return the filterContent
	 */
	public String getFilterContent() {
		return filterContent;
	}

	/**
	 * @param filterContent
	 *            the filterContent to set
	 */
	public void setFilterContent(String filterContent) {
		this.filterContent = filterContent;
	}
}

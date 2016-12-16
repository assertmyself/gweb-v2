package com.gbcom.update.server.xml;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * 服务器端产品信息
 * 
 * @author doujun
 * 
 */
@XStreamAlias("UpdateServer")
public class UpdateServer {
	/**
	 * 项目名称
	 */
	@XStreamAsAttribute
	private String name;
	/**
	 * 产品名称
	 */
	@XStreamAsAttribute
	private String product;
	/**
	 * 版本名称
	 */
	@XStreamAsAttribute
	private String version;
	/**
	 * 版本号
	 */
	@XStreamAsAttribute
	private String no;
	/**
	 * 日期
	 */
	@XStreamAsAttribute
	private String date;
	/**
	 * 升级方式(sh/code)
	 */
	@XStreamAsAttribute
	private String method;

	/**
	 * 打包规则
	 */
	@XStreamAlias("ZipRules")
	private List<ZipRule> zipRules;
	/**
	 * 过滤请求
	 */
	@XStreamAlias("FilterRules")
	private List<FilterRule> filterRules;

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
	 * @return the product
	 */
	public String getProduct() {
		return product;
	}

	/**
	 * @param product
	 *            the product to set
	 */
	public void setProduct(String product) {
		this.product = product;
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version
	 *            the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * @return the no
	 */
	public String getNo() {
		return no;
	}

	/**
	 * @param no
	 *            the no to set
	 */
	public void setNo(String no) {
		this.no = no;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the method
	 */
	public String getMethod() {
		return method;
	}

	/**
	 * @param method
	 *            the method to set
	 */
	public void setMethod(String method) {
		this.method = method;
	}

	/**
	 * @return the zipRules
	 */
	public List<ZipRule> getZipRules() {
		return zipRules;
	}

	/**
	 * @param zipRules
	 *            the zipRules to set
	 */
	public void setZipRules(List<ZipRule> zipRules) {
		this.zipRules = zipRules;
	}

	/**
	 * @return the filterRules
	 */
	public List<FilterRule> getFilterRules() {
		return filterRules;
	}

	/**
	 * @param filterRules
	 *            the filterRules to set
	 */
	public void setFilterRules(List<FilterRule> filterRules) {
		this.filterRules = filterRules;
	}
}

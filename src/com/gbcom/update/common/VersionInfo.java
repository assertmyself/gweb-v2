package com.gbcom.update.common;

/**
 * 产品基本信息
 * 
 * @author doujun
 * 
 */
public class VersionInfo {
	/**
	 * 项目名称
	 */
	private String name;
	/**
	 * 产品
	 */
	private String product;
	/**
	 * 版本名称
	 */
	private String version;
	/**
	 * 版本号，构建好。
	 */
	private String no;
	/**
	 * 版本创建日期
	 */
	private String date;
	/**
	 * 升级方式
	 */
	private String method;

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
	 * @return string
	 */
	@Override
	public String toString() {
		return "VersionInfo [date=" + date + ", method=" + method + ", name="
				+ name + ", no=" + no + ", product=" + product + ", version="
				+ version + "]";
	}

}

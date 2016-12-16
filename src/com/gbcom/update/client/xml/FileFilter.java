package com.gbcom.update.client.xml;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * 客户端文件过滤
 * 
 * @author doujun
 * 
 */
@XStreamAlias("FileFilter")
public class FileFilter {
	/**
	 * 过滤文件相对路径（相对于contextpath）
	 */
	@XStreamAsAttribute
	private String path;

	/**
	 * 排除
	 */
	@XStreamAsAttribute
	private String exclude;

	/**
	 * 包含
	 */
	@XStreamAsAttribute
	private String include;

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path
	 *            the path to set
	 */
	public void setPath(String path) {
		this.path = path;
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
}

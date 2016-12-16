package com.gbcom.common.template.xml.tpl;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * TplInfo
 * 
 * <p>
 * 
 * @author syz
 *         <p>
 * @date 2015-6-18,下午01:37:03
 *       <p>
 * @version v1.0.0
 *          <p>
 * @see com.gbcom.ccsv3.template.xml.tpl.TplInfo
 */
@XStreamAlias("TplInfo")
public class TplInfo {
	@XStreamAsAttribute
	private String id;
	@XStreamAsAttribute
	private String name;
	@XStreamAsAttribute
	private String sysmodels;
	@XStreamAsAttribute
	private String jsp;

	@XStreamAlias("list")
	private List<Tplclz> clzList;

	/**
	 * @return id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            String
	 */
	public void setId(String id) {
		this.id = id;
	}

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
	 * @return sysmodels
	 */
	public String getSysmodels() {
		return sysmodels;
	}

	/**
	 * @param sysmodels
	 *            String
	 */
	public void setSysmodels(String sysmodels) {
		this.sysmodels = sysmodels;
	}

	/**
	 * @return jsp
	 */
	public String getJsp() {
		return jsp;
	}

	/**
	 * @param jsp
	 *            String
	 */
	public void setJsp(String jsp) {
		this.jsp = jsp;
	}

	/**
	 * @return clzList
	 */
	public List<Tplclz> getClzList() {
		return clzList;
	}

	/**
	 * @param clzList
	 *            List<Tplclz>
	 */
	public void setClzList(List<Tplclz> clzList) {
		this.clzList = clzList;
	}

}

package com.gbcom.common.template.xml.snmp;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * 指标组-XStream
 * 
 * @author SunYanzheng
 * @date 上午9:52:08
 * @version v1.0.0
 * @see PmCollectionGroup
 */
@XStreamAlias("group")
public class PmCollectionGroup {

	@XStreamAsAttribute
	private String id;

	@XStreamAsAttribute
	private boolean name;

	/**
	 * 
	 * @return String
	 */
	public String getId() {
		return id;
	}

	/**
	 * 
	 * @param id String
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 
	 * @return boolean
	 */
	public boolean isName() {
		return name;
	}

	/**
	 * 
	 * @param name boolean
	 */
	public void setName(boolean name) {
		this.name = name;
	}

	@XStreamAlias("list")
	private List<MibNode> mibNodeList;

	/**
	 * @return the mibNodeList
	 */
	public List<MibNode> getMibNodeList() {
		return mibNodeList;
	}

	/**
	 * @param mibNodeList
	 *            the mibNodeList to set
	 */
	public void setMibNodeList(List<MibNode> mibNodeList) {
		this.mibNodeList = mibNodeList;
	}

}

package com.gbcom.common.template.xml.snmp;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * oid 某一项的集合
 * 
 * @author SunYanzheng
 * @date 上午9:52:08
 * @version v1.0.0
 * @see MibNodeCollection
 */
@XStreamAlias("collection")
public class MibNodeCollection {
	@XStreamAsAttribute
	private String item;

	/**
	 * @return item
	 */
	public String getItem() {
		return item;
	}

	/**
	 * @param item
	 *            String
	 */
	public void setItem(String item) {
		this.item = item;
	}

	@XStreamAsAttribute
	private boolean isVector;

	/**
	 * @return boolean
	 */
	public boolean isVector() {
		return isVector;
	}

	/**
	 * @param isVector
	 *            boolean
	 */
	public void setVector(boolean isVector) {
		this.isVector = isVector;
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

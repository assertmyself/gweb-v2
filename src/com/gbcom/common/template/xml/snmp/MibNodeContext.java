package com.gbcom.common.template.xml.snmp;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
/**
 * 节点上下文
 * @author SunYanzheng
 * @date 下午4:28:05
 * @version v1.0.0
 * @see MibNodeContext
 */
@XStreamAlias("context")
public class MibNodeContext {

	private List<MibNodeCollection> collectionList;

	/**
	 * @return the collection
	 */
	public List<MibNodeCollection> getCollectionList() {
		return collectionList;
	}

	/**
	 * @param collectionList the collection to set
	 */
	public void setCollectionList(List<MibNodeCollection> collectionList) {
		this.collectionList = collectionList;
	}
	
	
}

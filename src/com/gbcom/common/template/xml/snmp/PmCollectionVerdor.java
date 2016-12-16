package com.gbcom.common.template.xml.snmp;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("verdor")
public class PmCollectionVerdor {
	@XStreamAsAttribute
	String name;
	private List<PmCollectionGroup> groupList;

	/**
	 * 
	 * @return List<PmCollectionGroup>
	 */
	public List<PmCollectionGroup> getGroupList() {
		return groupList;
	}

	/**
	 * 
	 * @param groupList List<PmCollectionGroup>
	 */
	public void setGroupList(List<PmCollectionGroup> groupList) {
		this.groupList = groupList;
	}

	/**
	 * 
	 * @return String
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name String
	 */
	public void setName(String name) {
		this.name = name;
	}

}

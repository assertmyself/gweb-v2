package com.gbcom.common.template.xml.snmp;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("context")
public class PmCollectionContext {

	private List<PmCollectionVerdor> verdorList;

	/**
	 * 获取性能采集节点
	 * @return List<PmCollectionVerdor>
	 */
	public List<PmCollectionVerdor> getVerdorList() {
		return verdorList;
	}

	/**
	 * 
	 * @param verdorList List<PmCollectionVerdor>
	 */
	public void setVerdorList(List<PmCollectionVerdor> verdorList) {
		this.verdorList = verdorList;
	}

}

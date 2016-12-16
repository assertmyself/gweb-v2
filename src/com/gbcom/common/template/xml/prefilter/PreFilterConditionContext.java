package com.gbcom.common.template.xml.prefilter;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("conditionContext")
public class PreFilterConditionContext {
	@XStreamAlias("filterSwitch")
	private boolean filterSwitch = false;
	@XStreamAlias("list")
	private List<PreFilterCondition> list;

	/**
	 * @return the list
	 */
	public List<PreFilterCondition> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<PreFilterCondition> list) {
		this.list = list;
	}

	/**
	 * : (PreFilterConditionContext.setFilterSwitch)
	 * @param filterSwitch boolean
	 */ 
	public void setFilterSwitch(boolean filterSwitch) {
		this.filterSwitch = filterSwitch;
	}

	/**
	 * : (PreFilterConditionContext.isFilterSwitch)
	 * @return boolean
	 */
	public boolean isFilterSwitch() {
		return filterSwitch;
	}
	
}

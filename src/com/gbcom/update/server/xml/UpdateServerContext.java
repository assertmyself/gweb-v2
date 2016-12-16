package com.gbcom.update.server.xml;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 服务器端产品信息
 * 
 * @author doujun
 * 
 */
@XStreamAlias("UpdateServerContext")
public class UpdateServerContext {

	/**
	 * 版本信息
	 */
	@XStreamAlias("list")
	private List<UpdateServer> list;

	/**
	 * 开关
	 */
	@XStreamAlias("updateSwitch")
	private boolean updateSwitch = false;

	/**
	 * @return the list
	 */
	public List<UpdateServer> getList() {
		return list;
	}

	/**
	 * @param list
	 *            the list to set
	 */
	public void setList(List<UpdateServer> list) {
		this.list = list;
	}

	/**
	 * @return the updateSwitch
	 */
	public boolean isUpdateSwitch() {
		return updateSwitch;
	}

	/**
	 * @param updateSwitch
	 *            the updateSwitch to set
	 */
	public void setUpdateSwitch(boolean updateSwitch) {
		this.updateSwitch = updateSwitch;
	}

}

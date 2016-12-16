package com.gbcom.update.client.xml;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 客户端产品信息
 * 
 * @author doujun
 * 
 */
@XStreamAlias("UpdateClientContext")
public class UpdateClientContext {
	/**
	 * 客户端版本信息
	 */
	@XStreamAlias("UpdateClient")
	private UpdateClient updateClient;

	/**
	 * @return the updateClient
	 */
	public UpdateClient getUpdateClient() {
		return updateClient;
	}

	/**
	 * @param updateClient
	 *            the updateClient to set
	 */
	public void setUpdateClient(UpdateClient updateClient) {
		this.updateClient = updateClient;
	}
}

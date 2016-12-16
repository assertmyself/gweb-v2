/**
 * @(#)IVIMService.java       07/11
 *
 * Copyright (c) 2007 GBCom Communication Technology Co.Ltd
 * All right reserved.
 */
package com.gbcom.common.im;

import com.gbcom.common.im.exception.IMInitialException;

import java.util.HashMap;

/**
 * 显示模型的服务提供接口
 * 
 * @author fengjing
 * @version 2.0
 * 
 */
public interface IIMService {
	/**
	 * 信息模型和显示模型初始化
	 * 
	 * @return 是否初始化成功，是则返回true，否则返回false
	 * @throws IMInitialException com.gbcom.common.im.exception.IMInitialException
	 */
	public boolean initial() throws IMInitialException;

	/**
	 * 根据网元类型和版本号获取信息模型
	 * 
	 * @param type
	 *            网元类型
	 * @param version
	 *            版本号
	 * 
	 * @return 信息模型访问接口
	 */
	public IIM getIIM(String type, String version);

	/**
	 * 返回信息模型
	 * 
	 * @return 信息模型和显示模型
	 */
	public HashMap<String, IMVersionChain> getIM();

	/**
	 * 根据网元类型返回版本链
	 * 
	 * @param type
	 *            网元类型
	 * @return 版本链
	 */
	public IMVersionChain getIMChain(String type);
}

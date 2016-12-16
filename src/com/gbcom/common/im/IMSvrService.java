/**
 * @(#)VIMSvrService.java       07/11
 *
 * Copyright (c) 2007 GBCom Communication Technology Co.Ltd
 * All right reserved.
 */

package com.gbcom.common.im;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.apache.log4j.Logger;

import com.gbcom.common.im.desc.IAttributeDesc;
import com.gbcom.common.im.desc.IClassDesc;
import com.gbcom.common.im.exception.IMInitialException;
import com.gbcom.common.im.exception.IllegalCmArgumentException;
import com.gbcom.common.im.parse.DefaultParserFactory;
import com.gbcom.common.im.parse.alarm.IAlarmParser;

/**
 * 服务器的信息模型、显示模型调用接口，实现了IVIMService模型
 * 
 * <p>
 * 
 * @author syz
 *         <p>
 * @date 2015-6-8,下午01:17:47
 *       <p>
 * @version v1.0.0
 *          <p>
 * @see com.gbcom.common.im.IMSvrService
 */
public class IMSvrService implements IIMService {
	/**
	 * 记录日志
	 */
	private static final Logger log = Logger.getLogger(IMSvrService.class);

	private boolean init = false;
	/**
	 * 静态唯一实例
	 */
	private static IMSvrService instance = null;

	/**
	 * 版本链的map,
	 */
	private HashMap<String, IMVersionChain> imVersionMap;

	/**
	 * 构造函数
	 */
	private IMSvrService() {
	}

	/**
	 * 单实例模式
	 * 
	 * @return 返回唯一实例
	 */
	public static IMSvrService getInstance() {
		//off checkstyle
		if (instance == null) {
			synchronized (IMSvrService.class) {
				
				if (instance == null) {
					instance = new IMSvrService();
				}
			}
		}
		return instance;
	}

	/**
	 * 初始化信息模型 从文件解析获取
	 * 
	 * @return 初始化是否成功，是则返回true，否则返回false
	 * @throws IMInitialException com.gbcom.common.im.exception.IMInitialException
	 * 
	 */
	public boolean initial() throws IMInitialException {
		if (this.imVersionMap == null) {
			this.imVersionMap = new HashMap<String, IMVersionChain>();
		}

		IMConfiguration imConfig = IMConfiguration.getInstance();

		String filePath = this.getClass().getClassLoader().getResource("IM/")
				.getFile();
		log.info("IVM init .0......" + filePath);

		File file = new File(filePath);
		File[] files = file.listFiles();
		if (files == null) {
			throw new IMInitialException("not exit im file in directory:im");
		}

		for (File f : files) {
			if (f.isDirectory()) {/*
								 * File[] devFiles = f.listFiles(); if (devFiles
								 * != null) { for (File devFile : devFiles) { if
								 * (
								 * devFile.getName().toLowerCase().endsWith("im.xml"
								 * )) { this.putIM(imConfig.buildIM(devFile)); }
								 * } }
								 */
			} else {
				if (f.getName().toLowerCase().endsWith("im.xml")) {
					this.putIM(imConfig.buildIM(f));
				}

			}
		}

		// 信息模型解析完成后调用JointManager进行内模型联接
		JointManager.startJoint();
		this.init = true;
		return true;
	}

	/**
	 * 
	 * @return boolean
	 */
	public boolean isInit() {
		return this.init;
	}

	/**
	 * 添加信息模型
	 * 
	 * @param iim
	 *            信息模型实例
	 */
	private void putIM(IIM iim) {
		if (this.imVersionMap.containsKey(iim.getType())) {
			this.imVersionMap.get(iim.getType()).putIm(iim.getVersion(), iim);
		} else {
			IMVersionChain imVersionChain = new IMVersionChain(iim.getType());
			imVersionChain.putIm(iim.getVersion(), iim);
			this.imVersionMap
					.put(imVersionChain.getType() /*
												 * + "_" +
												 * iim.getRoot().getName()
												 */, imVersionChain);
		}
	}

	/**
	 * 根据网元类型和版本号获取信息模型
	 * 
	 * @param type
	 *            网元类型
	 * @param version
	 *            版本号
	 * @return 信息模型访问接口
	 */
	public IIM getIIM(String type, String version) {
		IMVersionChain vc = (IMVersionChain) this.imVersionMap.get(type);
		if (vc == null) {
			log.error("can't find IMVersionChain by type:" + type,
					new IllegalCmArgumentException("the input type not exist:"
							+ type));
			throw new IllegalCmArgumentException("the input type not exist:"
					+ type);
		}

		return vc.getIM(version);
	}

	/**
	 * 获取指定 设备类型 默认版本。(v1.0.0);
	 * 
	 * @param type
	 *            设备类型。
	 * @return IIM
	 */
	public IIM getDefaultIIM(String type) {
		IMVersionChain vc = (IMVersionChain) this.imVersionMap.get(type);
		if (vc == null) {
			log.error("can't find IMVersionChain by type:" + type,
					new IllegalCmArgumentException("the input type not exist:"
							+ type));
			throw new IllegalCmArgumentException("the input type not exist:"
					+ type);
		}

		return vc.getIM("v1.0.0");
	}

	/**
	 * 获取指定 设备类型 默认版本。(v1.0.0);
	 * 
	 * @return IIM
	 */
	public IIM getDefaultIIM() {
		IMVersionChain vc = (IMVersionChain) this.imVersionMap.get("AP");
		if (vc == null) {
			log.error("can't find IMVersionChain by type:AP",
					new IllegalCmArgumentException("the input type not exist:"
							+ "AP"));
			throw new IllegalCmArgumentException("the input type not exist:"
					+ "AP");
		}

		return vc.getIM("1.0.0");
	}

	/**
	 * 获取所有keys
	 * 
	 * @return LinkedHashMap<String, String[]>
	 */
	public LinkedHashMap<String, String[]> getKeys() {
		LinkedHashMap<String, String[]> keys = new LinkedHashMap<String, String[]>();
		for (IClassDesc desc : getDefaultIIM().getRoot().getChildren()) {
			if (desc.isVector()) {
				// 矢量，需要全集。
				for (int i = 0; i < desc.getMaxIndex(); i++) {
					IAttributeDesc[] attris = desc.getAttributeDescs();
					String[] classKey = new String[attris.length];
					for (int y = 0; y < attris.length; y++) {
						classKey[y] = attris[y].getName().replace("{index}",
								i + "");
					}
					keys.put(desc.getName() + "-" + i, classKey);
				}
			} else {
				IAttributeDesc[] attris = desc.getAttributeDescs();
				String[] classKey = new String[attris.length];
				for (int y = 0; y < attris.length; y++) {
					classKey[y] = attris[y].getName();
				}
				keys.put(desc.getName(), classKey);
			}
		}

		return keys;

	}

	/**
	 * 返回显示模型和信息模型列表
	 * 
	 * @return 返回列表
	 */
	public HashMap<String, IMVersionChain> getIM() {
		return this.imVersionMap;
	}

	/**
	 * 根据cid返回版本链
	 * 
	 * @param cid
	 *            类型ID
	 * @return 版本链
	 */
	/*
	 * public IMVersionChain getIMChain(int cid) { Iterator it =
	 * this.imVersionMap.values().iterator(); while (it.hasNext()) {
	 * IMVersionChain vc = (IMVersionChain) it.next(); if (vc.contains(cid)) {
	 * return vc; } } log.error("can't find IMVersionChain by cid:" + cid, new
	 * IllegalCmArgumentException("the input cid not exist:" + cid)); throw new
	 * IllegalCmArgumentException("the input cid not exist:" + cid); }
	 */

	/**
	 * 根据类型返回版本链
	 * 
	 * @param type
	 *            网元类型：厂家+设备型号
	 * @return 版本链
	 */
	public IMVersionChain getIMChain(String type) {
		if (this.imVersionMap.get(type) != null) {
			return this.imVersionMap.get(type);
		}
		log.error("can't find VIMVersionChain by type:" + type,
				new IllegalCmArgumentException("the input type not exist:"
						+ type));
		throw new IllegalCmArgumentException("the input type not exist:" + type);
	}

	/**
	 * 获取上报解析器
	 * 
	 * @param type String
	 * @param model String
	 * @param version String
	 * @return IAlarmParser
	 */
	public IAlarmParser getAlarmParserParser(String type, String model,
			String version) {
		IIM iim = getIIM(type + "_" + model, version);
		if (iim == null) {
			return null;
		}
		String className = iim.getRoot().getAlarmParserClassName();
		return DefaultParserFactory.getInstance().getAlarmParser(className);
	}
}
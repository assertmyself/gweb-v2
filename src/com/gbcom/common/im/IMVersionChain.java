/**
 * @(#)VIMVersionChain.java       07/11
 *
 * Copyright (c) 2007 GBCom Communication Technology Co.Ltd
 * All right reserved.
 */
package com.gbcom.common.im;

import com.gbcom.common.im.exception.IllegalCmArgumentException;

import java.io.Serializable;
import java.util.HashMap;

/**
 * 存放信息模型和显示模型版本链 按照网元类型存放所有版本的显示模型和信息模型
 * 
 * @author fengjing
 * @version 2.0
 * 
 */
public class IMVersionChain implements Serializable {
	/**
	 * 序列化版本ID
	 */
	private static final long serialVersionUID = 6540054084653216832L;

	/**
	 * 网元类型
	 */
	private String type = null;

	/**
	 * VIMItem列表
	 */
	private HashMap<String, IMItem> itemMap = new HashMap<String, IMItem>();

	/**
	 * 构造函数，必须传入网元类型
	 * 
	 * @param type String
	 */
	public IMVersionChain(String type) {
		this.type = type;
	}

	/**
	 * 存放信息模型，如果已经有该版本的信息模型则覆盖
	 * 
	 * @param version
	 *            版本号
	 * @param iim
	 *            信息模型实例
	 */
	public void putIm(String version, IIM iim) {
		if (itemMap.get(version) == null) {
			IMItem item = new IMItem(version);
			item.setIM(iim);
			itemMap.put(version, item);
			return;
		}

		itemMap.get(version).setIM(iim);
	}

	/**
	 * 根据版本号，获取信息模型
	 * 
	 * @param version
	 *            版本号
	 * @return 信息模型实例
	 */
	public IIM getIM(String version) {
		if (version == null || "".equals(version)) {
			IMItem[] item = this.itemMap.values().toArray(
					new IMItem[this.itemMap.values().size()]);
			if (item != null && item.length > 0) {
				return item[0].getIM();
			}
			throw new IllegalCmArgumentException("input type IIM not exist:"
					+ this.type);
		}

		if (this.itemMap.get(version) == null) {
			throw new IllegalCmArgumentException("input verion IIM not exist:"
					+ version + " in type:" + this.type);
		}

		return this.itemMap.get(version).getIM();
	}

	/**
	 * 获取该网元下所有版本号
	 * 
	 * @return 返回该网元下所有版本号
	 */
	public String[] getVersionList() {
		return this.itemMap.keySet().toArray(new String[0]);
	}

	/**
	 * 获取网元类型
	 * 
	 * @return 返回该chain对应的网元类型
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * 获取每个版本的显示模型和信息模型Item
	 * 
	 * @return 获取每个版本的显示模型和信息模型
	 */
	public IMItem[] getAllIM() {
		return this.itemMap.values().toArray(new IMItem[0]);
	}

	/**
	 * 判断该chain是否包含某个指定的cid
	 * 
	 * @param cid
	 *            指定的cid
	 * @return 是否包含，是则返回true，否则返回false
	 */
	public boolean contains(int cid) {
		// 获取所有模型版本信息列表
		String[] vers = this.getVersionList();

		if (vers == null) {
			return false;
		}

		for (int i = 0; i < vers.length; i++) {
			IIM tempIM = this.getIM(vers[i]);
			if (tempIM != null && tempIM.getClassDesc(cid) != null) {
				return true;
			}
		}

		return false;
	}

	/**
	 * 删除版本链上的一个模型项
	 * 
	 * @param version
	 *            版本号
	 */
	public void dropItem(String version) {
		itemMap.remove(version);
	}

	/**
	 * 显示模型和信息模型条目 每个版本对应 显示模型和信息模型各一个实例
	 * 
	 * @author fengjing
	 * @version 2.0
	 * 
	 */
	public class IMItem implements Serializable {
		/**
		 * 序列化版本ID
		 */
		private static final long serialVersionUID = 3160684320350465214L;

		/**
		 * 版本号
		 */
		private String version = null;

		/**
		 * 信息模型
		 */
		private IIM defaultIM = null;

		/**
		 * 构造函数
		 * 
		 * @param version
		 *            版本号
		 * @param defaultIM
		 *            对应的信息模型
		 */
		protected IMItem(String version, IIM defaultIM) {
			this.version = version;
			this.defaultIM = defaultIM;
		}

		/**
		 * 构造函数
		 * 
		 * @param version
		 *            版本号
		 */
		protected IMItem(String version) {
			this.version = version;
		}

		/**
		 * 设置信息模型
		 * 
		 * @param im
		 *            信息模型
		 */
		protected void setIM(IIM im) {
			this.defaultIM = im;
		}

		/**
		 * 获取该项的信息模型
		 * 
		 * @return 信息模型
		 */
		public IIM getIM() {
			return this.defaultIM;
		}

	}
}

/**
 * @(#)JointManager.java       07/11
 *
 * Copyright (c) 2007 GBCom Communication Technology Co.Ltd
 * All right reserved.
 */
package com.gbcom.common.im;

import java.lang.reflect.Array;
import java.util.ArrayList;

import com.gbcom.common.im.desc.ClassDesc;

import org.apache.log4j.Logger;

/**
 * 由于信息模型是按每个网元的版本划分,各个模型文件解析到内存后需要平滑串成整个树 此类是临时记录内部模型的子节点联接到各个网元节点的数据
 * 待所所有模型文件解析结束后再根据记录的临时信息联接起来
 * 
 * @author fengjing
 * @version 2.0
 * 
 */
public class JointManager {
	/**
	 * 信息模型的联接信息
	 */
	private static ArrayList<JointItem> imJointList = null;

	/**
	 * 记录日志
	 */
	private static final Logger log = Logger.getLogger(JointManager.class);

	/**
	 * 存放信息模型的联接信息
	 * 
	 * @param type
	 *            网元类型
	 * @param version
	 *            网元版本号
	 * 
	 * @param classId
	 *            类id
	 * @param childNeType
	 *            网元类型
	 */
	public static synchronized void putIMJointInfo(String type, String version,
			int classId, String childNeType) {
		if (imJointList == null) {
			imJointList = new ArrayList<JointItem>();
		}

		JointItem tmp = getJointItem(type, version, classId);
		if (imJointList.contains(tmp)) {
			for (JointItem item : imJointList) {
				if (item.equals(tmp)) {
					item.addChildNeType(childNeType);
				}
			}
		} else {
			tmp.addChildNeType(childNeType);
			imJointList.add(tmp);
		}
	}

	/**
	 * 开始联接
	 * 
	 */
	public static synchronized void startJoint() {
		if (imJointList != null) {
			for (JointItem item : imJointList) {
				ClassDesc classDesc = (ClassDesc) IMSvrService.getInstance()
						.getIIM(item.getType(), item.getVersion())
						.getClassDesc(item.classId);
				String[] childNeTypes = item.getChildNEType();
				if (childNeTypes != null && classDesc != null) {
					for (String childNeType : childNeTypes) {
						try {
							IMVersionChain versionChain = IMSvrService
									.getInstance().getIMChain(childNeType);
							if (versionChain != null) {
								IMVersionChain.IMItem[] vimItems = versionChain
										.getAllIM();
								if (vimItems != null) {
									for (IMVersionChain.IMItem vimItem : vimItems) {
										classDesc.addChild(vimItem.getIM()
												.getRoot());
										((ClassDesc) vimItem.getIM().getRoot())
												.setParent(classDesc);
									}
								}
							}
						} catch (Exception e) {
							log.warn("can't find ChildNe Type " + childNeType
									+ " at ClassDesc:" + classDesc.getID()
									+ " when joint IM");
						}
					}
				}
			}
		}

		// 联接完成后把内存中的联接信息删除
		imJointList = null;
	}

	/**
	 * 获取
	 * 
	 * @param type
	 * @param version
	 * @param classId
	 * @return
	 */
	private static JointItem getJointItem(String type, String version,
			int classId) {
		return new JointItem(type, version, classId);
	}

	/**
	 * 存储连接网元信息类
	 * 
	 * @author
	 * 
	 */
	private static class JointItem {
		/**
		 * 网元类型
		 */
		final String type;

		/**
		 * 网元版本
		 */
		private final String version;

		/**
		 * 类id
		 */
		private final int classId;

		/**
		 * 需要连接的网元类型
		 */
		private String[] childNEType;

		/**
		 * 构造函数
		 * 
		 * @param type
		 *            网元类型
		 * @param version
		 *            网元版本
		 * @param classId
		 *            类ID
		 */
		public JointItem(String type, String version, int classId) {
			this.type = type;
			this.version = version;
			this.classId = classId;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		public boolean equals(Object obj) {
			if (obj instanceof JointItem) {
				JointItem item = (JointItem) obj;
				if (this.type == null || this.version == null) {
					return false;
				}

				if (this.type.equals(item.getType())
						&& this.version.equals(item.getVersion())
						&& this.classId == item.getClassId()) {
					return true;
				}
				return false;
			} else {
				return false;
			}
		}

		/**
		 * 获取HashCode
		 */
		public int hashCode() {
			return new HashCodeBuilder().append(this.type).append(this.version)
					.append(this.classId).toHashCode();
		}

		/**
		 * 增加网元类型
		 * 
		 * @param childType
		 *            待增加的子网元类型
		 */
		public void addChildNeType(String childType) {
			if (this.childNEType == null || this.childNEType.length < 1) {
				this.childNEType = this.childNEType == null ? (String[]) Array
						.newInstance(childType.getClass(), 1)
						: (String[]) Array.newInstance(this.childNEType
								.getClass().getComponentType(), 1);
				this.childNEType[0] = childType;
				return;
			}

			for (int i = 0; i < this.childNEType.length; i++) {
				if (this.childNEType[i].equals(childType)) {
					return;
				}
			}

			String[] result = null;
			result = (String[]) Array.newInstance(this.childNEType.getClass()
					.getComponentType(), this.childNEType.length + 1);
			System.arraycopy(this.childNEType, 0, result, 0,
					this.childNEType.length);
			result[result.length - 1] = childType;
			this.childNEType = result;
		}

		/**
		 * 获得类型ID
		 * 
		 * @return 返回类ID
		 */
		public int getClassId() {
			return classId;
		}

		/**
		 * 获得网元类型
		 * 
		 * @return 返回网元类型
		 */
		public String getType() {
			return type;
		}

		/**
		 * 获得网元版本号
		 * 
		 * @return 返回网元版本号
		 */
		public String getVersion() {
			return version;
		}

		/**
		 * 获得子网元类型数组
		 * 
		 * @return 返回子网元类型数组
		 */
		public String[] getChildNEType() {
			return childNEType;
		}

	}
}

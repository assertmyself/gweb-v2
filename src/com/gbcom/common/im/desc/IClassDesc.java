/**
 * @(#)IClassDesc.java       07/10
 *
 * Copyright (c) 2007 GBCom Communication Technology Co.Ltd
 * All right reserved.
 */
package com.gbcom.common.im.desc;

import java.io.Serializable;

import com.gbcom.common.im.ds.IIDNameStruct;
import com.gbcom.common.im.parse.alarm.IAlarmParser;

/**
 * 类描述接口
 * 
 * @author fengjing
 * @version 2.0
 * 
 */
public interface IClassDesc extends IIDNameStruct, Serializable {

	/**
	 * 是否创建时可见
	 */
	public static final int IS_AVAILABLE_IN_CREATE_MASK = 8;

	/**
	 * 是否修改时可见
	 */
	public static final int IS_AVAILABLE_IN_MODIFY_MASK = 16;

	/**
	 * 根据类描述cid获取本类描述下子类描述
	 * 
	 * @param cid
	 *            本类的子类cid
	 * @return 类描述接口
	 */
	public IClassDesc getChild(int cid);

	/**
	 * 根据类描述名字获取本类描述下子类描述
	 * 
	 * @param cName
	 *            本类的子类名字
	 * @return 类描述接口
	 */
	public IClassDesc getChild(String cName);

	/**
	 * 获取本类的所有子类描述
	 * 
	 * @return 类描述接口数组
	 */
	public IClassDesc[] getChildren();

	/**
	 * 获取本类的父类描述
	 * 
	 * @return 类描述接口
	 */
	public IClassDesc getParent();

	/**
	 * 获取本类的版本
	 * 
	 * @return String
	 */
	public String getVersion();

	/**
	 * 获取本类的网元类型
	 * 
	 * @return String
	 */
	public String getType();

	/**
	 * 获取本类指定id的属性描述接口
	 * 
	 * @param aid
	 *            类属性描述id
	 * @return 类属性描述接口
	 */
	public IAttributeDesc getAttributeDesc(int aid);

	/**
	 * 获取本类指定名字的属性描述接口
	 * 
	 * @param attributeName
	 *            类属性描述名字
	 * @return 类属性描述接口
	 */
	public IAttributeDesc getAttributeDesc(String attributeName);

	/**
	 * 获取本类所有属性接口
	 * 
	 * @return IAttributeDesc[]类属性描述接口数组
	 */
	public IAttributeDesc[] getAttributeDescs();

	/**
	 * 获取非OMC属性
	 * 
	 * @return IAttributeDesc[]
	 */
	public IAttributeDesc[] getNonMOCAttributeDescs();

	/**
	 * 获取显示名
	 * 
	 * @return String类的界面显示名
	 */
	public String getUIName();

	/**
	 * 获取类名
	 * @return String
	 */
	public String getName();

	/**
	 * 返回关键属性描述接口
	 * 
	 * @return 关键属性描述接口数组
	 */
	public IAttributeDesc[] getKeyAttributeDesc();

	/**
	 * 返回管理状态属性描述接口
	 * 
	 * @return 关键属性描述接口数组
	 */
	public IAttributeDesc getManageStatusAttributeDesc();

	/**
	 * 返回管理状态属性描述接口
	 * 
	 * @return 关键属性描述接口数组
	 */
	public IAttributeDesc getRunStatusAttributeDesc();

	/**
	 * 返回管理状态属性描述接口
	 * 
	 * @return 关键属性描述接口数组
	 */
	public IAttributeDesc getRunDetailStatusAttributeDesc();

	/**
	 * 返回框架属性描述接口
	 * 
	 * @return IAttributeDesc[]框架属性描述接口数组
	 */
	public IAttributeDesc[] getSkeletonAttributeDesc();

	/**
	 * 友邻属性
	 * @return IAttributeDesc
	 */
	public IAttributeDesc getFriendAttributeDesc();

	/**
	 * 友邻属性集合
	 * 
	 * @return IAttributeDesc[]
	 */
	public IAttributeDesc[] getFriendAttributeDescs();

	/**
	 * 获取告警解析类名接口
	 * 
	 * @return String
	 */

	public String getAlarmParserClassName();

	/**
	 * 返回该类告警解析器接口
	 * 
	 * @return 类对象的告警解析器接口
	 */
	public IAlarmParser getAlarmParser();

	/**
	 * 根据aid获取其列表顺序（数组下标） 这个方法主要是提供给持久层调用 由于持久层按照属性列表的顺序（数组下标）依次存储在100列的表中
	 * 因此需要知道每个aid所在的顺序（数组下标）
	 * 
	 * @param aid int
	 * @return int 顺序id
	 */
	public int getAttributeDescIndex(int aid);

	/**
	 * 根据属性列表中的顺序id（数组下标）获取属性描述 这个方法主要是提供给持久层调用 由于持久层按照属性列表的顺序（数组下标）依次存储在100列的表中
	 * 因此需要知道每个顺序列对应的属性描述
	 * 
	 * @param index int
	 * @return IAttributeDesc指定ID下的属性描述
	 */
	public IAttributeDesc getAttributeDescByIndex(int index);

	/**
	 * 最大索引，syz 配置模板
	 * @return int
	 */
	public int getMaxIndex();

	/**
	 * set 最大索引
	 * @param max int
	 */
	public void setMaxIndex(int max);

	/**
	 * 是否是标量
	 * 
	 * @return 结果
	 */
	public boolean isVector();

	/**
	 * @return the mask
	 */
	public int getMask();

	/**
	 * @param mask
	 *            the mask to set
	 */
	public void setMask(int mask);

	/**
	 * 判断创建时是否可见
	 * 
	 * 
	 * @return 属性创建时是否可见，是则返回true，否则返回false
	 */
	public boolean isAvaliableInCreate();

	/**
	 * 判断修改时是否可见
	 * 
	 * 
	 * @return 属性修改时是否可见，是则返回true，否则返回false
	 */
	public boolean isAvaliableInModify();

}

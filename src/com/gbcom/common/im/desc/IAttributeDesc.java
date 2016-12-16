/**
 * @(#)IAttributeDesc.java       07/10
 *
 * Copyright (c) 2007 GBCom Communication Technology Co.Ltd
 * All right reserved.
 */
package com.gbcom.common.im.desc;

import com.gbcom.common.im.ds.IIDNameStruct;

/**
 * 属性描述接口
 * 
 * @author fengjing
 * @version 2.0
 * 
 */
public interface IAttributeDesc extends IIDNameStruct {
	/**
	 * 是否为关键属性 用于标识该属性是生成dn用
	 */
	public static final int IS_KEY_MASK = 1;

	/**
	 * 是否为网元关键属性 用于标识为该属性是网元定义的关键属性
	 */
	public static final int IS_NE_KEY_MASK = 2;

	/**
	 * 是否为只读属性
	 */
	public static final int IS_READ_ONLY_MASK = 4;

	/**
	 * 是否创建时可见
	 */
	public static final int IS_AVAILABLE_IN_CREATE_MASK = 8;

	/**
	 * 是否修改时可见
	 */
	public static final int IS_AVAILABLE_IN_MODIFY_MASK = 16;

	/**
	 * 是否显示可见
	 */
	public static final int IS_VISIBLE_MASK = 32;

	/**
	 * 是否为复杂属性
	 */
	public static final int IS_COMPLEX_MASK = 64;

	/**
	 * 是否可批量修改
	 */
	public static final int IS_BATCH_MODIFIED_MASK = 128;

	/**
	 * 是否为OMC内部属性
	 */
	public static final int IS_OMC_MASK = 256;

	/**
	 * 是否为SKT所需属性
	 */
	public static final int IS_SKT_NEED_MASK = 512;

	/**
	 * 是否是友好名属性
	 */
	public static final int IS_FRIEND_NAME_MASK = 1024;

	/**
	 * 是否为研发时可见
	 */
	public static final int IS_RD_VISIBLE_MASK = 2048;

	/**
	 * 是否为数组属性
	 */
	public static final int IS_ARRAY_MASK = 4096;

	/**
	 * 是否依赖父对象相关属性
	 */
	public static final int IS_DEPEND_ON_PARENT_MASK = 8192;

	/**
	 * 是否为ＭＯＣ
	 */
	public static final int IS_MOC_MASK = 16384;

	/**
	 * 是否为ＭＯＩ
	 */
	public static final int IS_MOI_MASK = 32768;

	/**
	 * 是否为管理状态
	 */
	public static final int IS_STATUS_MANAGER_MASK = 65536;

	/**
	 * 是否为运行状态
	 */
	public static final int IS_STATUS_RUN_MASK = 131072;

	/**
	 * 是否为运行详细状态
	 */
	public static final int IS_STATUS_RUNDETAIL_MASK = 262144;

	/**
	 * 是否研发创建时可见
	 */
	public static final int IS_RESEARCH_AVAILABLE_IN_CREATE_MASK = 524288;

	/**
	 * 是否研发修改时可见
	 */
	public static final int IS_RESEARCH_AVAILABLE_IN_MODIFY_MASK = 1048576;

	/**
	 * 是否动态属性
	 */
	public static final int IS_DYNAMIC_STATUS_MASK = 2097152;

	/**
	 * 获取属性显示名
	 * 
	 * @return String 属性显示名
	 */
	public String getUIName();

	/**
	 * 获取属性名
	 * @return String
	 */
	public String getName();

	/**
	 * 获取数据类型
	 * 
	 * @return DataType 数据类型
	 */
	public DataType getDataType();

	/**
	 * 获取该属性期望的控件的类型，用于指导客户端界面操作用
	 * 
	 * @return ControlType 属性期望的控件的类型
	 */
	public ControlType getControlType();

	/**
	 * 获取该属性的长度 属性的长度是指该属性的真实字节长度 如果是U16的数组长度为2的数组，那么其字节长度应为32个字节。 日期型的长度为4个字节
	 * 如果是不能处理的数据类型，则返回长度为－1
	 * 
	 * @return 该属性的长度
	 */
	public int getLength();

	/**
	 * 获取属性数组长度 如果数据类型为数组型的则返回其数组的长度，如果数据类型为U16,则应该真实的字节长度除以2
	 * 如果控件类型为结构数组的则返回的数组长度为其结构的属性个数,结构数组的数组长度通过getMax获得。 如果不是数据型数据类型和数组结构型则返回-1
	 * 
	 * @return int
	 */
	public int getArrayLength();

	/**
	 * 获取属性缺省值
	 * 
	 * @return String 属性缺省值
	 */
	public String getDefaultValue();

	/**
	 * 获取属性最大值
	 * 
	 * @return int 该属性的最大值
	 */
	public String getMaxValue();

	/**
	 * 获取属性最小值
	 * 
	 * @return String 该属性的最小值
	 */
	public String getMinValue();

	/**
	 * 返回该属性的掩码
	 * 
	 * @return 属性掩码
	 */
	public int getMask();

	/**
	 * 判断该属性是否是关键属性 用于指定该属性是生成DN的属性
	 * 
	 * @return 是否关键属性，是则返回true，否则返回false
	 */
	public boolean isKey();

	/**
	 * 判断该属性是否是MOC
	 * 
	 * @return boolean 是否是MOC，是则返回true，否则返回false
	 */
	public boolean isMOC();

	/**
	 * 判断该属性是否是MOI
	 * 
	 * @return boolean
	 */
	public boolean isMOI();

	/**
	 * 判断该属性是否是网元关键属性 用于标识网元多个关键属性
	 * 
	 * @return 是否与其他属性相关，是则返回true，否则返回false
	 */
	public boolean isNeKey();

	/**
	 * 判断该属性是否只读
	 * 
	 * @return 是否只读，是则返回true，否则返回false
	 */
	public boolean isReadOnly();

	/**
	 * 判断该属性是否可以批量修改
	 * 
	 * @return boolean 是否可批量修改，是则返回true，否则返回false
	 */
	public boolean isBatchModifiable();

	/**
	 * 判断该属性是否为复杂属性
	 * 
	 * @return 是否为复杂属性，是则返回true，否则返回false
	 */
	public boolean isComplex();

	/**
	 * 判断创建时是否可见
	 * 
	 * @return 属性创建时是否可见，是则返回true，否则返回false
	 */
	public boolean isAvaliableInCreate();

	/**
	 * 判断修改时是否可见
	 * 
	 * @return 属性修改时是否可见，是则返回true，否则返回false
	 */
	public boolean isAvaliableInModify();

	/**
	 * 判断该属性界面上是否可见
	 * 
	 * @return 属性在界面上是否可见，是则返回true，否则返回false
	 */
	public boolean isVisible();

	/**
	 * 判断该属性是否是OMC内部属性
	 * 
	 * @return 是否为OMC内部属性，是则返回true，否则返回false
	 */
	public boolean isOMC();

	/**
	 * 判断该属性是否SKT树所需属性
	 * 
	 * @return 是否为SKT所需属性，是则返回true，否则返回false
	 */
	public boolean isSKTNeeded();

	/**
	 * 判断该属性是否为友好名
	 * 
	 * @return boolean 是否为友好名，是则返回true，否则返回false
	 */
	public boolean isFriendlyName();

	/**
	 * 判断该属性是否仅对研发可见
	 * 
	 * @return boolean 是否仅对研发可见，是则返回true，否则返回false
	 */
	public boolean isRDVisible();

	/**
	 * 判断该属性是否是数组属性 如果是该值显示3，它们该属性的接下来的三个属性需要显示并且以树形式显示在本属性下面
	 * 
	 * @return 该属性是否是数组属性,若是则返回true，否则返回false
	 */
	public boolean isArray();

	/**
	 * 判断该属性是否是管理状态属性
	 * 
	 * @return 该属性是否为管理状态属性，若是返回true，否则返回false
	 */
	public boolean isManageStatus();

	/**
	 * 判断该属性是否是运行状态属性
	 * 
	 * @return 该属性是否为运行状态，若是
	 */
	public boolean isRunStatus();

	/**
	 * 判断该属性是否是运行详细属性
	 * 
	 * @return boolean
	 */
	public boolean isRunDetailStatus();

	/**
	 * 判断该属性是否依赖于父对象的相同属性
	 * 
	 * @return boolean
	 */
	public boolean isDependOnParent();

	/**
	 * 判断创建时是否研发可见
	 * 
	 * @return boolean 属性创建时是否可见，是则返回true，否则返回false
	 */
	public boolean isResearchAvaliableInCreate();

	/**
	 * 判断修改时是否研发可见
	 * 
	 * @return boolean 属性修改时是否可见，是则返回true，否则返回false
	 */
	public boolean isResearchAvaliableInModify();

	/**
	 * 判断是否是动态属性（和管理状态、运行状态、详细运行状态并列的，但不一致）
	 * 
	 * @return boolean
	 */
	public boolean isDynamicStatus();
}

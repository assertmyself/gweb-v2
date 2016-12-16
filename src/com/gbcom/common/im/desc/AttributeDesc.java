/**
 * @(#)AttributeDesc.java       07/11
 *
 * Copyright (c) 2007 GBCom Communication Technology Co.Ltd
 * All right reserved.
 */

package com.gbcom.common.im.desc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 属性描述类
 * 
 * @author fengjing
 * @version 2.0
 * 
 */
public class AttributeDesc implements IAttributeDesc {
	/**
	 * 序列化版本ID
	 */
	private static final long serialVersionUID = -8211634513815581627L;

	/**
	 * 日志记录
	 */
	/**
	 * 记录日志
	 */
	private Logger logger = LoggerFactory.getLogger(AttributeDesc.class);

	/**
	 * 属性ID
	 */
	protected int aid;

	/**
	 * 属性名
	 */
	protected String attrName;
	/**
	 * 属性值
	 */
	protected String attrValue;

	/**
	 * 属性别名
	 */
	protected String aliasName;

	/**
	 * 控制类型
	 */
	protected ControlType controlType;

	/**
	 * 数据类型
	 */
	protected DataType dataType;

	/**
	 * 获取字段长度
	 */
	protected int length;

	/**
	 * 默认值
	 */
	protected String defaultValue;

	/**
	 * 最大值
	 */
	protected String maxValue;

	/**
	 * 最小值
	 */
	protected String minValue;

	/**
	 * 属性掩码
	 */
	protected int mask;

	/**
	 * 属性设置请求对应的AT指令
	 */
	protected String atCmd;

	/**
	 * 属性设置应答解析类
	 */
	protected String rspParser;

	/**
	 * 
	 * @param attrID String
	 */
	public void setAttrID(String attrID) {
		try {
			this.aid = Integer.decode(attrID).intValue();
		} catch (Exception e) {
			logger.error(e.getMessage());
			this.aid = -1;
		}
	}

	/**
	 * 
	 * @param attrID int
	 */
	public void setAttrID(int attrID) {
		try {
			this.aid = attrID;
		} catch (Exception e) {
			logger.error(e.getMessage());
			this.aid = -1;
		}
	}

	@Override
	public ControlType getControlType() {
		return controlType;
	}

	@Override
	public DataType getDataType() {
		return dataType;
	}

	@Override
	public String getDefaultValue() {
		return this.defaultValue;
	}

	@Override
	public String getMaxValue() {
		return this.maxValue;
	}

	@Override
	public String getMinValue() {
		return this.minValue;
	}

	@Override
	public String getUIName() {
		return this.aliasName;
	}

	@Override
	public int getID() {
		return this.aid;
	}

	@Override
	public String getName() {
		return this.attrName;
	}

	@Override
	public int getMask() {
		return this.mask;
	}

	/**
	 * 
	 * @param attrName String
	 */
	public void setName(String attrName) {
		this.attrName = attrName;
	}

	/**
	 * 
	 * @param aliasName String
	 */
	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}

	/**
	 * 
	 * @param dataType String
	 */
	public void setData(String dataType) {
		try {
			this.dataType = DataType.getDT(Integer.parseInt(dataType));
		} catch (Exception e) {
			logger.error(e.getMessage());
			this.dataType = null;
		}
	}

	/**
	 * 
	 * @param controlType String
	 */
	public void setControl(String controlType) {
		try {
			this.controlType = ControlType.getCT(Integer.parseInt(controlType));
		} catch (Exception e) {
			logger.error(e.getMessage());
			this.controlType = null;
		}
	}

	/**
	 * 
	 * @param controlType ControlType
	 */
	public void setControl(ControlType controlType) {
		try {
			this.controlType = controlType;
		} catch (Exception e) {
			logger.error(e.getMessage());
			this.controlType = null;
		}
	}

	/**
	 * 
	 * @param defaultValue String
	 */
	public void setDefault(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	/**
	 * 
	 * @param maxValue String
	 */
	public void setMax(String maxValue) {
		this.maxValue = maxValue;
	}

	/**
	 * 
	 * @param minValue String
	 */
	public void setMin(String minValue) {
		this.minValue = minValue;
	}

	/**
	 * 
	 * @param mask String
	 */
	public void setMask(String mask) {
		try {
			this.mask = Integer.decode(mask).intValue();
		} catch (Exception e) {
			logger.error(e.getMessage());
			this.mask = -1;
		}
	}

	/**
	 * 
	 * @return String
	 */
	public String getAtCmd() {
		return this.atCmd;
	}

	/**
	 * 
	 * @param atCmd String
	 */
	public void setAtCmd(String atCmd) {
		this.atCmd = atCmd;
	}

	/**
	 * 
	 * @return String
	 */
	public String getRspParser() {
		return this.rspParser;
	}

	/**
	 * 
	 * @param rspParser String
	 */
	public void setRspParser(String rspParser) {
		this.rspParser = rspParser;
	}

	@Override
	public boolean isKey() {
		// 0位：是否是关键属性
		return (this.mask & IS_KEY_MASK) != 0;
	}

	@Override
	public boolean isReadOnly() {
		// 2位：是否是只读属性
		return (this.mask & IS_READ_ONLY_MASK) != 0;
	}

	@Override
	public boolean isNeKey() {
		// 1位：是否是关联属性
		return (this.mask & IS_NE_KEY_MASK) != 0;
	}

	@Override
	public boolean isComplex() {
		// 是否是复杂属性
		return (this.mask & IS_COMPLEX_MASK) != 0;
	}

	@Override
	public boolean isAvaliableInCreate() {
		// 3位：是否是创建对象时的不可见属性
		return (this.mask & IS_AVAILABLE_IN_CREATE_MASK) != 0;
	}

	@Override
	public boolean isAvaliableInModify() {
		// 4位：是否是修改对象时的不可见属性
		return (this.mask & IS_AVAILABLE_IN_MODIFY_MASK) != 0;
	}

	@Override
	public boolean isVisible() {
		// 5 位：是否在显示时不可见属性
		return (this.mask & IS_VISIBLE_MASK) != 0;
	}

	@Override
	public boolean isOMC() {
		// 8位：是否为OMC数据
		return (this.mask & IS_OMC_MASK) != 0;
	}

	@Override
	public boolean isSKTNeeded() {
		// 10位：是否是SKT需要属性
		return (this.mask & IS_SKT_NEED_MASK) != 0;
	}

	@Override
	public boolean isFriendlyName() {
		// 11位：是否是友好名属性
		return (this.mask & IS_FRIEND_NAME_MASK) != 0;
	}

	@Override
	public boolean isRDVisible() {
		// 12位：该属性是否是仅研发可见属性
		return (this.mask & IS_RD_VISIBLE_MASK) != 0;
	}

	@Override
	public boolean isArray() {
		// 13位：是否是数组属性
		return (this.mask & IS_ARRAY_MASK) != 0;
	}

	@Override
	public boolean isManageStatus() {
		// 17位：是否管理状态属性
		return (this.mask & IS_STATUS_MANAGER_MASK) != 0;
	}

	@Override
	public boolean isRunDetailStatus() {
		// 18位：是否运行状态
		return (this.mask & IS_STATUS_RUNDETAIL_MASK) != 0;
	}

	@Override
	public boolean isRunStatus() {
		// 19位：是否运行详细状态
		return (this.mask & IS_STATUS_RUN_MASK) != 0;
	}

	@Override
	public boolean isDynamicStatus() {
		// 22位 是否动态属性
		return (this.mask & IS_DYNAMIC_STATUS_MASK) != 0;
	}

	@Override
	public boolean isMOC() {
		return (this.mask & IS_MOC_MASK) != 0;
	}

	@Override
	public boolean isMOI() {
		return (this.mask & IS_MOI_MASK) != 0;
	}

	@Override
	public boolean isBatchModifiable() {
		// 7位：是否可成组修改
		return (this.mask & IS_BATCH_MODIFIED_MASK) != 0;
	}

	@Override
	public boolean isResearchAvaliableInCreate() {
		// 20位 是否研发可创建
		return (this.mask & IS_RESEARCH_AVAILABLE_IN_CREATE_MASK) != 0;
	}

	@Override
	public boolean isResearchAvaliableInModify() {
		// 21位 是否研发可创建
		return (this.mask & IS_RESEARCH_AVAILABLE_IN_MODIFY_MASK) != 0;
	}

	@Override
	public boolean isDependOnParent() {
		return (this.mask & IS_DEPEND_ON_PARENT_MASK) != 0;
	}

	@Override
	public int getLength() {
		if (this.dataType == DataType.ONEBYTE
				|| this.dataType == DataType.SIGNED_ONEBYTE) {
			// 单字节或有符号1字节
			return 1;
		} else if (this.dataType == DataType.TWOBYTE
				|| this.dataType == DataType.SIGNED_TWOBYTE) {
			// 双字节或有符号2字节
			return 2;
		} else if (this.dataType == DataType.FOURBYTE
				|| this.dataType == DataType.SIGNED_FOUTBYTE
		// || this.dataType == DataType.DATE
		) {
			// 四字节或有符号四字节
			// 日期类型，一共四字节，3,4字节放年,2字节放月，1字节放日
			return 4;
		} else if (this.dataType == DataType.EIGHTBYTE) {
			// 八字节
			return 8;
		} else if (this.dataType == DataType.DATE) {
			// 时间类型的长度为44
			return 44;
		} else if (this.dataType == DataType.ONEBYTEARRAY// 如果是一字节数组
				|| this.dataType == DataType.TWOBYTEARRAY// 如果是二字节数组
				|| this.dataType == DataType.FOURBYTEARRAY// 如果是四字节数组
				|| this.dataType == DataType.SIGNED_ONEBYTEARRAY// 如果是有符号一字节数组
				|| this.dataType == DataType.SIGNED_TWOBYTEARRAY// 如果是有符号二字节数组
				|| this.dataType == DataType.TWOBYTEARRAYARRAY// 如果是二字节二维数组
				|| this.dataType == DataType.SIGNED_FOURBYTEARRAY// 如果是有符号四字节数组
				|| this.dataType == DataType.STRING// 其它和字符串方式一样处理
				|| this.dataType == DataType.IPV4// 如果是IPV4
				|| this.dataType == DataType.IPV6// 如果是IPV6
				|| this.dataType == DataType.MAC// 如果是MAC
				|| this.dataType == DataType.STRINGARRAY// 如果是字符串数组
				|| this.dataType == DataType.TWOBYTEARRAYARRAY// 如果是双字节二维数组
				|| this.dataType == DataType.OXSTRING// 如果是十六进制字符串
				|| this.dataType == DataType.OXSTRINGARRAY// 如果是十六进制字符串数组
		) {
			return this.length;
		} else {
			return -1;
		}
	}

	/**
	 * 获取数组长度
	 * @return int
	 */
	public int getArrayLength() {
		if (this.dataType == DataType.ONEBYTE// 单字节
				|| this.dataType == DataType.TWOBYTE// 双字节
				|| this.dataType == DataType.FOURBYTE// 四字节
				|| this.dataType == DataType.EIGHTBYTE// 八字节
				|| this.dataType == DataType.SIGNED_ONEBYTE// 有符号1字节
				|| this.dataType == DataType.SIGNED_TWOBYTE// 有符号2字节
				|| this.dataType == DataType.SIGNED_FOUTBYTE// 有符号4字节
		) {
			if (this.controlType == ControlType.ARRAY_GROUP
					|| this.controlType == ControlType.ARRAY_RELATION) {
				return this.length;
			} else {
				return -1;
			}
		} else if (this.dataType == DataType.ONEBYTEARRAY// 如果是一字节数组
				|| this.dataType == DataType.SIGNED_ONEBYTEARRAY// 如果是有呼号一字节数组
		) {
			return this.length;
		} else if (this.dataType == DataType.TWOBYTEARRAY// 如果是二字节数组
				|| this.dataType == DataType.SIGNED_TWOBYTEARRAY // 如果是有符号二字节数组
				|| this.dataType == DataType.TWOBYTEARRAYARRAY // 如果是二字节二维数组
		) {
			return this.length / 2;
		} else if (this.dataType == DataType.FOURBYTEARRAY// 如果是四字节数组
				|| this.dataType == DataType.SIGNED_FOURBYTEARRAY// 如果是有符号四字节数组
		) {
			return this.length / 4;
		} else if (this.dataType == DataType.STRING// 其它和字符串方式一样处理
				|| this.dataType == DataType.OXSTRING// 十六进制字符串数组
				|| this.dataType == DataType.DATE// 日期类型，一共四字节，3,4字节放年,2字节放月，1字节放日
		) {
			return -1;
		} else if (this.dataType == DataType.MAC) {
			// 如果是MAC
			// return this.length/6;
			return this.length / 8;
		} else if (this.dataType == DataType.IPV4) {
			// 如果是IPV4
			return this.length / 4;
		} else if (this.dataType == DataType.IPV6) {
			// 如果是IPV6
			return this.length / 16;
		} else if (this.dataType == DataType.STRINGARRAY) {
			// 如果是字符串数组
			return this.length / Integer.parseInt(this.maxValue);
		} else if (this.dataType == DataType.OXSTRINGARRAY) {
			// 如果是十六进制字符串数组
			int max = 0;
			try {
				max = Integer.parseInt(this.maxValue);
			} catch (Exception e) {

			}
			if (max % 4 != 0) {
				max = (max / 4 + 1) * 4;
			}
			if (0 == max) {
				return -1;
			} else {
				return this.length / max;
			}
		} else {
			return -1;
		}
	}

	/**
	 * 
	 * @param length String
	 */
	public void setLength(String length) {
		if (length == null || "".equals(length)) {
			return;
		}
		this.length = Integer.parseInt(length);
	}

	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append("\n\nAttr id: " + this.getID());
		buf.append("\nAttr name: " + this.getName());
		buf.append("\nAttr ui name: " + this.getUIName());
		buf.append("\nAttr min value:" + this.getMinValue());
		buf.append("\nAttr max value:" + this.getMaxValue());
		buf.append("\nAttr default Name:" + this.getDefaultValue());
		buf.append("\nAttr length: " + this.getLength());
		buf.append("\nAttr mask: " + this.mask);
		buf.append("\nAttr at cmd: " + this.getAtCmd());
		buf.append("\nAttr rsp parser: " + this.getRspParser());

		return buf.toString();
	}

	@Override
	public AttributeDesc clone() {
		AttributeDesc attrDescClone = new AttributeDesc();
		attrDescClone.aid = this.aid;
		attrDescClone.aliasName = this.aliasName;
		attrDescClone.attrName = this.attrName;

		attrDescClone.controlType = this.controlType;
		attrDescClone.defaultValue = this.defaultValue;
		attrDescClone.dataType = this.dataType;
		attrDescClone.length = this.length;
		attrDescClone.mask = this.mask;
		attrDescClone.maxValue = this.maxValue;
		attrDescClone.minValue = this.minValue;

		attrDescClone.atCmd = this.atCmd;
		attrDescClone.rspParser = this.rspParser;

		return attrDescClone;
	}

}

/**
 * @(#)OperationDesc.java       07/11
 *
 * Copyright (c) 2007 GBCom Communication Technology Co.Ltd
 * All right reserved.
 */
package com.gbcom.common.im.desc;

/**
 * 操作描述类，实现了IOperationDesc接口
 * 
 * @author fengjing
 * @version 2.0
 * 
 */
public class OperationDesc implements IOperationDesc {
	private static final long serialVersionUID = -8211635415582628L;

	/**
	 * 操作组号
	 */
	private int operGroupId;

	/**
	 * 操作ID
	 */
	private int operID;

	/**
	 * 操作级别。
	 */
	private int level;

	/**
	 * 此OpeartionDesc的父结点，为一个ClassDesc
	 */
	private IClassDesc parentClass;

	/**
	 * 操作类名
	 */
	private String operName;

	/**
	 * AT指令
	 */
	private String atCmd;

	/**
	 * 应答解析器类名
	 */
	private String rspParserClassName;

	@Override
	public int getCID() {
		if (this.parentClass != null) {
			return this.parentClass.getID();
		} else {
			return -1;
		}
	}

	@Override
	public int getOperGroup() {
		return this.operGroupId;
	}

	@Override
	public int getOperID() {
		return this.operID;
	}

	@Override
	public int getOperLevel() {
		// 需要在VM表中定义
		return this.level;
	}

	/**
	 * 设置操作级别
	 * 
	 * @param level int
	 */
	public void setOperLevel(int level) {
		this.level = level;
	}

	@Override
	public String getOperName() {
		return this.operName;
	}

	@Override
	public String getAtCmd() {
		return this.atCmd;
	}

	@Override
	public String getRspParserClassName() {
		return this.rspParserClassName;
	}

	/**
	 * 设置独立界面类名
	 * 
	 * @param atCmd
	 *            AT指令
	 */
	public void setAtCmd(String atCmd) {
		this.atCmd = atCmd;
	}

	/**
	 * 设置
	 * 
	 * @param rspParserClassName
	 *            解析类
	 */
	public void setRspParserClassName(String rspParserClassName) {
		this.rspParserClassName = rspParserClassName;
	}

	/**
	 * 设置操作组
	 * 
	 * @param operGroupId
	 *            操作组
	 */
	public void setOperGroup(int operGroupId) {
		this.operGroupId = operGroupId;
	}

	/**
	 * 设置操作ID
	 * 
	 * @param operID
	 *            操作ID
	 */
	public void setOperID(int operID) {
		this.operID = operID;
	}

	/**
	 * 设置操作名
	 * 
	 * @param operName
	 *            操作名
	 */
	public void setOperName(String operName) {
		this.operName = operName;
	}

	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append("\n\nOpt group Id:" + this.getOperGroup());
		buf.append("\nOpt opt id:" + this.getOperID());
		buf.append("\nOpt opt name:" + this.getOperName());
		buf.append("\nOpt atCmd: " + getAtCmd());
		buf.append("\nOpt rspParserClassName: " + getRspParserClassName());

		return buf.toString();
	}

	/**
	 * 获取该操作的父节点类描述
	 * 
	 * @return 父节点类描述
	 */
	public IClassDesc getParentClass() {
		return parentClass;
	}

	/**
	 * 设置父节点类描述
	 * 
	 * @param parentClass
	 *            父节点类描述
	 */
	public void setParentClass(IClassDesc parentClass) {
		this.parentClass = parentClass;
	}

}

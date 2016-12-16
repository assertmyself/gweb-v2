/**
 * @(#)ClassDesc.java       07/10
 *
 * Copyright (c) 2007 GBCom Communication Technology Co.Ltd
 * All right reserved.
 */
package com.gbcom.common.im.desc;

import java.lang.reflect.Array;
import java.util.ArrayList;

import com.gbcom.common.im.ds.IDNameStructUtil;
import com.gbcom.common.im.parse.alarm.EmptyAlarmParser;
import com.gbcom.common.im.parse.alarm.IAlarmParser;

/**
 * 类描述
 * 
 * @author fengjing
 * @version 2.0
 * 
 */
public class ClassDesc implements IClassDesc, Cloneable {
	/**
	 * 序列化版本ID
	 */
	private static final long serialVersionUID = 3465065732137533127L;

	/**
	 * 子类描述数组
	 */
	private IClassDesc[] children;

	/**
	 * 父类描述
	 */
	private IClassDesc parent = null;

	/**
	 * 属性描述数组
	 */
	private IAttributeDesc[] attributes = new IAttributeDesc[0];

	/**
	 * 类名
	 */
	private String className;

	/**
	 * 类别名
	 */
	private String aliasName;

	/**
	 * 是否支持批量操作
	 * 
	 */
	private boolean canBatchOper;

	/**
	 * 告警解析器类名
	 */
	private String alarmParserClassName;

	/**
	 * 类ID
	 */
	private int classID;

	/**
	 * 告警解析器
	 */
	private IAlarmParser alarmParser = null;

	/**
	 * operationDescs
	 */
	private IOperationDesc[] operationDescs;

	/**
	 * APP-->Dev 请求响应解析类
	 */
	private String responseParserClassName;

	/**
	 * 矢量。
	 */
	private boolean vector;

	private int maxIndex;
	/**
	 * 属性掩码
	 */
	private int mask;

	/**
	 * 构造函数
	 * 
	 * @param classID
	 *            类ID
	 * @param className
	 *            类名
	 */
	public ClassDesc(String classID, String className) {
		try {
			this.classID = Integer.decode(classID).intValue();
			this.className = className;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 构造方法
	 * 
	 * @param classID int
	 * @param className String
	 */
	public ClassDesc(int classID, String className) {
		try {
			this.classID = classID;
			this.className = className;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	public IAttributeDesc getAttributeDesc(int aid) {
		if (attributes != null) {
			int index = IDNameStructUtil.binarySearch(attributes, aid);
			if (index != -1) {
				return attributes[index];
			}
		}
		return null;
	}

	@Override
	public IAttributeDesc getAttributeDesc(String attributeName) {
		if (attributes == null || attributeName == null) {
			return null;
		}
		for (int i = 0; i < attributes.length; i++) {
			if (attributes[i].getName().equals(attributeName)) {
				return attributes[i];
			}
		}
		return null;
	}

	@Override
	public IAttributeDesc[] getAttributeDescs() {
		return attributes;
	}

	@Override
	public IAttributeDesc[] getNonMOCAttributeDescs() {
		if (attributes == null) {
			return null;
		}
		ArrayList<IAttributeDesc> tempAL = new ArrayList<IAttributeDesc>();
		for (int i = 0; i < attributes.length; i++) {
			if (!attributes[i].isOMC()) {
				tempAL.add(attributes[i]);
			}
		}

		if (!tempAL.isEmpty()) {
			return tempAL.toArray(new IAttributeDesc[tempAL.size()]);
		}
		return null;
	}

	@Override
	public IClassDesc getChild(int cid) {
		if (children != null) {
			int index = IDNameStructUtil.binarySearch(children, cid);
			if (index != -1) {
				return children[index];
			}
		}
		return null;
	}

	@Override
	public IClassDesc getChild(String className) {
		if (children != null) {
			for (int i = 0; i < children.length; i++) {
				if (children[i].getName().equals(className)) {
					return children[i];
				}
			}
		}
		return null;
	}

	@Override
	public IClassDesc[] getChildren() {
		return this.children;
	}

	@Override
	public IAttributeDesc[] getKeyAttributeDesc() {
		if (attributes == null) {
			return null;
		}
		ArrayList<IAttributeDesc> tempAL = new ArrayList<IAttributeDesc>();
		for (int i = 0; i < attributes.length; i++) {
			if (attributes[i].isKey()) {
				tempAL.add(attributes[i]);
			}
		}

		if (!tempAL.isEmpty()) {
			return tempAL.toArray(new IAttributeDesc[tempAL.size()]);
		}

		return null;
	}

	@Override
	public IClassDesc getParent() {
		return this.parent;
	}

	@Override
	public IAttributeDesc[] getSkeletonAttributeDesc() {
		if (attributes == null) {
			return null;
		}
		ArrayList<IAttributeDesc> tempAL = new ArrayList<IAttributeDesc>();
		for (int i = 0; i < attributes.length; i++) {
			if (attributes[i].isSKTNeeded()) {
				tempAL.add(attributes[i]);
			}
		}

		if (!tempAL.isEmpty()) {
			return tempAL.toArray(new IAttributeDesc[tempAL.size()]);
		}
		return null;
	}

	@Override
	public IAttributeDesc getManageStatusAttributeDesc() {
		if (attributes == null) {
			return null;
		}
		for (int i = attributes.length - 1; i >= 0; i--) {
			if (attributes[i].isManageStatus()) {
				return attributes[i];
			}
		}
		return null;
	}

	@Override
	public IAttributeDesc getRunDetailStatusAttributeDesc() {
		if (attributes == null) {
			return null;
		}
		for (int i = attributes.length - 1; i >= 0; i--) {
			if (attributes[i].isRunDetailStatus()) {
				return attributes[i];
			}
		}
		return null;
	}

	@Override
	public IAttributeDesc getFriendAttributeDesc() {
		if (attributes == null) {
			return null;
		}
		for (int i = attributes.length - 1; i >= 0; i--) {
			if (attributes[i].isFriendlyName()) {
				return attributes[i];
			}
		}
		return null;
	}

	@Override
	public IAttributeDesc[] getFriendAttributeDescs() {
		if (attributes == null) {
			return null;
		}
		ArrayList<IAttributeDesc> tempAL = new ArrayList<IAttributeDesc>();
		for (int i = 0; i < attributes.length; i++) {
			if (attributes[i].isFriendlyName()) {
				tempAL.add(attributes[i]);
			}
		}

		if (!tempAL.isEmpty()) {
			return tempAL.toArray(new IAttributeDesc[tempAL.size()]);
		}
		return null;
	}
	@Override
	public String getAlarmParserClassName() {
		return alarmParserClassName;
	}

	@Override
	public IAttributeDesc getRunStatusAttributeDesc() {
		if (attributes == null) {
			return null;
		}
		for (int i = attributes.length - 1; i >= 0; i--) {
			if (attributes[i].isRunStatus()) {
				return attributes[i];
			}
		}
		return null;
	}

	@Override
	public String getUIName() {
		return this.aliasName;
	}

	@Override
	public int getID() {
		return this.classID;
	}

	@Override
	public String getName() {
		return this.className;
	}

	/**
	 * 
	 * @return boolean
	 */
	public boolean isCanBatchOper() {
		return canBatchOper;
	}

	/**
	 * 
	 * @param canBatchOper boolean
	 */
	public void setCanBatchOper(boolean canBatchOper) {
		this.canBatchOper = canBatchOper;
	}

	/**
	 * 增加该类对象的子类对象
	 * 
	 * @param child
	 *            待增加的子类对象描述
	 */
	public void addChild(IClassDesc child) {
		this.children = (IClassDesc[]) IDNameStructUtil.add(children, child);
	}

	/**
	 * 设置该类描述对象的子描述对象
	 * 
	 * @param children
	 *            子描述对象
	 */
	public void setChildren(IClassDesc[] children) {
		this.children = (IClassDesc[]) IDNameStructUtil.sort(children);
	}

	/**
	 * 增加该类描述的属性描述
	 * 
	 * @param attrDesc
	 *            待增加的属性描述
	 */
	public void addAttr(IAttributeDesc attrDesc) {
		this.attributes = (IAttributeDesc[]) IDNameStructUtil.add(attributes,
				attrDesc);
	}

	/**
	 * 设置该类描述的属性描述
	 * 
	 * @param attrDescs
	 *            该类描述的属性描述
	 */
	public void setAttrs(IAttributeDesc[] attrDescs) {
		this.attributes = (IAttributeDesc[]) IDNameStructUtil.sort(attrDescs);
	}

	/**
	 * 设置类描述的别名
	 * 
	 * @param aliasName String
	 */
	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}

	/**
	 * 设置该类描述对应的触发器类名称
	 * 
	 * @param alarmParserClassName
	 *            待设定的触发器类名
	 */
	public void setAlarmParserName(String alarmParserClassName) {
		this.alarmParserClassName = alarmParserClassName;
	}

	/**
	 * 
	 * @return String
	 */
	public String getResponseParserClassName() {
		return responseParserClassName;
	}

	/**
	 * 
	 * @param responseParserClassName String
	 */
	public void setResponseParserClassName(String responseParserClassName) {
		this.responseParserClassName = responseParserClassName;
	}

	@Override
	public IAlarmParser getAlarmParser() {
		if (this.alarmParser != null) {
			return this.alarmParser;
		}

		try {
			IAlarmParser alarmParser = (IAlarmParser) this.getClass()
					.getClassLoader().loadClass(this.alarmParserClassName)
					.newInstance();
			return alarmParser;
		} catch (Exception e) {
			// 如果不存在触发器，则分配一个空触发器，避免每次触发器为空时都去加载。
			this.alarmParser = new EmptyAlarmParser();
			return this.alarmParser;
		}
	}

	/**
	 * 设置触发器
	 * 
	 * @param alarmParser IAlarmParser
	 */
	public void serAlarmParser(IAlarmParser alarmParser) {
		this.alarmParser = alarmParser;
	}

	/**
	 * 设置该类描述的父类描述
	 * 
	 * @param parent
	 *            待设定的父类描述
	 */
	public void setParent(IClassDesc parent) {
		this.parent = parent;
	}

	/**
	 * 根据aid获取其列表顺序（数组下标） 这个方法主要是提供给持久层调用 由于持久层按照属性列表的顺序（数组下标）依次存储在100列的表中<br>
	 * 因此需要知道每个aid所在的顺序（数组下标）
	 * 
	 * @param aid int
	 * @return 顺序id
	 */
	public int getAttributeDescIndex(int aid) {
		return IDNameStructUtil.binarySearch(this.attributes, aid);
	}

	/**
	 * 根据属性列表中的顺序id（数组下标）获取属性描述 这个方法主要是提供给持久层调用 由于持久层按照属性列表的顺序（数组下标）依次存储在100列的表中<br>
	 * 因此需要知道每个顺序列对应的属性描述
	 * 
	 * @param index
	 *            指定的顺序ID
	 * @return 对应顺序ID下的属性描述
	 */
	public IAttributeDesc getAttributeDescByIndex(int index) {
		if (this.attributes == null) {
			return null;
		}
		if (this.attributes.length > index) {
			return this.attributes[index];
		}
		throw new ArrayIndexOutOfBoundsException("index:" + index
				+ " is out of AtrributeDesc length:" + this.attributes.length);
	}

	@Override
	public String getVersion() {
		return this.parent.getVersion();
	}

	@Override
	public String getType() {
		return this.parent.getType();
	}

	/**
	 * 
	 * @param operationDesc IOperationDesc
	 */
	public void addOperationDescs(IOperationDesc operationDesc) {
		if (this.operationDescs == null || this.operationDescs.length < 1) {
			this.operationDescs = this.operationDescs == null ? (IOperationDesc[]) Array
					.newInstance(operationDesc.getClass(), 1)
					: (IOperationDesc[]) Array.newInstance(this.operationDescs
							.getClass().getComponentType(), 1);
			this.operationDescs[0] = operationDesc;
			return;
		}

		for (int i = 0; i < this.operationDescs.length; i++) {
			if (this.operationDescs[i].equals(operationDesc)) {
				return;
			}
		}

		IOperationDesc[] result = null;
		result = (IOperationDesc[]) Array.newInstance(IOperationDesc.class,
				this.operationDescs.length + 1);
		System.arraycopy(this.operationDescs, 0, result, 0,
				this.operationDescs.length);
		result[result.length - 1] = operationDesc;
		this.operationDescs = result;
	}

	@Override
	public Object clone() {
		ClassDesc cloneCD = new ClassDesc(this.classID, this.className);
		cloneCD.children = this.children;
		cloneCD.parent = this.parent;
		cloneCD.setAttrs(this.getAttributeDescs());
		cloneCD.setAliasName(this.getUIName());

		cloneCD.alarmParserClassName = this.alarmParserClassName;
		cloneCD.alarmParser = this.alarmParser;

		return cloneCD;
	}

	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append("\n----------------------------");
		buf.append("\nClass ClassCid: " + this.getID());
		if (this.getParent() != null) {
			buf.append("\nclass parent's id: " + this.getParent().getID());
		} else {
			buf.append("\nclass parent: " + "null");
		}

		buf.append("\nClass name: " + this.getName());
		buf.append("\nClass UI name: " + this.getUIName());

		buf.append("\n\nAttributes :");
		IAttributeDesc[] descs = (IAttributeDesc[]) this.getAttributeDescs();
		if (descs != null) {
			for (int i = 0; i < descs.length; i++) {
				buf.append((IAttributeDesc) descs[i]).toString();
			}
		}

		buf.append("\n----------------------------");
		return buf.toString();
	}

	/**
	 * @return the mask
	 */
	public int getMask() {
		return mask;
	}

	/**
	 * @param mask
	 *            the mask to set
	 */
	public void setMask(int mask) {
		this.mask = mask;
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
	public int getMaxIndex() {
		// TODO Auto-generated method stub
		return maxIndex;
	}

	@Override
	public void setMaxIndex(int max) {
		this.maxIndex = max;

	}

	/**
	 * 
	 * @param vector boolean
	 */
	public void setVector(boolean vector) {
		this.vector = vector;
	}

	/**
	 * @return boolean
	 */
	public boolean isVector() {
		return vector;
	}

}

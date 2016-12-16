/**
 * @(#)DefaultIM.java       07/11
 *
 * Copyright (c) 2007 GBCom Communication Technology Co.Ltd
 * All right reserved.
 */
package com.gbcom.common.im;

import com.gbcom.common.im.desc.IClassDesc;
import com.gbcom.common.im.ds.IDArray;
import com.gbcom.common.im.ds.IIDNameStruct;
import com.gbcom.common.im.ds.NameArray;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 信息模型，根据.xml文件生成，实现IIM接口
 * 
 * @author fengjing
 * @version 2.0
 * 
 */
public class DefaultIM implements IIM {
	/**
	 * 序列化版本ID
	 */
	private static final long serialVersionUID = 321654032546832013L;

	/**
	 * 版本号
	 */
	private String version;

	/**
	 * 网元类型
	 */
	private String type;

	/**
	 * 根类描述
	 */
	private IClassDesc root;

	/**
	 * 时间戳
	 */
	private long timeStamp;

	/**
	 * 根据类ID构成的对象数组
	 */
	private IDArray classIdMap;

	/**
	 * 根据类名构成的对象数组
	 */
	private NameArray classNameMap;

	/**
	 * 根据tableName构成对象数组
	 */
	private Map<String, IClassDesc> tableNameMap = null;

	/**
	 * 构造函数
	 * 
	 * @param type
	 *            该信息模型的根类型
	 * @param ver
	 *            该信息模型的版本
	 * @param root
	 *            该信息模型的根类描述
	 * @param data
	 *            该信息模型包含的所有类描述
	 * @param timeStamp
	 *            该信息模型对应的文件的时间戳
	 */
	public DefaultIM(String type, String ver, IClassDesc root,
			ArrayList<IIDNameStruct> data, long timeStamp) {
		this.version = ver;
		this.type = type;
		this.root = root;
		this.classIdMap = new IDArray(data);
		this.classNameMap = new NameArray(data);
		this.tableNameMap = new HashMap<String, IClassDesc>();
		for (IIDNameStruct struct : data) {
			IClassDesc desc = (IClassDesc) struct;
			// ...
		}
		this.timeStamp = timeStamp;
	}

	@Override
	public long getTimeStamp() {
		return this.timeStamp;
	}

	@Override
	public IClassDesc getClassDesc(int cid) {
		return (IClassDesc) classIdMap.get(cid);
	}

	@Override
	public IClassDesc getClassDesc(String classDesc) {
		return (IClassDesc) classNameMap.findFirst(classDesc);
	}

	@Override
	public IClassDesc getClassDescByTableName(String snmpTableName) {
		return tableNameMap.get(snmpTableName);
	}

	/**
	 * 在信息模型中增加类描述
	 * 
	 * @param classDesc
	 *            待增加的类描述
	 */
	protected void addClassDesc(IClassDesc classDesc) {
		classIdMap.put((IIDNameStruct) classDesc);
		classNameMap.put((IIDNameStruct) classDesc);
		if (tableNameMap == null) {
			tableNameMap = new HashMap<String, IClassDesc>();
		}
	}

	@Override
	public IClassDesc getRoot() {
		return this.root;
	}

	@Override
	public String getVersion() {
		return this.version;
	}

	@Override
	public String getType() {
		return this.type;
	}

	/**
	 * 
	 * @return IClassDesc[]
	 */
	public IClassDesc[] getAllClassDescs() {
		IIDNameStruct[] ids = classIdMap.get();
		IClassDesc[] classes = new IClassDesc[ids.length];
		for (int i = 0; i < ids.length; i++) {
			classes[i] = (IClassDesc) ids[i];
		}

		return classes;
	}

	@Override
	public String toString() {
		// print out all classes
		StringBuffer buf = new StringBuffer();

		// vm info
		buf.append("\n #################IIM type:" + this.getType());
		buf.append("#####Version:" + this.getVersion());
		buf.append("#####timeStamp:" + new Date(this.getTimeStamp())
				+ "#################");

		// detail info
		IClassDesc[] classes = this.getAllClassDescs();
		for (int i = 0; i < classes.length; i++) {
			buf.append("\n " + ((IClassDesc) classes[i]).toString());
		}

		return buf.toString();
	}
}

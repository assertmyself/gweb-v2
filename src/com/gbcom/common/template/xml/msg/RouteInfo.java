package com.gbcom.common.template.xml.msg;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("route")
public class RouteInfo {
	@XStreamAsAttribute
    private  int  msgid;
	@XStreamAsAttribute
    private  String  name;
	@XStreamAsAttribute
	private  int  msgtype;
	@XStreamAsAttribute
    private  String  processor;
	@XStreamAsAttribute
    private  String  modulename;
	/**
	 * @return the msgid
	 */
	public int getMsgid() {
		return msgid;
	}
	/**
	 * @param msgid the msgid to set
	 */
	public void setMsgid(int msgid) {
		this.msgid = msgid;
	}
	/**
	 * @return the msgtype
	 */
	public int getMsgtype() {
		return msgtype;
	}
	/**
	 * @param msgtype the msgtype to set
	 */
	public void setMsgtype(int msgtype) {
		this.msgtype = msgtype;
	}
	
	/**
	 * @return the modulename
	 */
	public String getModulename() {
		return modulename;
	}
	/**
	 * @param modulename the modulename to set
	 */
	public void setModulename(String modulename) {
		this.modulename = modulename;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the processor
	 */
	public String getProcessor() {
		return processor;
	}
	/**
	 * @param processor the processor to set
	 */
	public void setProcessor(String processor) {
		this.processor = processor;
	}
	
}

package com.gbcom.common.template.xml.snmp;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * OID 节点
 * 
 * @author SunYanzheng
 * @date 上午9:48:34
 * @version v1.0.0
 * @see MibNode
 */
@XStreamAlias("node")
public class MibNode {
	@XStreamAsAttribute
	private String name;
	@XStreamAsAttribute
	private String oid;
	@XStreamAsAttribute
	private boolean required;
	@XStreamAsAttribute
	private String regex;
	@XStreamAsAttribute
	private String defValue;
	@XStreamAsAttribute
	private String type;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the oid
	 */
	public String getOid() {
		return oid;
	}

	/**
	 * @param oid
	 *            the oid to set
	 */
	public void setOid(String oid) {
		this.oid = oid;
	}

	/**
	 * @return required
	 */
	public boolean isRequired() {
		return required;
	}

	/**
	 * @param required
	 *            boolean
	 */
	public void setRequired(boolean required) {
		this.required = required;
	}

	/**
	 * @return boolean
	 */
	public String getRegex() {
		return regex;
	}

	/**
	 * @param regex
	 *            String
	 */
	public void setRegex(String regex) {
		this.regex = regex;
	}

	/**
	 * @return defValue
	 */
	public String getDefValue() {
		return defValue;
	}

	/**
	 * @param defValue
	 *            String
	 */
	public void setDefValue(String defValue) {
		this.defValue = defValue;
	}

	/**
	 * @return type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            String
	 */
	public void setType(String type) {
		this.type = type;
	}

}

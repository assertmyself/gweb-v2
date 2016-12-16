package com.gbcom.common.template.xml.prefilter;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("condition")
public class PreFilterCondition {
	@XStreamAsAttribute
	private String sysmodel;
	@XStreamAsAttribute
	private String version;
	@XStreamAsAttribute
	private String oper;
	@XStreamAsAttribute
	private String value;

	/**
	 * @return the sysmodel
	 */
	public String getSysmodel() {
		return sysmodel;
	}

	/**
	 * @param sysmodel
	 *            the sysmodel to set
	 */
	public void setSysmodel(String sysmodel) {
		this.sysmodel = sysmodel;
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version
	 *            the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * @return the oper
	 */
	public String getOper() {
		return oper;
	}

	/**
	 * @param oper
	 *            the oper to set
	 */
	public void setOper(String oper) {
		this.oper = oper;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DevFilterCondition [oper=" + oper + ", sysmodel=" + sysmodel
				+ ", value=" + value + ", version=" + version + "]";
	}

}

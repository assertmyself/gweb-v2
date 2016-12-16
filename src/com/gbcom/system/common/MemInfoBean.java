package com.gbcom.system.common;

import java.io.Serializable;

public class MemInfoBean implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 内存总量
	 */
	private String memTotal;
	/**
	 * 当前内存使用量
	 */
	private String memUsed;
	/**
	 * 当前内存剩余量
	 */
	private String memFree;
	/**
	 * @return the memTotal
	 */
	public String getMemTotal() {
		return memTotal;
	}
	/**
	 * @param memTotal the memTotal to set
	 */
	public void setMemTotal(String memTotal) {
		this.memTotal = memTotal;
	}
	/**
	 * @return the memUsed
	 */
	public String getMemUsed() {
		return memUsed;
	}
	/**
	 * @param memUsed the memUsed to set
	 */
	public void setMemUsed(String memUsed) {
		this.memUsed = memUsed;
	}
	/**
	 * @return the memFree
	 */
	public String getMemFree() {
		return memFree;
	}
	/**
	 * @param memFree the memFree to set
	 */
	public void setMemFree(String memFree) {
		this.memFree = memFree;
	}
	/**
	 * @return the memUseRate
	 */
	@Override
	public String toString() {
		return "MemInfoBean [memTotal=" + memTotal + ", memUsed=" + memUsed
				+ ", memFree=" + memFree + ", memUseRate=" + "]";
	}
	
}

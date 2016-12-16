package com.gbcom.system.common;

import java.io.Serializable;

/**
 * 系统CPU的基本信息
 * 
 * @author caomin
 * @see CpuInfoBean
 */
public class CpuInfoBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**
	 * cpu 用户使用率
	 */
	private String cpuUser;
	/**
	 * cpu 系统使用率
	 */
	private String cpuSys;
	/**
	 * cpu 当前等待率
	 */
	private String cpuWait;
	/**
	 * cpu 当前空闲率
	 */
	private String cpuIdle;
	/**
	 * cpu 总的使用率
	 */
	private String cpuCombined;
	/**
	 * @return the cpuUser
	 */
	public String getCpuUser() {
		return cpuUser;
	}
	/**
	 * @param cpuUser the cpuUser to set
	 */
	public void setCpuUser(String cpuUser) {
		this.cpuUser = cpuUser;
	}
	/**
	 * @return the cpuSys
	 */
	public String getCpuSys() {
		return cpuSys;
	}
	/**
	 * @param cpuSys the cpuSys to set
	 */
	public void setCpuSys(String cpuSys) {
		this.cpuSys = cpuSys;
	}
	/**
	 * @return the cpuWait
	 */
	public String getCpuWait() {
		return cpuWait;
	}
	/**
	 * @param cpuWait the cpuWait to set
	 */
	public void setCpuWait(String cpuWait) {
		this.cpuWait = cpuWait;
	}
	/**
	 * @return the cpuIdle
	 */
	public String getCpuIdle() {
		return cpuIdle;
	}
	/**
	 * @param cpuIdle the cpuIdle to set
	 */
	public void setCpuIdle(String cpuIdle) {
		this.cpuIdle = cpuIdle;
	}
	/**
	 * @return the cpuCombined
	 */
	public String getCpuCombined() {
		return cpuCombined;
	}
	/**
	 * @param cpuCombined the cpuCombined to set
	 */
	public void setCpuCombined(String cpuCombined) {
		this.cpuCombined = cpuCombined;
	}


	@Override
	public String toString() {
		return "CpuInfoBean [cpuUser=" + cpuUser + ", cpuSys=" + cpuSys
				+ ", cpuWait=" + cpuWait + ", cpuIdle=" + cpuIdle
				+ ", cpuCombined=" + cpuCombined + "]";
	}
	
	
}

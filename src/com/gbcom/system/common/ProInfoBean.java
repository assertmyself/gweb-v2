package com.gbcom.system.common;

import java.io.Serializable;
/**
 * 系统进程的基本信息
 * 
 * @author caomin
 * @see ProInfoBean
 */
public class ProInfoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 进程名称
	 */
	private String name;
	/**
	 * 进程pid
	 */
	private String pid;
	/**
	 * 进程开启时间
	 */
	private String startTime;
	/**
	 * 该进程占用的内存
	 */
	private String memUse;
	/**
	 * cpu运行时间
	 */
	private String cpuTime;
	/**
	 * 该进程的路径
	 */
	private String path;
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
	 * @return the pid
	 */
	public String getPid() {
		return pid;
	}
	/**
	 * @param pid the pid to set
	 */
	public void setPid(String pid) {
		this.pid = pid;
	}
	/**
	 * @return the startTime
	 */
	public String getStartTime() {
		return startTime;
	}
	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	/**
	 * @return the memUse
	 */
	public String getMemUse() {
		return memUse;
	}
	/**
	 * @param memUse the memUse to set
	 */
	public void setMemUse(String memUse) {
		this.memUse = memUse;
	}
	/**
	 * @return the cpuTime
	 */
	public String getCpuTime() {
		return cpuTime;
	}
	/**
	 * @param cpuTime the cpuTime to set
	 */
	public void setCpuTime(String cpuTime) {
		this.cpuTime = cpuTime;
	}
	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}
	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}
	
	@Override
	public String toString() {
		return "ProInfoBean [name=" + name + ", pid=" + pid + ", startTime="
				+ startTime + ", memUse=" + memUse + ", cpuTime=" + cpuTime
				+ ", path=" + path + "]";
	}
	
}

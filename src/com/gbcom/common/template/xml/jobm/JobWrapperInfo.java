package com.gbcom.common.template.xml.jobm;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * JOB封装POJO, 包括<code>triggerList</code>,<code>jobParamList</code>
 * 
 * @author SunYanzheng
 * @date 上午9:52:08
 * @version v1.0.0
 * @see JobWrapperInfo
 */
@XStreamAlias("JobWrapper")
public class JobWrapperInfo {
	@XStreamAsAttribute
	private String jobGroup;
	@XStreamAsAttribute
	private String jobName;
	@XStreamAsAttribute
	private String jobClass;
	@XStreamAlias("triggerList")
	private List<TriggerInfo> triggerList;
	private List<JobParam> jobParamList;

	/**
	 * 获取jobGroup
	 * 
	 * @return jobGroup
	 */
	public String getJobGroup() {
		return jobGroup;
	}

	/**
	 * 设置jobGroup
	 * 
	 * @param jobGroup
	 *            String
	 */
	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}

	/**
	 * 获取jobName
	 * 
	 * @return jobName
	 */
	public String getJobName() {
		return jobName;
	}

	/**
	 * 设置jobName
	 * 
	 * @param jobName
	 *            String
	 */
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	/**
	 * 获取jobClass
	 * 
	 * @return jobClass
	 */
	public String getJobClass() {
		return jobClass;
	}

	/**
	 * 设置jobClass
	 * 
	 * @param jobClass
	 *            String
	 */
	public void setJobClass(String jobClass) {
		this.jobClass = jobClass;
	}

	/**
	 * 获取triggerList
	 * 
	 * @return triggerList
	 */
	public List<TriggerInfo> getTriggerList() {
		return triggerList;
	}

	/**
	 * 设置triggerList
	 * 
	 * @param triggerList
	 *            List<TriggerInfo>
	 */
	public void setTriggerList(List<TriggerInfo> triggerList) {
		this.triggerList = triggerList;
	}

	/**
	 * 获取jobParamList
	 * 
	 * @return jobParamList
	 */
	public List<JobParam> getJobParamList() {
		return jobParamList;
	}

	/**
	 * 设置jobParamList
	 * 
	 * @param jobParamList
	 *            List<JobParam>
	 */
	public void setJobParamList(List<JobParam> jobParamList) {
		this.jobParamList = jobParamList;
	}

}

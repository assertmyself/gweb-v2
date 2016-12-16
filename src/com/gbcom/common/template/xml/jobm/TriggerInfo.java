package com.gbcom.common.template.xml.jobm;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * JOB 触发器配置
 * 
 * @author SunYanzheng
 * @date 上午9:48:34
 * @version v1.0.0
 * @see TriggerInfo
 */
@XStreamAlias("trigger")
public class TriggerInfo {
	@XStreamAsAttribute
	private String triggerGroup;
	@XStreamAsAttribute
	private String triggerName;
	@XStreamAsAttribute
	private String begainTime;
	@XStreamAsAttribute
	private String endTime;
	@XStreamAsAttribute
	private long repeatInterval;
	@XStreamAsAttribute
	private String repeatCount;

	@XStreamAsAttribute
	private boolean isSample;
	@XStreamAsAttribute
	private String cronExpression;

	/**
	 * 获取triggerGroup
	 * 
	 * @return triggerGroup
	 */
	public String getTriggerGroup() {
		return triggerGroup;
	}

	/**
	 * 设置triggerGroup
	 * 
	 * @param triggerGroup
	 *            String
	 */
	public void setTriggerGroup(String triggerGroup) {
		this.triggerGroup = triggerGroup;
	}

	/**
	 * 获取triggerName
	 * 
	 * @return triggerName
	 */
	public String getTriggerName() {
		return triggerName;
	}

	/**
	 * 设置triggerName
	 * 
	 * @param triggerName
	 *            String
	 */
	public void setTriggerName(String triggerName) {
		this.triggerName = triggerName;
	}

	/**
	 * 获取begainTime
	 * 
	 * @return begainTime
	 */
	public String getBegainTime() {
		return begainTime;
	}

	/**
	 * 设置begainTime
	 * 
	 * @param begainTime
	 *            String
	 */
	public void setBegainTime(String begainTime) {
		this.begainTime = begainTime;
	}

	/**
	 * 获取endTime
	 * 
	 * @return endTime
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * 设置endTime
	 * 
	 * @param endTime
	 *            String
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	/**
	 * 获取repeatInterval
	 * 
	 * @return repeatInterval
	 */
	public long getRepeatInterval() {
		return repeatInterval;
	}

	/**
	 * 设置repeatInterval
	 * 
	 * @param repeatInterval
	 *            long
	 */
	public void setRepeatInterval(long repeatInterval) {
		this.repeatInterval = repeatInterval;
	}

	/**
	 * 获取repeatCount
	 * 
	 * @return repeatCount
	 */
	public String getRepeatCount() {
		return repeatCount;
	}

	/**
	 * 设置repeatCount
	 * 
	 * @param repeatCount
	 *            String
	 */
	public void setRepeatCount(String repeatCount) {
		this.repeatCount = repeatCount;
	}

	/**
	 * 获取isSample
	 * 
	 * @return isSample
	 */
	public boolean isSample() {
		return isSample;
	}

	/**
	 * 设置isSample
	 * 
	 * @param isSample
	 *            boolean
	 */
	public void setSample(boolean isSample) {
		this.isSample = isSample;
	}

	/**
	 * 获取cronExpression
	 * 
	 * @return cronExpression
	 */
	public String getCronExpression() {
		return cronExpression;
	}

	/**
	 * 设置cronExpression
	 * 
	 * @param cronExpression
	 *            String
	 */
	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

}

package com.gbcom.common.template.xml.jobm;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gbcom.op.scheduler.moduler.CronJobTrigger;
import com.gbcom.op.scheduler.moduler.Job;
import com.gbcom.op.scheduler.moduler.JobInfo;
import com.gbcom.op.scheduler.moduler.JobTrigger;
import com.gbcom.op.scheduler.moduler.SimpleJobTrigger;
import com.gbcom.op.scheduler.util.JobException;
import com.gbcom.op.util.Assert;

/**
 * Job与Trigger封装类
 * 
 * <p>
 * 作业与触发器均按组/名分类，一个作业可以对应多个触发器。
 * 
 * <p>
 * <li>作业信息：{@link #JobWrapper(String, String, Class)}
 * <li>作业上下文信息：{@link #addJobProperty(String, Object)}
 * <li>添加单次定时触发器：{@link #addTrigger(String, String, Date)}
 * <li>添加周期触发器：{@link #addPeriodicTrigger(String, String, Date, Date, long)}
 * <li>添加Cron触发器：{@link #addCronTrigger(String, String, Date, Date, String)}
 * 
 * @author duanxiongwen
 * @version 1.0.0
 */
public class JobWrapper {
	private static final Logger LOG = LoggerFactory.getLogger(JobWrapper.class);
	private Job job = new Job();
	private JobInfo jobInfo = new JobInfo();
	private List<JobTrigger> triggerList = new LinkedList<JobTrigger>();

	/**
	 * 缺省构造函数
	 * 
	 * @param jobGroup
	 *            作业组
	 * @param jobName
	 *            作业名
	 * @param jobClass
	 *            作业执行类
	 */
	public JobWrapper(final String jobGroup, final String jobName,
			final Class<? extends org.quartz.Job> jobClass) {
		jobInfo.setJobGroup(jobGroup);
		jobInfo.setJobName(jobName);
		jobInfo.setJobClass(jobClass);
		jobInfo.setDurability(false);
		job.setJobinfo(jobInfo);
		job.setJobTriggers(triggerList);

	}

	/**
	 * 设置持久化
	 * 
	 * @param durability
	 *            持久化
	 */
	public void setDurability(boolean durability) {
		jobInfo.setDurability(durability);
	}

	/**
	 * 添加作业信息
	 * 
	 * @param key
	 *            Key
	 * @param value
	 *            Value
	 */
	public void addJobProperty(String key, Object value) {
		Assert.notNull(key, "argument key is null");
		Assert.notNull(value, "argument value is null");
		jobInfo.getMap().add(key, value);
	}

	/**
	 * 添加定时单次执行的Trigger
	 * 
	 * @param triggerGroup
	 *            Trigger组
	 * @param triggerName
	 *            Trigger名
	 * @param triggerTime
	 *            触发时间
	 */
	public void addTrigger(String triggerGroup, String triggerName,
			Date triggerTime) {
		Assert.notNull(triggerTime, "argument triggerTime is null");
		if (LOG.isDebugEnabled()) {
			LOG
					.debug(
							"addPeriodicTrigger, triggerGroup[{}], triggerName[{}], triggerTime[{}]",
							new Object[] { triggerGroup, triggerName,
									triggerTime });
		}

		SimpleJobTrigger trigger = new SimpleJobTrigger(triggerName,
				triggerGroup);
		trigger.setBegainTime(triggerTime);
		triggerList.add(trigger);
	}

	/**
	 * 添加周期执行Trigger，不设置<code>beginTime</code>表示立即开始，不设置<code>endTime</code>
	 * 表示永不结束
	 * 
	 * @param triggerGroup
	 *            Trigger组
	 * 
	 * @param triggerName
	 *            Trigger名
	 * 
	 * @param beginTime
	 *            开始时间
	 * 
	 * @param endTime
	 *            结束时间
	 * @param repeatInterval
	 *            调度间隔，单位：ms
	 */
	public void addPeriodicTrigger(String triggerGroup, String triggerName,
			Date beginTime, Date endTime, long repeatInterval) {
		LOG
				.debug(
						"addPeriodicTrigger, triggerGroup[{}], triggerName[{}], beginTime[{}], endTime[{}], repeatInterval[{}]",
						new Object[] { triggerGroup, triggerName, beginTime,
								endTime, repeatInterval });

		SimpleJobTrigger trigger = new SimpleJobTrigger(triggerName,
				triggerGroup);
		trigger.setBegainTime(beginTime == null ? new Date() : beginTime);
		if (endTime != null) {
			trigger.setEndTime(endTime);
		}
		trigger.setRepeatInterval(repeatInterval);
		trigger.setRepeatCount(-1);// 不断地重复，直到endtime
		triggerList.add(trigger);
	}

	/**
	 * 添加CronTrigger
	 * 
	 * @param triggerGroup
	 *            Trigger组
	 * 
	 * @param triggerName
	 *            Trigger名
	 * 
	 * @param beginTime
	 *            开始时间
	 * 
	 * @param endTime
	 *            结束时间
	 * @param cronExpression
	 *            Cron表达式
	 */
	public void addCronTrigger(String triggerGroup, String triggerName,
			Date beginTime, Date endTime, String cronExpression) {
		if (LOG.isDebugEnabled()) {
			LOG
					.debug(
							"addCronTrigger, triggerGroup[{}], triggerName[{}], beginTime[{}], endTime[{}], cronExpression[{}]",
							new Object[] { triggerGroup, triggerName,
									beginTime, endTime, cronExpression });
		}
		CronJobTrigger trigger = new CronJobTrigger(triggerName, triggerGroup);
		if (beginTime != null) {
			trigger.setBegainTime(beginTime);
		}
		if (endTime != null) {
			trigger.setEndTime(endTime);
		}
		try {
			trigger.setCronExpression(cronExpression);
			triggerList.add(trigger);
		} catch (JobException e) {
			LOG.error("add cron trigger failed", e);
		}
	}

	/**
	 * 获取JOB
	 * 
	 * @return Job
	 */
	public Job getJob() {
		return job;
	}
}

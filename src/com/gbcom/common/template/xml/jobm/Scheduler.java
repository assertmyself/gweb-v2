/**
 * Copyright 2009 GBCom Co.,Ltd. All rights reserved.
 * gbcom PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gbcom.common.template.xml.jobm;

import com.gbcom.op.scheduler.oper.IJobOper;
import com.gbcom.op.scheduler.oper.JobOperImpl;
import com.gbcom.op.scheduler.util.JobException;

/**
 * 调度封装类
 * <p>
 * <li>注册作业{@link #register(JobWrapper)}
 * <li>注销作业{@link #cancelJob(String, String)}
 * <li>注销一组作业{@link #cancelJobs(String)}
 * <li>注销触发器{@link #cancelTrigger(String, String)}
 * <li>注销一组触发器{@link #cancelTriggers(String)}
 * 
 * @author duanxiongwen
 * @version 1.0.0
 */
public final class Scheduler {
	
	private static Scheduler instance = new Scheduler();


	private IJobOper jobOper;

	/**
	 * 获取调度单例
	 * 
	 * @return {@link #instance}
	 */
	public static Scheduler getInstance() {
		return instance;
	}

	/**
	 * 注册作业
	 * 
	 * @param jobWrapper 作业包装类
	 * @throws JobException 异常
	 */
	public void register(JobWrapper jobWrapper) throws JobException {
		jobOper.register(jobWrapper.getJob());
	}

	/**
	 * 注销作业
	 * 
	 * @param jobGroup 作业组
	 * @param jobName 作业名
	 * @throws JobException 异常
	 */
	public void cancelJob(String jobGroup, String jobName) throws JobException {
		jobOper.cancelJob(jobName, jobGroup);
	}

	/**
	 * 注销一组作业,并且将他们都删除，因为如果用户先挂起一个任务，修改，再激活，那么就比较复杂了，所以直接删除，这样所有的就重来
	 * 
	 * @param jobGroup 作业组
	 * @throws JobException 异常
	 */
	public void cancelJobs(String jobGroup) throws JobException {
		
		jobOper.cancelJob(jobGroup);
		String[] jobNames = new String[]{"begin","stat","end"};
		for (String jobName : jobNames) {
			jobOper.delete(jobName, jobGroup);
		}
		
	}

	/**
	 * 注销触发器
	 * 
	 * @param triggerGroup 触发器组名
	 * @param triggerName 触发器名
	 * @throws JobException 异常
	 */
	public void cancelTrigger(String triggerGroup, String triggerName) throws JobException {
		jobOper.deleteTrigger(triggerGroup, triggerName);
	}

	/**
	 * 注销一组触发器
	 * 
	 * @param triggerGroup 触发器组名
	 * @throws JobException 异常
	 */
	public void cancelTrigger(String triggerGroup) throws JobException {
		
	}


	/**
	 * 私有构造函数
	 */
	private Scheduler() {
		jobOper = new JobOperImpl();
	}
}

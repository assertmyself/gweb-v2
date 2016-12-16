/**
 * Copyright 2009 gbcom Co.,Ltd. All rights reserved.
 * gb PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gbcom.common.template.xml.jobm.job;

import java.util.Date;

import org.apache.log4j.Logger;

import com.gbcom.op.scheduler.execute.AbstractStatelessJob;
import com.gbcom.op.scheduler.execute.JobExeContext;
import com.gbcom.op.scheduler.util.JobException;

/**
 * 云平台自动同步任务
 * 
 * @author SunYanzheng
 * @date 2014-9-3
 * @version v1.0.0
 * @see com.gbcom.omc.GwifiSyncJob.server.service.basic.job.CmpsSyncJob
 */
public class GwifiSyncJob extends AbstractStatelessJob {
	private static final Logger LOG = Logger.getLogger(GwifiSyncJob.class);

	/**
	 * 执行job
	 * 
	 * @param jobexecontext
	 *            JobExeContext
	 * @throws JobException
	 *             Exception
	 */
	@Override
	public void execute(JobExeContext jobexecontext) throws JobException {
		doWork1();
	}

	/**
	 * DO-WORK
	 */
	public void doWork1() {
		LOG.info("JOB  CmpsSyncJob  OB --BEGIN TIME = " + new Date());
		long begin = System.currentTimeMillis();
		try {
//			GwifiManager gwifManager = (GwifiManager) SpringUtils
//					.getBean("gwifiManager");
//			gwifManager.synCmpsToCCS();
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("cmps  sync  failed!!!! ", e);
		}
		LOG.info("JOB  CmpsSyncJob OB --END TIME = " + new Date()
				+ "spend-time = " + (System.currentTimeMillis() - begin) / 1000
				+ "s");
	}

}

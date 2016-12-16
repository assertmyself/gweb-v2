/**
 * Copyright 2009 gbcom Co.,Ltd. All rights reserved.
 * gb PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gbcom.common.template.xml.jobm.job;

import org.apache.log4j.Logger;
import com.gbcom.op.scheduler.execute.AbstractStatelessJob;
import com.gbcom.op.scheduler.execute.JobExeContext;
import com.gbcom.op.scheduler.util.JobException;

/**
 * ClearCpeRunJob,根据消除门限 清楚CPE
 * 
 * @author SunYanzheng
 * @date 下午6:43:32
 * @version v1.0.0
 * @see ClearCpeRunJob
 */
public class ClearCpeRunJob extends AbstractStatelessJob {
	private static final Logger LOG = Logger.getLogger(ClearCpeRunJob.class);

	/**
	 * ClearCpeRunJob
	 * 
	 * @param jobexecontext
	 *            JobExeContext
	 * @throws JobException
	 *             Exception
	 */
	@Override
	public void execute(JobExeContext jobexecontext) throws JobException {
		LOG.info("ClearCpeRunJob --- clear cpe form DB -- Begin");
		try {
			doWork1();
		} catch (Exception e) {
			LOG.error(" ClearCpeRunJob Exception  unknow", e);
		}
		LOG.info("ClearCpeRunJob --- clear cpe form DB -- END");
	}

	/**
	 * DO WORK
	 */
	public void doWork1() {/*
							 * ApDeviceService service =
							 * ApListSingleton.getInstance().getService(); int
							 * interval =
							 * SysConfigManager.getInstance().getConfig
							 * ().getClearThreshold(); if(interval ==
							 * SysConst.CMS_CONFIG_VALUE_ZERO){LOG.warn(
							 * "CMS_CONFIG_VALUE_ZERO  ;; NO NEED TO CLEAR UP");
							 * return ; } Date date = DateUtil.add(new Date(),
							 * Calendar.MONTH , -interval); List<ApDevice> list
							 * = service.getListBfTime(date); for(ApDevice bean
							 * : list){
							 * ApListSingleton.getInstance().remove(bean); }
							 */
	}

}

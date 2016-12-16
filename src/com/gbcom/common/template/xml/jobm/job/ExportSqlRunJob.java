/**
 * Copyright 2009 gbcom Co.,Ltd. All rights reserved.
 * gb PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gbcom.common.template.xml.jobm.job;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.gbcom.common.template.xml.jobm.JobParam;
import com.gbcom.common.template.xml.jobm.JobWrapperInfo;
import com.gbcom.op.scheduler.execute.AbstractStatelessJob;
import com.gbcom.op.scheduler.execute.JobExeContext;
import com.gbcom.op.scheduler.util.JobException;
import com.gbcom.op.util.SystemUtils;
import com.gbcom.system.mysql.SqlExportManager;

/**
 * 定时导出SQL文件并上传到FTP服务器 1.调用CfgSqlExportService 生成文件 2.获取jobMAP 参数
 * 3.使用平台包FTP上传至服务器
 * 
 * @author SunYanzheng
 * @date 下午1:03:57
 * @version v1.0.0
 * @see ExportSqlRunJob
 */
public class ExportSqlRunJob extends AbstractStatelessJob {
	private static final String FILENAME = "ExportSQL";
	private static final Logger LOG = Logger.getLogger(ExportSqlRunJob.class);
	private static final String PARAM_SERVERIP = "serverIp";
	private static final String PARAM_USERNAME = "userName";
	private static final String PARAM_PASSWORD = "password";

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
		Map<String, String> jobMap = new HashMap<String, String>();
		JobWrapperInfo jobWrapperInfo = (JobWrapperInfo) jobexecontext.getMap()
				.getValue("job");
		String jobName = jobWrapperInfo.getJobName();
		for (JobParam param : jobWrapperInfo.getJobParamList()) {
			LOG.info("JOB - " + jobName + " ::param name=" + param.getName()
					+ " value=" + param.getValue());
			jobMap.put(param.getName(), param.getValue());
		}
		try {
			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss)");
			String fileName = FILENAME + sf.format(date.getTime()) + ".sql";
			String path = SystemUtils.USER_HOME + File.separator + fileName;
			SqlExportManager sqlService = new SqlExportManager();
			boolean isSuccess = sqlService.exportSql(path);
			if (isSuccess) {
				//todo
			}
		} catch (IOException e) {
			LOG.error("export sql failed>> IOException:读取属性文件错误", e);
		} catch (Exception e) {
			LOG.error("export sql failed >>other exception", e);
		}

	}

}

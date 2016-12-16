package com.gbcom.common.template.xml.jobm;

import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gbcom.common.template.xml.northful.NorthConfigManager;
import com.gbcom.op.scheduler.util.JobException;
import com.gbcom.op.util.xml.XStreamUtil;

/**
 * Job管理服务，使用OP平台Schedule，底层使用Qutarz实现 1.加载jobWrapper.xml 2.注册为服务并运行
 * 
 * @author SunYanzheng
 * @date 上午10:18:59
 * @version v1.0.0
 * @see JobWrapperManager
 */
public final class JobWrapperManager {
	private static final Logger LOG = LoggerFactory
			.getLogger(JobWrapperManager.class);

	private JobWrapperContext context = new JobWrapperContext();
	private Map<String, JobWrapperInfo> jobWrapperMap;
	private static JobWrapperManager instance = new JobWrapperManager();

	/**
	 * 获取单例
	 * 
	 * @return MibNodeManager
	 */
	public static JobWrapperManager getInstance() {
		return instance;
	}

	private JobWrapperManager() {
		jobWrapperMap = new HashMap<String, JobWrapperInfo>();
		try {
			final Class<?>[] classContext = { JobWrapperContext.class,
					JobWrapperInfo.class, TriggerInfo.class, JobParam.class };
			
			List<JobWrapperInfo> jobinfo = new ArrayList<JobWrapperInfo>();
			//核心 job 
			final URL url = Thread.currentThread().getContextClassLoader()
					.getResource("config/job/core_jobwrapper.xml");
			JobWrapperContext coreContext = XStreamUtil.fromXML(JobWrapperContext.class,url.getFile(),classContext);
			for (JobWrapperInfo info : coreContext.getList()) {
				jobinfo.add(info);
			}
			
			//辅助 北向的job:允许多个
			List<String> names = NorthConfigManager.getInstance().getNorthConfig().getList();
			for (String name : names) {
				String path = null;
				//华数
				if(name.equals("xxx")){
					path = "config/job/wasu_jobwrapper.xml";
				}

				if( path != null){
					final URL url2 = Thread.currentThread().getContextClassLoader() .getResource(path);
					JobWrapperContext northContext = XStreamUtil.fromXML(JobWrapperContext.class,url2.getFile(),classContext);
					for (JobWrapperInfo info : northContext.getList()) {
						jobinfo.add(info);
					}
				}
			}
			context.setList(jobinfo);
			for (JobWrapperInfo jobWrapper : context.getList()) {
				jobWrapperMap.put(jobWrapper.getJobName(), jobWrapper);
			}
			LOG.info("parse file success ;;; url=" + url);
		} catch (Exception e) {
			LOG.error("parse file failed!", e);
		}
	}
	
	/**
	 * 从配置文件中获取所有JOB信息
	 * @return List<JobWrapperInfo> 
	 */
	public List<JobWrapperInfo> getJobWrapperList() {
		return context.getList();
	}

	/**
	 * 获取指定JOB的 信息，
	 * @param jobName JOB名称，用来唯一确定JOB。
	 * @return JobWrapperInfo
	 */
	public JobWrapperInfo getJobWrapperByName(final String jobName) {
		return jobWrapperMap.get(jobName);
	}

	/**
	 * 更新jobWrapper.xml的 JOBmap信息
	 * 
	 * @param jobName JOB名称，用来唯一确定JOB。
	 * @param params List<JobParam>JOB map信息
	 */
	public void setJobParamList(final String jobName,
			final List<JobParam> params) {
		final Class<?>[] classContext = { JobWrapperContext.class,
				JobWrapperInfo.class, TriggerInfo.class, JobParam.class };
		final JobWrapperInfo info = jobWrapperMap.get(jobName);
		info.setJobParamList(params);
		final URL url = Thread.currentThread().getContextClassLoader()
				.getResource("config/job/core_jobwrapper.xml");
		XStreamUtil.toXML(context, url.getFile(), classContext);
	}

	/**
	 * 注册JOB 1.读取配置文件所有job 2.安装job的调度策略执行 3.将job信息封装到JobMap中
	 * 
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws JobException JobException
	 */
	@SuppressWarnings("unchecked")
	public void register() throws ClassNotFoundException, JobException {
		// 1.读取所有job
		final List<JobWrapperInfo> jobList = JobWrapperManager.getInstance()
				.getJobWrapperList();
		for (JobWrapperInfo info : jobList) {
			final List<TriggerInfo> triggerList = info.getTriggerList();
			if (triggerList == null || triggerList.isEmpty()) {
				continue;
			}
			final String jobClass = info.getJobClass();
			final String jobGroup = info.getJobGroup();
			final String jobName = info.getJobName();
			final Class clz =  Class.forName(jobClass);
			final JobWrapper jobWrapper = new JobWrapper(jobGroup, jobName, clz);
			for (TriggerInfo trigger : triggerList) {
				final boolean isSample = trigger.isSample();
				final String triggerGroup = trigger.getTriggerGroup();
				final String triggerName = trigger.getTriggerName();
				if (isSample) {
					final long repeatInterval = trigger.getRepeatInterval();
					jobWrapper.addPeriodicTrigger(triggerGroup, triggerName,
							null, null, repeatInterval);
				} else {
					final String cronExpression = trigger.getCronExpression();
					jobWrapper.addCronTrigger(triggerGroup, triggerName, null,
							null, cronExpression);
				}
			}
			jobWrapper.addJobProperty("job", info);
			Scheduler.getInstance().register(jobWrapper);
		}
	}

	/**
	 * test
	 * 
	 * @param args
	 *            String[]
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws JobException JobException
	 * @throws ParseException ParseException
	 */
	public static void main(final String[] args) throws ClassNotFoundException,
			JobException, ParseException {
		JobWrapperManager.getInstance().register();
		final List<JobParam> list = new ArrayList<JobParam>();
		for (int i = 0; i < 5; i++) {
			final JobParam param = new JobParam();
			param.setName("111" + i);
			param.setValue("222" + i);
			list.add(param);
		}
		JobWrapperManager.getInstance().setJobParamList("example", list);
	}

}

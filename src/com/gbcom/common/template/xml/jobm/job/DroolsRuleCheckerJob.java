package com.gbcom.common.template.xml.jobm.job;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.gbcom.common.drools.DroolsEngineFactory;
import com.gbcom.common.drools.DroolsRuleEngine;
import com.gbcom.op.scheduler.execute.AbstractStatelessJob;
import com.gbcom.op.scheduler.execute.JobExeContext;
import com.gbcom.op.scheduler.util.JobException;
import com.gbcom.system.utils.DateUtil;

/**
 * DroolsRule 规则检查。。
 *   非 DroolsRule 规则的加载，为 excute fact 流程。
 * 
 * <p>
 * @author syz
 * <p>
 * @date 2015-7-6,下午05:01:30
 * <p>
 * @version v1.0.0
 * <p>
 * @see com.gbcom.ccsv3.template.xml.jobm.job.DroolsRuleCheckerJob
 */
public class DroolsRuleCheckerJob extends AbstractStatelessJob {

	private static final Logger LOG = Logger.getLogger(DroolsRuleCheckerJob.class);
	
	/**
	 * 执行job
	 * @param context JobExeContext
	 * @throws JobException Exception
	 */
	@Override
	public void execute(JobExeContext context) throws JobException {
		//单线程方式执行
		start();
		
	}
	
	
	/**
	 * 开始执行job
	 */
	public void start() {
		// 重启队列可以考虑阻塞队列
//		AutoRebootHandler.getInstance().clear();
		doJob();
	}

	private synchronized void doJob() {
//		List<ApCache> list = HeartListSingleton.getInstance().getOnlineList();
		List list = new ArrayList(0);
		long begin = System.currentTimeMillis();
		LOG.info("~~~~ BEGIN Check DroolsRuleCheckerJob  ~~~~  size="+list.size()+"; Time= "+DateUtil.format(new Date()));
		DroolsRuleEngine engine = DroolsEngineFactory.dbEngine();
		engine.refreshEnginRule();
		List<Object> facts = new ArrayList<Object>();
		for(Object obj : list){
			facts.add(obj);
		}
		engine.executeRuleEngine(facts);
		LOG.info("~~~~ END Check DroolsRuleCheckerJob  ~~~~  Time= "+DateUtil.format(new Date()) +";spengd times=" + (System.currentTimeMillis()-begin)/1000+"S");
	}

	

}

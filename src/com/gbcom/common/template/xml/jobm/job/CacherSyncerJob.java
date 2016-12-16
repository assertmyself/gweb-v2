package com.gbcom.common.template.xml.jobm.job;

import java.util.Date;
import org.apache.log4j.Logger;
import com.gbcom.op.scheduler.execute.AbstractStatelessJob;
import com.gbcom.op.scheduler.execute.JobExeContext;
import com.gbcom.op.scheduler.util.JobException;

/**
 * 系统缓存同步。
 * 
 * @author SunYanzheng
 * @date 2014-12-23
 * @version v1.0.0
 * @see com.gbcom.omc.ccsv2.server.service.basic.job.CacherSyncerJob
 */
public class CacherSyncerJob extends AbstractStatelessJob {

	private static final Logger LOG = Logger.getLogger(CacherSyncerJob.class);

	/**
	 * 执行job
	 * 
	 * @param context
	 *            JobExeContext
	 * @throws JobException
	 *             Exception
	 */
	@Override
	public void execute(JobExeContext context) throws JobException {
		LOG.info("JOB  CacherSyncerJob  OB --BEGIN TIME = " + new Date());
		// 单线程方式执行
		executeJOB();

		LOG.info("JOB  CacherSyncerJob  OB --END TIME = " + new Date());
	}

	private void executeJOB() {/*
								 * Map<String,Map<String, ApDevice>> gwMap =
								 * ApListSingleton.getInstance().getGwApMap();
								 * 
								 * 
								 * synchronized (gwMap) {
								 * for(Entry<String,Map<String,ApDevice>> entry
								 * : gwMap.entrySet()){ try { String gwId =
								 * entry.getKey(); Map<String,ApDevice> subMap =
								 * entry.getValue(); Set<String> set =
								 * subMap.keySet(); Iterator<String> it =
								 * set.iterator(); while (it.hasNext()) { String
								 * k = it.next();
								 * if(!subMap.get(k).getGwId().equals(gwId)){
								 * //热点信息发生了更改
								 * LOG.error("GWAPMAP-CACHER (GW-ID ERROR):   MAP="
								 * +entry.getValue());LOG.error(
								 * "GWAPMAP-CACHER (GW-ID ERROR):   old-GWID=" +
								 * gwId + " ,  gwId="+subMap.get(k).getGwId()
								 * +" , apMac="+subMap.get(k).getManageMac());
								 * it.remove(); }
								 * 
								 * if(!subMap.get(k).getManageMac().equals(k)){
								 * //设备MAC发生更改LOG.error(
								 * "GWAPMAP-CACHER (MAC ERROR):   OLD-MAC=" + k
								 * + " ,  MAC="+subMap.get(k).getManageMac());
								 * it.remove(); } } } catch (Exception e) {
								 * continue; }
								 * 
								 * 
								 * } }
								 */
	}

	/**
	 * 测试方法
	 * 
	 * @param args
	 *            String[]
	 */
	public static void main(String[] args) {
		new CacherSyncerJob().executeJOB();
	}

}

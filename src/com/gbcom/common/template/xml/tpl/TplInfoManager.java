package com.gbcom.common.template.xml.tpl;

import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gbcom.common.template.xml.oem.Oem;
import com.gbcom.common.template.xml.oem.OemManager;
import com.gbcom.op.scheduler.util.JobException;
import com.gbcom.op.util.xml.XStreamUtil;

/**
 * 
 * TplInfoManager
 * <p>
 * 
 * @author syz
 *         <p>
 * @date 2015-6-18,下午01:44:16
 *       <p>
 * @version v1.0.0
 *          <p>
 * @see com.gbcom.ccsv3.template.xml.tpl.TplInfoManager
 */
public final class TplInfoManager {
	private static final Logger LOG = LoggerFactory
			.getLogger(TplInfoManager.class);

	private TplInfoContext context;
	private Map<String, List<Tplclz>> sysmodeMap;
	private Map<String, String> jspMap;
	private static TplInfoManager instance = new TplInfoManager();

	/**
	 * 获取单例
	 * 
	 * @return MibNodeManager
	 */
	public static TplInfoManager getInstance() {
		return instance;
	}

	private TplInfoManager() {
		sysmodeMap = new HashMap<String, List<Tplclz>>();
		jspMap = new HashMap<String, String>();
		try {
			final Class<?>[] classContext = { TplInfoContext.class,
					TplInfo.class, Tplclz.class };
			final URL url = Thread.currentThread().getContextClassLoader()
					.getResource("IM/im_model_case.xml");
			context = XStreamUtil.fromXML(TplInfoContext.class, url.getFile(),
					classContext);
			Oem oem = OemManager.getInstance().getOem();
			for (TplInfo tpl : context.getList()) {
				for (String sysmodel : tpl.getSysmodels().split(",")) {
					List<Tplclz> sub = new ArrayList<Tplclz>();
					for(Tplclz clz : tpl.getClzList()){
						if(clz.getPline()!=null&&!clz.getPline().trim().equals("")){
							LOG.info("the oem pline tpl ,need extend process!, clz="+clz.getName()+";getPline "+clz.getPline() +";getTab1"+clz.getTab1());
							if(clz.getPline().equalsIgnoreCase(oem.getPline())){
								sub.add(clz);
							}
						}else{
							sub.add(clz);
						}
					}
					sysmodeMap.put(sysmodel.trim(), sub);
					jspMap.put(sysmodel.trim(), tpl.getJsp());
				}
			}
			LOG.info("parse file success ;;; url=" + url);
		} catch (Exception e) {
			LOG.error("parse file failed!", e);
		}
	}

	/**
	 * 从配置文件中获取所有JOB信息
	 * 
	 * @return List<JobWrapperInfo>
	 */
	public List<TplInfo> getJobWrapperList() {
		return context.getList();
	}

	/**
	 * 获取指定JOB的 信息， 当前所有菜单
	 * 
	 * @param sysmodel
	 *            JOB名称，用来唯一确定JOB。
	 * @return JobWrapperInfo
	 * @throws Exception Exception
	 */
	public List<Tplclz> getClzBysysmodel(final String sysmodel) throws Exception {
		if(sysmodeMap.get(sysmodel.toUpperCase()) == null){
			throw new Exception("配置模板不存在！");
		}
		return sysmodeMap.get(sysmodel.toUpperCase());
	}

	
	/**
	 * 判断设备型号是否存在
	 * 如果不存在，则不能进行后续操作
	 * @param sysmodel String
	 * @return boolean
	 */
	public boolean isContains(final String sysmodel){
		return sysmodeMap.containsKey(sysmodel)&&jspMap.containsKey(sysmodel);
	}
	/**
	 * 根据设备类型获取相应的配置模板页面
	 * 
	 * @param sysmodel
	 *            设备类型
	 * @return 模板页面
	 */
	public String getJspBysysmodel(final String sysmodel) {
		return jspMap.get(sysmodel);
	}

	/**
	 * test
	 * 
	 * @param args
	 *            String[]
	 * @throws ClassNotFoundException
	 *             ClassNotFoundException
	 * @throws JobException
	 *             JobException
	 * @throws ParseException
	 *             ParseException
	 * @throws Exception  Exception
	 */
	@SuppressWarnings("unchecked")
	public static void main(final String[] args) throws ClassNotFoundException,
			JobException, ParseException, Exception {

		List list = TplInfoManager.getInstance().getClzBysysmodel("wa2020");
		System.out.println(list);
		String jsp = TplInfoManager.getInstance().getJspBysysmodel("WA2020");
		System.out.println(jsp);
	}

}

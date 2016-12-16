package com.gbcom.common.template.xml.sys;

import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.log4j.Logger;

import com.gbcom.omc.si.common.Const;
import com.gbcom.omc.si.discovery.discover.SnmpSingleton;
import com.gbcom.op.util.Assert;
import com.gbcom.op.util.xml.XStreamUtil;

/**
 * 配置文件单例类:只获取配置数据
 * 
 * @author SunYanzheng
 * @date 下午3:33:05
 * @version v1.0.0
 * @see SysConfigManager
 */
public final class SysConfigManager {
	private static final Logger LOG = Logger.getLogger(SysConfigManager.class);
	private static final String CONFIG_PATH = "config/sys/sys_config.xml";
	private SacConfig conf = null;
	// 有次序MAP
	private Map<String, ApSysModel> devMap = new LinkedHashMap<String, ApSysModel>();
	private boolean isInit = false;
	private static SysConfigManager instance = new SysConfigManager();

	private SysConfigManager() {
		// init();
	}

	/**
	 * 单例对象
	 * 
	 * @return 单例对象
	 */
	public static SysConfigManager getInstance() {
		return instance;
	}

	/**
	 * 初始化
	 */
	public void init() {
		/**
		 * 解析配置文件
		 */

		LOG.info("---- init system config : sac_config.xml file ---");
		parseFile();
		/**
		 * 设置SI源ip地址
		 */
		try {
			if (!Const.sourceSnmpIp.equals(conf.getSnmpIp())) {
				Const.sourceSnmpIp = conf.getSnmpIp();
				SnmpSingleton.refreshTransportMapping();
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}

		if (!isInit) {
			// 从数据库获取配置，，sxp需求
/*			try {
				SysCfgDBService sysCfgDBService = (SysCfgDBService) SpringUtils
						.getBean("sysCfgDBService");
				SysCfgDB db = sysCfgDBService.getCfg();
				if (db != null) {
					conf.getFtp().setFtpIp(db.getFtpIp());
					conf.getFtp().setFtpWIp(db.getFtpWIp());
					conf.getFtp().setFtpWport(db.getFtpWport());
					conf.setSnmpIp(db.getSnmpIp());
					save();
				}
			} catch (Exception e) {
			}*/
		}

		isInit = true;
	}

	private void parseFile() {
		Class<?>[] classContext = { SacConfig.class, ApSysModel.class,
				TargetVer.class, FtpConfig.class };
		URL url = this.getClass().getClassLoader().getResource(CONFIG_PATH);
		conf = XStreamUtil
				.fromXML(SacConfig.class, url.getFile(), classContext);
		for (ApSysModel dev : conf.getDevices()) {
			devMap.put(dev.getSysModel(), dev);
		}
	}

	/**
	 * 获取配置
	 * 
	 * @return CmsConfig
	 */
	public SacConfig getConfig() {
		return conf;
	}

	/**
	 * 是否初始化
	 * 
	 * @return boolean
	 */
	public boolean isInit() {
		return isInit;
	}

	/**
	 * 获取所有设备类型
	 * 
	 * @return List<Device>
	 */
	public Map<String, ApSysModel> getDevMap() {
		return devMap;
	}

	/**
	 * sysmodel list
	 * 
	 * @return List<String>
	 */
	public List<String> getSysmodelList() {
		List<String> apModelList = new ArrayList<String>();
		for (Entry<String, ApSysModel> entity : devMap.entrySet()) {
			apModelList.add(entity.getKey());
		}

		return apModelList;

	}

	/**
	 * 获取指定OEM对应的硬件平台信息。
	 * 
	 * @param sysModel
	 *            String
	 * @return List<Device>
	 */
	public ApSysModel getApSysModel(String sysModel) {
		return devMap.get(sysModel);
	}

	/**
	 * 获取目标版本
	 * 
	 * @param type
	 *            String
	 * @return TargetVer
	 */
	public TargetVer getTargetVer(String type) {
		List<TargetVer> tarVers = conf.getTargetVers();
		for (TargetVer ver : tarVers) {
			if (ver.getDeType().equals(type)) {
				return ver;
			}
		}
		return null;
	}

	/**
	 * 保存
	 * 
	 * @param name
	 *            String
	 * @param value
	 *            String
	 */
	@Deprecated
	public void save(String name, String value) {
		Assert.notNull(name);
		Assert.notNull(value);
		if (name.equals(SysConst.CONFIG_PORT)) {
			conf.setPort(value);
		}
		if (name.equals(SysConst.CONFIG_INNERALARM)) {
			conf.setInnerAlarm(Boolean.valueOf(value));
		}
		if (name.equals(SysConst.CONFIG_FTPDIR)) {
			FtpConfig ftpConfig = conf.getFtp();
			ftpConfig.setFtpIp(value);
			conf.setFtp(ftpConfig);
		}
		if (name.equals(SysConst.CONFIG_HEART)) {
			conf.setHeart(Integer.parseInt(value));
		}
		if (name.equals(SysConst.CONFIG_SNMP_IP)) {
			conf.setSnmpIp(value);
		}
		if (name.equals(SysConst.CONFIG_REPORT_INTERVAL)) {
			conf.setReportInterval(Integer.parseInt(value));
		}
		if (name.equals(SysConst.CONFIG_AUTO_UPGRADE)) {
			conf.setAutoUpgrade(Boolean.parseBoolean(value));
		}
		if (name.equals(SysConst.CONFIG_ACTTHRESHOLD)) {
			conf.setActThreshold(Integer.parseInt(value));
		}
		if (name.equals(SysConst.CONFIG_CLEARTHRESHOLD)) {
			conf.setClearThreshold(Integer.parseInt(value));
		}
		save();
		init();
	}

	/**
	 * 保存文件
	 */
	public void save() {
		Class<?>[] classContext = { SacConfig.class, ApSysModel.class,
				TargetVer.class, FtpConfig.class };
		URL url = this.getClass().getClassLoader().getResource(CONFIG_PATH);
		// 保存文件失败
		XStreamUtil.toXML(conf, url.getFile(), classContext);
		LOG.info("SAVE CONFIG_FILE SUCCESS! ");

	}

	/**
	 * test--method
	 * 
	 * @param args
	 *            string
	 * @throws Exception
	 *             exception
	 */
	public static void main(String[] args) throws Exception {

		SysConfigManager.getInstance().init();

		// 加入 升级队列 并交个升级线程处理 ：
		// 调用升级服务
		FtpConfig conf = SysConfigManager.getInstance().getConfig().getFtp();
		// 目标版本
		// 建立ftp连接 FTP验证是否存在该版本
		@SuppressWarnings("unused")
		FTPClient client = new FTPClient();
		System.out.println("--------------------Device-------");
		System.out.println(SysConfigManager.getInstance().getDevMap());
		System.out.println("--------------------Device-------");

	}
	
	private static class SysConst{
		public static final String CONFIG_PORT="Port";
		public static final String CONFIG_HEART="Heart";
		public static final String CONFIG_ACTTHRESHOLD="actThreshold";
		public static final String CONFIG_CLEARTHRESHOLD="clearThreshold";
		public static final String CONFIG_SNMP_IP="SnmpIP";
		public static final String CONFIG_REPORT_INTERVAL="ReportInterval";
		public static final String CONFIG_AUTO_UPGRADE="AutoUpgrade";
		public static final String CONFIG_INNERALARM="InnerAlarm";
		public static final String CONFIG_FTPDIR="FtpDir";
	}
}

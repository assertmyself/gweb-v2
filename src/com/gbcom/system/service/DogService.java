package com.gbcom.system.service;

import java.io.File;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import p1.DOGException;

import com.gbcom.common.template.res.BasicResManager;
import com.gbcom.common.template.xml.sys.SysConfigManager;
import com.gbcom.omc.verify.SoftDogLicense;
import com.gbcom.omc.verify.SoftDogVerfier;
import com.gbcom.system.service.support.DogServerDlg;

/**
 * 心跳服务
 * 
 * @author SunYanzheng
 * @date 下午5:11:53
 * @version v1.0.0
 * @see MonitorService
 */
public final class DogService {
	private static DogService instance = new DogService();
	private static final Logger LOG = Logger.getLogger(DogService.class);
	private final int accessNum; 
	private static final int  MAX_ACCESS_NUM=64; 
	//是否合法
	private boolean validate = false;
	private String message = "启动失败";

	/**
	 * isValidate
	 * @return boolean
	 */
	public boolean isValidate() {
		return validate;
	}

	/**
	 * setValidate
	 * @param validate boolean
	 */ 
	public void setValidate(final boolean validate) {
		this.validate = validate;
	}

	/**
	 * getMessage
	 * @return String
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * setMessage
	 * @param message String
	 */
	public void setMessage(final String message) {
		this.message = message;
	}

	private DogService() {
		final int number = SysConfigManager.getInstance().getConfig().getNumber();
		accessNum = number>MAX_ACCESS_NUM?MAX_ACCESS_NUM:number;
	}

	/**
	 * 获取单例对象
	 * 
	 * @return HeartService
	 */
	public static DogService getInstance() {
		return instance;
	}

	private void init() {
		if (!System.getProperty("os.name").toLowerCase().contains("windows")){
			//非window平台 不进行加密狗检测  ,so the 
			validate = true;
			return;
		}
		
		LOG.info("INIT DogService");
		// 任务调度线程池
		
		final ScheduledExecutorService schedule = Executors
				.newScheduledThreadPool(1);
		schedule.scheduleWithFixedDelay(new Runnable() {
			@Override
			public void run() {
				try {
//					long cpenum = ApListSingleton.getInstance().getService().getAllCounts();
//					long cpenum = ((ApDeviceService)SpringUtils.getBean("apDeviceService")).getAllCounts();
					long cpenum = 0;
					if(LOG.isDebugEnabled()){
						LOG.info("Check DOG ::: THE TOTAL AP COUNT IS "+cpenum+" ;  TIME = "+new Date());
					}
					if (cpenum <= accessNum) {
						DogServerDlg.getInstance().showDailog("success");
						DogServerDlg.setValidate(true);
						setValidate(true);
						setMessage("success");
					} else {
						long result = SoftDogVerfier.validate();
						if (result!=0) {
							DogServerDlg.getInstance().showDailog(
									DOGException.getErrorMsg((int) result, 0));
							DogServerDlg.setValidate(false);
							setValidate(false);
							LOG.info("DogService : "
									+ DOGException.getErrorMsg((int) result, 0));
							setMessage(DOGException.getErrorMsg((int) result, 0));
						} else {
							int cpeAuthorNum = 0;
							try {
								cpeAuthorNum = Integer.valueOf(SoftDogVerfier.readAuthorNum());
							} catch (NumberFormatException e) {
								DogServerDlg.getInstance().showDailog(
										" ---  加密狗读取授权数失败!  --");
								DogServerDlg.setValidate(false);
								setValidate(false);
								LOG.info("DogService : "
										+ DOGException.getErrorMsg((int) result, 0));
								setMessage(" ---  加密狗读取授权数失败!  --");
							}
							if (cpenum > cpeAuthorNum) {
								DogServerDlg.getInstance().showDailog(
										"您的授权项数目为" + cpeAuthorNum + "小于您的实际使用值"
												+ cpenum);
								DogServerDlg.setValidate(false);
								setValidate(false);
								LOG.info("DogService : "
										+ DOGException.getErrorMsg((int) result, 0));
								setMessage("您的授权项数目为" + cpeAuthorNum + "小于您的实际使用值"
										+ cpenum);
							} else {
								DogServerDlg.getInstance().showDailog("success");
								DogServerDlg.setValidate(true);
								setValidate(true);
								if(LOG.isDebugEnabled()){
									LOG.info("DogService : "
											+ DOGException.getErrorMsg((int) result, 0));
								}
								setMessage("success");
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					LOG.error("ERROR", e);
				}

			}

		}, 1, 3, TimeUnit.SECONDS);		

		LOG.info("INIT DogService  end！");
	}

	/**
	 * 开始
	 */
	public void start() {
		init();
	}

	
	/**
	 * License 扩容：String
	 * @param character String
	 * @return boolean
	 */
	public boolean upgradeLicenseStr(final String character){
		LOG.info("DOG -- upgradeLicenseStr :: character="+character );
		boolean flag = false;
		try {
			flag = SoftDogLicense.upgradeLicenseStr(character);
		} catch (Exception e) {
			LOG.error("DOG -- upgradeLicenseStr  failed!  character="+ character,e);
			throw new RuntimeException(BasicResManager.getString("license_upgrade_failed")+" ; character="+character);
		}
		LOG.info("DOG -- upgradeLicenseStr :: character="+character +"; resutl="+flag);
		return flag;
	} 
	
	/**
	 * License 扩容:文件
	 * @param fileName String
	 * @return boolean
	 */
	public boolean upgradelicenseFile(final String fileName){
		boolean flag = false;
		try {
			flag = SoftDogLicense.upgradeLicenseStr(new File(fileName));
		} catch (Exception e) {
			LOG.error("DOG -- upgradelicenseFile  failed!  fileName="+fileName ,e);
			throw new RuntimeException(BasicResManager.getString("license_upgrade_failed")+" ; fileName="+fileName);
		}
		LOG.info("DOG -- upgradelicenseFile :: fileName="+fileName +"; resutl="+flag);
		return flag;
	} 
	
	/**
	 * 获得加密狗授权数，供外界调用
	 * 
	 * @return accessNum String
	 */
	public String getDogAccessNum() {
		if(!System.getProperty("os.name").toLowerCase().contains("windows")){
			//linux 为无穷
			return "----";
		}
		String authorNum = "";
		long result = SoftDogVerfier.validate();
		if (result != 0L) {
			authorNum = BasicResManager.getString("Basic_dog_number") + accessNum;
		} else {
			try {
				int readAuthorNumber = Integer.valueOf(SoftDogVerfier.readAuthorNum());
				authorNum = ""+ readAuthorNumber;
			} catch (NumberFormatException e) {
				authorNum = BasicResManager.getString("Basic_dog_number")+accessNum;
				LOG.error("NumberFormatException：",e);
			}
		}
		return authorNum;
	}
	
	
	
}

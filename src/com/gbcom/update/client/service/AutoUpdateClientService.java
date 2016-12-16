package com.gbcom.update.client.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.gbcom.system.utils.HttpClientUtil;
import com.gbcom.system.utils.JsonUtil;
import com.gbcom.update.client.manager.VersionInfoManager;
import com.gbcom.update.client.xml.UpdateClientContextManager;
import com.gbcom.update.common.VersionInfo;
import com.gbcom.update.common.exception.AutoUpdateException;
import com.gbcom.update.common.util.AutoUpdateUtil;

/**
 * 客户端自动升级业务类
 * 
 * @author doujun
 * 
 */
@Service("AutoUpdateService")
public class AutoUpdateClientService {
	/**
	 * 日志记录器
	 */
	private static final Logger LOG = Logger
			.getLogger(AutoUpdateClientService.class);

	/**
	 * 检查版本信息
	 * 
	 * @return 版本信息
	 * @throws AutoUpdateException
	 *             AutoUpdateException
	 */
	public VersionInfo checkedVer() throws AutoUpdateException {
		try {
			String product = UpdateClientContextManager.getInstance()
					.getUpdateProduct();
			VersionInfo versionInfoCli = UpdateClientContextManager
					.getInstance().getVersionInfo(product);
			String json = HttpClientUtil.get(AutoUpdateUtil
					.getProperty("server.url.pre")
					+ "/rest/update/checkedVer?product="
					+ product
					+ "&curVer="
					+ versionInfoCli.getVersion()
					+ "&verNo="
					+ versionInfoCli.getNo()
					+ "&time="
					+ versionInfoCli.getDate());
			if (json == null || json.contains("404")) {
				return null;
			}else if(json.contains("404")){
				throw new AutoUpdateException("无法连接到版本服务器");
			} else {
				versionInfoCli = JsonUtil.jsonToBean(json, VersionInfo.class);
				// 缓存最新版本信息
				VersionInfoManager.getInstance().put(product, versionInfoCli);
				return versionInfoCli;
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			throw new AutoUpdateException("check version exception :", e);
		}
	}

	/**
	 * 
	 * 获取服务器上最新的版本
	 * 
	 * @param product
	 *            产品
	 * @param request
	 *            HttpServletRequest
	 * @throws AutoUpdateException
	 *             AutoUpdateException
	 */
	public void downNewestVersion(HttpServletRequest request, String product)
			throws AutoUpdateException {
		try {
			InputStream inputStream = HttpClientUtil.getAsStream(AutoUpdateUtil
					.getProperty("server.url.pre")
					+ "/rest/update/downloadVer?product=" + product);
			if (inputStream == null) {
				throw new Exception(
						"file that you want to download is not exist!");
			}
			String realPath = request.getSession().getServletContext()
					.getRealPath("");
			realPath = AutoUpdateUtil.parsePath(realPath, AutoUpdateUtil
					.getProperty("client.temp.path"));
			File tempFile = new File(realPath);
			if (!tempFile.exists()) {
				tempFile.mkdir();
			}
			String filePath = realPath + File.separator + product + ".zip";
			filePath = filePath.replaceAll("\\\\", "/");
			long startTime = System.currentTimeMillis();
			LOG.info("client down file start; file path : " + filePath);
			AutoUpdateUtil.downloadAsStream(filePath, inputStream);
			long endTime = System.currentTimeMillis();
			long spend = (endTime - startTime) / 1000;
			LOG.info("client down file done! fiel path : " + filePath
					+ ";; spend " + spend + " s");
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			throw new AutoUpdateException(
					"down newest version file exception :", e);
		}
	};

	/**
	 * 获取最新版本的数据库脚本
	 * 
	 * @param product
	 *            产品
	 * @param request
	 *            HttpServletRequest
	 * @param time
	 *            时间
	 * @throws AutoUpdateException
	 *             AutoUpdateException
	 */
	public void downNewestSql(HttpServletRequest request, String product,
			String time) throws AutoUpdateException {
		try {
			InputStream inputStream = HttpClientUtil.getAsStream(AutoUpdateUtil
					.getProperty("server.url.pre")
					+ "/rest/update/downloadSQL?product="
					+ product
					+ "&time="
					+ time);
			if (inputStream == null) {
				throw new AutoUpdateException(
						"you want to download file is not exsit!");
			}
			String realPath = request.getSession().getServletContext()
					.getRealPath("");
			realPath = realPath.substring(0, realPath.indexOf("apache-tomcat"));
			String filePath = AutoUpdateUtil.parsePath(realPath, AutoUpdateUtil
					.getProperty("client.temp.path"));
			filePath = filePath + File.separator
					+ AutoUpdateUtil.getProperty("project.sql.name") + ".zip";
			filePath = filePath.replaceAll("\\\\", "/");
			LOG.info("client down file start! fiel path : " + filePath);
			AutoUpdateUtil.downloadAsStream(filePath, inputStream);
			LOG.info("client down file done! fiel path : " + filePath);
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
			throw new AutoUpdateException("down newest sql file exception :", e);
		}
	};

	/**
	 * 重启服务
	 * 
	 * @param path
	 *            服务器路径
	 * @throws Exception
	 *             Exception
	 */
	public void restartServer(String path) throws Exception {
		LOG.info("restart tomcat !");
		try {
			if (System.getProperties().getProperty("os.name").equals("Linux")) {
				linuxRestartServer(path);
			} else {
				windowsRestartServer(path);
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			throw new AutoUpdateException("restart server exception :", e);
		}
	};

	/**
	 * linux环境下重启服务器
	 * 
	 * @param path
	 *            服务器路径
	 * @throws Exception
	 *             Exception
	 */
	private void linuxRestartServer(String path) throws Exception {
		try {
			if (path == null || path.equals("")) {
				throw new Exception("tomcat path wrong :" + path);
			}
			stopLinuxServer(path);
			String command = path + File.separator + "startup.sh";
			AutoUpdateUtil.executeCmd(command, null);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			throw e;
		}
	}

	/**
	 * window环境下重启服务器
	 * 
	 * @param path
	 *            服务器路径
	 * @throws Exception
	 *             Exception
	 */
	private void windowsRestartServer(String path) throws Exception {
		if (path == null || path.equals("") || path.indexOf("bin") == -1) {
			throw new Exception("tomcat path wrong :" + path);
		}
		stopWindowsServer(path);
		String command = "cmd /c " + path + File.separator + "startup.bat";
		String filePath = path.substring(0, path.indexOf("bin") - 1);
		AutoUpdateUtil.executeCmd(command, filePath);

	}

	private void stopLinuxServer(String path) throws Exception {
		String command = path + File.separator + "shutdown.sh";
		AutoUpdateUtil.executeCmd(command, null);
	}

	/**
	 * 停止window服务器
	 * 
	 * @param path
	 *            服务器bin目录
	 * @throws Exception
	 *             Exception
	 */
	private void stopWindowsServer(String path) throws Exception {
		String command = "cmd /c " + path + File.separator + "shutdown.bat";
		String filePath = path.substring(0, path.indexOf("bin") - 1);
		AutoUpdateUtil.executeCmd(command, filePath);
	}

	/**
	 * sh 升级方式
	 * 
	 * @param shellPath
	 *            shell文件地址+shell文件名称
	 * @throws AutoUpdateException
	 *             AutoUpdateException
	 */
	public void shUpdate(String shellPath) throws AutoUpdateException {
		try {
			AutoUpdateUtil.executeCmd(shellPath, null);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			throw new AutoUpdateException("sh method update exception :", e);
		}
	}

	/**
	 * 代码升级方式 升级步骤： 1) 备份 2）解压 3）还原
	 * 
	 * @param path
	 *            工程的绝对路径
	 * @throws AutoUpdateException
	 *             AutoUpdateException
	 */
	public void codeUpdate(String path) throws AutoUpdateException {
		try {
			AutoUpdateUtil.backup(path);
			AutoUpdateUtil.unzipFile(path);
			AutoUpdateUtil.recovery(path);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			throw new AutoUpdateException("code method update exception :", e);
		}
	}

}

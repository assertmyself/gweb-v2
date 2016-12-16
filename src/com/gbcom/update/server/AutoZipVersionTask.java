package com.gbcom.update.server;

import java.util.List;
import org.apache.log4j.Logger;
import com.gbcom.update.common.util.AutoUpdateUtil;
import com.gbcom.update.server.service.AutoUpdateServerService;
import com.gbcom.update.server.xml.UpdateServerContextManager;
import com.hc.core.utils.SpringUtils;

/**
 * 文件压缩任务， 如果相应的路径下无相应的压缩文件，则执行压缩
 * 
 * @author doujun
 * 
 */
public class AutoZipVersionTask {
	private static final Logger LOG = Logger
			.getLogger(AutoZipVersionTask.class);
	private Thread t = null;

	/**
	 * 开始
	 */
	public void start() {
		LOG.info("zip task start !");
		String path = Thread.currentThread().getContextClassLoader()
				.getResource("/").getPath();
		Thread t = new Thread(new AutoZipVersionThread(path));
		t.start();
	}

	/**
	 * 结束
	 */
	@SuppressWarnings("deprecation")
	public void stop() {
		if (t != null) {
			t.stop();
		}
		LOG.info("zip task end!");
	}

	/**
	 * @author doujun
	 * 
	 */
	class AutoZipVersionThread implements Runnable {
		private String path;

		/**
		 * 无参构造器
		 */
		public AutoZipVersionThread() {

		}

		/**
		 * @param path
		 *            路径
		 */
		public AutoZipVersionThread(String path) {
			this.path = path;
		}

		/**
		 * 运行
		 */
		@Override
		public void run() {
			String product = "";
			try {
				AutoUpdateServerService autoUpgradeService = (AutoUpdateServerService) SpringUtils
						.getBean("AutoUpgradeService");
				if (path == null || path.equals("")) {
					stop();
					LOG.error("can't get file absolute path!");
				}
				if (path.indexOf("WEB-INF") == -1) {
					stop();
					LOG.error("file path is wrong path !" + path);
				}
				path = path.substring(1, path.indexOf("WEB-INF"));
				List<String> products = UpdateServerContextManager
						.getInstance().getProducts();
				for (int i = 0; i < products.size(); i++) {
					product = products.get(i);
					boolean result = autoUpgradeService.zipNewestVersion(path
							+ AutoUpdateUtil.getProperty("server.war.path"),
							path
									+ AutoUpdateUtil
											.getProperty("server.war.path"),
							UpdateServerContextManager.getInstance()
									.getExcludes(product),
							UpdateServerContextManager.getInstance()
									.getIncludes(product));
					if (result) {
						LOG.info("zip " + product + " success!");
					} else {
						LOG.info(product + ".zip has existed !");
					}
				}
				stop();
			} catch (Exception e) {
				LOG.info("zip " + product + " failed !");
				LOG.error(e.getMessage(), e);
				stop();
			}
		}
	}
}

package com.gbcom.system.mysql;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.gbcom.op.util.RunTimeUtil;
import com.gbcom.op.util.RunTimeUtil.CmdInfo;
import com.gbcom.system.manager.MysqlManager;
import com.hc.core.utils.SpringUtils;

/**
 * 
 * 
 * {@link jobWraper.xml} 配置数据脚本导出服务器端
 * 
 * @author Suyuejia
 * 
 */
public class SqlExportManager {
	private static final String DEFAULT_MYSQL_DIR = "C:\\Program Files\\MySQL\\MySQL Server 5.1\\bin";
	/**
	 * 记录日志
	 */
	private static final Logger LOG = Logger
			.getLogger(SqlExportManager.class);

	/**
	 * 根据属性文件的配置导出指定位置的指定数据库到指定位置
	 * 
	 * @throws IOException
	 *             IOException
	 * @param exportPath
	 *            String
	 * @return string
	 */
	public boolean exportSql(final String exportPath) throws IOException {
		// boolean isSuccess = exportFile(exportPath);
		// if (isSuccess) {
		// String ftpFilePath = CmConst.fileDir + "//" + fileName;
		// putFileToFtp(exportPath, ftpFilePath);
		// return ftpFilePath;
		// }
		return exportFile(exportPath);
	}

	private boolean exportFile(final String exportPath) throws IOException {

		// 根据操作系统分别处理
		if (System.getProperties().getProperty("os.name").equals("Linux")) {
			return exportFileForLinux(exportPath);
		} else {
			return exportFileForWindows(exportPath);
		}
	}

	/**
	 * 对于Linux系统的备份
	 * 
	 * @param exportPath
	 *            导出路径
	 * @throws IOException
	 *             Exception
	 * @return boolean
	 */
	private boolean exportFileForLinux(String exportPath) throws IOException {
		final String profilePath = Thread.currentThread()
				.getContextClassLoader().getResource(
						"/context/application.properties").getPath();
		LOG.info("PROPERTIES-DIR :" + profilePath);
		LOG.info("-- LOAD properties file!");
		final InputStream input = new FileInputStream(profilePath);
		final Properties properties = new Properties();
		properties.load(input);

		final String command = getExportCommand(properties, exportPath);
		LOG.info("mysql_command:" + command);
		Process pro = Runtime.getRuntime().exec(
				new String[] { "/bin/sh", "-c", command });
		int exitVal = 1;
		try {
			exitVal = pro.waitFor();
		} catch (InterruptedException e) {
			LOG.error(e.getMessage(), e);
		}
		// 这个输入流是获取shell输出的
		BufferedReader reader = new BufferedReader(new InputStreamReader(pro
				.getInputStream()));
		String line;
		while ((line = reader.readLine()) != null) {
			LOG.info("shell_out:" + line);
		}
		reader.close();
		pro.destroy();
		return exitVal == 0 ? true : false;// 如果是0,则表示正常中止
	}

	/**
	 * 对于Windows系统的备份
	 * 
	 * @param exportPath
	 * @return
	 * @throws IOException
	 */
	private boolean exportFileForWindows(String exportPath) throws IOException {
		String mysqlPath = DEFAULT_MYSQL_DIR;
		// mysqlPath = DbPathSqlManager.getMySqlPath();
		final String profilePath = Thread.currentThread()
				.getContextClassLoader().getResource(
						"/context/application.properties").getPath();
		mysqlPath = ((MysqlManager) SpringUtils
				.getBean("MysqlManager")).getRealMySqlPath()
				+ "bin";
		LOG.info("PROPERTIES-DIR :" + profilePath);
		LOG.info("Mysql-DIR :" + mysqlPath);
		LOG.info("-- LOAD properties file!");
		final InputStream input = new FileInputStream(profilePath);
		// final InputStream input =
		// Thread.currentThread().getContextClassLoader().getResourceAsStream(
		// "jdbc.properties");
		final Properties properties = new Properties();
		properties.load(input);

		final String root = mysqlPath.split("\\\\")[0];
		final String command = getExportCommand(properties, exportPath);
		LOG.info("mysqlPaht is : " + mysqlPath + " cmd "
				+ "C:/WINDOWS/system32/cmd.exe /c " + root + " & cd \\ & cd "
				+ mysqlPath + " & " + command);
		final CmdInfo info = RunTimeUtil.exec("C:/WINDOWS/system32/cmd.exe /c "
				+ root + " & cd \\ & cd " + mysqlPath + " & " + command);
		final int exitVal = info.getExitValue();
		LOG.info(info.getErrorStreamInfo());
		LOG.info(info.getOutputStreamInfo());
		LOG.info("exitVal: " + exitVal);
		return exitVal == 0 ? true : false;// 如果是0,则表示正常中止

	}

	/**
	 * 利用属性文件提供的配置来拼装命令语句 在拼装命令语句的时候有一点是需要注意的：一般我们在命令窗口直接使用命令来
	 * 进行导出的时候可以简单使用“>”来表示导出到什么地方，即mysqldump -uusername -ppassword databaseName
	 * > exportPath， 但在Java中这样写是不行的，它需要你用-r明确的指出导出到什么地方，如： mysqldump -uusername
	 * -ppassword databaseName -r exportPath。
	 * 
	 * @param properties
	 * @return
	 */
	private String getExportCommand(final Properties properties,
			final String path) {
		final StringBuffer command = new StringBuffer();
		final String username = properties.getProperty("user");// 用户名
		final String password = properties.getProperty("password");// 用户密码
		final String exportDBName = properties.getProperty("databasename");// 需要导出的数据库名
		// boolean isAll =
		// Boolean.valueOf(properties.getProperty("jdbc.isAll"));
		// isAll = true;
		// String host = properties.getProperty("db.host");//
		// 从哪个主机导出数据库，如果没有指定这个值，则默认取localhost
		// String port = properties.getProperty("db.port");// 使用的端口号
		// String characterset = properties.getProperty("db.characterset");

		// 注意哪些地方要空格，哪些不要空格
		// String tables = "";
		// if (!isAll) {
		// tables = " bssidmap cpedevice location plan upgpolicy";
		// }
		// /usr/local/mysql/bin/mysqldump
		// command.append("mysqldump -u").append(username).append(" -p").append(password).append(" ")//
		// 密码是用的小p，而端口是用的大P。
		// .append(exportDBName).append(tables).append(" -r ").append("\"" +
		// path + "\"");
		command.append("mysqldump  -u").append(username).append(" -p").append(
				password).append(" ")// 密码是用的小p，而端口是用的大P。
				.append(exportDBName).append(" -r ").append("\"" + path + "\"");

		return command.toString();
	}

	/**
	 * 将文件放到Ftp上
	 * 
	 * @param fileName
	 * @param ftpFilePath
	 * @throws ConfigException
	 */
	// private void putFileToFtp(String fileName, String ftpFilePath)
	// throws ConfigException {
	// IMOTree tree = null;
	// try {
	// tree = MOTService.getInstance().getIMOTree();
	// } catch (TreeNotExistException e) {
	// throw new ConfigException(e.getMessage());
	// }
	// IMO[] ftpMOs = tree.findMO(CmConst.CID_FTPSVR);
	// FTPInfo info = new FTPInfo();
	// info
	// .setIp(ftpMOs[0].getAttributeBean(CmConst.AID_FTPSVR_IP)
	// .getValue());
	// info.setPassword(ftpMOs[0]
	// .getAttributeBean(CmConst.AID_FTPSVR_PASSWORD).getValue());
	// info.setUser(ftpMOs[0].getAttributeBean(CmConst.AID_FTPSVR_USERNAME)
	// .getValue());
	// FtpUtil.upload(info, ftpFilePath, fileName, true);
	//
	// }

}

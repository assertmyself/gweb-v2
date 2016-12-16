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

public class SqlImportManager {
	private static final String DEFAULT_MYSQL_DIR = "C:\\Program Files\\MySQL\\MySQL Server 5.1\\bin";

	/**
	 * 记录日志
	 */
	private static final Logger LOG = Logger
			.getLogger(SqlImportManager.class);

	/**
	 * importSql
	 * 
	 * @param filePath
	 *            ftpPath
	 * @return boolean
	 * @throws IOException
	 *             IOException
	 */
	public boolean importSql(final String filePath) throws IOException {
		// String filePath = getFile(ftpPath);
		final String profilePath = Thread.currentThread()
				.getContextClassLoader().getResource(
						"/context/application.properties").getPath();
		LOG.info("PROPERTIES-DIR :" + profilePath);
		final InputStream input = new FileInputStream(profilePath);

		// InputStream is =
		// Thread.currentThread().getContextClassLoader().getResourceAsStream(
		// "jdbc.properties");
		final Properties properties = new Properties();
		properties.load(input);
		// 根据操作系统分别处理
		if (System.getProperties().getProperty("os.name").equals("Linux")) {
			return importSqlForLinux(properties, filePath);
		} else {
			return importSqlForWindows(properties, filePath);
		}

	}

	// /**
	// * 从Ftp上获取文件
	//
	// *
	// * @param filePath
	// * @throws ConfigException
	// * @throws Exception
	// */
	// private String getFile(String filePath) throws ConfigException{
	// IMOTree tree = null;
	// try {
	// tree = MOTService.getInstance().getIMOTree();
	// } catch (TreeNotExistException e) {
	// throw new ConfigException(e.getMessage());
	// }
	// Date date = new Date();
	// String fileName = FILENAME + date.getTime()+".sql";
	// String exportPath = SystemUtils.USER_HOME + File.separator
	// + fileName;// 导出路径
	// IMO[] ftpMOs = tree.findMO(CmConst.CID_FTPSVR);
	// FTPInfo info = new FTPInfo();
	// info
	// .setIp(ftpMOs[0].getAttributeBean(CmConst.AID_FTPSVR_IP)
	// .getValue());
	// info.setPassword(ftpMOs[0]
	// .getAttributeBean(CmConst.AID_FTPSVR_PASSWORD).getValue());
	// info.setUser(ftpMOs[0].getAttributeBean(CmConst.AID_FTPSVR_USERNAME)
	// .getValue());
	// FtpUtil.download(info, filePath, exportPath, true);
	// return exportPath;
	// }

	/**
	 * 根据属性文件的配置把指定位置的指定文件内容导入到指定的数据库中 在命令窗口进行mysql的数据库导入一般分三步走： 第一步是登到到mysql；
	 * 
	 * mysql -uusername -ppassword -hhost -Pport
	 * -DdatabaseName;如果在登录的时候指定了数据库名则会 直接转向该数据库，这样就可以跳过第二步，直接第三步；
	 * 第二步是切换到导入的目标数据库；use importDatabaseName； 第三步是开始从目标文件导入数据到目标数据库；source
	 * importPath；
	 * 
	 * @param properties
	 *            Properties
	 * @param importPath
	 *            importPath
	 * @throws IOException
	 *             IOException
	 * @return boolean
	 */
	public boolean importSqlForWindows(final Properties properties,
			final String importPath) throws IOException {
		String mysqlPath = DEFAULT_MYSQL_DIR;
		// mysqlPath = DbPathSqlManager.getMySqlPath();
		mysqlPath = ((MysqlManager) SpringUtils
				.getBean("MysqlManager")).getRealMySqlPath()
				+ "bin";
		LOG.info("Mysql-DIR :" + mysqlPath);
		// 因为在命令窗口进行mysql数据库的导入一般分三步走，所以所执行的命令将以字符串数组的形式出现
		final String[] cmdarray = getImportCommand(properties, importPath);// 根据属性文件的配置获取数据库导入所需的命令，组成一个数组
		// runtime.exec(cmdarray);//这里也是简单的直接抛出异常
		CmdInfo info;
		// String mysqlPath = userDir.substring(0,
		// userDir.length()-3)+"MYSQL5.0\\bin";
		final String root = mysqlPath.split("\\\\")[0];
		LOG.info("mysqlPaht is : " + mysqlPath);
		final String mainCmd = "C:/WINDOWS/system32/cmd.exe /c " + root
				+ " & cd \\ & cd " + mysqlPath + " & " + cmdarray[0];
		
		final String mainCmdInstead = "C:/WINDOWS/system32/cmd.exe /c " + root
				+ " & cd \\ & cd " + mysqlPath + " & " + cmdarray[0] + " " +  properties.getProperty("databasename") + "< " + importPath;
		final String cmdInstead = properties.getProperty("databasename") + "< " + importPath;
		info = RunTimeUtil.exec(mainCmdInstead);
		//info = RunTimeUtil.exec(mainCmd, cmdarray[1] + "\r\n" + cmdarray[2]);
		final int result = info.getExitValue();
		return result == 0;
	}

	/**
	 * 会对Linux系统的数据库还原
	 * 
	 * @param properties
	 *            连接配置
	 * @param importPath
	 *            路径
	 * @return 是否成功
	 * @throws IOException
	 *             IO异常
	 */
	public boolean importSqlForLinux(final Properties properties,
			final String importPath) throws IOException {

		String command = "mysql -u " + properties.getProperty("user") + " -p"
				+ properties.getProperty("password") + " ccsv3 < " + importPath;
		LOG.info("command======" + command);
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
	 * 根据属性文件的配置，分三步走获取从目标文件导入数据到目标数据库所需的命令 如果在登录的时候指定了数据库名则会
	 * 直接转向该数据库，这样就可以跳过第二步，直接第三步；
	 * 
	 * @param properties
	 * @return
	 */
	private String[] getImportCommand(final Properties properties,
			final String importPath) {
		final String username = properties.getProperty("user");// 用户名

		final String password = properties.getProperty("password");// 密码
		// String host = properties.getProperty("db.host");// 导入的目标数据库所在的主机
		// String port = properties.getProperty("db.port");// 使用的端口号
		final String importDBName = properties.getProperty("databasename");// 导入的目标数据库的名称

		// 第一步，获取登录命令语句
		final String loginCommand = new StringBuffer().append("mysql -u")
				.append(username).append(" -p").append(password)/*
																														 */.toString();
		// 第二步，获取切换数据库到目标数据库的命令语句
		final String switchCommand = new StringBuffer("use ").append(
				importDBName).toString();
		// 第三步，获取导入的命令语句

		final String importCommand = new StringBuffer("source ").append(
				importPath).toString();
		// 需要返回的命令语句数组
		final String[] commands = new String[] { loginCommand, switchCommand,
				importCommand };
		return commands;
	}

}

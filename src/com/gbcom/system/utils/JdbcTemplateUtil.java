package com.gbcom.system.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.log4j.Logger;

public class JdbcTemplateUtil {
	private static final Logger log = Logger.getLogger(JdbcTemplateUtil.class);

	/**
	 * 获取数据库连接
	 * @return Connection Connection
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws SQLException SQLException
	 */
	public static Connection getDBConnection() throws ClassNotFoundException, SQLException{
		Connection con = null;
		Properties properties = new Properties();
		InputStream in = null;
		try {
			//取消空格
			String classPath = JdbcTemplateUtil.class.getResource("").getPath().replaceAll("%20", " ");  
            String path = classPath.substring(0, classPath.indexOf("WEB-INF")) + "WEB-INF"+ File.separator+"classes" +File.separator+ "context" + File.separator +"application.properties";  
			in = new FileInputStream(path);
			properties.load(in);
			final String username = properties.getProperty("user");// 用户名
			final String password = properties.getProperty("password");// 用户密码
			final String url = properties.getProperty("jdbcUrl");// 数据库连接
			final String driverClass = properties.getProperty("driverClass");// 数据库连接驱动
			Class.forName(driverClass);
			con= DriverManager.getConnection(url,username,password);
		} catch (IOException e) {
			log.error(e.getMessage());
		}finally{
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
					log.error(e.getMessage(),e);
				}
			}
		}
		
		return con;
	}
	
	/**
	 * 通用增删改方法
	 * 
	 * @param params 参数s
	 * @param sql sql语句 
	 * @return boolean 结果
	 */
	public static boolean executeUpdate(String[] params,String sql ){
		boolean bool = false;
		Connection conn = null;
		PreparedStatement ps = null;
		int result = 0;
		try {
			conn = getDBConnection();
			ps = conn.prepareStatement(sql);
			if (params != null && params.length > 0) {
				for (int i = 0; i < params.length; i++) {
					ps.setString(i + 1, params[i]);
				}
			}
			result = ps.executeUpdate();
			if(result > 0){
				bool = true;
			}
		} catch (ClassNotFoundException e) {
			log.error("Class not founded, jdbc", e);
		} catch (SQLException e) {
			log.error("SQLeXCEPTION !", e);
		}finally{
			JdbcTemplateUtil.freeDBResource(conn, ps, null);
		}		
		return bool;
	}
	
	
	
	/**
	 * 关闭数据库资源. 对不存在的资源ex.<code>rs</code>，直接传递null。
	 * 建议在 <code>finally</code>中使用
	 * @param conn Connection
	 * @param stmt Statement
	 * @param rs ResultSet
	 */
	public static void freeDBResource(Connection conn,Statement stmt,ResultSet rs) {
		if (rs != null) { // 关闭记录集
			try {
				rs.close();
			} catch (SQLException e) {
				log.error("freeDBResource -- CLOSE ResultSet FAILED", e);
			}
		}
		if (stmt != null) { // 关闭声明
			try {
				stmt.close();
			} catch (SQLException e) {
				log.error("freeDBResource -- CLOSE Statement FAILED", e);
			}
		}
		if (conn != null) { // 关闭连接对象
			try {
				conn.close();
			} catch (SQLException e) {
				log.error("freeDBResource -- CLOSE Connection FAILED", e);
			}
		}
	}
	
}

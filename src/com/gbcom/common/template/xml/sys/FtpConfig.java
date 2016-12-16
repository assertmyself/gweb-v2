//off checkstyle
package com.gbcom.common.template.xml.sys;

import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * FTP相关配置
 * 
 *  <Ftp ftpDir="S" ftpIp="127.0.0.1" ftpPort="21" ftpWIp="127.0.0.1" ftpWport="21" userName="gbcom" pwd="gbcom"/>
 *  
 * @author SunYanzheng
 * @date 下午2:21:51
 * @version v1.0.0
 * @see FtpConfig
 */
public class FtpConfig {
	@XStreamAsAttribute
    private String ftpDir;
	
	@XStreamAsAttribute
    private String ftpIp;
	@XStreamAsAttribute
	private String ftpPort;
    
	
	@XStreamAsAttribute
    private String ftpWIp;
	@XStreamAsAttribute
	private String ftpWport;
    
	
	
	@XStreamAsAttribute
    private String userName;
    
	@XStreamAsAttribute
    private String pwd;

	public String getFtpDir() {
		return ftpDir;
	}

	public void setFtpDir(String ftpDir) {
		this.ftpDir = ftpDir;
	}

	public String getFtpIp() {
		return ftpIp;
	}

	public void setFtpIp(String ftpIp) {
		this.ftpIp = ftpIp;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
   
	@Override
	public String toString(){
		return "ftpIp=" + this.ftpIp + "  ftpDir="+this.ftpDir +"   userName="+this.userName +"   pwd="+this.pwd;
	}

	public String getFtpPort() {
		return ftpPort;
	}

	public void setFtpPort(String ftpPort) {
		this.ftpPort = ftpPort;
	}

	public String getFtpWIp() {
		return ftpWIp;
	}

	public void setFtpWIp(String ftpWIp) {
		this.ftpWIp = ftpWIp;
	}

	public String getFtpWport() {
		return ftpWport;
	}

	public void setFtpWport(String ftpWport) {
		this.ftpWport = ftpWport;
	}

}

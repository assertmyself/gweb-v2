package com.gbcom.common.template.xml.mail;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * SMTP用户鉴权
 * @author SunYanzheng
 * @date 2014-6-25
 * @version v1.0.0
 * @see com.gbcom.omc.ccsv2.server.mail.SmtpAuthentic
 */
public class SmtpAuthentic extends Authenticator {

	/** Creates a new instance of Authentic */
	public SmtpAuthentic() {
		super();
	}

	/**
	 * 
	 * TODO description here
	 * 
	 * @param name
	 *            name
	 * @param password
	 *            password
	 */
	public SmtpAuthentic(final String name, final String password) {
		super();
		this.username = name;
		this.pwd = password;
	}

	// username为发送邮箱@前面的部分

	private String username = "";

	// pwd为发送邮箱的密码
	private String pwd = "";

	/** 
	 * 获取认证密码
	 * @return PasswordAuthentication
	 */
	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(getUsername(), getPwd());
	}

	/**
	 * 
	 * TODO description here
	 * 
	 * @return username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * 
	 * TODO description here
	 * 
	 * @param username
	 *            username
	 */
	public void setUsername(final String username) {
		this.username = username;
	}

	/**
	 * 
	 * TODO description here
	 * 
	 * @return pwd
	 */
	public String getPwd() {
		return pwd;
	}

	/**
	 * 
	 * TODO description here
	 * 
	 * @param pwd
	 *            pwd
	 */
	public void setPwd(final String pwd) {
		this.pwd = pwd;
	}

}

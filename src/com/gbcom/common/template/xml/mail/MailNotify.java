package com.gbcom.common.template.xml.mail;

import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


/**
 * 封装邮件服务--静态方法，需要额外提供邮件的地址
 * 参考{@code MailSender }
 * @author SunYanzheng
 * @date 下午3:18:52
 * @version v1.0.0
 * @see MailSender
 */
public class MailNotify {

	/**
	 * 发送邮件方法
	 * 
	 * 
	 * @param sendAddress
	 *            : 发件人地址
	 * @param name
	 *            : 发件人姓名
	 * @param password
	 *            : 发件人密码
	 * @param emailServer
	 *            : 发送的邮箱服务器地址 例smtp.163.com
	 * @param revAddresses
	 *            : 收件箱地址
	 * @param title
	 *            : 发送邮件的主题
	 * @param content
	 *            ： 发送邮件的内容 可以是超文本标记语言
	 * @throws MessagingException
	 *             MessagingException
	 * @throws AddressException
	 *             AddressException
	 * 
	 */
	public static void sendSmtpEmail(String sendAddress, String name,
			String password, String emailServer, String[] revAddresses,
			String title, String content) throws AddressException,
			MessagingException {
		if (revAddresses == null || revAddresses.length == 0) {
			return;
		}

		// 1.0 设置邮件协议属性
		Properties props = new Properties();
		props.put("mail.host", emailServer);//// 服务器地址  
		props.put("mail.smtp.port", "" + 25); // 端口号  
		props.put("mail.transport.protocol","smtp");// 暂时使用SMTP协议,可去掉
		props.put("mail.smtp.auth", "true");

		// 2.0 产生新的邮件Session 服务
		// Session mailSession = Session.getDefaultInstance(props, auth);//不带鉴权

		// 需鉴权,用户名及密码：：用户名为@前的部分,如"pujing@gbcom.com.cn",用户名为:pujing
		SmtpAuthentic auth = new SmtpAuthentic(name, password);
		Session mailSession = Session.getInstance(props, auth);//带简单鉴权
		mailSession.setDebug(true);////邮件打印
		
		// 3.0 封装邮件Msg
		Message msg = new MimeMessage(mailSession);
		msg.setFrom(new InternetAddress(sendAddress));// 设定传送邮件的发信人
		// 设定传送邮件至收信人的信箱列表
		InternetAddress address = null;
		for (String revAddress : revAddresses) {
			address = new InternetAddress(revAddress);
			msg.addRecipient(Message.RecipientType.TO, address);
		}

		
		msg.setSubject(title);// 设定信中的主题
		msg.setSentDate(new Date());// 设定送信的时间

		// 可以发送超文本的邮件 html格式 第一个参数可以指定为任意字符串
		// 设定传送信的MIME Type
		msg.setDataHandler(new javax.activation.DataHandler("alarm",
				"text/html"));

		// content为发送的内容
		msg.setContent(content, "text/html;charset=utf-8");
		msg.saveChanges();
		Transport.send(msg);
	}

	/**
	 * 发送邮件方法
	 * 
	 * @param sendAddress
	 *            : 发件人地址
	 * @param name
	 *            : 发件人姓名
	 * 
	 * 
	 * @param password
	 *            : 发件人密码
	 * 
	 * 
	 * @param emailServer
	 *            : 发送的邮箱服务器地址 例smtp.163.com
	 * @param revAddresses
	 *            : 收件箱地址
	 * @param title
	 *            : 发送邮件的主题
	 * @param content
	 *            ： 发送邮件的内容 可以是超文本标记语言
	 * @param file
	 *            ： 发送邮件的附件
	 * @throws MessagingException
	 *             MessagingException
	 * @throws AddressException
	 *             AddressException
	 * 
	 */
	public static void sendSmtpEmail(String sendAddress, String name,
			String password, String emailServer, String[] revAddresses,
			String title, String content, Vector<String> file)
			throws AddressException, MessagingException {
		if (revAddresses == null || revAddresses.length == 0) {
			return;
		}
		// 设定所要用的Mail 服务器和所使用的传输协议

		Properties props = new Properties();
		props.put("mail.host", emailServer);
		props.put("mail.smtp.auth", "true");
		SmtpAuthentic auth = new SmtpAuthentic(name, password);
		Session mailSession = Session.getInstance(props, auth);
		boolean sessionDebug = false;
		mailSession.setDebug(sessionDebug);
		Message msg = new MimeMessage(mailSession);
		msg.setFrom(new InternetAddress(sendAddress));
		InternetAddress address = null;
		for (String revAddress : revAddresses) {
			address = new InternetAddress(revAddress);
			msg.addRecipient(Message.RecipientType.TO, address);
		}
		msg.setSubject(title);
		msg.setSentDate(new Date());
		// msg.setDataHandler(new
		// javax.activation.DataHandler("alarm","text/html"));
		// multipart
		Multipart mp = new MimeMultipart();
		MimeBodyPart mbpContent = new MimeBodyPart();
		mbpContent.setText(content);
		mp.addBodyPart(mbpContent);

		/* 往邮件中添加附件 */
		Enumeration<String> efile = file.elements();
		String fileName;
		while (efile.hasMoreElements()) {
			MimeBodyPart mbpFile = new MimeBodyPart();
			fileName = efile.nextElement().toString();
			FileDataSource fds = new FileDataSource(fileName);
			mbpFile.setDataHandler(new DataHandler(fds));
			mbpFile.setFileName((fds.getName()));
			mp.addBodyPart(mbpFile);
		}
		msg.setContent(mp);

		msg.saveChanges();
		Transport.send(msg);
	}

	/**
	 * test-mail
	 * @param args no
	 */ 
	public static void main(String[] args) {

		try {
			MailNotify.sendSmtpEmail("gbcomcms@163.com", "gbcomcms",
					"Welcome123", "smtp.163.com",
					new String[] { "sunyanzheng@gbcom.com.cn" }, "test",
					"content+who are you");
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}
}

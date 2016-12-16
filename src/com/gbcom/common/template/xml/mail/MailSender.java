package com.gbcom.common.template.xml.mail;

import java.net.URL;
import java.util.Date;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;

import com.gbcom.op.util.xml.XStreamUtil;

/**
 * 默认邮件发送器，邮件地址等信息默认使用模板配置， 另额外提供静态方法，不受模板文件束缚；
 * 
 * @see MailNotify
 * @author SunYanzheng
 * @date 下午5:29:55
 * @version v1.0.0
 */
public final class MailSender {
	private static final Logger LOGGER = Logger.getLogger(MailSender.class);
	private MailContext context;// 模板文件
	private static final MailSender INSTANCE = new MailSender();

	private MailSender() {
		final Class<?>[] classContext = { MailContext.class, MailReceiver.class };
		final URL url = Thread.currentThread().getContextClassLoader()
				.getResource("config/mail/mail.xml");
		context = XStreamUtil.fromXML(MailContext.class, url.getFile(),
				classContext);
		LOGGER.info("MailSender init success , ----- from file ： config/mail/mail.xml");
	}

	/**
	 * 获取单例
	 * 
	 * @return MailSender
	 */
	public static MailSender getInstance() {
		return INSTANCE;
	}

	/**
	 * Mail 配置 Context
	 * 
	 * @return MailContext
	 */
	public MailContext getMailContext() {
		return context;
	}

	/**
	 * 发送邮件方法 需要指定主题和内容，当操作失败返回异常信息
	 * 
	 * @param subject
	 *            主题
	 * @param content
	 *            内容
	 * @throws MessagingException
	 *             异常
	 */
	public void sendMail(final String subject, final String content)
			throws MessagingException {
		final Message msg = buildDefaultMsg();
		msg.setSubject(subject);// 设定信中的主题
		msg.setSentDate(new Date());// 设定送信的时间
		msg.setContent(content, "text/html;charset=utf-8");
		msg.saveChanges();
		// 4.0发送
		Transport.send(msg);
		LOGGER.info("Send mail success  ---  subject=" + subject
				+ " : content=" + content);

	}

	/**
	 * 发送带附件的邮件信息
	 * 
	 * @param subject
	 *            主题
	 * @param content
	 *            内容
	 * @param files
	 *            附件
	 * @throws MessagingException
	 *             异常
	 */
	public void sendMail(final String subject, final String content, final Set<String> files)
			throws MessagingException {
		final Message msg = buildDefaultMsg();
		msg.setSubject(subject);// 设定信中的主题
		msg.setSentDate(new Date());// 设定送信的时间

		final Multipart mlp = new MimeMultipart();
		final MimeBodyPart mbpContent = new MimeBodyPart();
		mbpContent.setText(content);
		mlp.addBodyPart(mbpContent);
		/* 往邮件中添加附件 */
		MimeBodyPart mbpFile;
		for (String fileName : files) {
			mbpFile = new MimeBodyPart();
			final FileDataSource fds = new FileDataSource(fileName);
			mbpFile.setDataHandler(new DataHandler(fds));
			mbpFile.setFileName((fds.getName()));
			mlp.addBodyPart(mbpFile);
		}
		msg.setContent(mlp);
		msg.saveChanges();
		// 4.0发送
		Transport.send(msg);
		LOGGER.info("Send mail success  ---  subject=" + subject
				+ " : content=" + content + " :: file=" + files);

	}

	private Message buildDefaultMsg() throws MessagingException {
		// 1.0 封装参数,从模板获取
		final Properties props = new Properties();
		props.put("mail.host", context.getHost());// // 服务器地址
		props.put("mail.smtp.port", context.getPort()); // 端口号
		props.put("mail.transport.protocol", context.getProtocol());// 暂时使用SMTP协议,可去掉
		props.put("mail.smtp.auth", context.getAuth());

		// 2.0 产生新的邮件Session 服务
		final SmtpAuthentic auth = new SmtpAuthentic(context.getSenderName(),
				context.getSenderPwd());
		final Session mailSession = Session.getInstance(props, auth);// 带简单鉴权
		mailSession.setDebug(true);// //邮件打印

		// 3.0 封装邮件Msg
		final Message msg = new MimeMessage(mailSession);
		msg.setFrom(new InternetAddress(context.getSenderAddr()));// 设定传送邮件的发信人
		for (MailReceiver rev : context.getReceivers()) {
			if (rev.getReceiverType().equalsIgnoreCase("BCC")) {
				msg.addRecipient(Message.RecipientType.BCC,
						new InternetAddress(rev.getReceiverAddr()));
			} else if (rev.getReceiverType().equalsIgnoreCase("CC")) {
				msg.addRecipient(Message.RecipientType.CC, new InternetAddress(
						rev.getReceiverAddr()));
			} else {
				msg.addRecipient(Message.RecipientType.TO, new InternetAddress(
						rev.getReceiverAddr()));
			}
		}
		msg.setDataHandler(new javax.activation.DataHandler("alarm",
				"text/html"));
		return msg;
	}

	/**
	 * test-
	 * 
	 * @param args
	 *            参数
	 */
	public static void main(final String[] args) {
		Set<String> files = new HashSet<String>();
		try {
			for (int i = 0; i < 10; i++) {
				files.add("D:\\example.xml");
				MailSender.getInstance().sendMail("hellow", "chinese room", files);
			}
		} catch (MessagingException e) {
			LOGGER.error(e.getMessage());
		}
	}
	/**
	 * 上线文
	 * @return MailContext
	 */
	public MailContext getContext() {
		return context;
	}
	/**
	 * Set 上下文
	 * @param context MailContext
	 */
	public void setContext(final MailContext context) {
		this.context = context;
	}

}

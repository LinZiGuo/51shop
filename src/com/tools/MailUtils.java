package com.tools;

import java.util.Properties;

public class MailUtils {

	public static void sendMail(String email, String emailMsg) {
			//throws AddressException,MessagingException
		//1.创建一个程序与邮件服务器会话对象Session
		Properties properties = new Properties();
		//1.1.设置发送协议
		properties.setProperty("mail.transport.protocol", "SMTP");
		//1.2.设置发送邮件的服务器
		properties.setProperty("mail.host", "localhost");
		properties.setProperty("mail.smtp.auth", "true");  //指定验证为true
		//1.3.创建验证器
//		Authenticator auth = new Authenticator({
//			//1.3.1 设置发送人的账号和密码
//			return new PasswordAuthentication("service","123");
//		});
//		
//		Session session = Session.getInstance(properties, auth);
//		
//		//2.创建一个Message，它相当于是邮件内容
//		Message message = new MimeMessageParser(session);
//		//2.1设置发送者
//		message.setFrom(new InternetAddress("service@shop.com"));
//		//2.2设置发送方式与接受者
//		message.setRecipient(RecipientType.TO,new InternetAddress(email));
//		//2.3设置邮件主题
//		message.setSubject("用户激活");
//		//2.4设置邮件内容
//		message.setContent(emailMsg, "text/html;charset=utf-8");
//		
//		//3.创建Transport用于邮件发送
//		Transport.send(message);
	}
}

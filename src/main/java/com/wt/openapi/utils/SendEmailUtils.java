package com.wt.openapi.utils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.wt.openapi.common.configuration.ConfigProperties;


public class SendEmailUtils {
	
	public void SendEmailAuth(String emailAuthKey, String toAddress, String memNo) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		
		String username = ConfigProperties.getProperty("email.from.address");
		 
		// 메일 내용
		String subject = ConfigProperties.getProperty("email.auth.subject");		
		String authUrl = ConfigProperties.getProperty("email.auth.host")+emailAuthKey+"&memNo="+memNo;
		String mailPath = "joinAuth.html";
		
		String template = getHtmlTemplate(mailPath);
		
		map.put("authUrl", authUrl);
		map.put("userId", toAddress);
		map.put("tempPassword", "");
		
		String content = getContent(template, map);
		
		//properties 설정
		Properties props = new Properties();
		props.put("mail.smtp.host", "localhost");
		props.put("mail.smtp.port", "25");
		props.put("mail.smtps.auth", "true");
		// 메일 세션
        Session session = Session.getDefaultInstance(props);
        MimeMessage msg = new MimeMessage(session);
 
        // 메일 관련
        msg.setSubject(subject);
        msg.setContent(content, "text/html;charset=utf-8");
        msg.setFrom(new InternetAddress(username));
        msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toAddress));
		 
		// 발송
        Transport.send(msg);

	}
	
	public void SendModifyPwd(String toAddress, String tempPassword) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		
		String username = ConfigProperties.getProperty("email.from.address");
		 
		// 메일 내용
		String subject = ConfigProperties.getProperty("modify.password.subject");
		String mailPath = "modifyPwd.html";
		
		String template = getHtmlTemplate(mailPath);
		
		map.put("authUrl", "");
		map.put("tempPassword", tempPassword);
		map.put("userId", toAddress);
		String content = getContent(template, map);
		
		//properties 설정
		Properties props = new Properties();
		props.put("mail.smtp.host", "localhost");
		props.put("mail.smtp.port", "25");
		props.put("mail.smtps.auth", "true");
		// 메일 세션
        Session session = Session.getDefaultInstance(props);
        MimeMessage msg = new MimeMessage(session);
        
        // 메일 관련
        msg.setSubject(subject);
        msg.setContent(content, "text/html;charset=utf-8");
        msg.setFrom(new InternetAddress(username));
        msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toAddress));
		 
		// 발송
        Transport.send(msg);
	}
	
	private String getContent(String template, HashMap<String, String> map) {
		
		template = template.replace("${authUrl}", StringUtils.nvlStr(map.get("authUrl")));
		template = template.replace("${userId}", StringUtils.nvlStr(map.get("userId")));
		template = template.replace("${tempPassword}", StringUtils.nvlStr(map.get("tempPassword")));
		
		return template;
	}
	
	public String getHtmlTemplate(String mailPath) throws IOException {
		StringBuilder html = new StringBuilder();
		
		InputStreamReader isr = null;
		
		try {
			isr = new InputStreamReader(this.getClass().getResourceAsStream("/property/" + mailPath));
			char[] buffer = new char[8192];
			int nReadByteLen = -1;  
			while ((nReadByteLen = isr.read(buffer)) != -1) {
				html.append(buffer, 0, nReadByteLen);  
			}
		} finally {
			if (isr != null) {
				try {
					isr.close();
				} catch (IOException ioe) {}
			}
		}
		
		return html.toString();
	}
	
}

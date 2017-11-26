package com.rivanmota.utils;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class Email {

	private String from;
	private String to;
	private String message;
	private String subject;
	
//	private ConfiguracaoDAO configuracaoDAO;
	
//	public Email(HttpServletRequest request) {
////		this.configuracaoDAO = new ConfiguracaoDAOImplThreadSafe(connection);
//		this.configuracaoDAO = new ConfiguracaoDAOImpl(request);
//	}
	
	private static final String EMAIL_HOST = "email-smtp.us-east-1.amazonaws.com";
	private static final String EMAIL_PORTA = "587";
	private static final String EMAIL_USER = "AKIAIP37CJEZYQI6ZN3A";
	private static final String EMAIL_SENHA = "Alp5zyE3z4OlJpLKODPRp+odg7r/oke8yIXMNKKrrBJ4";
	private static final String EMAIL_FROM = "contato@gameconecta.com.br";
	
	public void setFrom(String from) {
		this.from = from;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void enviar() {
		Properties mailProps = System.getProperties(); 

		mailProps.put("mail.smtp.host", EMAIL_HOST);
		mailProps.put("mail.smtp.auth", "true");	
		mailProps.put("mail.debug", "true");  
		mailProps.put("mail.smtp.debug", "true");  
		mailProps.put("mail.smtp.port", EMAIL_PORTA);
		mailProps.put("mail.transport.protocol", "smtp");
		mailProps.put("mail.smtp.user" , EMAIL_USER);
		mailProps.put("mail.smtp.password" , EMAIL_SENHA);
		
		Session session = Session.getDefaultInstance(mailProps); 
		session.setDebug(true);
		
		MimeMessage msg = new MimeMessage(session);
		try {
			msg.setFrom(new InternetAddress(EMAIL_FROM));
			msg.setFrom(new InternetAddress(EMAIL_FROM, "Game Conecta"));
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
			
			msg.setSentDate(new Date());
			
			msg.setSubject(subject, "UTF-8");

			msg.setContent(message, "text/html;charset=utf-8");
			
			Transport transport = session.getTransport();
			transport.connect(EMAIL_HOST, EMAIL_USER, EMAIL_SENHA);
			transport.sendMessage(msg, msg.getAllRecipients()); 
			transport.close(); 
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	public void enviarUsandoFrom() {
		Properties mailProps = System.getProperties(); 

		mailProps.put("mail.smtp.host", EMAIL_HOST);
		mailProps.put("mail.smtp.auth", "true");	
		mailProps.put("mail.debug", "true");  
		mailProps.put("mail.smtp.debug", "true");  
		mailProps.put("mail.smtp.port", EMAIL_PORTA);
		mailProps.put("mail.transport.protocol", "smtp");
		mailProps.put("mail.smtp.user" , EMAIL_USER);
		mailProps.put("mail.smtp.password" , EMAIL_SENHA);
		
		Session session = Session.getDefaultInstance(mailProps); 
		session.setDebug(true);
	
		MimeMessage msg = new MimeMessage(session);
		try {
			msg.setFrom(new InternetAddress(from, "Game Conecta"));
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));

			msg.setSentDate(new Date());
			
			msg.setSubject(subject, "UTF-8");

			msg.setContent(message, "text/html;charset=utf-8");
			
			Transport transport = session.getTransport();
			transport.connect(EMAIL_HOST, EMAIL_USER, EMAIL_SENHA);
			transport.sendMessage(msg, msg.getAllRecipients()); 
			transport.close(); 
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	public void enviarEmail (String message, String emailTo, String subject) {			
		this.setMessage(message);	
		this.setTo(emailTo);
		this.setSubject(subject);
		this.enviar();	
	}
	
}

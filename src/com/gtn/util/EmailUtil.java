package com.gtn.util;

import java.util.Properties;

import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailParseException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(EmailUtil.class);
	private JavaMailSender mailSender;
	private Environment enviornment;
	    
    @Autowired
	public EmailUtil(Environment env){
    	enviornment = env;
		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
		
		javaMailSender.setHost(env.getProperty("mail.host"));
		javaMailSender.setPort(Integer.parseInt(env.getProperty("mail.port")));
		javaMailSender.setUsername(env.getProperty("mail.username"));
		javaMailSender.setPassword(env.getProperty("mail.password"));
		
		javaMailSender.setJavaMailProperties(getMailProperties());
		
		mailSender = javaMailSender;
	}
	
	public void setMailSender() {
		
	}

	public void sendMail(String from, String to, String subject, String msg) {
		
		SimpleMailMessage message = new SimpleMailMessage();

		message.setFrom(from);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(msg);
		mailSender.send(message);
	}
	
	public void sendMailHtml(String from, String to, String subject, String msg) {

		logger.info("******** Starting forgot password email ********");
		
		//SimpleMailMessage message = new SimpleMailMessage();
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = null;
		
		try{
			helper = new MimeMessageHelper(message, true);
			helper.setFrom(from);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText("", msg);
			mailSender.send(message);
			
			logger.info("******** End forgot password email. ********");
			
		}catch(MessagingException e){
			throw new MailParseException(e);
		}

	}
	
	public void sendMailHtmlWithAttachement(String from, String to, String subject, String msg, String attachmentFilename, DataSource dataSource) {

		logger.info("******** Starting forgot password email ********");
		
		//SimpleMailMessage message = new SimpleMailMessage();
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = null;
		
		try{
			helper = new MimeMessageHelper(message, true);
			helper.setFrom(from);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText("", msg);
			helper.addAttachment(attachmentFilename, dataSource);
			mailSender.send(message);
			
			logger.info("******** End forgot password email. ********");
			
		}catch(MessagingException e){
			throw new MailParseException(e);
		}

	}
	
	private Properties getMailProperties() {
        Properties properties = new Properties();
        properties.setProperty("mail.transport.protocol", enviornment.getProperty("mail.transport.protocol"));
        properties.setProperty("mail.smtp.auth", enviornment.getProperty("mail.smtp.auth"));
        properties.setProperty("mail.smtp.starttls.enable", enviornment.getProperty("mail.smtp.starttls.enable"));
        properties.setProperty("mail.debug", enviornment.getProperty("mail.debug"));
        return properties;
    }
}

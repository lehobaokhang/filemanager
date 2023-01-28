package com.miroslav.filemanager.service;

import java.nio.charset.StandardCharsets;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class EmailSenderService {
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private TemplateEngine templateEngine;
	
	@Value("${spring.mail.username}")
	private String from;
	
	public void sendVerifyMail(String to, String subject, String token){
		MimeMessage message = mailSender.createMimeMessage();		
		Context context = new Context();
		context.setVariable("token", token);
		
		String html = templateEngine.process("mail/verify-mail", context);
		
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
			
			helper.setFrom(from);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(html, true);
			
			mailSender.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}		
	}
}
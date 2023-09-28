package com.example.send_email.service;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	private JavaMailSender mailSender;

	public EmailService(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void send(String from, List<String> to, String subject, String text) {
		
		SimpleMailMessage message = new SimpleMailMessage();
		String[] recievers = new String[to.size()];
		 
        for (int i = 0; i < to.size(); i++) {
        	recievers[i] = to.get(i);
        }
		message.setFrom(from);
		message.setTo(recievers);
		message.setSubject(subject);
		message.setText(text);
		mailSender.send(message);
	}

	public void sendWithAttach(String from, String to, String subject, String text, String attachName,
			InputStreamSource inputStream) throws MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setFrom(from);
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(text, true);
		helper.addAttachment(attachName, inputStream);
		mailSender.send(message);
	}

}

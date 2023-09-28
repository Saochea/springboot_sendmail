package com.example.send_email;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

@SpringBootTest
class SendEmailApplicationTests {
	
	private JavaMailSender mailSender;

    public SendEmailApplicationTests(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void send(String from, String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }

    public void sendWithAttach(String from, String to, String subject,
                               String text, String attachName,
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


	@Test
	void contextLoads() {
		this.send("mrr.kh0001@gmail.com", "saocheaphan@gmail.com", "test", "Hello");
	}

}

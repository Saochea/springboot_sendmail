package com.example.send_email;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.send_email.service.EmailService;

@SpringBootApplication
@RestController
public class SendEmailApplication {
	
	@Autowired
	EmailService emailService;

	public static void main(String[] args) {
		SpringApplication.run(SendEmailApplication.class, args);

	}
	
	@GetMapping("/send")
	public String test() {
		List <String> list = new ArrayList <String> ();  
		list.add("saocheaphan@gmail.com");
		list.add("rysarakmony@gmail.com");
		
		emailService.send("mrr.kh0001@gmail.com",list,"test","Hello");
		
		return "Ok";
		
	}

}

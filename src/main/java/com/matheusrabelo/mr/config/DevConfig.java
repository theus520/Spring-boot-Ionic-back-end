package com.matheusrabelo.mr.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.matheusrabelo.mr.services.DBService;
import com.matheusrabelo.mr.services.EmailService;
import com.matheusrabelo.mr.services.SmtpEmailService;

@Configuration
@Profile("dev")
public class DevConfig {
// todos os bin só será ativado quando os profiles forem criado

	@Autowired
	private DBService dbService;

	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;

	@Bean
	public boolean instantiateDatabase() throws ParseException {
		if (!"create".equals(strategy)) {
			return false;
		}

		dbService.instantiateTestDatabase();
		return true;

	}
	
	@Bean 
	public EmailService emailService() {
	return new SmtpEmailService();
		
	}
	}

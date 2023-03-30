package com.examinationPortal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

@SpringBootApplication
public class ExaminationPortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExaminationPortalApplication.class, args);
	}

}

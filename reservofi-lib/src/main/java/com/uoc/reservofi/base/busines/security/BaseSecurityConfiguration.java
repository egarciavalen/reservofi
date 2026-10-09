package com.uoc.reservofi.base.busines.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BaseSecurityConfiguration {

	@Bean
	public PasswordEncoder encoder() {
		// BCryps generará una salt aleatoria
		return new BCryptPasswordEncoder();
	}
}

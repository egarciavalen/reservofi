package com.uoc.reservofi.base.business.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class APISecurityFilterConfiguration {

	@Bean
	public SecurityFilterChain apiSecurityFilterChain(HttpSecurity http) {
		// Configuración de seguridad para las APIs
		// TODO: SE PERMITE TODO POR DEFECTO, HAY QUE CAMBIARLO CON SECURIZACIÓN
		http.securityMatcher("/")
				.authorizeHttpRequests(auth -> auth.anyRequest().permitAll());

		return http.build();
	}
}
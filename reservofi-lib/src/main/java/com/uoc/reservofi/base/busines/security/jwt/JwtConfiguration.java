package com.uoc.reservofi.base.busines.security.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class JwtConfiguration {

	// Esta clase de configuración determina los criterios que establece si una petición request
	// ha de ser revisada por el middleware (jwtAuthenticationFilter)

	// En otras palabras, aquí se gestiona las URL

	private final AuthenticationProvider authenticationProvider;
	private final JwtAuthenticationFilter jwtAuthenticationFilter;


	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) {

		// NO se usa cookes de sesión (JWT) por lo que se desactiva el csrf

		http.csrf(AbstractHttpConfigurer::disable)
				.cors(Customizer.withDefaults()) // Acepta el bean corsConfigurationSource
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/api/auth/**").permitAll() // Los endpoints de autenticación NO requieren token
						.anyRequest().authenticated()) // El resto SI los requiere
				.sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Cada petición lleva su token
				.authenticationProvider(authenticationProvider) // Proveedor de autenticación (UserDetailsService y encoder)
				.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class); // Hay que pasar por el filtro jwt ANTES, si no nunca se autenticará el usuario

		return http.build();
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {

		// Dado que el frontend y el backend están en diferentes puertos,
		// es necesario permitir el origen y los métodos que se requieran

		CorsConfiguration configuration = new CorsConfiguration();

		// Puerto frontend
		configuration.setAllowedOrigins(List.of("http://localhost:8000"));
		// Opciones
		configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
		// Headers
		configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));

		// Indica la ruta en la cual se aplican la configuración (todas las rutas)
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);

		return source;
	}
}
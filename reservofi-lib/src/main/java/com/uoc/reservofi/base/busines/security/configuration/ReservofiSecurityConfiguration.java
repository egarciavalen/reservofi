package com.uoc.reservofi.base.busines.security.configuration;

import com.uoc.reservofi.usuario.business.model.Usuario;
import com.uoc.reservofi.usuario.business.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ReservofiSecurityConfiguration {

	// Clase de configuración global que proporciona los beans necesarios
	// para la posterior configuración por token JWT
	// así como herramientas para la codificación de la contrasenia

	private final UsuarioRepository usuarioRepository;

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) {
		return config.getAuthenticationManager();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		// Define la estrategia para establecer la autenticación
		// Esencialmente, se hace la búsqueda del usuario por el correo y se comprueba la contrasenia con el hash de base de datos
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(userDetailsService());
		authProvider.setPasswordEncoder(encoder());
		return authProvider;
	}

	@Bean
	public UserDetailsService userDetailsService() {
		return username -> {
			Usuario usuario = usuarioRepository.findByCorreoCorporativo(username);
			if (usuario == null) {
				throw new UsernameNotFoundException("No se ha encontrado al usuario");
			}
			return usuario;
		};
	}

	@Bean
	public PasswordEncoder encoder() {
		// BCryps generará una salt aleatoria para codificar la contrasenia
		return new BCryptPasswordEncoder();
	}

}

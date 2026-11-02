package com.uoc.reservofi.base.busines.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	// Clase encargada de establecer el filtro del token JWT
	// Determina si es válido, en cuyo caso marca al usuario como autenticado

	private final JwtService jwtService;
	private final UserDetailsService userDetailsService;
	private final HandlerExceptionResolver handlerExceptionResolver;

	@Override
	protected void doFilterInternal(
			@NonNull HttpServletRequest request,
			@NonNull HttpServletResponse response,
			@NonNull FilterChain filterChain
	) throws ServletException, IOException {

		// Lectura de la cabecera
		final String authHeader = request.getHeader("Authorization");

		// Si NO hay un token, pasa la petición y finaliza el proceso
		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			// Deja pasar la cadena, es decir, finaliza el proceso aquí
			filterChain.doFilter(request, response);
			return;
		}

		try {
			// Extrae el token y el usuario (en este contexto, el correo corporativo)
			final String jwt = authHeader.substring(7);
			final String correoCorporativo = jwtService.extractUsername(jwt);

			// Comprueba que el usuario no esté autenticado
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

			if (correoCorporativo != null && authentication == null) {
				// Si hay correo corporativo y NO está autenticado, se carga el usuario
				UserDetails userDetails = this.userDetailsService.loadUserByUsername(correoCorporativo);

				// Validación del token
				if (jwtService.isTokenValid(jwt, userDetails)) {
					// Construye la autenticación
					UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
							userDetails,
							null,
							userDetails.getAuthorities()
					);
					// Guarda información adicional
					authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					// Establece el contexto de autenticación
					SecurityContextHolder.getContext().setAuthentication(authToken);
				}
			}
			// Continúa con la cadena de proceso
			filterChain.doFilter(request, response);
		} catch (Exception exception) {
			// Gestión de errores
			handlerExceptionResolver.resolveException(request, response, null, exception);
		}
	}
}
package com.uoc.reservofi.base.business.controller;

import com.uoc.reservofi.base.busines.exception.GestionException;
import com.uoc.reservofi.base.business.exception.RestException;
import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);


	@ExceptionHandler(GestionException.class)
	public ResponseEntity<String> gestionException(GestionException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_CONTENT);
	}

	@ExceptionHandler(RestException.class)
	public ResponseEntity<String> restException(RestException e) {
		return new ResponseEntity<>(e.getMessage() + ". Por favor, si desea realizar la operación póngase en contacto con el administrador.", HttpStatus.UNPROCESSABLE_CONTENT);
	}

	@ExceptionHandler(ExpiredJwtException.class)
	public ResponseEntity<String> expiredJwtException(RestException e) {
		return new ResponseEntity<>("El token JTW no es válido. Por favor, genere un nuevo token.", HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<String> usernameNotFoundException() {
		return new ResponseEntity<>("No se ha encontrado al usuario en el sistema. Por favor, revise que las credenciales sean correctas.", HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(DisabledException.class)
	public ResponseEntity<String> disabledException() {
		return new ResponseEntity<>("El usuario no está dado de alta en el sistema. Por favor, comuníquelo a un administrador.", HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> globalException(Exception e) {
		logger.error(e.getMessage()); // Depuración
		return new ResponseEntity<>("Ha sucedido un error interno no identificado procesando la petición. Por favor, vuelva a intentarlo o contacte con el administrador.", HttpStatus.INTERNAL_SERVER_ERROR);
	}

}

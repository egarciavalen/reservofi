package com.uoc.reservofi.base.business.controller;

import com.uoc.reservofi.base.business.exception.RestException;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> globalException(Exception e) {
		return new ResponseEntity<>("Ha sucedido un error interno no identificado procesando la petición", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(RestException.class)
	public ResponseEntity<String> restException(RestException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(ExpiredJwtException.class)
	public ResponseEntity<String> expiredJwtException(RestException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<String> usernameNotFoundException(RestException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
	}
}

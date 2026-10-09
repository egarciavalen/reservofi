package com.uoc.reservofi.base.business.controller;

import com.uoc.reservofi.base.business.exception.RestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestControllerAdvice {

	// Manejador de excepciones

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> globalException(Exception e) {
		// Si es una excepción global se muestra un mensaje genérico
		return new ResponseEntity<>("Ha sucedido un error interno no identificado procesando la petición", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(RestException.class)
	public ResponseEntity<String> restException(RestException e) {
		// Se muestra al usuario la información del mensaje de error
		return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

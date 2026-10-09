package com.uoc.reservofi.base.business.exception;

public class RestException extends RuntimeException {
	public RestException(String mensaje) {
		super(mensaje);
	}
}
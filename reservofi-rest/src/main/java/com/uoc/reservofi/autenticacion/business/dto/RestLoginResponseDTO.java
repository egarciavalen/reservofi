package com.uoc.reservofi.autenticacion.business.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestLoginResponseDTO {
	private String token;
	private long idUsuario;
	private long expiresIn;
}

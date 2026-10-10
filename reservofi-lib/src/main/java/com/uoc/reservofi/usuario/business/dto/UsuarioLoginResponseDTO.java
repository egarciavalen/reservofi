package com.uoc.reservofi.usuario.business.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioLoginResponseDTO {
	private String token;
	private long expiresIn;
}

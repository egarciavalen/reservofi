package com.uoc.reservofi.usuario.business.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioLoginRequestDTO {

	@NotEmpty(message = "usuario.correoCorporativo.obligatorio")
	@Size(max = 100, message = "usuario.correoCorporativo.tamanio.maximo")
	private String correoCorporativo;

	@NotEmpty(message = "usuario.contrasenia.obligatorio")
	@Size(max = 255, message = "usuario.contrasenia.tamanio.maximo")
	private String contrasenia;

}

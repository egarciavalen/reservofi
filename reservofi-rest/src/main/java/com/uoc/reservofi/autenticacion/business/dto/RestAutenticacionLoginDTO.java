package com.uoc.reservofi.autenticacion.business.dto;

import com.uoc.reservofi.base.business.dto.RestEntidadDTO;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestAutenticacionLoginDTO extends RestEntidadDTO {

	@NotEmpty(message = "usuario.correoCorporativo.obligatorio")
	@Size(max = 100, message = "usuario.correoCorporativo.tamanio.maximo")
	private String correoCorporativo;

	@NotEmpty(message = "usuario.contrasenia.obligatorio")
	@Size(max = 255, message = "usuario.contrasenia.tamanio.maximo")
	private String contrasenia;

}

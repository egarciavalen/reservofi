package com.uoc.reservofi.autenticacion.business.dto;

import com.uoc.reservofi.base.business.dto.RestEntidadDTO;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RestAutenticacionRegistroDTO extends RestEntidadDTO {

	@NotEmpty(message = "usuario.nombre.obligatorio")
	@Size(max = 100, message = "usuario.nombre.tamanio.maximo")
	private String nombre;

	@NotEmpty(message = "usuario.primerApellido.obligatorio")
	@Size(max = 100, message = "usuario.primerApellido.tamanio.maximo")
	private String primerApellido;

	@NotEmpty(message = "usuario.segundoApellido.obligatorio")
	@Size(max = 100, message = "usuario.segundoApellido.tamanio.maximo")
	private String segundoApellido;

	@NotEmpty(message = "usuario.correoCorporativo.obligatorio")
	@Size(max = 100, message = "usuario.correoCorporativo.tamanio.maximo")
	private String correoCorporativo;

	@NotEmpty(message = "usuario.contrasenia.obligatorio")
	@Size(max = 255, message = "usuario.contrasenia.tamanio.maximo")
	private String contrasenia;

	@NotNull(message = "usuario.activo.obligatorio")
	private Boolean activo;

	private List<Long> idsRoles;
}

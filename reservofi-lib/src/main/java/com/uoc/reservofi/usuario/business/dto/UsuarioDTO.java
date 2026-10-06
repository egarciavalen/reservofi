package com.uoc.reservofi.usuario.business.dto;

import com.uoc.reservofi.base.busines.dto.GestionEntidadActualizableDTO;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UsuarioDTO extends GestionEntidadActualizableDTO {

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

	@NotEmpty(message = "usuario.contraseniaHash.obligatorio")
	@Size(max = 100, message = "usuario.contraseniaHash.tamanio.maximo")
	private String contraseniaHash;

	@NotNull(message = "usuario.activo.obligatorio")
	private Boolean activo;

	private List<Long> idsRoles;
}

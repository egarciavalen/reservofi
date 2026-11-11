package com.uoc.reservofi.usuario.business.dto;

import com.uoc.reservofi.base.busines.dto.GestionEntidadDTO;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioRolDTO extends GestionEntidadDTO {

	@NotEmpty(message = "usuario.rol.nombre.obligatorio")
	@Size(max = 100, message = "usuario.rol.nombre.tamanio.maximo")
	private String nombre;

}

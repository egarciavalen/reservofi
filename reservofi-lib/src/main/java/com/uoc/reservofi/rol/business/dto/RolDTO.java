package com.uoc.reservofi.rol.business.dto;

import com.uoc.reservofi.base.busines.dto.GestionEntidadDTO;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RolDTO extends GestionEntidadDTO {

	@NotEmpty(message = "rol.nombre.obligatorio")
	@Size(max = 100, message = "rol.nombre.tamanio.maximo")
	private String nombre;
}

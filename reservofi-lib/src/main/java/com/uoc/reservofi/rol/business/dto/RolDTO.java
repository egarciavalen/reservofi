package com.uoc.reservofi.rol.business.dto;

import com.uoc.reservofi.base.busines.dto.GestionEntidadActualizableDTO;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RolDTO extends GestionEntidadActualizableDTO {

	@NotEmpty(message = "rol.nombre.obligatorio")
	private String nombre;
}

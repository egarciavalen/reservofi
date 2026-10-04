package com.uoc.reservofi.base.busines.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GestionEntidadDTO {

	@NotEmpty(message = "entidad.id.obligatorio")
	private Long id;

}

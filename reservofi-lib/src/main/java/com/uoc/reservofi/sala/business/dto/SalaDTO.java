package com.uoc.reservofi.sala.business.dto;

import com.uoc.reservofi.base.busines.dto.GestionEntidadActualizableDTO;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SalaDTO extends GestionEntidadActualizableDTO {

	@NotEmpty(message = "sala.nombre.obligatorio")
	@Size(min = 0, message = "sala.nombre.tamanio.minimo")
	@Size(max = 100, message = "sala.nombre.tamanio.maximo")
	private String nombre;
}

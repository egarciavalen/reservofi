package com.uoc.reservofi.sala.business.dto;

import com.uoc.reservofi.base.business.dto.RestEntidadDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestSalaConsultaDTO extends RestEntidadDTO {

	private String nombre;
	private String rutaImagen;
}

package com.uoc.reservofi.sala.business.dto;

import com.uoc.reservofi.base.business.dto.RestEntidadConsultaDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestSalaConsultaDTO extends RestEntidadConsultaDTO {

	private String nombre;
	private String rutaImagen;
	private String nombreImagen;

}

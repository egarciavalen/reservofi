package com.uoc.reservofi.puesto.business.dto;

import com.uoc.reservofi.base.business.dto.RestEntidadConsultaDTO;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RestPuestoConsultaDTO extends RestEntidadConsultaDTO {

	private Long idSala;
	private String nombre;
	private BigDecimal coordenadaX;
	private BigDecimal coordenadaY;
	private BigDecimal ancho;
	private BigDecimal alto;
	private String nombreSala;
	private String rutaImagen;

}

package com.uoc.reservofi.reserva.business.dto;

import com.uoc.reservofi.base.business.dto.RestEntidadDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class RestReservaConsultaDTO extends RestEntidadDTO {

	private Long idUsuario;

	private Long idPuesto;

	private LocalDateTime fechaInicio;

	private LocalDateTime fechaFin;

	private String nombrePuesto;

	private String nombreSala;

	private String rutaImagen;

}

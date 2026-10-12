package com.uoc.reservofi.reserva.business.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.uoc.reservofi.base.business.dto.RestEntidadDTO;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class RestReservaDTO extends RestEntidadDTO {

	@NotNull(message = "reserva.usuario.obligatorio")
	private Long idUsuario;

	@NotNull(message = "reserva.puesto.obligatorio")
	private Long idPuesto;

	@NotNull(message = "reserva.fechaInicio.obligatorio")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime fechaInicio;

	@NotNull(message = "reserva.fechaFin.obligatorio")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime fechaFin;

}

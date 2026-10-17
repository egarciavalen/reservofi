package com.uoc.reservofi.reserva.business.dto;

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

	// Se espera una conversión de string (yyyy-MM-ddTHH:mm) a LocalDateTime
	@NotNull(message = "reserva.fechaInicio.obligatorio")
	private LocalDateTime fechaInicio;

	@NotNull(message = "reserva.fechaFin.obligatorio")
	private LocalDateTime fechaFin;

}

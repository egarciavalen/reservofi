package com.uoc.reservofi.reserva.business.dto;

import com.uoc.reservofi.base.busines.dto.GestionEntidadDTO;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReservaDTO extends GestionEntidadDTO {

	@NotNull(message = "reserva.usuario.obligatorio")
	private Long idUsuario;

	@NotNull(message = "reserva.puesto.obligatorio")
	private Long idPuesto;

	@NotNull(message = "reserva.fechaInicio.obligatorio")
	private LocalDateTime fechaInicio;

	@NotNull(message = "reserva.fechaFin.obligatorio")
	private LocalDateTime fechaFin;

}

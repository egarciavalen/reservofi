package com.uoc.reservofi.reserva.business.dto;

import com.uoc.reservofi.base.busines.dto.GestionEntidadDTO;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReservaDTO extends GestionEntidadDTO {

	@NotEmpty(message = "reserva.usuario.obligatorio")
	private Long idUsuario;

	@NotEmpty(message = "reserva.puesto.obligatorio")
	private Long idPuesto;

	@NotEmpty(message = "reserva.fechaInicio.obligatorio")
	private LocalDateTime fechaInicio;

	@NotEmpty(message = "reserva.fechaFin.obligatorio")
	private LocalDateTime fechaFin;

}

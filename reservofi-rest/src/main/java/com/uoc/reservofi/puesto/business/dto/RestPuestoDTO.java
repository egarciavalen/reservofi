package com.uoc.reservofi.puesto.business.dto;

import com.uoc.reservofi.base.business.dto.RestEntidadDTO;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RestPuestoDTO extends RestEntidadDTO {

	@NotNull(message = "puesto.sala.obligatorio")
	private Long idSala;

	@NotEmpty(message = "puesto.nombre.obligatorio")
	@Size(max = 100, message = "puesto.nombre.tamanio.maximo")
	private String nombre;

	@NotNull(message = "puesto.coordenadaX.obligatorio")
	@DecimalMax(value = "99999.99", message = "puesto.coordenadaX.tamanio.maximo")
	private BigDecimal coordenadaX;

	@NotNull(message = "puesto.coordenadaY.obligatorio")
	@DecimalMax(value = "99999.99", message = "puesto.coordenadaY.tamanio.maximo")
	private BigDecimal coordenadaY;

}

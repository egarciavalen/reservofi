package com.uoc.reservofi.puesto.business.dto;

import com.uoc.reservofi.base.busines.dto.GestionEntidadActualizableDTO;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PuestoDTO extends GestionEntidadActualizableDTO {

	@NotEmpty(message = "puesto.sala.obligatorio")
	private Long idSala;

	@NotEmpty(message = "puesto.nombre.obligatorio")
	@Size(min = 0, message = "puesto.nombre.tamanio.minimo")
	@Size(max = 100, message = "puesto.nombre.tamanio.maximo")
	private String nombre;

	@NotEmpty(message = "puesto.coordenadaX.obligatorio")
	@DecimalMin(value = "0.00", message = "puesto.coordenadaX.tamanio.minimo")
	@DecimalMax(value = "99999.99", message = "puesto.coordenadaX.tamanio.minimo")
	private BigDecimal coordenadaX;

	@NotEmpty(message = "puesto.coordenadaY.obligatorio")
	@DecimalMin(value = "0.00", message = "puesto.coordenadaY.tamanio.minimo")
	@DecimalMax(value = "99999.99", message = "puesto.coordenadaY.tamanio.minimo")
	private BigDecimal coordenadaY;

}

package com.uoc.reservofi.puesto.business.model;

import com.uoc.reservofi.base.busines.model.BaseEntidadActualizable;
import com.uoc.reservofi.sala.business.model.Sala;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Table(name = "Puesto", uniqueConstraints = @UniqueConstraint(
		name = "uq_puesto_nombre",
		columnNames = "nombre")
)
public class Puesto extends BaseEntidadActualizable {

	@Column(name = "nombre", nullable = false, length = 100)
	private String nombre;

	@Column(name = "coordenada_x", precision = 8, scale = 2, nullable = false)
	private BigDecimal coordenadaX;

	@Column(name = "coordenada_y", precision = 8, scale = 2, nullable = false)
	private BigDecimal coordenadaY;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_sala", nullable = false)
	private Sala sala;

}

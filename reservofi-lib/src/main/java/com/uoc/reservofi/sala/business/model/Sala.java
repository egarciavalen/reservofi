package com.uoc.reservofi.sala.business.model;

import com.uoc.reservofi.base.busines.model.BaseEntidadActualizable;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "Sala", uniqueConstraints = @UniqueConstraint(
		name = "uq_sala_nombre",
		columnNames = "nombre")
)
public class Sala extends BaseEntidadActualizable {

	@Column(name = "nombre", nullable = false, length = 100)
	private String nombre;

}

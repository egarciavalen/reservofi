package com.uoc.reservofi.sala.business.model;

import com.uoc.reservofi.base.busines.model.BaseEntidad;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Sala", uniqueConstraints = @UniqueConstraint(
		name = "uq_sala_nombre",
		columnNames = "nombre")
)
public class Sala extends BaseEntidad {

	@Column(name = "nombre", nullable = false, length = 100)
	private String nombre;

}

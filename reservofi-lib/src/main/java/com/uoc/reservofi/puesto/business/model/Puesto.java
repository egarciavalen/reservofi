package com.uoc.reservofi.puesto.business.model;

import com.uoc.reservofi.base.busines.model.BaseEntidad;
import com.uoc.reservofi.sala.business.model.Sala;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "Puesto", uniqueConstraints = @UniqueConstraint(
		name = "uq_puesto_nombre",
		columnNames = "nombre")
)
public class Puesto extends BaseEntidad {


	@Column(name = "nombre", nullable = false, length = 100)
	private String nombre;

	@Column(name = "coordenada_x", precision = 8, scale = 2, nullable = false)
	private BigDecimal coordenadaX;

	@Column(name = "coordenada_y", precision = 8, scale = 2, nullable = false)
	private BigDecimal coordenadaY;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_sala", nullable = false, foreignKey = @ForeignKey(name = "fk_puesto_sala"))
	private Sala sala;

}

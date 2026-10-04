package com.uoc.reservofi.reserva.business.model;

import com.uoc.reservofi.base.busines.model.BaseEntidadActualizable;
import com.uoc.reservofi.usuario.business.model.Usuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Table(name = "Reserva")
public class Reserva extends BaseEntidadActualizable {

	@Column(name = "fecha_inicio", nullable = false)
	public LocalDateTime fechaInicio;

	@Column(name = "fecha_fin", nullable = false)
	public LocalDateTime fechaFin;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario", nullable = false)
	private Usuario usuario;

}

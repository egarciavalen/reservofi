package com.uoc.reservofi.base.busines.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
public class BaseEntidadActualizable extends BaseEntidad {

	@Column(name = "creacion_usuario", nullable = false, length = 100)
	public String creacionUsuario;

	@Column(name = "creacion_fecha", nullable = false)
	public LocalDateTime creacionFecha;

	@Column(name = "actualizacion_usuario", nullable = true, length = 100)
	@LastModifiedBy
	public String actualizacionUsuario;

	@Column(name = "actualizacion_fecha", nullable = true)
	@LastModifiedDate
	public LocalDateTime actualizacionFecha;

}

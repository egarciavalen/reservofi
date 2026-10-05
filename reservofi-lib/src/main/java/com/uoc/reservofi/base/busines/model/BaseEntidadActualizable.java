package com.uoc.reservofi.base.busines.model;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class) // Para poder auditar los datos
public class BaseEntidadActualizable extends BaseEntidad {

	@CreatedBy
	@Column(name = "creacion_usuario", nullable = false, length = 100)
	public String creacionUsuario;

	@CreatedDate
	@Column(name = "creacion_fecha", nullable = false)
	public LocalDateTime creacionFecha;

	@LastModifiedBy
	@Column(name = "actualizacion_usuario", length = 100)
	public String actualizacionUsuario;

	@LastModifiedDate
	@Column(name = "actualizacion_fecha")
	public LocalDateTime actualizacionFecha;

}

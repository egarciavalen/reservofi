package com.uoc.reservofi.base.busines.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass // Permite que las entidades hijas hereden los atributos
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class) // Para poder auditar los datos
public abstract class BaseEntidad {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;

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

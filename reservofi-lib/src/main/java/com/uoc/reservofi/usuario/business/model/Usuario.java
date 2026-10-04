package com.uoc.reservofi.usuario.business.model;

import com.uoc.reservofi.base.busines.model.BaseEntidadActualizable;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "Usuario", uniqueConstraints = @UniqueConstraint(
		name = "uq_rol_correo_corporativo",
		columnNames = "correo_corporativo")
)
public class Usuario extends BaseEntidadActualizable {

	@Column(name = "nombre", nullable = false, length = 100)
	private String nombre;

	@Column(name = "primer_apellido", nullable = false, length = 100)
	private String primerApellido;

	@Column(name = "segundo_apellido", nullable = false, length = 100)
	private String segundoApellido;

	@Column(name = "correo_corporativo", nullable = false, length = 100)
	private String correoCorporativo;

	@Column(name = "contrasenia_hash", nullable = false, length = 255)
	private String contraseniaHash;

	@Column(name = "activo", nullable = false)
	private Boolean activo;
}

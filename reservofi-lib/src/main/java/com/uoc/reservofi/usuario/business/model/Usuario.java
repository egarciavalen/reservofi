package com.uoc.reservofi.usuario.business.model;

import com.uoc.reservofi.base.busines.model.BaseEntidadActualizable;
import com.uoc.reservofi.rol.business.model.Rol;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Table(name = "Usuario", uniqueConstraints = @UniqueConstraint(
		name = "uq_usuario_correo_corporativo",
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

	// Relación n a n
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "usuario_rol",
			joinColumns = @JoinColumn(name = "id_usuario"),
			inverseJoinColumns = @JoinColumn(name = "id_rol")
	)
	private Set<Rol> roles = new HashSet<>();
}

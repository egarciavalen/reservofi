package com.uoc.reservofi.rol.business.model;

import com.uoc.reservofi.base.busines.model.BaseEntidadActualizable;
import com.uoc.reservofi.usuario.business.model.Usuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Table(name = "Rol", uniqueConstraints = @UniqueConstraint(
		name = "uq_rol_nombre",
		columnNames = "nombre")
)
public class Rol extends BaseEntidadActualizable {

	@Column(name = "nombre", nullable = false, length = 100)
	private String nombre;

	// Relación n a n
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
	private Set<Usuario> usuarios = new HashSet<>();
}

package com.uoc.reservofi.usuario.business.model;

import com.uoc.reservofi.base.busines.model.BaseEntidad;
import com.uoc.reservofi.rol.business.model.Rol;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Usuario", uniqueConstraints = @UniqueConstraint(
		name = "uq_usuario_correo_corporativo",
		columnNames = "correo_corporativo")
)
public class Usuario extends BaseEntidad implements UserDetails {

	@Column(name = "nombre", nullable = false, length = 100)
	private String nombre;

	@Column(name = "primer_apellido", nullable = false, length = 100)
	private String primerApellido;

	@Column(name = "segundo_apellido", nullable = false, length = 100)
	private String segundoApellido;

	@Column(name = "correo_corporativo", nullable = false, length = 100)
	private String correoCorporativo;

	@Column(name = "contrasenia", nullable = false)
	private String contrasenia;

	@Column(name = "activo", nullable = false)
	private Boolean activo;

	// Relación n a n
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "usuario_rol",
			joinColumns = @JoinColumn(name = "id_usuario"),
			inverseJoinColumns = @JoinColumn(name = "id_rol"),
			foreignKey = @ForeignKey(name = "fk_usuario_rol_usuario"),
			inverseForeignKey = @ForeignKey(name = "fk_usuario_rol_rol")
	)
	private Set<Rol> roles = new HashSet<>(); // Uso de Set para mayor eficiencia

	// Métodos adicionales que se obtiene de UserDetails
	// Se implementan para la gestión de seguridad mediante token JWT
	// Por defecto, isAccountNonExpired, isAccountNonLocked, isCredentialsNonExpired y isEnabled ya vienen a true

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// Gestión de roles en la aplicación si fuera necesario
		return List.of();
	}

	@Override
	public @Nullable String getPassword() {
		return contrasenia;
	}

	@Override
	public String getUsername() {
		return correoCorporativo;
	}
}

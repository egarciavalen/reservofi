package com.uoc.reservofi.usuario.business.mapper;

import com.uoc.reservofi.rol.business.model.Rol;
import com.uoc.reservofi.rol.business.service.base.BaseRolService;
import com.uoc.reservofi.usuario.business.dto.UsuarioDTO;
import com.uoc.reservofi.usuario.business.dto.UsuarioRolDTO;
import com.uoc.reservofi.usuario.business.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public abstract class UsuarioMapperDecorator implements UsuarioMapper {

	// Uso de @Autowired por problemas con el constructor por defecto

	@Autowired
	private UsuarioMapper mapper;

	// Servicios de entidades asociadas
	@Autowired
	private BaseRolService baseRolService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Usuario DTOAentidad(UsuarioDTO dto) {
		return this.gestionLogicaAdicional(dto, null);
	}

	@Override
	public Usuario DTOAentidad(UsuarioDTO dto, Usuario entidad) {
		return this.gestionLogicaAdicional(dto, entidad);
	}

	private Usuario gestionLogicaAdicional(UsuarioDTO dto, Usuario entidad) {

		Usuario usuarioRelacion;

		if (entidad == null) {
			usuarioRelacion = mapper.DTOAentidad(dto);
		} else {
			usuarioRelacion = mapper.DTOAentidad(dto, entidad);
		}

		// Únicamente si llega una contraseña desde el DTO
		if (dto.getContrasenia() != null) {
			// Hash de la contrasenia
			// Esto es esencial, puesto que a) no hay que guardar contrasenias en plano y b) si no se hace, la autenticación fallará
			usuarioRelacion.setContrasenia(passwordEncoder.encode(dto.getContrasenia()));
		}

		// Roles
		Set<Rol> rolesUsuario = new HashSet<>();
		if (dto.getRoles()!= null && !dto.getRoles().isEmpty()) {
			for (UsuarioRolDTO usuarioRol :  dto.getRoles()) {
				Rol rol = baseRolService.findById(usuarioRol.getId());
				rolesUsuario.add(rol);
			}
		}

		usuarioRelacion.setRoles(rolesUsuario);
		return usuarioRelacion;
	}

	@Override
	public UsuarioDTO entidadADTO(Usuario entidad) {
		UsuarioDTO dto = mapper.entidadADTO(entidad);

		// Roles
		if (entidad.getRoles() != null && !entidad.getRoles().isEmpty()) {

			List<UsuarioRolDTO> roles = new ArrayList<>();
			for (Rol rol : entidad.getRoles()) {
				UsuarioRolDTO rolDTO = new UsuarioRolDTO();
				rolDTO.setId(rol.getId());
				rolDTO.setNombre(rol.getNombre());
				roles.add(rolDTO);
			}
			dto.setRoles(roles);
		}
		return dto;
	}

	@Override
	public List<UsuarioDTO> entidadADTOList(List<Usuario> entidadList) {
		if (entidadList == null) return null;
		List<UsuarioDTO> returnList = new ArrayList<>(entidadList.size());
		for (Usuario usuario : entidadList) {
			returnList.add(this.entidadADTO(usuario));
		}
		return returnList;
	}
}

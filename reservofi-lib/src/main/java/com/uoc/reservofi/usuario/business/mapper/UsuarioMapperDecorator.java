package com.uoc.reservofi.usuario.business.mapper;

import com.uoc.reservofi.rol.business.model.Rol;
import com.uoc.reservofi.rol.business.service.base.BaseRolService;
import com.uoc.reservofi.usuario.business.dto.UsuarioDTO;
import com.uoc.reservofi.usuario.business.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;

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

	@Override
	public Usuario DTOAentidad(UsuarioDTO dto) {
		return this.gestionarRelaciones(dto, null);
	}

	@Override
	public Usuario DTOAentidad(UsuarioDTO dto, Usuario entidad) {
		return this.gestionarRelaciones(dto, entidad);
	}

	private Usuario gestionarRelaciones(UsuarioDTO dto, Usuario entidad) {

		Usuario usuarioRelacion;

		if (entidad == null) {
			usuarioRelacion = mapper.DTOAentidad(dto);
		} else {
			usuarioRelacion = mapper.DTOAentidad(dto, entidad);
		}

		// Roles
		Set<Rol> rolesUsuario;
		if (dto.getIdsRoles() != null && !dto.getIdsRoles().isEmpty()) {
			List<Rol> roles = baseRolService.findAllById(dto.getIdsRoles());
			rolesUsuario = new HashSet<>(roles);
		} else {
			rolesUsuario = new HashSet<>();
		}
		usuarioRelacion.setRoles(rolesUsuario);

		return usuarioRelacion;
	}

	@Override
	public UsuarioDTO entidadADTO(Usuario entidad) {
		UsuarioDTO dto = mapper.entidadADTO(entidad);

		// Roles
		if (entidad.getRoles() != null && !entidad.getRoles().isEmpty()) {
			List<Long> idsRoles = new ArrayList<>();
			for (Rol rol : entidad.getRoles()) {
				idsRoles.add(rol.getId());
			}
			dto.setIdsRoles(idsRoles);
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

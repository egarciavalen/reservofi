package com.uoc.reservofi.usuario.business.mapper;

import com.uoc.reservofi.base.busines.exception.GestionException;
import com.uoc.reservofi.rol.business.model.Rol;
import com.uoc.reservofi.rol.business.service.base.BaseRolService;
import com.uoc.reservofi.usuario.business.dto.UsuarioDTO;
import com.uoc.reservofi.usuario.business.model.Usuario;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
public abstract class UsuarioMapperDecorator implements UsuarioMapper {

	private final UsuarioMapper mapper;

	// Servicios de entidades asociadas
	private final BaseRolService baseRolService;

	@Override
	public Usuario DTOaEntidad(UsuarioDTO dto) {
		Usuario entidad = mapper.DTOaEntidad(dto);

		// Roles
		if (dto.getIdsRoles() != null && !dto.getIdsRoles().isEmpty()) {
			Set<Rol> roles = new HashSet<>();
			for (Long idRol : dto.getIdsRoles()) {
				Rol rol = baseRolService.findById(idRol);
				if (rol == null) {
					throw new GestionException("No existe el rol que se quiere insertar");
				}
				roles.add(rol);
			}
			entidad.setRoles(roles);
		}
		return entidad;
	}

	@Override
	public UsuarioDTO entidadADTO(Usuario entidad) {
		UsuarioDTO dto = mapper.entidadADTO(entidad);

		// Roles
		if (entidad.getRoles() != null && !entidad.getRoles().isEmpty()) {
			Set<Long> idsRoles = new HashSet<>();
			for (Rol rol : entidad.getRoles()) {
				idsRoles.add(rol.getId());
			}
			dto.setIdsRoles(idsRoles);
		}
		return dto;
	}
}

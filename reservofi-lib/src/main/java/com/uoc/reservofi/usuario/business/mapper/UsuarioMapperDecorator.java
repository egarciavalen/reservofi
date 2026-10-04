package com.uoc.reservofi.usuario.business.mapper;

import com.uoc.reservofi.rol.business.model.Rol;
import com.uoc.reservofi.rol.business.service.base.BaseRolService;
import com.uoc.reservofi.usuario.business.dto.UsuarioDTO;
import com.uoc.reservofi.usuario.business.model.Usuario;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

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
			List<Rol> roles = baseRolService.findAllById(dto.getIdsRoles());
			entidad.setRoles(new HashSet<>(roles)); // Se almacena como Set
		}
		return entidad;
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
}

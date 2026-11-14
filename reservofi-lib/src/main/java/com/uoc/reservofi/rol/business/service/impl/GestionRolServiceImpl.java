package com.uoc.reservofi.rol.business.service.impl;

import com.uoc.reservofi.base.busines.exception.GestionException;
import com.uoc.reservofi.base.busines.service.impl.AbstractGestionEntidadServiceImpl;
import com.uoc.reservofi.rol.business.dto.RolDTO;
import com.uoc.reservofi.rol.business.mapper.RolMapper;
import com.uoc.reservofi.rol.business.model.Rol;
import com.uoc.reservofi.rol.business.service.GestionRolService;
import com.uoc.reservofi.rol.business.service.base.BaseRolService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(GestionRolService.BEAN_NAME)
@RequiredArgsConstructor
@Transactional(rollbackFor = GestionException.class)
public class GestionRolServiceImpl extends AbstractGestionEntidadServiceImpl<RolDTO> implements GestionRolService {

	private static final Logger LOGGER = LoggerFactory.getLogger(GestionRolServiceImpl.class);
	private final BaseRolService baseRolService;
	private final RolMapper rolMapper;

	@Override
	public RolDTO insert(@Valid RolDTO dto) {
		Rol entidad = rolMapper.DTOAentidad(dto);
		entidad = baseRolService.insert(entidad);
		return rolMapper.entidadADTO(entidad);
	}

	@Override
	public RolDTO update(@Valid RolDTO dto) {

		if (dto.getId() == null) {
			LOGGER.error("Ha ocurrido un error al actualizar el rol en el sistema. Nombre del rol: {}", dto.getNombre());
			throw new GestionException("No se ha podido procesar la actualización del elemento. Por favor, contacte con el administrador.");
		}

		Rol entidadBD = baseRolService.findById(dto.getId());

		if (entidadBD == null) {
			LOGGER.error("Se está intentando actualizar una entidad no existente. Identificador del rol: {}", dto.getId());
			throw new GestionException("No se ha podido procesar la actualización del elemento. Por favor, contacte con el administrador.");
		}

		Rol entidad = rolMapper.DTOAentidad(dto, entidadBD);
		entidad = baseRolService.update(entidad);
		return rolMapper.entidadADTO(entidad);
	}

	@Override
	public void deleteById(Long id) {
		baseRolService.deleteById(id);
	}

	@Override
	public List<RolDTO> findAll() {
		List<Rol> entidadList = baseRolService.findAll();
		return rolMapper.entidadADTOList(entidadList);
	}

	@Override
	public List<RolDTO> findAllById(List<Long> ids) {
		List<Rol> entidadList = baseRolService.findAllById(ids);
		return rolMapper.entidadADTOList(entidadList);
	}

	@Override
	public RolDTO findById(Long id) {
		Rol entidad = baseRolService.findById(id);

		if (entidad == null) {
			return null;
		}

		return rolMapper.entidadADTO(entidad);
	}

	@Override
	public boolean existsById(Long id) {
		return baseRolService.existsById(id);
	}

	@Override
	public List<RolDTO> findByCreacionUsuario(String usuario) {
		List<Rol> entidadList = baseRolService.findByCreacionUsuario(usuario);
		return rolMapper.entidadADTOList(entidadList);
	}

	@Override
	public List<RolDTO> findByActualizacionUsuario(String usuario) {
		List<Rol> entidadList = baseRolService.findByActualizacionUsuario(usuario);
		return rolMapper.entidadADTOList(entidadList);
	}

	@Override
	public Long getIdByNombre(String nombre) {
		return baseRolService.getIdByNombre(nombre);
	}
}

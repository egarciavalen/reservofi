package com.uoc.reservofi.rol.business.service.impl;

import com.uoc.reservofi.base.busines.service.impl.GestionEntidadActualizableServiceImpl;
import com.uoc.reservofi.rol.business.dto.RolDTO;
import com.uoc.reservofi.rol.business.mapper.RolMapper;
import com.uoc.reservofi.rol.business.model.Rol;
import com.uoc.reservofi.rol.business.service.GestionRolService;
import com.uoc.reservofi.rol.business.service.base.BaseRolService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(GestionRolService.BEAN_NAME)
@RequiredArgsConstructor
public class GestionRolServiceImpl extends GestionEntidadActualizableServiceImpl<RolDTO> implements GestionRolService {

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
		Rol entidad = rolMapper.DTOAentidad(dto);
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

}

package com.uoc.reservofi.puesto.business.service.impl;

import com.uoc.reservofi.base.busines.service.impl.GestionEntidadActualizableServiceImpl;
import com.uoc.reservofi.puesto.business.dto.PuestoDTO;
import com.uoc.reservofi.puesto.business.mapper.PuestoMapper;
import com.uoc.reservofi.puesto.business.model.Puesto;
import com.uoc.reservofi.puesto.business.service.GestionPuestoService;
import com.uoc.reservofi.puesto.business.service.base.BasePuestoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(GestionPuestoService.BEAN_NAME)
@RequiredArgsConstructor
public class GestionPuestoServiceImpl extends GestionEntidadActualizableServiceImpl<PuestoDTO> implements GestionPuestoService {

	private final BasePuestoService basePuestoService;
	private final PuestoMapper PuestoMapper;

	@Override
	public PuestoDTO insert(@Valid PuestoDTO dto) {
		Puesto entidad = PuestoMapper.DTOaEntidad(dto);
		entidad = basePuestoService.insert(entidad);
		return PuestoMapper.entidadADTO(entidad);
	}

	@Override
	public PuestoDTO update(@Valid PuestoDTO dto) {
		Puesto entidad = PuestoMapper.DTOaEntidad(dto);
		entidad = basePuestoService.update(entidad);
		return PuestoMapper.entidadADTO(entidad);
	}

	@Override
	public void deleteById(Long id) {
		basePuestoService.deleteById(id);
	}

	@Override
	public List<PuestoDTO> findAll() {
		List<Puesto> entidadList = basePuestoService.findAll();
		return PuestoMapper.entidadADTOList(entidadList);
	}

	@Override
	public PuestoDTO findById(Long id) {
		Puesto entidad = basePuestoService.findById(id);
		return PuestoMapper.entidadADTO(entidad);
	}

	@Override
	public boolean existsById(Long id) {
		return basePuestoService.existsById(id);
	}

	@Override
	public List<PuestoDTO> findByCreacionUsuario(String usuario) {
		List<Puesto> entidadList = basePuestoService.findByCreacionUsuario(usuario);
		return PuestoMapper.entidadADTOList(entidadList);
	}

	@Override
	public List<PuestoDTO> findByActualizacionUsuario(String usuario) {
		List<Puesto> entidadList = basePuestoService.findByActualizacionUsuario(usuario);
		return PuestoMapper.entidadADTOList(entidadList);
	}

}

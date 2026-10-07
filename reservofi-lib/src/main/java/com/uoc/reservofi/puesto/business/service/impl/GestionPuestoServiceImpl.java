package com.uoc.reservofi.puesto.business.service.impl;

import com.uoc.reservofi.base.busines.exception.GestionException;
import com.uoc.reservofi.base.busines.service.impl.GestionEntidadServiceImpl;
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
public class GestionPuestoServiceImpl extends GestionEntidadServiceImpl<PuestoDTO> implements GestionPuestoService {

	private final BasePuestoService basePuestoService;
	private final PuestoMapper puestoMapper;

	@Override
	public PuestoDTO insert(@Valid PuestoDTO dto) {
		Puesto entidad = puestoMapper.DTOAentidad(dto);
		entidad = basePuestoService.insert(entidad);
		return puestoMapper.entidadADTO(entidad);
	}

	@Override
	public PuestoDTO update(@Valid PuestoDTO dto) {

		if (dto.getId() == null) {
			throw new GestionException("La entidad que se quiere actualizar ha de tener el campo identificador");
		}

		Puesto entidadBD = basePuestoService.findById(dto.getId());

		if (entidadBD == null) {
			throw new GestionException("Se está intentando actualizar una entidad no existente");
		}

		Puesto entidad = puestoMapper.DTOAentidad(dto, entidadBD);
		entidad = basePuestoService.update(entidad);
		return puestoMapper.entidadADTO(entidad);
	}

	@Override
	public void deleteById(Long id) {
		basePuestoService.deleteById(id);
	}

	@Override
	public List<PuestoDTO> findAll() {
		List<Puesto> entidadList = basePuestoService.findAll();
		return puestoMapper.entidadADTOList(entidadList);
	}

	@Override
	public PuestoDTO findById(Long id) {
		Puesto entidad = basePuestoService.findById(id);
		return puestoMapper.entidadADTO(entidad);
	}

	@Override
	public List<PuestoDTO> findAllById(List<Long> ids) {
		List<Puesto> entidadList = basePuestoService.findAllById(ids);
		return puestoMapper.entidadADTOList(entidadList);
	}

	@Override
	public boolean existsById(Long id) {
		return basePuestoService.existsById(id);
	}

	@Override
	public List<PuestoDTO> findByCreacionUsuario(String usuario) {
		List<Puesto> entidadList = basePuestoService.findByCreacionUsuario(usuario);
		return puestoMapper.entidadADTOList(entidadList);
	}

	@Override
	public List<PuestoDTO> findByActualizacionUsuario(String usuario) {
		List<Puesto> entidadList = basePuestoService.findByActualizacionUsuario(usuario);
		return puestoMapper.entidadADTOList(entidadList);
	}

}

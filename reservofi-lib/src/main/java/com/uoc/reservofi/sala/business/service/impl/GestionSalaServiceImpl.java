package com.uoc.reservofi.sala.business.service.impl;

import com.uoc.reservofi.base.busines.service.impl.GestionEntidadActualizableServiceImpl;
import com.uoc.reservofi.sala.business.dto.SalaDTO;
import com.uoc.reservofi.sala.business.mapper.SalaMapper;
import com.uoc.reservofi.sala.business.model.Sala;
import com.uoc.reservofi.sala.business.service.GestionSalaService;
import com.uoc.reservofi.sala.business.service.base.BaseSalaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(GestionSalaService.BEAN_NAME)
@RequiredArgsConstructor
public class GestionSalaServiceImpl extends GestionEntidadActualizableServiceImpl<SalaDTO> implements GestionSalaService {

	private final BaseSalaService baseSalaService;
	private final SalaMapper salaMapper;

	@Override
	public SalaDTO insert(@Valid SalaDTO dto) {
		Sala entidad = salaMapper.DTOaEntidad(dto);
		entidad = baseSalaService.insert(entidad);
		return salaMapper.entidadADTO(entidad);
	}

	@Override
	public SalaDTO update(@Valid SalaDTO dto) {
		Sala entidad = salaMapper.DTOaEntidad(dto);
		entidad = baseSalaService.update(entidad);
		return salaMapper.entidadADTO(entidad);
	}

	@Override
	public void deleteById(Long id) {
		baseSalaService.deleteById(id);
	}

	@Override
	public List<SalaDTO> findAll() {
		List<Sala> entidadList = baseSalaService.findAll();
		return salaMapper.entidadADTOList(entidadList);
	}

	@Override
	public SalaDTO findById(Long id) {
		Sala entidad = baseSalaService.findById(id);
		return salaMapper.entidadADTO(entidad);
	}

	@Override
	public List<SalaDTO> findAllById(List<Long> ids) {
		List<Sala> entidadList = baseSalaService.findAllById(ids);
		return salaMapper.entidadADTOList(entidadList);
	}

	@Override
	public boolean existsById(Long id) {
		return baseSalaService.existsById(id);
	}

	@Override
	public List<SalaDTO> findByCreacionUsuario(String usuario) {
		List<Sala> entidadList = baseSalaService.findByCreacionUsuario(usuario);
		return salaMapper.entidadADTOList(entidadList);
	}

	@Override
	public List<SalaDTO> findByActualizacionUsuario(String usuario) {
		List<Sala> entidadList = baseSalaService.findByActualizacionUsuario(usuario);
		return salaMapper.entidadADTOList(entidadList);
	}

}

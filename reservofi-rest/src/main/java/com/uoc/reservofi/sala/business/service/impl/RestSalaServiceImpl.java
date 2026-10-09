package com.uoc.reservofi.sala.business.service.impl;

import com.uoc.reservofi.base.business.service.impl.AbstractRestEntidadServiceImpl;
import com.uoc.reservofi.sala.business.dto.RestSalaDTO;
import com.uoc.reservofi.sala.business.dto.SalaDTO;
import com.uoc.reservofi.sala.business.mapper.RestSalaMapper;
import com.uoc.reservofi.sala.business.service.GestionSalaService;
import com.uoc.reservofi.sala.business.service.RestSalaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service(RestSalaService.BEAN_NAME)
@RequiredArgsConstructor
public class RestSalaServiceImpl extends AbstractRestEntidadServiceImpl<RestSalaDTO> implements RestSalaService {

	private final GestionSalaService gestionSalaService;
	private final RestSalaMapper restSalaMapper;

	@Override
	public RestSalaDTO insert(@Valid RestSalaDTO restDTO) {
		SalaDTO dto = restSalaMapper.restDTOADTO(restDTO);
		dto = gestionSalaService.insert(dto);
		return restSalaMapper.DTOArestDTO(dto);
	}

	@Override
	public RestSalaDTO update(@Valid RestSalaDTO restDTO) {
		SalaDTO dto = restSalaMapper.restDTOADTO(restDTO);
		dto = gestionSalaService.update(dto);
		return restSalaMapper.DTOArestDTO(dto);
	}

	@Override
	public void deleteById(Long id) {
		gestionSalaService.deleteById(id);
	}

	@Override
	public RestSalaDTO findById(Long id) {
		SalaDTO dto = gestionSalaService.findById(id);
		return restSalaMapper.DTOArestDTO(dto);
	}
}

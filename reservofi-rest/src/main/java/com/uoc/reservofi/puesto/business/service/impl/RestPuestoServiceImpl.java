package com.uoc.reservofi.puesto.business.service.impl;

import com.uoc.reservofi.base.business.service.impl.AbstractRestEntidadServiceImpl;
import com.uoc.reservofi.puesto.business.dto.PuestoDTO;
import com.uoc.reservofi.puesto.business.dto.RestPuestoConsultaDTO;
import com.uoc.reservofi.puesto.business.dto.RestPuestoGuardadoDTO;
import com.uoc.reservofi.puesto.business.mapper.RestPuestoMapper;
import com.uoc.reservofi.puesto.business.service.GestionPuestoService;
import com.uoc.reservofi.puesto.business.service.RestPuestoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(RestPuestoService.BEAN_NAME)
@RequiredArgsConstructor
public class RestPuestoServiceImpl extends AbstractRestEntidadServiceImpl<RestPuestoConsultaDTO, RestPuestoGuardadoDTO> implements RestPuestoService {

	private final GestionPuestoService gestionPuestoService;
	private final RestPuestoMapper restPuestoMapper;

	@Override
	public RestPuestoConsultaDTO insert(@Valid RestPuestoGuardadoDTO restDTO) {
		PuestoDTO dto = restPuestoMapper.restDTOADTO(restDTO);
		dto = gestionPuestoService.insert(dto);
		return restPuestoMapper.DTOArestDTO(dto);
	}

	@Override
	public RestPuestoConsultaDTO update(@Valid RestPuestoGuardadoDTO restDTO) {
		PuestoDTO dto = restPuestoMapper.restDTOADTO(restDTO);
		dto = gestionPuestoService.update(dto);
		return restPuestoMapper.DTOArestDTO(dto);
	}

	@Override
	public void deleteById(Long id) {
		gestionPuestoService.deleteById(id);
	}

	@Override
	public RestPuestoConsultaDTO findById(Long id) {
		PuestoDTO dto = gestionPuestoService.findById(id);
		return restPuestoMapper.DTOArestDTO(dto);
	}

	@Override
	public List<RestPuestoConsultaDTO> findAll() {
		List<PuestoDTO> dtoList = gestionPuestoService.findAll();
		return restPuestoMapper.DTOArestDTOList(dtoList);
	}
}

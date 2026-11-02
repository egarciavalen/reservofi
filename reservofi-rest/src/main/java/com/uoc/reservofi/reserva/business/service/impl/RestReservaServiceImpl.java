package com.uoc.reservofi.reserva.business.service.impl;

import com.uoc.reservofi.base.business.service.impl.AbstractRestEntidadServiceImpl;
import com.uoc.reservofi.reserva.business.dto.ReservaDTO;
import com.uoc.reservofi.reserva.business.dto.RestReservaDTO;
import com.uoc.reservofi.reserva.business.mapper.RestReservaMapper;
import com.uoc.reservofi.reserva.business.service.GestionReservaService;
import com.uoc.reservofi.reserva.business.service.RestReservaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(RestReservaService.BEAN_NAME)
@RequiredArgsConstructor
public class RestReservaServiceImpl extends AbstractRestEntidadServiceImpl<RestReservaDTO> implements RestReservaService {

	private final GestionReservaService gestionReservaService;
	private final RestReservaMapper restReservaMapper;

	@Override
	public RestReservaDTO insert(@Valid RestReservaDTO restDTO) {
		ReservaDTO dto = restReservaMapper.restDTOADTO(restDTO);
		dto = gestionReservaService.insert(dto);
		return restReservaMapper.DTOArestDTO(dto);
	}

	@Override
	public RestReservaDTO update(@Valid RestReservaDTO restDTO) {
		ReservaDTO dto = restReservaMapper.restDTOADTO(restDTO);
		dto = gestionReservaService.update(dto);
		return restReservaMapper.DTOArestDTO(dto);
	}

	@Override
	public void deleteById(Long id) {
		gestionReservaService.deleteById(id);
	}

	@Override
	public RestReservaDTO findById(Long id) {
		ReservaDTO dto = gestionReservaService.findById(id);
		return restReservaMapper.DTOArestDTO(dto);
	}

	@Override
	public List<RestReservaDTO> findAll() {
		List<ReservaDTO> dtoList = gestionReservaService.findAll();
		return restReservaMapper.DTOArestDTOList(dtoList);
	}

	@Override
	public List<RestReservaDTO> findByUsuarioId(Long idUsuario) {
		List<ReservaDTO> dtoList = gestionReservaService.findByUsuarioId(idUsuario);
		return restReservaMapper.DTOArestDTOList(dtoList);
	}
}

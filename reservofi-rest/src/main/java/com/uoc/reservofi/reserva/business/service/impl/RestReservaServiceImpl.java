package com.uoc.reservofi.reserva.business.service.impl;

import com.uoc.reservofi.base.business.service.impl.AbstractRestEntidadServiceImpl;
import com.uoc.reservofi.reserva.business.dto.ReservaDTO;
import com.uoc.reservofi.reserva.business.dto.RestReservaConsultaDTO;
import com.uoc.reservofi.reserva.business.dto.RestReservaGuardadoDTO;
import com.uoc.reservofi.reserva.business.mapper.RestReservaMapper;
import com.uoc.reservofi.reserva.business.service.GestionReservaService;
import com.uoc.reservofi.reserva.business.service.RestReservaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(RestReservaService.BEAN_NAME)
@RequiredArgsConstructor
public class RestReservaServiceImpl extends AbstractRestEntidadServiceImpl<RestReservaConsultaDTO> implements RestReservaService {

	private final GestionReservaService gestionReservaService;
	private final RestReservaMapper restReservaMapper;

	@Override
	public RestReservaConsultaDTO insert(@Valid RestReservaGuardadoDTO restDTO) {
		ReservaDTO dto = restReservaMapper.restDTOADTO(restDTO);
		dto = gestionReservaService.insert(dto);
		return restReservaMapper.DTOArestDTO(dto);
	}

	@Override
	public RestReservaConsultaDTO update(RestReservaGuardadoDTO restDTO, Long idSala) {
		// No tiene implementación por el momento
		return null;
	}

	@Override
	public void deleteById(Long id) {
		gestionReservaService.deleteById(id);
	}

	@Override
	public RestReservaConsultaDTO findById(Long id) {
		ReservaDTO dto = gestionReservaService.findById(id);
		return restReservaMapper.DTOArestDTO(dto);
	}

	@Override
	public List<RestReservaConsultaDTO> findAll() {
		List<ReservaDTO> dtoList = gestionReservaService.findAll();
		return restReservaMapper.DTOArestDTOList(dtoList);
	}

	@Override
	public List<RestReservaConsultaDTO> findByUsuarioId(Long idUsuario) {
		List<ReservaDTO> dtoList = gestionReservaService.findByUsuarioId(idUsuario);
		return restReservaMapper.DTOArestDTOList(dtoList);
	}
}

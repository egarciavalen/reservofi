package com.uoc.reservofi.reserva.business.service.impl;

import com.uoc.reservofi.base.busines.service.impl.GestionEntidadActualizableServiceImpl;
import com.uoc.reservofi.reserva.business.dto.ReservaDTO;
import com.uoc.reservofi.reserva.business.mapper.ReservaMapper;
import com.uoc.reservofi.reserva.business.model.Reserva;
import com.uoc.reservofi.reserva.business.service.GestionReservaService;
import com.uoc.reservofi.reserva.business.service.base.BaseReservaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(GestionReservaService.BEAN_NAME)
@RequiredArgsConstructor
public class GestionReservaServiceImpl extends GestionEntidadActualizableServiceImpl<ReservaDTO> implements GestionReservaService {

	private final BaseReservaService baseReservaService;
	private final ReservaMapper ReservaMapper;

	@Override
	public ReservaDTO insert(@Valid ReservaDTO dto) {
		Reserva entidad = ReservaMapper.DTOaEntidad(dto);
		entidad = baseReservaService.insert(entidad);
		return ReservaMapper.entidadADTO(entidad);
	}

	@Override
	public ReservaDTO update(@Valid ReservaDTO dto) {
		Reserva entidad = ReservaMapper.DTOaEntidad(dto);
		entidad = baseReservaService.update(entidad);
		return ReservaMapper.entidadADTO(entidad);
	}

	@Override
	public void deleteById(Long id) {
		baseReservaService.deleteById(id);
	}

	@Override
	public List<ReservaDTO> findAll() {
		List<Reserva> entidadList = baseReservaService.findAll();
		return ReservaMapper.entidadADTOList(entidadList);
	}

	@Override
	public ReservaDTO findById(Long id) {
		Reserva entidad = baseReservaService.findById(id);
		return ReservaMapper.entidadADTO(entidad);
	}

	@Override
	public boolean existsById(Long id) {
		return baseReservaService.existsById(id);
	}

	@Override
	public List<ReservaDTO> findByCreacionUsuario(String usuario) {
		List<Reserva> entidadList = baseReservaService.findByCreacionUsuario(usuario);
		return ReservaMapper.entidadADTOList(entidadList);
	}

	@Override
	public List<ReservaDTO> findByActualizacionUsuario(String usuario) {
		List<Reserva> entidadList = baseReservaService.findByActualizacionUsuario(usuario);
		return ReservaMapper.entidadADTOList(entidadList);
	}

}

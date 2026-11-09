package com.uoc.reservofi.reserva.business.service;

import com.uoc.reservofi.base.business.service.RestEntidadService;
import com.uoc.reservofi.reserva.business.dto.RestReservaConsultaDTO;
import com.uoc.reservofi.reserva.business.dto.RestReservaGuardadoDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface RestReservaService extends RestEntidadService<RestReservaConsultaDTO> {

	String BEAN_NAME = "restReservaService";

	RestReservaConsultaDTO insert(@Valid RestReservaGuardadoDTO restDTO);

	RestReservaConsultaDTO update(@Valid RestReservaGuardadoDTO restDTO, Long idSala);

	List<RestReservaConsultaDTO> findByUsuarioId(Long idUsuario);
}

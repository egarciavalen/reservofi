package com.uoc.reservofi.reserva.business.service;

import com.uoc.reservofi.base.business.service.RestEntidadService;
import com.uoc.reservofi.reserva.business.dto.RestReservaDTO;

import java.util.List;

public interface RestReservaService extends RestEntidadService<RestReservaDTO> {

	String BEAN_NAME = "restReservaService";

	List<RestReservaDTO> findByUsuarioId(Long idUsuario);
}

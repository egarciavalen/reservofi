package com.uoc.reservofi.reserva.business.service;

import com.uoc.reservofi.base.busines.service.GestionEntidadService;
import com.uoc.reservofi.reserva.business.dto.ReservaDTO;

public interface GestionReservaService extends GestionEntidadService<ReservaDTO> {

	String BEAN_NAME = "gestionReservaService";
}

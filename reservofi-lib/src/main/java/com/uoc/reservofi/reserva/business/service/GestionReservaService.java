package com.uoc.reservofi.reserva.business.service;

import com.uoc.reservofi.base.busines.service.GestionEntidadActualizableService;
import com.uoc.reservofi.reserva.business.dto.ReservaDTO;

public interface GestionReservaService extends GestionEntidadActualizableService<ReservaDTO> {

	String BEAN_NAME = "gestionReservaService";
}

package com.uoc.reservofi.sala.business.service;

import com.uoc.reservofi.base.busines.service.GestionEntidadActualizableService;
import com.uoc.reservofi.sala.business.dto.SalaDTO;

public interface GestionSalaService extends GestionEntidadActualizableService<SalaDTO> {

	String BEAN_NAME = "gestionSalaService";
}

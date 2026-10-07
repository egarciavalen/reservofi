package com.uoc.reservofi.sala.business.service;

import com.uoc.reservofi.base.busines.service.GestionEntidadService;
import com.uoc.reservofi.sala.business.dto.SalaDTO;

public interface GestionSalaService extends GestionEntidadService<SalaDTO> {

	String BEAN_NAME = "gestionSalaService";
}

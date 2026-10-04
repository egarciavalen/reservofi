package com.uoc.reservofi.puesto.business.service;

import com.uoc.reservofi.base.busines.service.GestionEntidadActualizableService;
import com.uoc.reservofi.puesto.business.dto.PuestoDTO;

public interface GestionPuestoService extends GestionEntidadActualizableService<PuestoDTO> {

	String BEAN_NAME = "gestionPuestoService";
}

package com.uoc.reservofi.puesto.business.service;

import com.uoc.reservofi.base.busines.service.GestionEntidadService;
import com.uoc.reservofi.puesto.business.dto.PuestoDTO;

public interface GestionPuestoService extends GestionEntidadService<PuestoDTO> {

	String BEAN_NAME = "gestionPuestoService";
}

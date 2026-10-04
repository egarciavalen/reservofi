package com.uoc.reservofi.rol.business.service;

import com.uoc.reservofi.base.busines.service.GestionEntidadActualizableService;
import com.uoc.reservofi.rol.business.dto.RolDTO;

public interface GestionRolService extends GestionEntidadActualizableService<RolDTO> {

	String BEAN_NAME = "gestionRolService";
}

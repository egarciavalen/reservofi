package com.uoc.reservofi.rol.service;

import com.uoc.reservofi.base.busines.service.GestionEntidadActualizableService;
import com.uoc.reservofi.rol.dto.RolDTO;

public interface GestionRolService extends GestionEntidadActualizableService<RolDTO> {

	String BEAN_NAME = "gestionRolService";
}

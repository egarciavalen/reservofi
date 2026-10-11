package com.uoc.reservofi.rol.business.service;

import com.uoc.reservofi.base.busines.service.GestionEntidadService;
import com.uoc.reservofi.rol.business.dto.RolDTO;

public interface GestionRolService extends GestionEntidadService<RolDTO> {

	String BEAN_NAME = "gestionRolService";

	Long getIdByNombre(String nombre);
}

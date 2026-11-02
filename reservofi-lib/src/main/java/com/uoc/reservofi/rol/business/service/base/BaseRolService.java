package com.uoc.reservofi.rol.business.service.base;

import com.uoc.reservofi.base.busines.service.base.BaseEntidadService;
import com.uoc.reservofi.rol.business.model.Rol;

public interface BaseRolService extends BaseEntidadService<Rol> {

	String BEAN_NAME = "baseRolService";

	Long getIdByNombre(String nombre);
}

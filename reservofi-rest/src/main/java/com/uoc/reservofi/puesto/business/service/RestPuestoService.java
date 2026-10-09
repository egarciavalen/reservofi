package com.uoc.reservofi.puesto.business.service;

import com.uoc.reservofi.base.business.service.RestEntidadService;
import com.uoc.reservofi.puesto.business.dto.RestPuestoDTO;

public interface RestPuestoService extends RestEntidadService<RestPuestoDTO> {

	String BEAN_NAME = "restPuestoService";
}

package com.uoc.reservofi.puesto.business.service;

import com.uoc.reservofi.base.business.service.RestEntidadService;
import com.uoc.reservofi.puesto.business.dto.RestPuestoConsultaDTO;
import com.uoc.reservofi.puesto.business.dto.RestPuestoGuardadoDTO;

public interface RestPuestoService extends RestEntidadService<RestPuestoConsultaDTO, RestPuestoGuardadoDTO> {

	String BEAN_NAME = "restPuestoService";
}

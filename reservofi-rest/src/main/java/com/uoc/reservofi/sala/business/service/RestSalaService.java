package com.uoc.reservofi.sala.business.service;

import com.uoc.reservofi.base.business.service.RestEntidadService;
import com.uoc.reservofi.sala.business.dto.RestSalaConsultaDTO;
import com.uoc.reservofi.sala.business.dto.RestSalaGuardadoDTO;

public interface RestSalaService extends RestEntidadService<RestSalaConsultaDTO, RestSalaGuardadoDTO> {

	String BEAN_NAME = "restSalaService";

}

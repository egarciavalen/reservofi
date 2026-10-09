package com.uoc.reservofi.sala.business.service;

import com.uoc.reservofi.base.business.service.RestEntidadService;
import com.uoc.reservofi.sala.business.dto.RestSalaDTO;

public interface RestSalaService extends RestEntidadService<RestSalaDTO> {

	String BEAN_NAME = "restSalaService";
}

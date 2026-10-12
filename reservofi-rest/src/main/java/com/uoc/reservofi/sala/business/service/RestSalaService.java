package com.uoc.reservofi.sala.business.service;

import com.uoc.reservofi.base.business.service.RestEntidadService;
import com.uoc.reservofi.sala.business.dto.RestSalaConsultaDTO;
import com.uoc.reservofi.sala.business.dto.RestSalaCreacionDTO;
import jakarta.validation.Valid;

public interface RestSalaService extends RestEntidadService<RestSalaConsultaDTO> {

	String BEAN_NAME = "restSalaService";

	// Consulta propia al trabajar con diferentes DTOs

	RestSalaConsultaDTO create(@Valid RestSalaCreacionDTO restDTO);

}

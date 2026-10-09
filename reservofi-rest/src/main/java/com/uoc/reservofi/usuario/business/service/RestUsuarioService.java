package com.uoc.reservofi.usuario.business.service;

import com.uoc.reservofi.base.business.service.RestEntidadService;
import com.uoc.reservofi.usuario.business.dto.RestUsuarioActualizacionDTO;
import com.uoc.reservofi.usuario.business.dto.RestUsuarioConsultaDTO;
import jakarta.validation.Valid;

public interface RestUsuarioService extends RestEntidadService<RestUsuarioConsultaDTO> {

	String BEAN_NAME = "restUsuarioService";

	// Consulta propia al trabajar con diferentes DTOs
	RestUsuarioConsultaDTO update(@Valid RestUsuarioActualizacionDTO restDTO);
}

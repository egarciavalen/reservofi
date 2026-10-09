package com.uoc.reservofi.usuario.business.service;

import com.uoc.reservofi.base.business.service.RestEntidadService;
import com.uoc.reservofi.usuario.business.dto.RestUsuarioDTO;

public interface RestUsuarioService extends RestEntidadService<RestUsuarioDTO> {

	String BEAN_NAME = "restUsuarioService";
}

package com.uoc.reservofi.rol.business.service;

import com.uoc.reservofi.base.business.service.RestEntidadService;
import com.uoc.reservofi.rol.business.dto.RestRolConsultaDTO;
import com.uoc.reservofi.rol.business.dto.RestRolGuardadoDTO;

public interface RestRolService extends RestEntidadService<RestRolConsultaDTO, RestRolGuardadoDTO> {

	String BEAN_NAME = "restRolService";
}

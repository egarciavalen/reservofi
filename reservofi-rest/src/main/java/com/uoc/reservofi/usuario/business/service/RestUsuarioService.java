package com.uoc.reservofi.usuario.business.service;

import com.uoc.reservofi.base.business.service.RestEntidadService;
import com.uoc.reservofi.usuario.business.dto.RestUsuarioConsultaDTO;
import com.uoc.reservofi.usuario.business.dto.RestUsuarioGuardadoDTO;

public interface RestUsuarioService extends RestEntidadService<RestUsuarioConsultaDTO, RestUsuarioGuardadoDTO> {

	String BEAN_NAME = "restUsuarioService";

	RestUsuarioConsultaDTO toggleActivo(Long idUsuario);

	RestUsuarioConsultaDTO toggleAdmin(Long idUsuario);

	boolean checkAdmin(Long idUsuario);
}

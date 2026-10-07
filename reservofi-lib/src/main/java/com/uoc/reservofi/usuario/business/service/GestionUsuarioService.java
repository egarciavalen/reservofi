package com.uoc.reservofi.usuario.business.service;

import com.uoc.reservofi.base.busines.service.GestionEntidadService;
import com.uoc.reservofi.usuario.business.dto.UsuarioDTO;

public interface GestionUsuarioService extends GestionEntidadService<UsuarioDTO> {

	String BEAN_NAME = "gestionUsuarioService";
}

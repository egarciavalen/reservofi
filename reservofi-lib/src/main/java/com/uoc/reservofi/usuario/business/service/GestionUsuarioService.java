package com.uoc.reservofi.usuario.business.service;

import com.uoc.reservofi.base.busines.service.GestionEntidadActualizableService;
import com.uoc.reservofi.usuario.business.dto.UsuarioDTO;

public interface GestionUsuarioService extends GestionEntidadActualizableService<UsuarioDTO> {

	String BEAN_NAME = "gestionUsuarioService";
}

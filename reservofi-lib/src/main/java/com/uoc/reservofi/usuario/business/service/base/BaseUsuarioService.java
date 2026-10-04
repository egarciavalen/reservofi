package com.uoc.reservofi.usuario.business.service.base;

import com.uoc.reservofi.base.busines.service.base.BaseEntidadActualizableService;
import com.uoc.reservofi.usuario.business.model.Usuario;

public interface BaseUsuarioService extends BaseEntidadActualizableService<Usuario> {

	String BEAN_NAME = "baseUsuarioService";
}

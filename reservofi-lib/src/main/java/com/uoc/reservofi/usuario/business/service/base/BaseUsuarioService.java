package com.uoc.reservofi.usuario.business.service.base;

import com.uoc.reservofi.base.busines.service.base.BaseEntidadService;
import com.uoc.reservofi.usuario.business.model.Usuario;

public interface BaseUsuarioService extends BaseEntidadService<Usuario> {

	String BEAN_NAME = "baseUsuarioService";

	Usuario findByCorreoCorporativo(String correoCorporativo);
}

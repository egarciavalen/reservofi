package com.uoc.reservofi.base.busines.service.impl;

import com.uoc.reservofi.base.busines.dto.GestionEntidadActualizableDTO;
import com.uoc.reservofi.base.busines.exception.GestionException;
import com.uoc.reservofi.base.busines.service.GestionEntidadActualizableService;

import java.util.List;

public class GestionEntidadActualizableServiceImpl<T extends GestionEntidadActualizableDTO> extends GestionEntidadServiceImpl<T> implements GestionEntidadActualizableService<T> {

	@Override
	public List<T> findByCreacionUsuario(String usuario) {
		throw new GestionException("Operación no implementada");
	}

	@Override
	public List<T> findByActualizacionUsuario(String usuario) {
		throw new GestionException("Operación no implementada");
	}
}

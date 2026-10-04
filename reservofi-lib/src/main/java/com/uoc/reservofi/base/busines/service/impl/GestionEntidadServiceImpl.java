package com.uoc.reservofi.base.busines.service.impl;

import com.uoc.reservofi.base.busines.dto.GestionEntidadDTO;
import com.uoc.reservofi.base.busines.exception.GestionException;
import com.uoc.reservofi.base.busines.service.GestionEntidadService;

import java.util.List;

public abstract class GestionEntidadServiceImpl<T extends GestionEntidadDTO> implements GestionEntidadService<T> {

	@Override
	public T insert(T entidad) {
		throw new GestionException("Operación no implementada");
	}

	@Override
	public T update(T entidad) {
		throw new GestionException("Operación no implementada");
	}

	@Override
	public void deleteById(Long id) {
		throw new GestionException("Operación no implementada");
	}

	@Override
	public List<T> findAll() {
		throw new GestionException("Operación no implementada");
	}

	@Override
	public T findById(Long id) {
		throw new GestionException("Operación no implementada");
	}

	@Override
	public boolean existsById(Long id) {
		throw new GestionException("Operación no implementada");
	}
}

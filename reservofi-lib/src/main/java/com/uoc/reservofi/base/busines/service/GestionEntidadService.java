package com.uoc.reservofi.base.busines.service;

import com.uoc.reservofi.base.busines.dto.GestionEntidadDTO;

import java.util.List;

public interface GestionEntidadService<T extends GestionEntidadDTO> {

	T insert(T entidad);

	T update(T entidad);

	void deleteById(Long id);

	List<T> findAll();

	T findById(Long id);

	boolean existsById(Long id);

}

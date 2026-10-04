package com.uoc.reservofi.base.busines.service.base;

import com.uoc.reservofi.base.busines.model.BaseEntidad;

import java.util.List;

public interface BaseEntidadService<T extends BaseEntidad> {

	T insert(T entidad);

	T update(T entidad);

	void deleteById(Long id);

	List<T> findAll();

	T findById(Long id);

	boolean existsById(Long id);

}

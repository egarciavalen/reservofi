package com.uoc.reservofi.base.business.service;

import com.uoc.reservofi.base.business.dto.RestEntidadDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface RestEntidadService<T extends RestEntidadDTO> {

	T insert(@Valid T entidad);

	T update(@Valid T entidad);

	void deleteById(Long id);

	T findById(Long id);

	List<T> findAll();
}

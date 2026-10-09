package com.uoc.reservofi.base.business.service.impl;

import com.uoc.reservofi.base.business.dto.RestEntidadDTO;
import com.uoc.reservofi.base.business.exception.RestException;
import com.uoc.reservofi.base.business.service.RestEntidadService;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

@Validated // Validaciones @Valid
public abstract class AbstractRestEntidadServiceImpl<T extends RestEntidadDTO> implements RestEntidadService<T> {

	// Las clases hijas deciden qué métodos van a implementar

	@Override
	public T insert(@Valid T entidad) {
		throw new RestException("Operación no implementada");
	}

	@Override
	public T update(@Valid T entidad) {
		throw new RestException("Operación no implementada");
	}

	@Override
	public void deleteById(Long id) {
		throw new RestException("Operación no implementada");
	}

	@Override
	public T findById(Long id) {
		throw new RestException("Operación no implementada");
	}

}

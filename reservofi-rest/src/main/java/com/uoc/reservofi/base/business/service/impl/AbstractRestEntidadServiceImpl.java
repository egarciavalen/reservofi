package com.uoc.reservofi.base.business.service.impl;

import com.uoc.reservofi.base.business.dto.RestEntidadConsultaDTO;
import com.uoc.reservofi.base.business.dto.RestEntidadGuardadoDTO;
import com.uoc.reservofi.base.business.exception.RestException;
import com.uoc.reservofi.base.business.service.RestEntidadService;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated // Validaciones @Valid
public abstract class AbstractRestEntidadServiceImpl<T extends RestEntidadConsultaDTO, D extends RestEntidadGuardadoDTO> implements RestEntidadService<T, D> {

	// Las clases hijas deciden qué métodos van a implementar

	@Override
	public T insert(@Valid D entidad) {
		throw new RestException("Operación no implementada");
	}

	@Override
	public T update(@Valid D entidad) {
		throw new RestException("Operación no implementada");
	}

	@Override
	public T update(Long id, @Valid D entidad) {
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

	@Override
	public List<T> findAll() {
		throw new RestException("Operación no implementada");
	}

}

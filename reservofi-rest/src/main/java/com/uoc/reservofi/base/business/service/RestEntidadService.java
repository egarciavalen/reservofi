package com.uoc.reservofi.base.business.service;

import com.uoc.reservofi.base.business.dto.RestEntidadConsultaDTO;
import com.uoc.reservofi.base.business.dto.RestEntidadGuardadoDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface RestEntidadService<T extends RestEntidadConsultaDTO, D extends RestEntidadGuardadoDTO> {

	T insert(@Valid D entidad);

	T update(@Valid D entidad);

	T update(Long id, @Valid D entidad);

	void deleteById(Long id);

	T findById(Long id);

	List<T> findAll();
}

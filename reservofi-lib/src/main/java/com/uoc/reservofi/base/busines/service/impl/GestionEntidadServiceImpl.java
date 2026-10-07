package com.uoc.reservofi.base.busines.service.impl;

import com.uoc.reservofi.base.busines.dto.GestionEntidadDTO;
import com.uoc.reservofi.base.busines.exception.GestionException;
import com.uoc.reservofi.base.busines.service.GestionEntidadService;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated // Validaciones @Valid
public abstract class GestionEntidadServiceImpl<T extends GestionEntidadDTO> implements GestionEntidadService<T> {

	// Las clases hijas deciden qué metodos van a implementar

	@Override
	public T insert(@Valid T entidad) {
		throw new GestionException("Operación no implementada");
	}

	@Override
	public T update(@Valid T entidad) {
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
	public List<T> findAllById(List<Long> ids) {
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

	@Override
	public List<T> findByCreacionUsuario(String usuario) {
		throw new GestionException("Operación no implementada");
	}

	@Override
	public List<T> findByActualizacionUsuario(String usuario) {
		throw new GestionException("Operación no implementada");
	}

}

package com.uoc.reservofi.base.busines.service;

import com.uoc.reservofi.base.busines.dto.GestionEntidadDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface GestionEntidadService<T extends GestionEntidadDTO> {

	T insert(@Valid T entidad);

	T update(@Valid T entidad);

	void deleteById(Long id);

	List<T> findAll();

	T findById(Long id);

	List<T> findAllById(List<Long> ids);

	boolean existsById(Long id);

	List<T> findByCreacionUsuario(String usuario);

	List<T> findByActualizacionUsuario (String usuario);

}

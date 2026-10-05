package com.uoc.reservofi.base.busines.service.base.impl;

import com.uoc.reservofi.base.busines.exception.GestionException;
import com.uoc.reservofi.base.busines.model.BaseEntidadActualizable;
import com.uoc.reservofi.base.busines.repository.BaseEntidadActualizableRepository;
import com.uoc.reservofi.base.busines.service.base.BaseEntidadActualizableService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public abstract class BaseEntidadActualizableServiceImpl<T extends BaseEntidadActualizable> extends BaseEntidadServiceImpl<T> implements BaseEntidadActualizableService<T> {

	@Override
	@Transactional(readOnly = true)
	public List<T> findByCreacionUsuario(String usuario) {
		return ((BaseEntidadActualizableRepository<T>) getRepository()).findByCreacionUsuario(usuario);
	}

	@Override
	@Transactional(readOnly = true)
	public List<T> findByActualizacionUsuario(String usuario) {
		return ((BaseEntidadActualizableRepository<T>) getRepository()).findByActualizacionUsuario(usuario);
	}

	@Override
	public T update(T entidad) {

		// La idea es que desde los servicios de gestión se rellene la información de la entidad previo a un findById
		// Si NO fue el caso, se corta aquí el proceso porque fallará al hacer el save en base de datos

		if (entidad != null && entidad.getCreacionUsuario() == null) {
			throw new GestionException("Datos de auditoría no registrados: usuario creador no existente");
		}

		if (entidad != null && entidad.getCreacionFecha() == null) {
			throw new GestionException("Datos de auditoría no registrados: fecha de creación no existente");
		}

		return super.update(entidad);
	}

}

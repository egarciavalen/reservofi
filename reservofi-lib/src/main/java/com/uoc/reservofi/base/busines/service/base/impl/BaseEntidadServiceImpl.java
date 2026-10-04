package com.uoc.reservofi.base.busines.service.base.impl;

import com.uoc.reservofi.base.busines.exception.BaseException;
import com.uoc.reservofi.base.busines.model.BaseEntidad;
import com.uoc.reservofi.base.busines.repository.BaseEntidadRepository;
import com.uoc.reservofi.base.busines.service.base.BaseEntidadService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor // Evita tener que escribir @Autowired directamente
public abstract class BaseEntidadServiceImpl<T extends BaseEntidad> implements BaseEntidadService<T> {

	// Si fuera necesario indicar más métodos comunes a todas las entidades, indicarlos aquí

	// Las clases hijas serán las responsables de asignar el repositorio
	public abstract BaseEntidadRepository<T> getRepository();

	@Transactional
	public T insert(T entidad) {
		if (entidad == null) {
			throw new BaseException("Entidad no existente");
		}
		if (entidad.getId() != null) {
			throw new BaseException("No puede tener identificador asociado");
		}

		return getRepository().save(entidad);
	}

	@Transactional
	public T update(T entidad) {
		if (entidad == null) {
			throw new BaseException("Entidad no existente");
		}
		if (entidad.getId() == null) {
			throw new BaseException("Ha de tener un identificador asociado");
		}

		if (this.findById(entidad.getId()) == null) {
			throw new BaseException("La entidad ha de existir en base de datos");
		}
		return getRepository().save(entidad);
	}

	@Transactional
	public void deleteById(Long id) {
		this.getRepository().deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public T findById(Long id) {
		return getRepository().findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public List<T> findAll() {
		return getRepository().findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<T> findAllById(List<Long> ids) {
		return getRepository().findAllById(ids);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean existsById(Long id) {
		return getRepository().existsById(id);
	}

}

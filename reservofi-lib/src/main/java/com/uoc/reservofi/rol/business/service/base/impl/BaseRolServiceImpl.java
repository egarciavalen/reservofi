package com.uoc.reservofi.rol.business.service.base.impl;

import com.uoc.reservofi.base.busines.exception.BaseException;
import com.uoc.reservofi.base.busines.repository.BaseEntidadRepository;
import com.uoc.reservofi.base.busines.service.base.impl.AbstractBaseEntidadServiceImpl;
import com.uoc.reservofi.rol.business.model.Rol;
import com.uoc.reservofi.rol.business.repository.RolRepository;
import com.uoc.reservofi.rol.business.service.base.BaseRolService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(BaseRolService.BEAN_NAME)
@RequiredArgsConstructor
@Transactional(rollbackFor = BaseException.class)
public class BaseRolServiceImpl extends AbstractBaseEntidadServiceImpl<Rol> implements BaseRolService {

	private final RolRepository rolRepository;

	@Override
	public BaseEntidadRepository<Rol> getRepository() {
		return rolRepository;
	}

	@Override
	public Long getIdByNombre(String nombre) {
		return ((RolRepository) getRepository()).getIdByNombre(nombre);
	}
}

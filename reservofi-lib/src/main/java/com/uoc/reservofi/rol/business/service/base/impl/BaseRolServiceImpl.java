package com.uoc.reservofi.rol.business.service.base.impl;

import com.uoc.reservofi.base.busines.repository.BaseEntidadRepository;
import com.uoc.reservofi.base.busines.service.base.impl.BaseEntidadServiceImpl;
import com.uoc.reservofi.rol.business.model.Rol;
import com.uoc.reservofi.rol.business.repository.RolRepository;
import com.uoc.reservofi.rol.business.service.base.BaseRolService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service(BaseRolService.BEAN_NAME)
@RequiredArgsConstructor
public class BaseRolServiceImpl extends BaseEntidadServiceImpl<Rol> implements BaseRolService {

	private final RolRepository rolRepository;

	@Override
	public BaseEntidadRepository<Rol> getRepository() {
		return rolRepository;
	}
}

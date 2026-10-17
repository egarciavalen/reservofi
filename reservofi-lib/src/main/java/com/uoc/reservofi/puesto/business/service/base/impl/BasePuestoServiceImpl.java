package com.uoc.reservofi.puesto.business.service.base.impl;

import com.uoc.reservofi.base.busines.exception.BaseException;
import com.uoc.reservofi.base.busines.repository.BaseEntidadRepository;
import com.uoc.reservofi.base.busines.service.base.impl.AbstractBaseEntidadServiceImpl;
import com.uoc.reservofi.puesto.business.model.Puesto;
import com.uoc.reservofi.puesto.business.repository.PuestoRepository;
import com.uoc.reservofi.puesto.business.service.base.BasePuestoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(BasePuestoService.BEAN_NAME)
@RequiredArgsConstructor
@Transactional(rollbackFor = BaseException.class)
public class BasePuestoServiceImpl extends AbstractBaseEntidadServiceImpl<Puesto> implements BasePuestoService {

	private final PuestoRepository puestoRepository;

	@Override
	public BaseEntidadRepository<Puesto> getRepository() {
		return puestoRepository;
	}
}

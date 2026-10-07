package com.uoc.reservofi.puesto.business.service.base.impl;

import com.uoc.reservofi.base.busines.repository.BaseEntidadRepository;
import com.uoc.reservofi.base.busines.service.base.impl.BaseEntidadServiceImpl;
import com.uoc.reservofi.puesto.business.model.Puesto;
import com.uoc.reservofi.puesto.business.repository.PuestoRepository;
import com.uoc.reservofi.puesto.business.service.base.BasePuestoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service(BasePuestoService.BEAN_NAME)
@RequiredArgsConstructor
public class BasePuestoServiceImpl extends BaseEntidadServiceImpl<Puesto> implements BasePuestoService {

	private final PuestoRepository PuestoRepository;

	@Override
	public BaseEntidadRepository<Puesto> getRepository() {
		return PuestoRepository;
	}
}

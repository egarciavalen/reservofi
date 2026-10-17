package com.uoc.reservofi.sala.business.service.base.impl;

import com.uoc.reservofi.base.busines.exception.BaseException;
import com.uoc.reservofi.base.busines.repository.BaseEntidadRepository;
import com.uoc.reservofi.base.busines.service.base.impl.AbstractBaseEntidadServiceImpl;
import com.uoc.reservofi.sala.business.model.Sala;
import com.uoc.reservofi.sala.business.repository.SalaRepository;
import com.uoc.reservofi.sala.business.service.base.BaseSalaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(BaseSalaService.BEAN_NAME)
@RequiredArgsConstructor
@Transactional(rollbackFor = BaseException.class)
public class BaseSalaServiceImpl extends AbstractBaseEntidadServiceImpl<Sala> implements BaseSalaService {

	private final SalaRepository salaRepository;

	@Override
	public BaseEntidadRepository<Sala> getRepository() {
		return salaRepository;
	}
}

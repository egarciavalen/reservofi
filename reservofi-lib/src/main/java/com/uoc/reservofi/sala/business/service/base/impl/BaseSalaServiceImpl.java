package com.uoc.reservofi.sala.business.service.base.impl;

import com.uoc.reservofi.base.busines.repository.BaseEntidadRepository;
import com.uoc.reservofi.base.busines.service.base.impl.BaseEntidadServiceImpl;
import com.uoc.reservofi.sala.business.model.Sala;
import com.uoc.reservofi.sala.business.repository.SalaRepository;
import com.uoc.reservofi.sala.business.service.base.BaseSalaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service(BaseSalaService.BEAN_NAME)
@RequiredArgsConstructor
public class BaseSalaServiceImpl extends BaseEntidadServiceImpl<Sala> implements BaseSalaService {

	private final SalaRepository SalaRepository;

	@Override
	public BaseEntidadRepository<Sala> getRepository() {
		return SalaRepository;
	}
}

package com.uoc.reservofi.reserva.business.service.base.impl;

import com.uoc.reservofi.base.busines.exception.BaseException;
import com.uoc.reservofi.base.busines.repository.BaseEntidadRepository;
import com.uoc.reservofi.base.busines.service.base.impl.AbstractBaseEntidadServiceImpl;
import com.uoc.reservofi.reserva.business.model.Reserva;
import com.uoc.reservofi.reserva.business.repository.ReservaRepository;
import com.uoc.reservofi.reserva.business.service.base.BaseReservaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(BaseReservaService.BEAN_NAME)
@RequiredArgsConstructor
@Transactional(rollbackFor = BaseException.class)
public class BaseReservaServiceImpl extends AbstractBaseEntidadServiceImpl<Reserva> implements BaseReservaService {

	private final ReservaRepository ReservaRepository;

	@Override
	public BaseEntidadRepository<Reserva> getRepository() {
		return ReservaRepository;
	}
}

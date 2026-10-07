package com.uoc.reservofi.reserva.business.service.base.impl;

import com.uoc.reservofi.base.busines.repository.BaseEntidadRepository;
import com.uoc.reservofi.base.busines.service.base.impl.BaseEntidadServiceImpl;
import com.uoc.reservofi.reserva.business.model.Reserva;
import com.uoc.reservofi.reserva.business.repository.ReservaRepository;
import com.uoc.reservofi.reserva.business.service.base.BaseReservaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service(BaseReservaService.BEAN_NAME)
@RequiredArgsConstructor
public class BaseReservaServiceImpl extends BaseEntidadServiceImpl<Reserva> implements BaseReservaService {

	private final ReservaRepository ReservaRepository;

	@Override
	public BaseEntidadRepository<Reserva> getRepository() {
		return ReservaRepository;
	}
}

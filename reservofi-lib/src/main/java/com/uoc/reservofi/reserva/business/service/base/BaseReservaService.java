package com.uoc.reservofi.reserva.business.service.base;

import com.uoc.reservofi.base.busines.service.base.BaseEntidadService;
import com.uoc.reservofi.reserva.business.model.Reserva;

import java.util.List;

public interface BaseReservaService extends BaseEntidadService<Reserva> {

	String BEAN_NAME = "baseReservaService";

	List<Reserva> findByUsuarioId(Long idUsuario);

	List<Reserva> findByPuestoId(Long idPuesto);

}

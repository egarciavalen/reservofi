package com.uoc.reservofi.puesto.business.service.base;

import com.uoc.reservofi.base.busines.service.base.BaseEntidadService;
import com.uoc.reservofi.puesto.business.model.Puesto;

import java.util.List;

public interface BasePuestoService extends BaseEntidadService<Puesto> {

	String BEAN_NAME = "basePuestoService";

	List<Puesto> findBySalaId(Long idSala);
}

package com.uoc.reservofi.base.busines.service.impl;

import com.uoc.reservofi.base.busines.dto.GestionEntidadActualizableDTO;
import com.uoc.reservofi.base.busines.dto.GestionEntidadDTO;
import com.uoc.reservofi.base.busines.exception.BaseException;
import com.uoc.reservofi.base.busines.model.BaseEntidad;
import com.uoc.reservofi.base.busines.model.BaseEntidadActualizable;
import com.uoc.reservofi.base.busines.service.GestionEntidadActualizableService;
import com.uoc.reservofi.base.busines.service.GestionEntidadService;
import com.uoc.reservofi.base.busines.service.base.BaseEntidadService;
import org.springframework.transaction.annotation.Transactional;

public abstract class GestionEntidadActualizableServiceImpl<T extends GestionEntidadActualizableDTO, E extends BaseEntidadActualizable> extends GestionEntidadServiceImpl<T, E> implements GestionEntidadActualizableService<T> {

}

package com.uoc.reservofi.base.busines.service.impl;

import com.uoc.reservofi.base.busines.dto.GestionEntidadDTO;
import com.uoc.reservofi.base.busines.exception.BaseException;
import com.uoc.reservofi.base.busines.model.BaseEntidad;
import com.uoc.reservofi.base.busines.repository.BaseEntidadRepository;
import com.uoc.reservofi.base.busines.service.GestionEntidadService;
import com.uoc.reservofi.base.busines.service.base.BaseEntidadService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public abstract class GestionEntidadServiceImpl<T extends GestionEntidadDTO, E extends BaseEntidad> implements GestionEntidadService<T> {

}

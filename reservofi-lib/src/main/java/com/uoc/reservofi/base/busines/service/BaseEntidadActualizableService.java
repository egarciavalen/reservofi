package com.uoc.reservofi.base.busines.service;

import com.uoc.reservofi.base.busines.model.BaseEntidadActualizable;

import java.util.List;

public interface BaseEntidadActualizableService<T extends BaseEntidadActualizable> extends BaseEntidadService<T> {

    List<T> findByCreacionUsuario(String usuario);
    List<T> findByActualizacionUsuario (String usuario);

}

package com.uoc.reservofi.base.busines.service;

import com.uoc.reservofi.base.busines.dto.GestionEntidadActualizableDTO;

import java.util.List;

public interface GestionEntidadActualizableService<T extends GestionEntidadActualizableDTO> extends GestionEntidadService<T>{

    List<T> findByCreacionUsuario(String usuario);
    List<T> findByActualizacionUsuario (String usuario);

}

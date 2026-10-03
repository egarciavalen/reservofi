package com.uoc.reservofi.base.busines.service;

import com.uoc.reservofi.base.busines.dto.GestionEntidadActualizableDTO;
import com.uoc.reservofi.base.busines.dto.GestionEntidadDTO;

import java.util.List;
import java.util.Optional;

public interface GestionEntidadActualizableService<T extends GestionEntidadActualizableDTO> extends GestionEntidadService<T>{

    List<T> findByCreacionUsuario(String usuario);
    List<T> findByActualizacionUsuario (String usuario);

}

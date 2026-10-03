package com.uoc.reservofi.base.busines.service;

import com.uoc.reservofi.base.busines.dto.GestionEntidadDTO;

import java.util.List;
import java.util.Optional;

public interface GestionEntidadService <T extends GestionEntidadDTO> {

    T insert(T entidad);
    T update(T entidad);
    void deleteById(Long id);
    List<T> findAll();
    Optional<T> findById(Long id);
    boolean existsById(Long id);

}

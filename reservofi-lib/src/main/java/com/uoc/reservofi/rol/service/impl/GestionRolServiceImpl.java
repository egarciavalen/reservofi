package com.uoc.reservofi.rol.service.impl;

import com.uoc.reservofi.base.busines.service.impl.GestionEntidadActualizableServiceImpl;
import com.uoc.reservofi.rol.dto.RolDTO;
import com.uoc.reservofi.rol.model.Rol;
import com.uoc.reservofi.rol.service.GestionRolService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service(GestionRolService.BEAN_NAME)
public class GestionRolServiceImpl extends GestionEntidadActualizableServiceImpl<RolDTO, Rol> implements GestionRolService {

    // TODO: DETERMINAR QUE VAS A SOBREESCRIBIR Y QUE VAS A MANTENER

    @Override
    public List<RolDTO> findByCreacionUsuario(String usuario) {
        return List.of();
    }

    @Override
    public List<RolDTO> findByActualizacionUsuario(String usuario) {
        return List.of();
    }

    @Override
    public RolDTO insert(RolDTO entidad) {
        return null;
    }

    @Override
    public RolDTO update(RolDTO entidad) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<RolDTO> findAll() {
        return List.of();
    }

    @Override
    public Optional<RolDTO> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long id) {
        return false;
    }
}

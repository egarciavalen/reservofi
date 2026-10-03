package com.uoc.reservofi.base.busines.service.impl;

import com.uoc.reservofi.base.busines.model.BaseEntidadActualizable;
import com.uoc.reservofi.base.busines.repository.BaseEntidadActualizableRepository;
import com.uoc.reservofi.base.busines.service.BaseEntidadActualizableService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public abstract class BaseEntidadActualizableServiceImpl<T extends BaseEntidadActualizable> extends BaseEntidadServiceImpl<T> implements BaseEntidadActualizableService<T> {

    @Override
    @Transactional(readOnly = true)
    public List<T> findByCreacionUsuario(String usuario) {
        return ((BaseEntidadActualizableRepository<T>) getRepository()).findByCreacionUsuario(usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> findByActualizacionUsuario(String usuario) {
        return ((BaseEntidadActualizableRepository<T>) getRepository()).findByActualizacionUsuario(usuario);
    }

}

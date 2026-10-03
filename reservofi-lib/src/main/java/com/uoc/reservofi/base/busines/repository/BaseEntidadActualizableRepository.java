package com.uoc.reservofi.base.busines.repository;

import com.uoc.reservofi.base.busines.model.BaseEntidadActualizable;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface BaseEntidadActualizableRepository<T extends BaseEntidadActualizable> extends BaseEntidadRepository<T> {
    List<T> findByCreacionUsuario(String usuario);
    List<T> findByActualizacionUsuario (String usuario);

}

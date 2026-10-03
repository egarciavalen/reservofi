package com.uoc.reservofi.base.busines.repository;

import com.uoc.reservofi.base.busines.model.BaseEntidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseEntidadRepository<T> extends JpaRepository <T, Long>  {

}

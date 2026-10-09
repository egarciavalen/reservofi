package com.uoc.reservofi.base.busines.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.util.List;

@NoRepositoryBean
public interface BaseEntidadRepository<T> extends JpaRepository <T, Long>  {

	List<T> findByCreacionUsuario(@Param("creacion_usuario") String usuario);

	List<T> findByActualizacionUsuario (@Param("actualizacion_usuario") String usuario);
}

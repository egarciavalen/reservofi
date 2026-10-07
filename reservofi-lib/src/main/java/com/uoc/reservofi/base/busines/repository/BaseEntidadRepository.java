package com.uoc.reservofi.base.busines.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface BaseEntidadRepository<T> extends JpaRepository <T, Long>  {

	List<T> findByCreacionUsuario(String usuario);
	List<T> findByActualizacionUsuario (String usuario);
}

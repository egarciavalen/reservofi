package com.uoc.reservofi.puesto.business.repository;

import com.uoc.reservofi.base.busines.repository.BaseEntidadRepository;
import com.uoc.reservofi.puesto.business.model.Puesto;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PuestoRepository extends BaseEntidadRepository<Puesto> {

	List<Puesto> findBySalaId(@Param("id") Long idSala);
}

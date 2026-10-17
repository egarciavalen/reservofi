package com.uoc.reservofi.reserva.business.repository;

import com.uoc.reservofi.base.busines.repository.BaseEntidadRepository;
import com.uoc.reservofi.reserva.business.model.Reserva;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservaRepository extends BaseEntidadRepository<Reserva> {

	List<Reserva> findByUsuarioId(@Param("id") Long idUsuario);

	List<Reserva> findByPuestoId(@Param("id") Long idPuesto);
}

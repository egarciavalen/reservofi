package com.uoc.reservofi.reserva.business.service;

import com.uoc.reservofi.base.busines.exception.GestionException;
import com.uoc.reservofi.base.busines.service.GestionEntidadService;
import com.uoc.reservofi.reserva.business.dto.ReservaDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface GestionReservaService extends GestionEntidadService<ReservaDTO> {

	String BEAN_NAME = "gestionReservaService";

	List<ReservaDTO> findByUsuarioId(Long idUsuario);

	void deleteByPuestoId(Long idPuesto);

	// Se hace public para facilitar las pruebas unitarias y probar únicamente la validación
	void validarReserva(@Valid ReservaDTO dto) throws GestionException;
}

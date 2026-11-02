package com.uoc.reservofi.sala.business.service;

import com.uoc.reservofi.base.busines.service.GestionEntidadService;
import com.uoc.reservofi.sala.business.dto.SalaDTO;

public interface GestionSalaService extends GestionEntidadService<SalaDTO> {

	String BEAN_NAME = "gestionSalaService";

	// Consultas adicionales para gestionar el guardado de la imagen
	SalaDTO createAndSaveImage(SalaDTO dto);

	// Para borrar también la imagen del disco
	void deleteWithImage(Long id);
}

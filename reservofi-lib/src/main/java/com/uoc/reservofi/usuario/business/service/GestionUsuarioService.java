package com.uoc.reservofi.usuario.business.service;

import com.uoc.reservofi.base.busines.service.GestionEntidadService;
import com.uoc.reservofi.usuario.business.dto.UsuarioDTO;
import com.uoc.reservofi.usuario.business.dto.UsuarioLoginRequestDTO;
import com.uoc.reservofi.usuario.business.dto.UsuarioLoginResponseDTO;

public interface GestionUsuarioService extends GestionEntidadService<UsuarioDTO> {

	String BEAN_NAME = "gestionUsuarioService";

	UsuarioDTO findByCorreoCorporativo(String correoCorporativo);

	UsuarioLoginResponseDTO login(UsuarioLoginRequestDTO requestDTO);
}

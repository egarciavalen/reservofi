package com.uoc.reservofi.usuario.business.service;

import com.uoc.reservofi.base.busines.service.GestionEntidadService;
import com.uoc.reservofi.usuario.business.dto.UsuarioDTO;
import com.uoc.reservofi.usuario.business.dto.UsuarioLoginRequestDTO;
import com.uoc.reservofi.usuario.business.dto.UsuarioLoginResponseDTO;
import jakarta.validation.Valid;

public interface GestionUsuarioService extends GestionEntidadService<UsuarioDTO> {

	String BEAN_NAME = "gestionUsuarioService";

	UsuarioDTO findByCorreoCorporativo(String correoCorporativo);

	UsuarioDTO register(@Valid UsuarioDTO dto);

	UsuarioLoginResponseDTO login(@Valid UsuarioLoginRequestDTO requestDTO);

}

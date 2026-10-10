package com.uoc.reservofi.autenticacion.business.service.impl;

import com.uoc.reservofi.autenticacion.business.dto.RestAutenticacionLoginDTO;
import com.uoc.reservofi.autenticacion.business.dto.RestAutenticacionResponseDTO;
import com.uoc.reservofi.autenticacion.business.service.RestAutenticacionService;
import com.uoc.reservofi.base.business.exception.RestException;
import com.uoc.reservofi.usuario.business.dto.UsuarioDTO;
import com.uoc.reservofi.usuario.business.service.GestionUsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service(RestAutenticacionService.BEAN_NAME)
@RequiredArgsConstructor
public class RestAutenticacionServiceImpl implements RestAutenticacionService {

	private final GestionUsuarioService gestionUsuarioService;

	private final PasswordEncoder passwordEncoder;

	@Override
	public RestAutenticacionResponseDTO login(RestAutenticacionLoginDTO restDTO) {
		UsuarioDTO usuarioDTO = gestionUsuarioService.findByCorreoCorporativo(restDTO.getCorreoCorporativo());

		// Se revisa si las contrasenias coinciden
		boolean isMatch = passwordEncoder.matches(restDTO.getCorreoCorporativo(), usuarioDTO.getContrasenia());
		if (!isMatch) {
			throw new RestException("No se ha podido autenticar al usuario");
		}

		// TODO: CAMBIAR POR TOKEN JWT
		return new RestAutenticacionResponseDTO();
	}
}

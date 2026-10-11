package com.uoc.reservofi.autenticacion.business.service.impl;

import com.uoc.reservofi.autenticacion.business.dto.RestLoginRequestDTO;
import com.uoc.reservofi.autenticacion.business.dto.RestLoginResponseDTO;
import com.uoc.reservofi.autenticacion.business.dto.RestRegisterRequestDTO;
import com.uoc.reservofi.autenticacion.business.dto.RestRegisterResponseDTO;
import com.uoc.reservofi.autenticacion.business.mapper.RestAutenticacionMapper;
import com.uoc.reservofi.autenticacion.business.service.RestAutenticacionService;
import com.uoc.reservofi.usuario.business.dto.UsuarioDTO;
import com.uoc.reservofi.usuario.business.dto.UsuarioLoginRequestDTO;
import com.uoc.reservofi.usuario.business.dto.UsuarioLoginResponseDTO;
import com.uoc.reservofi.usuario.business.service.GestionUsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service(RestAutenticacionService.BEAN_NAME)
@RequiredArgsConstructor
public class RestAutenticacionServiceImpl implements RestAutenticacionService {

	private final GestionUsuarioService gestionUsuarioService;

	private final RestAutenticacionMapper autenticacionMapper;

	@Override
	public RestRegisterResponseDTO register(RestRegisterRequestDTO restDTO) {
		UsuarioDTO dto = autenticacionMapper.registerRestRequestDTOARequestDTO(restDTO);
		dto = gestionUsuarioService.register(dto);
		return autenticacionMapper.registerResponseDTOARestResponseDTO(dto);
	}


	@Override
	public RestLoginResponseDTO login(RestLoginRequestDTO restDTO) {
		UsuarioLoginRequestDTO requestDTO = autenticacionMapper.loginRestequestDTORequestDTO(restDTO);
		UsuarioLoginResponseDTO responseDTO = gestionUsuarioService.login(requestDTO);
		return autenticacionMapper.loginResponseDTOARestResponseDTO(responseDTO);
	}
}

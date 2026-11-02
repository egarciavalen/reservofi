package com.uoc.reservofi.autenticacion.business.service;

import com.uoc.reservofi.autenticacion.business.dto.RestLoginRequestDTO;
import com.uoc.reservofi.autenticacion.business.dto.RestLoginResponseDTO;
import com.uoc.reservofi.autenticacion.business.dto.RestRegisterRequestDTO;
import com.uoc.reservofi.autenticacion.business.dto.RestRegisterResponseDTO;

public interface RestAutenticacionService {

	String BEAN_NAME = "restAutenticacionService";

	RestRegisterResponseDTO register(RestRegisterRequestDTO restDTO);

	RestLoginResponseDTO login(RestLoginRequestDTO restDTO);
}

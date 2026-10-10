package com.uoc.reservofi.autenticacion.business.service;

import com.uoc.reservofi.autenticacion.business.dto.RestLoginRequestDTO;
import com.uoc.reservofi.autenticacion.business.dto.RestLoginResponseDTO;
import com.uoc.reservofi.autenticacion.business.dto.RestRegisterRequestDTO;
import com.uoc.reservofi.autenticacion.business.dto.RestRegisterResponseDTO;
import org.springframework.web.bind.annotation.RequestBody;

public interface RestAutenticacionService {

	String BEAN_NAME = "restAutenticacionService";

	RestLoginResponseDTO login(@RequestBody RestLoginRequestDTO restDTO);

	RestRegisterResponseDTO register(RestRegisterRequestDTO restDTO);
}

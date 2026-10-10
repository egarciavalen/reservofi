package com.uoc.reservofi.autenticacion.business.service;

import com.uoc.reservofi.autenticacion.business.dto.RestAutenticacionLoginDTO;
import com.uoc.reservofi.autenticacion.business.dto.RestAutenticacionResponseDTO;
import org.springframework.web.bind.annotation.RequestBody;

public interface RestAutenticacionService {

	String BEAN_NAME = "restAutenticacionService";

	RestAutenticacionResponseDTO login(@RequestBody RestAutenticacionLoginDTO restDTO);

}

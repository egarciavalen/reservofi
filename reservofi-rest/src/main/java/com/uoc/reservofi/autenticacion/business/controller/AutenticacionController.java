package com.uoc.reservofi.autenticacion.business.controller;

import com.uoc.reservofi.autenticacion.business.dto.RestLoginRequestDTO;
import com.uoc.reservofi.autenticacion.business.dto.RestLoginResponseDTO;
import com.uoc.reservofi.autenticacion.business.dto.RestRegisterRequestDTO;
import com.uoc.reservofi.autenticacion.business.dto.RestRegisterResponseDTO;
import com.uoc.reservofi.autenticacion.business.service.RestAutenticacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/")
@RequiredArgsConstructor
public class AutenticacionController {

	// TODO: HAY QUE REFACTORIZAR CON USO DE TOKEN JWT

	private final RestAutenticacionService restAutenticacionService;

	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	RestRegisterResponseDTO register(@RequestBody RestRegisterRequestDTO restDTO) {
		RestRegisterResponseDTO r = restAutenticacionService.register(restDTO);
		return r;
	}

	@PostMapping("/login")
	@ResponseStatus(HttpStatus.OK)
	RestLoginResponseDTO login(@RequestBody RestLoginRequestDTO restDTO) {
		return restAutenticacionService.login(restDTO);

	}

}

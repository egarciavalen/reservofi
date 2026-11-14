package com.uoc.reservofi.rol.business.controller;

import com.uoc.reservofi.rol.business.dto.RestRolConsultaDTO;
import com.uoc.reservofi.rol.business.service.RestRolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RolController {

	private final RestRolService restRolService;

	@PreAuthorize("hasAuthority('Empleado')")
	@GetMapping("/rol/{id}")
	@ResponseStatus(HttpStatus.OK)
	RestRolConsultaDTO findById(@PathVariable Long id) {
		return restRolService.findById(id);
	}

	@PreAuthorize("hasAuthority('Administrador')")
	@GetMapping("/rol")
	@ResponseStatus(HttpStatus.OK)
	List<RestRolConsultaDTO> findAll() {
		return restRolService.findAll();
	}
}

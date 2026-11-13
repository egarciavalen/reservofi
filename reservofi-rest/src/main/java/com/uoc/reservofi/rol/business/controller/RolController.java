package com.uoc.reservofi.rol.business.controller;

import com.uoc.reservofi.base.business.exception.RestException;
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
		RestRolConsultaDTO restRolDTO = restRolService.findById(id);
		if (restRolDTO == null) {
			throw new RestException("Entidad no encontrada");
		}
		return restRolDTO;
	}

	@PreAuthorize("hasAuthority('Administrador')")
	@GetMapping("/rol")
	@ResponseStatus(HttpStatus.OK)
	List<RestRolConsultaDTO> findAll() {
		List<RestRolConsultaDTO> restRolDTOList = restRolService.findAll();
		if (restRolDTOList == null || restRolDTOList.isEmpty()) {
			throw new RestException("Entidades no encontradas");
		}
		return restRolDTOList;
	}
}

package com.uoc.reservofi.puesto.business.controller;

import com.uoc.reservofi.base.business.exception.RestException;
import com.uoc.reservofi.puesto.business.dto.RestPuestoConsultaDTO;
import com.uoc.reservofi.puesto.business.dto.RestPuestoGuardadoDTO;
import com.uoc.reservofi.puesto.business.service.RestPuestoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PuestoController {

	private final RestPuestoService restPuestoService;

	@PreAuthorize("hasAuthority('Administrador')")
	@PostMapping("/puesto")
	@ResponseStatus(HttpStatus.CREATED)
	RestPuestoConsultaDTO create(@RequestBody RestPuestoGuardadoDTO restDTO) {
		return restPuestoService.insert(restDTO);
	}

	@PreAuthorize("hasAuthority('Empleado')")
	@GetMapping("/puesto/{id}")
	@ResponseStatus(HttpStatus.OK)
	RestPuestoConsultaDTO findById(@PathVariable Long id) {
		RestPuestoConsultaDTO puestoConsultaDTO = restPuestoService.findById(id);
		if (puestoConsultaDTO == null) {
			throw new RestException("No se ha podido recuperar la entidad");
		}
		return puestoConsultaDTO;
	}

	@PreAuthorize("hasAuthority('Empleado')")
	@GetMapping("/puesto")
	@ResponseStatus(HttpStatus.OK)
	List<RestPuestoConsultaDTO> findAll() {
		return restPuestoService.findAll();
	}

	@PreAuthorize("hasAuthority('Administrador')")
	@DeleteMapping("/puesto/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	void deleteById(@PathVariable Long id) {
		restPuestoService.deleteById(id);
	}
}

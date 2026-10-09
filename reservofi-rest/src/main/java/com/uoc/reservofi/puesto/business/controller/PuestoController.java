package com.uoc.reservofi.puesto.business.controller;

import com.uoc.reservofi.base.business.exception.RestException;
import com.uoc.reservofi.puesto.business.dto.RestPuestoDTO;
import com.uoc.reservofi.puesto.business.service.RestPuestoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PuestoController {

	private final RestPuestoService restPuestoService;

	@PostMapping("/puesto")
	RestPuestoDTO insert(@RequestBody RestPuestoDTO restDTO) {
		return restPuestoService.insert(restDTO);
	}

	@PutMapping("/puesto")
	RestPuestoDTO update(@RequestBody RestPuestoDTO restDTO) {
		return restPuestoService.update(restDTO);
	}

	@GetMapping("/puesto/{id}")
	RestPuestoDTO findById(@PathVariable Long id) {
		RestPuestoDTO restPuestoDTO = restPuestoService.findById(id);
		if (restPuestoDTO == null) {
			throw new RestException("Entidad no encontrada");
		}
		return restPuestoDTO;
	}

	@DeleteMapping("/puesto/{id}")
	void deleteById(@PathVariable Long id) {
		restPuestoService.deleteById(id);
	}
}

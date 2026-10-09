package com.uoc.reservofi.rol.business.controller;

import com.uoc.reservofi.base.business.exception.RestException;
import com.uoc.reservofi.rol.business.dto.RestRolDTO;
import com.uoc.reservofi.rol.business.service.RestRolService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class RolController {

	private final RestRolService restRolService;

	@PostMapping("/rol")
	RestRolDTO insert(@RequestBody RestRolDTO restDTO) {
		return restRolService.insert(restDTO);
	}

	@PutMapping("/rol")
	RestRolDTO update(@RequestBody RestRolDTO restDTO) {
		return restRolService.update(restDTO);
	}

	@GetMapping("/rol/{id}")
	RestRolDTO findById(@PathVariable Long id) {
		RestRolDTO restRolDTO = restRolService.findById(id);
		if (restRolDTO == null) {
			throw new RestException("Entidad no encontrada");
		}
		return restRolDTO;
	}

	@DeleteMapping("/rol/{id}")
	void deleteById(@PathVariable Long id) {
		restRolService.deleteById(id);
	}
}

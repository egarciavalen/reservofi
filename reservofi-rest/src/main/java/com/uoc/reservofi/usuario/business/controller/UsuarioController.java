package com.uoc.reservofi.usuario.business.controller;

import com.uoc.reservofi.base.business.exception.RestException;
import com.uoc.reservofi.usuario.business.dto.RestUsuarioDTO;
import com.uoc.reservofi.usuario.business.service.RestUsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UsuarioController {

	private final RestUsuarioService restUsuarioService;

	@PostMapping("/usuario")
	RestUsuarioDTO insert(@RequestBody RestUsuarioDTO restDTO) {
		return restUsuarioService.insert(restDTO);
	}

	@PutMapping("/usuario")
	RestUsuarioDTO update(@RequestBody RestUsuarioDTO restDTO) {
		return restUsuarioService.update(restDTO);
	}

	@GetMapping("/usuario/{id}")
	RestUsuarioDTO findById(@PathVariable Long id) {
		RestUsuarioDTO restUsuarioDTO = restUsuarioService.findById(id);
		if (restUsuarioDTO == null) {
			throw new RestException("Entidad no encontrada");
		}
		return restUsuarioDTO;
	}

	@DeleteMapping("/usuario/{id}")
	void deleteById(@PathVariable Long id) {
		restUsuarioService.deleteById(id);
	}
}

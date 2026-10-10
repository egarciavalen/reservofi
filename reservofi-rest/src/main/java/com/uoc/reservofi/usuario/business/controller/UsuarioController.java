package com.uoc.reservofi.usuario.business.controller;

import com.uoc.reservofi.base.business.exception.RestException;
import com.uoc.reservofi.usuario.business.dto.RestUsuarioConsultaDTO;
import com.uoc.reservofi.usuario.business.dto.RestUsuarioModificacionDTO;
import com.uoc.reservofi.usuario.business.service.RestUsuarioService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class UsuarioController {

	private final RestUsuarioService restUsuarioService;

	// ¿Insert? -> Controlador de autenticación

	@PutMapping("/usuario")
	@ResponseStatus(HttpStatus.OK)
	RestUsuarioConsultaDTO update(@RequestBody RestUsuarioModificacionDTO restDTO) {
		return restUsuarioService.update(restDTO);
	}

	@GetMapping("/usuario/{id}")
	@ResponseStatus(HttpStatus.OK)
	RestUsuarioConsultaDTO findById(@PathVariable Long id) {
		RestUsuarioConsultaDTO restUsuarioDTO = restUsuarioService.findById(id);
		if (restUsuarioDTO == null) {
			throw new RestException("Entidad no encontrada");
		}
		return restUsuarioDTO;
	}

	@DeleteMapping("/usuario/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	void deleteById(@PathVariable Long id) {
		restUsuarioService.deleteById(id);
	}
}

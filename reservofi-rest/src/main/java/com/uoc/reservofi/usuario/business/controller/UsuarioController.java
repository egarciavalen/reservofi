package com.uoc.reservofi.usuario.business.controller;

import com.uoc.reservofi.base.business.exception.RestException;
import com.uoc.reservofi.usuario.business.dto.RestUsuarioConsultaDTO;
import com.uoc.reservofi.usuario.business.dto.RestUsuarioGuardadoDTO;
import com.uoc.reservofi.usuario.business.service.RestUsuarioService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class UsuarioController {

	private final RestUsuarioService restUsuarioService;

	// ¿Insert? -> Controlador de autenticación

	@PreAuthorize("hasAuthority('Empleado')")
	@PutMapping("/usuario/{id}")
	@ResponseStatus(HttpStatus.OK)
	RestUsuarioConsultaDTO update(@PathVariable Long id, @RequestBody RestUsuarioGuardadoDTO restDTO) {
		return restUsuarioService.update(id, restDTO);
	}

	@PreAuthorize("hasAuthority('Empleado')")
	@GetMapping("/usuario/{id}")
	@ResponseStatus(HttpStatus.OK)
	RestUsuarioConsultaDTO findById(@PathVariable Long id) {
		RestUsuarioConsultaDTO usuarioConsultaDTO = restUsuarioService.findById(id);
		if (usuarioConsultaDTO == null) {
			throw new RestException("No se ha podido recuperar la entidad");
		}
		return usuarioConsultaDTO;
	}

	@PreAuthorize("hasAuthority('Empleado')")
	@GetMapping("/usuario")
	@ResponseStatus(HttpStatus.OK)
	List<RestUsuarioConsultaDTO> findAll() {
		List<RestUsuarioConsultaDTO> restUsuarioDTOList = restUsuarioService.findAll();
		if (restUsuarioDTOList == null) {
			return new ArrayList<>();
		}
		return restUsuarioDTOList;
	}

	@PreAuthorize("hasAuthority('Administrador')")
	@DeleteMapping("/usuario/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	void deleteById(@PathVariable Long id) {
		restUsuarioService.deleteById(id);
	}

	@PreAuthorize("hasAuthority('Administrador')")
	@PostMapping("/usuario/{id}/toggleActivo")
	@ResponseStatus(HttpStatus.OK)
	RestUsuarioConsultaDTO toggleActivo(@PathVariable Long id) {
		return restUsuarioService.toggleActivo(id);
	}

	@PreAuthorize("hasAuthority('Administrador')")
	@PostMapping("/usuario/{id}/toggleAdmin")
	@ResponseStatus(HttpStatus.OK)
	RestUsuarioConsultaDTO toggleAdmin(@PathVariable Long id) {
		return restUsuarioService.toggleAdmin(id);
	}


	@PreAuthorize("hasAuthority('Empleado')")
	@GetMapping("/usuario/{id}/checkAdmin")
	@ResponseStatus(HttpStatus.OK)
	boolean checkAdmin(@PathVariable Long id) {
		return restUsuarioService.checkAdmin(id);
	}
}

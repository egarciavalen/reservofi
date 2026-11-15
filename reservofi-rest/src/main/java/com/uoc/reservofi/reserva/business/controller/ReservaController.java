package com.uoc.reservofi.reserva.business.controller;

import com.uoc.reservofi.base.business.exception.RestException;
import com.uoc.reservofi.reserva.business.dto.RestReservaConsultaDTO;
import com.uoc.reservofi.reserva.business.dto.RestReservaGuardadoDTO;
import com.uoc.reservofi.reserva.business.service.RestReservaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ReservaController {

	private final RestReservaService restReservaService;

	@PreAuthorize("hasAuthority('Empleado')")
	@PostMapping("/reserva")
	@ResponseStatus(HttpStatus.CREATED)
	RestReservaConsultaDTO create(@RequestBody RestReservaGuardadoDTO restDTO) {
		return restReservaService.insert(restDTO);
	}

	@PreAuthorize("hasAuthority('Empleado')")
	@GetMapping("/reserva/{id}")
	@ResponseStatus(HttpStatus.OK)
	RestReservaConsultaDTO findById(@PathVariable Long id) {
		RestReservaConsultaDTO reservaConsultaDTO = restReservaService.findById(id);
		if (reservaConsultaDTO == null) {
			throw new RestException("No se ha podido recuperar la entidad");
		}
		return reservaConsultaDTO;
	}

	@PreAuthorize("hasAuthority('Administrador')")
	@GetMapping("/reserva")
	@ResponseStatus(HttpStatus.OK)
	List<RestReservaConsultaDTO> findAll() {
		return restReservaService.findAll();
	}

	@PreAuthorize("hasAuthority('Empleado')")
	@GetMapping("/reserva/usuario/{id}")
	@ResponseStatus(HttpStatus.OK)
	List<RestReservaConsultaDTO> findByUsuarioId(@PathVariable Long id) {
		return restReservaService.findByUsuarioId(id);
	}

	@PreAuthorize("hasAuthority('Empleado')")
	@DeleteMapping("/reserva/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	void deleteById(@PathVariable Long id) {
		restReservaService.deleteById(id);
	}
}

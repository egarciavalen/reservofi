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
		RestReservaConsultaDTO restReservaDTO = restReservaService.findById(id);
		if (restReservaDTO == null) {
			throw new RestException("Entidad no encontrada");
		}
		return restReservaDTO;
	}

	@PreAuthorize("hasAuthority('Administrador')")
	@GetMapping("/reserva")
	@ResponseStatus(HttpStatus.OK)
	List<RestReservaConsultaDTO> findAll() {
		List<RestReservaConsultaDTO> restReservaDTOList = restReservaService.findAll();
		if (restReservaDTOList == null || restReservaDTOList.isEmpty()) {
			throw new RestException("Entidades no encontradas");
		}
		return restReservaDTOList;
	}

	@PreAuthorize("hasAuthority('Empleado')")
	@GetMapping("/reserva/usuario/{id}")
	@ResponseStatus(HttpStatus.OK)
	List<RestReservaConsultaDTO> findByUsuarioId(@PathVariable Long id) {
		List<RestReservaConsultaDTO> restReservaDTOList = restReservaService.findByUsuarioId(id);
		if (restReservaDTOList == null || restReservaDTOList.isEmpty()) {
			throw new RestException("Entidades no encontradas");
		}
		return restReservaDTOList;
	}

	@PreAuthorize("hasAuthority('Administrador')")
	@DeleteMapping("/reserva/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	void deleteById(@PathVariable Long id) {
		restReservaService.deleteById(id);
	}
}

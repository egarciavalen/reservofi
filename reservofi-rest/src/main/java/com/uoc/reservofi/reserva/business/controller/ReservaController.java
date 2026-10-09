package com.uoc.reservofi.reserva.business.controller;

import com.uoc.reservofi.base.business.exception.RestException;
import com.uoc.reservofi.reserva.business.dto.RestReservaDTO;
import com.uoc.reservofi.reserva.business.service.RestReservaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ReservaController {

	private final RestReservaService restReservaService;

	@PostMapping("/reserva")
	RestReservaDTO insert(@RequestBody RestReservaDTO restDTO) {
		return restReservaService.insert(restDTO);
	}

	@PutMapping("/reserva")
	RestReservaDTO update(@RequestBody RestReservaDTO restDTO) {
		return restReservaService.update(restDTO);
	}

	@GetMapping("/reserva/{id}")
	RestReservaDTO findById(@PathVariable Long id) {
		RestReservaDTO restReservaDTO = restReservaService.findById(id);
		if (restReservaDTO == null) {
			throw new RestException("Entidad no encontrada");
		}
		return restReservaDTO;
	}

	@DeleteMapping("/reserva/{id}")
	void deleteById(@PathVariable Long id) {
		restReservaService.deleteById(id);
	}
}

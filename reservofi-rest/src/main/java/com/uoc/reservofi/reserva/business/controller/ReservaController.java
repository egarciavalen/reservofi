package com.uoc.reservofi.reserva.business.controller;

import com.uoc.reservofi.base.business.exception.RestException;
import com.uoc.reservofi.reserva.business.dto.RestReservaDTO;
import com.uoc.reservofi.reserva.business.service.RestReservaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ReservaController {

	private final RestReservaService restReservaService;

	@PostMapping("/reserva")
	@ResponseStatus(HttpStatus.CREATED)
	RestReservaDTO insert(@RequestBody RestReservaDTO restDTO) {
		return restReservaService.insert(restDTO);
	}

	@PutMapping("/reserva")
	@ResponseStatus(HttpStatus.OK)
	RestReservaDTO update(@RequestBody RestReservaDTO restDTO) {
		return restReservaService.update(restDTO);
	}

	@GetMapping("/reserva/{id}")
	@ResponseStatus(HttpStatus.OK)
	RestReservaDTO findById(@PathVariable Long id) {
		RestReservaDTO restReservaDTO = restReservaService.findById(id);
		if (restReservaDTO == null) {
			throw new RestException("Entidad no encontrada");
		}
		return restReservaDTO;
	}

	@DeleteMapping("/reserva/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	void deleteById(@PathVariable Long id) {
		restReservaService.deleteById(id);
	}
}

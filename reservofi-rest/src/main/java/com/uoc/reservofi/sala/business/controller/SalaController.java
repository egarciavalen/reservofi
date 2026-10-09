package com.uoc.reservofi.sala.business.controller;

import com.uoc.reservofi.base.business.exception.RestException;
import com.uoc.reservofi.sala.business.dto.RestSalaDTO;
import com.uoc.reservofi.sala.business.service.RestSalaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SalaController {

	private final RestSalaService restSalaService;

	@PostMapping("/sala")
	RestSalaDTO insert(@RequestBody RestSalaDTO restDTO) {
		return restSalaService.insert(restDTO);
	}

	@PutMapping("/sala")
	RestSalaDTO update(@RequestBody RestSalaDTO restDTO) {
		return restSalaService.update(restDTO);
	}

	@GetMapping("/sala/{id}")
	RestSalaDTO findById(@PathVariable Long id) {
		RestSalaDTO restSalaDTO = restSalaService.findById(id);
		if (restSalaDTO == null) {
			throw new RestException("Entidad no encontrada");
		}
		return restSalaDTO;
	}

	@DeleteMapping("/sala/{id}")
	void deleteById(@PathVariable Long id) {
		restSalaService.deleteById(id);
	}
}

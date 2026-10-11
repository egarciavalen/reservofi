package com.uoc.reservofi.sala.business.controller;

import com.uoc.reservofi.base.business.exception.RestException;
import com.uoc.reservofi.sala.business.dto.RestSalaDTO;
import com.uoc.reservofi.sala.business.service.RestSalaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SalaController {

	private final RestSalaService restSalaService;

	@PostMapping("/sala")
	@ResponseStatus(HttpStatus.CREATED)
	RestSalaDTO insert(@RequestBody RestSalaDTO restDTO) {
		return restSalaService.insert(restDTO);
	}

	@PutMapping("/sala")
	@ResponseStatus(HttpStatus.OK)
	RestSalaDTO update(@RequestBody RestSalaDTO restDTO) {
		return restSalaService.update(restDTO);
	}

	@GetMapping("/sala/{id}")
	@ResponseStatus(HttpStatus.OK)
	RestSalaDTO findById(@PathVariable Long id) {
		RestSalaDTO restSalaDTO = restSalaService.findById(id);
		if (restSalaDTO == null) {
			throw new RestException("Entidad no encontrada");
		}
		return restSalaDTO;
	}

	@GetMapping("/sala")
	@ResponseStatus(HttpStatus.OK)
	List<RestSalaDTO> findAll() {
		List<RestSalaDTO> restSalaDTOList = restSalaService.findAll();
		if (restSalaDTOList == null || restSalaDTOList.isEmpty()) {
			throw new RestException("Entidades no encontradas");
		}
		return restSalaDTOList;
	}

	@DeleteMapping("/sala/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	void deleteById(@PathVariable Long id) {
		restSalaService.deleteById(id);
	}
}

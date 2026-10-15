package com.uoc.reservofi.sala.business.controller;

import com.uoc.reservofi.base.business.exception.RestException;
import com.uoc.reservofi.sala.business.dto.RestSalaConsultaDTO;
import com.uoc.reservofi.sala.business.dto.RestSalaGuardadoDTO;
import com.uoc.reservofi.sala.business.service.RestSalaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SalaController {

	private final RestSalaService restSalaService;

	@PostMapping(path = "/sala", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
		// Uso de @ModelAttribute en vez de @RequestBody al no trabajar con JSON
	RestSalaConsultaDTO save(@ModelAttribute RestSalaGuardadoDTO restDTO) {
		return restSalaService.create(restDTO);
	}

	@PutMapping(path = "/sala/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@ResponseStatus(HttpStatus.OK)
		// Uso de @ModelAttribute en vez de @RequestBody al no trabajar con JSON
	RestSalaConsultaDTO update(@ModelAttribute RestSalaGuardadoDTO restDTO, @PathVariable Long id) {
		return restSalaService.update(restDTO, id);
	}


	@GetMapping("/sala/{id}")
	@ResponseStatus(HttpStatus.OK)
	RestSalaConsultaDTO findById(@PathVariable Long id) {
		RestSalaConsultaDTO restSalaDTO = restSalaService.findById(id);
		if (restSalaDTO == null) {
			throw new RestException("Entidad no encontrada");
		}
		return restSalaDTO;
	}

	@GetMapping("/sala")
	@ResponseStatus(HttpStatus.OK)
	List<RestSalaConsultaDTO> findAll() {
		List<RestSalaConsultaDTO> restSalaDTOList = restSalaService.findAll();
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

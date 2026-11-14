package com.uoc.reservofi.sala.business.controller;

import com.uoc.reservofi.sala.business.dto.RestSalaConsultaDTO;
import com.uoc.reservofi.sala.business.dto.RestSalaGuardadoDTO;
import com.uoc.reservofi.sala.business.service.RestSalaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SalaController {

	private final RestSalaService restSalaService;

	@PreAuthorize("hasAuthority('Administrador')")
	@PostMapping(path = "/sala", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
		// Uso de @ModelAttribute en vez de @RequestBody al no trabajar con JSON
	RestSalaConsultaDTO create(@ModelAttribute RestSalaGuardadoDTO restDTO) {
		return restSalaService.insert(restDTO);
	}

	@PreAuthorize("hasAuthority('Administrador')")
	@PutMapping(path = "/sala/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@ResponseStatus(HttpStatus.OK)
		// Uso de @ModelAttribute en vez de @RequestBody al no trabajar con JSON
	RestSalaConsultaDTO update(@ModelAttribute RestSalaGuardadoDTO restDTO, @PathVariable Long id) {
		return restSalaService.update(id, restDTO);
	}

	@PreAuthorize("hasAuthority('Empleado')")
	@GetMapping("/sala/{id}")
	@ResponseStatus(HttpStatus.OK)
	RestSalaConsultaDTO findById(@PathVariable Long id) {
		return restSalaService.findById(id);
	}

	@PreAuthorize("hasAuthority('Empleado')")
	@GetMapping("/sala")
	@ResponseStatus(HttpStatus.OK)
	List<RestSalaConsultaDTO> findAll() {
		return restSalaService.findAll();
	}

	@PreAuthorize("hasAuthority('Administrador')")
	@DeleteMapping("/sala/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	void deleteById(@PathVariable Long id) {
		restSalaService.deleteById(id);
	}
}

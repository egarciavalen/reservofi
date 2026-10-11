package com.uoc.reservofi.rol.business.controller;

import com.uoc.reservofi.base.business.exception.RestException;
import com.uoc.reservofi.rol.business.dto.RestRolDTO;
import com.uoc.reservofi.rol.business.service.RestRolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RolController {

	private final RestRolService restRolService;

	@PostMapping("/rol")
	@ResponseStatus(HttpStatus.CREATED)
	RestRolDTO insert(@RequestBody RestRolDTO restDTO) {
		return restRolService.insert(restDTO);
	}

	@PutMapping("/rol")
	@ResponseStatus(HttpStatus.OK)
	RestRolDTO update(@RequestBody RestRolDTO restDTO) {
		return restRolService.update(restDTO);
	}

	@GetMapping("/rol/{id}")
	@ResponseStatus(HttpStatus.OK)
	RestRolDTO findById(@PathVariable Long id) {
		RestRolDTO restRolDTO = restRolService.findById(id);
		if (restRolDTO == null) {
			throw new RestException("Entidad no encontrada");
		}
		return restRolDTO;
	}

	@GetMapping("/rol")
	@ResponseStatus(HttpStatus.OK)
	List<RestRolDTO> findAll() {
		List<RestRolDTO> restRolDTOList = restRolService.findAll();
		if (restRolDTOList == null || restRolDTOList.isEmpty()) {
			throw new RestException("Entidades no encontradas");
		}
		return restRolDTOList;
	}

	@DeleteMapping("/rol/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	void deleteById(@PathVariable Long id) {
		restRolService.deleteById(id);
	}
}

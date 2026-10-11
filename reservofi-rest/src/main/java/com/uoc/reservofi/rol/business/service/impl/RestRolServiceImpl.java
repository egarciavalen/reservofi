package com.uoc.reservofi.rol.business.service.impl;

import com.uoc.reservofi.base.business.service.impl.AbstractRestEntidadServiceImpl;
import com.uoc.reservofi.rol.business.dto.RestRolDTO;
import com.uoc.reservofi.rol.business.dto.RolDTO;
import com.uoc.reservofi.rol.business.mapper.RestRolMapper;
import com.uoc.reservofi.rol.business.service.GestionRolService;
import com.uoc.reservofi.rol.business.service.RestRolService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(RestRolService.BEAN_NAME)
@RequiredArgsConstructor
public class RestRolServiceImpl extends AbstractRestEntidadServiceImpl<RestRolDTO> implements RestRolService {

	private final GestionRolService gestionRolService;
	private final RestRolMapper restRolMapper;

	@Override
	public RestRolDTO insert(@Valid RestRolDTO restDTO) {
		RolDTO dto = restRolMapper.restDTOADTO(restDTO);
		dto = gestionRolService.insert(dto);
		return restRolMapper.DTOArestDTO(dto);
	}

	@Override
	public RestRolDTO update(@Valid RestRolDTO restDTO) {
		RolDTO dto = restRolMapper.restDTOADTO(restDTO);
		dto = gestionRolService.update(dto);
		return restRolMapper.DTOArestDTO(dto);
	}

	@Override
	public void deleteById(Long id) {
		gestionRolService.deleteById(id);
	}

	@Override
	public RestRolDTO findById(Long id) {
		RolDTO dto = gestionRolService.findById(id);
		return restRolMapper.DTOArestDTO(dto);
	}

	@Override
	public List<RestRolDTO> findAll() {
		List<RolDTO> dtoList = gestionRolService.findAll();
		return restRolMapper.DTOArestDTOList(dtoList);
	}
}

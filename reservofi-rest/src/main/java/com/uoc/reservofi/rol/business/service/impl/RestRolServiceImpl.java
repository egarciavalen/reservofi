package com.uoc.reservofi.rol.business.service.impl;

import com.uoc.reservofi.base.business.service.impl.AbstractRestEntidadServiceImpl;
import com.uoc.reservofi.rol.business.dto.RestRolConsultaDTO;
import com.uoc.reservofi.rol.business.dto.RestRolGuardadoDTO;
import com.uoc.reservofi.rol.business.dto.RolDTO;
import com.uoc.reservofi.rol.business.mapper.RestRolMapper;
import com.uoc.reservofi.rol.business.service.GestionRolService;
import com.uoc.reservofi.rol.business.service.RestRolService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(RestRolService.BEAN_NAME)
@RequiredArgsConstructor
public class RestRolServiceImpl extends AbstractRestEntidadServiceImpl<RestRolConsultaDTO, RestRolGuardadoDTO> implements RestRolService {

	private final GestionRolService gestionRolService;
	private final RestRolMapper restRolMapper;

	@Override
	public RestRolConsultaDTO findById(Long id) {
		RolDTO dto = gestionRolService.findById(id);
		return restRolMapper.DTOArestDTO(dto);
	}

	@Override
	public List<RestRolConsultaDTO> findAll() {
		List<RolDTO> dtoList = gestionRolService.findAll();
		return restRolMapper.DTOArestDTOList(dtoList);
	}
}

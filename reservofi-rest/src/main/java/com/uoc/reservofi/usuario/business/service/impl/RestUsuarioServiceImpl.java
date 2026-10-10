package com.uoc.reservofi.usuario.business.service.impl;

import com.uoc.reservofi.base.business.service.impl.AbstractRestEntidadServiceImpl;
import com.uoc.reservofi.usuario.business.dto.RestUsuarioConsultaDTO;
import com.uoc.reservofi.usuario.business.dto.RestUsuarioModificacionDTO;
import com.uoc.reservofi.usuario.business.dto.UsuarioDTO;
import com.uoc.reservofi.usuario.business.mapper.RestUsuarioMapper;
import com.uoc.reservofi.usuario.business.service.GestionUsuarioService;
import com.uoc.reservofi.usuario.business.service.RestUsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service(RestUsuarioService.BEAN_NAME)
@RequiredArgsConstructor
public class RestUsuarioServiceImpl extends AbstractRestEntidadServiceImpl<RestUsuarioConsultaDTO> implements RestUsuarioService {

	private final GestionUsuarioService gestionUsuarioService;
	private final RestUsuarioMapper restUsuarioMapper;


	// El insert/update de la abstracción NO se usan al trabajar con diferentes DTO
	@Override
	public RestUsuarioConsultaDTO insert(RestUsuarioModificacionDTO restDTO) {
		UsuarioDTO dto = restUsuarioMapper.restDTOADTO(restDTO);
		dto = gestionUsuarioService.insert(dto);
		return restUsuarioMapper.DTOArestDTO(dto);
	}

	@Override
	public RestUsuarioConsultaDTO update(@Valid RestUsuarioModificacionDTO restDTO) {
		UsuarioDTO dto = restUsuarioMapper.restDTOADTO(restDTO);
		dto = gestionUsuarioService.update(dto);
		return restUsuarioMapper.DTOArestDTO(dto);
	}

	@Override
	public void deleteById(Long id) {
		gestionUsuarioService.deleteById(id);
	}

	@Override
	public RestUsuarioConsultaDTO findById(Long id) {
		UsuarioDTO dto = gestionUsuarioService.findById(id);
		return restUsuarioMapper.DTOArestDTO(dto);
	}
}

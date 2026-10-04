package com.uoc.reservofi.usuario.business.service.impl;

import com.uoc.reservofi.base.busines.service.impl.GestionEntidadActualizableServiceImpl;
import com.uoc.reservofi.usuario.business.dto.UsuarioDTO;
import com.uoc.reservofi.usuario.business.mapper.UsuarioMapper;
import com.uoc.reservofi.usuario.business.model.Usuario;
import com.uoc.reservofi.usuario.business.service.GestionUsuarioService;
import com.uoc.reservofi.usuario.business.service.base.BaseUsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(GestionUsuarioService.BEAN_NAME)
@RequiredArgsConstructor
public class GestionUsuarioServiceImpl extends GestionEntidadActualizableServiceImpl<UsuarioDTO> implements GestionUsuarioService {

	private final BaseUsuarioService baseUsuarioService;
	private final UsuarioMapper UsuarioMapper;

	@Override
	public UsuarioDTO insert(@Valid UsuarioDTO dto) {
		Usuario entidad = UsuarioMapper.DTOaEntidad(dto);
		entidad = baseUsuarioService.insert(entidad);
		return UsuarioMapper.entidadADTO(entidad);
	}

	@Override
	public UsuarioDTO update(@Valid UsuarioDTO dto) {
		Usuario entidad = UsuarioMapper.DTOaEntidad(dto);
		entidad = baseUsuarioService.update(entidad);
		return UsuarioMapper.entidadADTO(entidad);
	}

	@Override
	public void deleteById(Long id) {
		baseUsuarioService.deleteById(id);
	}

	@Override
	public List<UsuarioDTO> findAll() {
		List<Usuario> entidadList = baseUsuarioService.findAll();
		return UsuarioMapper.entidadADTOList(entidadList);
	}

	@Override
	public UsuarioDTO findById(Long id) {
		Usuario entidad = baseUsuarioService.findById(id);
		return UsuarioMapper.entidadADTO(entidad);
	}

	@Override
	public boolean existsById(Long id) {
		return baseUsuarioService.existsById(id);
	}

	@Override
	public List<UsuarioDTO> findByCreacionUsuario(String usuario) {
		List<Usuario> entidadList = baseUsuarioService.findByCreacionUsuario(usuario);
		return UsuarioMapper.entidadADTOList(entidadList);
	}

	@Override
	public List<UsuarioDTO> findByActualizacionUsuario(String usuario) {
		List<Usuario> entidadList = baseUsuarioService.findByActualizacionUsuario(usuario);
		return UsuarioMapper.entidadADTOList(entidadList);
	}

}

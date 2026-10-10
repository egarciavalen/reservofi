package com.uoc.reservofi.usuario.business.service.impl;

import com.uoc.reservofi.base.busines.exception.GestionException;
import com.uoc.reservofi.base.busines.security.jwt.JwtService;
import com.uoc.reservofi.base.busines.service.impl.AbstractGestionEntidadServiceImpl;
import com.uoc.reservofi.usuario.business.dto.UsuarioDTO;
import com.uoc.reservofi.usuario.business.dto.UsuarioLoginRequestDTO;
import com.uoc.reservofi.usuario.business.dto.UsuarioLoginResponseDTO;
import com.uoc.reservofi.usuario.business.mapper.UsuarioMapper;
import com.uoc.reservofi.usuario.business.model.Usuario;
import com.uoc.reservofi.usuario.business.service.GestionUsuarioService;
import com.uoc.reservofi.usuario.business.service.base.BaseUsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(GestionUsuarioService.BEAN_NAME)
@RequiredArgsConstructor
public class GestionUsuarioServiceImpl extends AbstractGestionEntidadServiceImpl<UsuarioDTO> implements GestionUsuarioService {

	private final BaseUsuarioService baseUsuarioService;
	private final UsuarioMapper usuarioMapper;

	// Gestión de token JWT
	private final AuthenticationManager authenticationManager;
	private final JwtService jwtService;

	@Override
	public UsuarioDTO insert(@Valid UsuarioDTO dto) {
		Usuario entidad = usuarioMapper.DTOAentidad(dto);
		entidad = baseUsuarioService.insert(entidad);
		return usuarioMapper.entidadADTO(entidad);
	}

	@Override
	public UsuarioDTO update(@Valid UsuarioDTO dto) {

		if (dto.getId() == null) {
			throw new GestionException("La entidad que se quiere actualizar ha de tener el campo identificador");
		}

		Usuario entidadBD = baseUsuarioService.findById(dto.getId());

		if (entidadBD == null) {
			throw new GestionException("Se está intentando actualizar una entidad no existente");
		}

		Usuario entidad = usuarioMapper.DTOAentidad(dto, entidadBD);

		entidad = baseUsuarioService.update(entidad);
		return usuarioMapper.entidadADTO(entidad);
	}

	@Override
	public void deleteById(Long id) {
		baseUsuarioService.deleteById(id);
	}

	@Override
	public List<UsuarioDTO> findAll() {
		List<Usuario> entidadList = baseUsuarioService.findAll();
		return usuarioMapper.entidadADTOList(entidadList);
	}

	@Override
	public UsuarioDTO findById(Long id) {
		Usuario entidad = baseUsuarioService.findById(id);
		return usuarioMapper.entidadADTO(entidad);
	}

	@Override
	public List<UsuarioDTO> findAllById(List<Long> ids) {
		List<Usuario> entidadList = baseUsuarioService.findAllById(ids);
		return usuarioMapper.entidadADTOList(entidadList);
	}

	@Override
	public boolean existsById(Long id) {
		return baseUsuarioService.existsById(id);
	}

	@Override
	public List<UsuarioDTO> findByCreacionUsuario(String usuario) {
		List<Usuario> entidadList = baseUsuarioService.findByCreacionUsuario(usuario);
		return usuarioMapper.entidadADTOList(entidadList);
	}

	@Override
	public List<UsuarioDTO> findByActualizacionUsuario(String usuario) {
		List<Usuario> entidadList = baseUsuarioService.findByActualizacionUsuario(usuario);
		return usuarioMapper.entidadADTOList(entidadList);
	}

	@Override
	public UsuarioDTO findByCorreoCorporativo(String correoCorporativo) {
		Usuario entidad = baseUsuarioService.findByCorreoCorporativo(correoCorporativo);
		return usuarioMapper.entidadADTO(entidad);
	}

	@Override
	public UsuarioLoginResponseDTO login(UsuarioLoginRequestDTO requestDTO) {

		// Paso 1: se comprueba que las credenciales sean válidas
		// En caso de que NO lo sean, lanzaría una excepción aquí (ver ReservofiSecurityConfiguration)
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						requestDTO.getCorreoCorporativo(),
						requestDTO.getContrasenia()
				)
		);

		// Paso 2: se extrae el usuario de base de datos (previamente se ha revisado que existiera)
		Usuario entidad = baseUsuarioService.findByCorreoCorporativo(requestDTO.getCorreoCorporativo());

		// Paso 3: se genera el token
		String jwtToken = jwtService.generateToken(entidad);

		// Paso 4: se construye la respuesta de vuelta
		UsuarioLoginResponseDTO responseDTO = new UsuarioLoginResponseDTO();
		responseDTO.setToken(jwtToken);
		responseDTO.setExpiresIn(jwtService.getExpirationTime());

		return responseDTO;
	}
}

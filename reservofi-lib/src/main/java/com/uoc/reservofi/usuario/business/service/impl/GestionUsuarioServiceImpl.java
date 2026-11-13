package com.uoc.reservofi.usuario.business.service.impl;

import com.uoc.reservofi.base.busines.exception.GestionException;
import com.uoc.reservofi.base.busines.security.jwt.JwtService;
import com.uoc.reservofi.base.busines.service.impl.AbstractGestionEntidadServiceImpl;
import com.uoc.reservofi.rol.business.service.GestionRolService;
import com.uoc.reservofi.usuario.business.dto.UsuarioDTO;
import com.uoc.reservofi.usuario.business.dto.UsuarioLoginRequestDTO;
import com.uoc.reservofi.usuario.business.dto.UsuarioLoginResponseDTO;
import com.uoc.reservofi.usuario.business.dto.UsuarioRolDTO;
import com.uoc.reservofi.usuario.business.mapper.UsuarioMapper;
import com.uoc.reservofi.usuario.business.model.Usuario;
import com.uoc.reservofi.usuario.business.service.GestionUsuarioService;
import com.uoc.reservofi.usuario.business.service.base.BaseUsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(GestionUsuarioService.BEAN_NAME)
@RequiredArgsConstructor
@Transactional(rollbackFor = GestionException.class)
public class GestionUsuarioServiceImpl extends AbstractGestionEntidadServiceImpl<UsuarioDTO> implements GestionUsuarioService {

	private static final Logger LOGGER = LoggerFactory.getLogger(GestionUsuarioServiceImpl.class);

	private final BaseUsuarioService baseUsuarioService;
	private final UsuarioMapper usuarioMapper;

	// Servicio de gestión de rol
	private final GestionRolService gestionRolService;

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
			LOGGER.error("Ha ocurrido un error al actualizar el usuario en el sistema. Nombre del usuario: {}", dto.getNombre());
			throw new GestionException("La entidad que se quiere actualizar ha de tener el campo identificador");
		}

		Usuario entidadBD = baseUsuarioService.findById(dto.getId());

		if (entidadBD == null) {
			LOGGER.error("Se está intentando actualizar una entidad no existente. Identificador del usuario: {}", dto.getId());
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
	public UsuarioDTO register(UsuarioDTO dto) {
		// El usuario registrado tendrá el rol de empleado por defecto
		Long idEmpleado = this.gestionRolService.getIdByNombre("Empleado");

		UsuarioRolDTO usuarioRolDTO = new UsuarioRolDTO();
		usuarioRolDTO.setId(idEmpleado);
		usuarioRolDTO.setNombre("Empleado");

		dto.setRoles(List.of(usuarioRolDTO));

		// Se inserta la información y se devuelve el usuario
		return this.insert(dto);
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
		responseDTO.setIdUsuario(entidad.getId());
		responseDTO.setExpiresIn(jwtService.getExpirationTime());

		return responseDTO;
	}

	@Override
	public UsuarioDTO toggleActivo(Long idUsuario) {

		Usuario usuario = baseUsuarioService.findById(idUsuario);

		// Validación
		if (usuario == null) {
			LOGGER.error("Se está intentando actualizar el campo activo de una entidad no existente. Identificador del usuario: {}", idUsuario);
			throw new GestionException("Se está intentando actualizar una entidad no existente");
		}

		// Se asigna el opuesto
		usuario.setActivo(!usuario.getActivo());

		usuario = this.baseUsuarioService.update(usuario);

		// Devolver el DTO
		return this.usuarioMapper.entidadADTO(usuario);
	}

	@Override
	public UsuarioDTO toggleAdmin(Long idUsuario) {

		UsuarioDTO usuarioDTO = this.findById(idUsuario);

		// Validación
		if (usuarioDTO == null) {
			LOGGER.error("Se está intentando actualizar el rol de administrador de una entidad no existente. Identificador del usuario: {}", idUsuario);
			throw new GestionException("Se está intentando actualizar una entidad no existente");
		}

		// Se busca el identificador que se corresponda al administrador
		Long idAdmin = this.gestionRolService.getIdByNombre("Administrador");

		List<UsuarioRolDTO> rolesUsuario = usuarioDTO.getRoles();
		boolean isAdminErased = false;

		if (rolesUsuario != null) {

			for (UsuarioRolDTO usuarioRolDTO : rolesUsuario) {
				if (usuarioRolDTO.getId().equals(idAdmin)) {
					// Si ya tiene el rol, lo elimina
					rolesUsuario.remove(usuarioRolDTO);
					isAdminErased = true;
					break;
				}
			}

			if (!isAdminErased) {
				UsuarioRolDTO usuarioRolDTO = new UsuarioRolDTO();
				usuarioRolDTO.setId(idAdmin);
				usuarioRolDTO.setNombre("Administrador");
				rolesUsuario.add(usuarioRolDTO);
			}
		}

		usuarioDTO.setRoles(rolesUsuario);

		// Devolver el DTO
		usuarioDTO.setContrasenia(null); // Para que no reescriba la contraseña
		return this.update(usuarioDTO);
	}

}

package com.uoc.reservofi.autenticacion.business.mapper;

import com.uoc.reservofi.autenticacion.business.dto.RestLoginRequestDTO;
import com.uoc.reservofi.autenticacion.business.dto.RestLoginResponseDTO;
import com.uoc.reservofi.autenticacion.business.dto.RestRegisterRequestDTO;
import com.uoc.reservofi.autenticacion.business.dto.RestRegisterResponseDTO;
import com.uoc.reservofi.usuario.business.dto.UsuarioDTO;
import com.uoc.reservofi.usuario.business.dto.UsuarioLoginRequestDTO;
import com.uoc.reservofi.usuario.business.dto.UsuarioLoginResponseDTO;
import com.uoc.reservofi.usuario.business.mapper.RestUsuarioRolMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = RestUsuarioRolMapper.class)
public interface RestAutenticacionMapper {

	@Named("restDTOADTO")
	@BeanMapping(ignoreByDefault = true)
	@Mapping(source = "id", target = "id")
	@Mapping(source = "nombre", target = "nombre")
	@Mapping(source = "primerApellido", target = "primerApellido")
	@Mapping(source = "segundoApellido", target = "segundoApellido")
	@Mapping(source = "correoCorporativo", target = "correoCorporativo")
	@Mapping(source = "contrasenia", target = "contrasenia")
	@Mapping(source = "activo", target = "activo")
	UsuarioDTO registerRestRequestDTOARequestDTO(RestRegisterRequestDTO restDTO);

	@Named("responseDTOARestResponseDTO")
	@BeanMapping(ignoreByDefault = true)
	@Mapping(source = "nombre", target = "nombre")
	@Mapping(source = "primerApellido", target = "primerApellido")
	@Mapping(source = "segundoApellido", target = "segundoApellido")
	@Mapping(source = "correoCorporativo", target = "correoCorporativo")
	@Mapping(source = "activo", target = "activo")
	RestRegisterResponseDTO registerResponseDTOARestResponseDTO(UsuarioDTO dto);


	@Named("loginRequestDTOARestRequestDTO")
	@BeanMapping(ignoreByDefault = true)
	@Mapping(source = "correoCorporativo", target = "correoCorporativo")
	@Mapping(source = "contrasenia", target = "contrasenia")
	UsuarioLoginRequestDTO loginRestequestDTORequestDTO(RestLoginRequestDTO restDTO);


	@Named("loginResponseDTOARestResponseDTO")
	@BeanMapping(ignoreByDefault = true)
	@Mapping(source = "token", target = "token")
	@Mapping(source = "idUsuario", target = "idUsuario")
	@Mapping(source = "expiresIn", target = "expiresIn")
	RestLoginResponseDTO loginResponseDTOARestResponseDTO(UsuarioLoginResponseDTO dto);
}
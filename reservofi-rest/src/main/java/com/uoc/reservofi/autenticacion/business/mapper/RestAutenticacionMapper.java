package com.uoc.reservofi.autenticacion.business.mapper;

import com.uoc.reservofi.autenticacion.business.dto.RestAutenticacionRegistroDTO;
import com.uoc.reservofi.autenticacion.business.dto.RestLoginRequestDTO;
import com.uoc.reservofi.autenticacion.business.dto.RestLoginResponseDTO;
import com.uoc.reservofi.usuario.business.dto.UsuarioDTO;
import com.uoc.reservofi.usuario.business.dto.UsuarioLoginRequestDTO;
import com.uoc.reservofi.usuario.business.dto.UsuarioLoginResponseDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
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
	@Mapping(source = "idsRoles", target = "idsRoles")
	UsuarioDTO restDTOADTO(RestAutenticacionRegistroDTO restDTO);

	@Named("restRequestLoginDTOALoginDTO")
	@BeanMapping(ignoreByDefault = true)
	@Mapping(source = "correoCorporativo", target = "correoCorporativo")
	@Mapping(source = "contrasenia", target = "contrasenia")
	UsuarioLoginRequestDTO restRequestLoginDTOALoginDTO(RestLoginRequestDTO restDTO);


	@Named("loginDTOARestLoginDTO")
	@BeanMapping(ignoreByDefault = true)
	@Mapping(source = "token", target = "token")
	@Mapping(source = "expiresIn", target = "expiresIn")
	RestLoginResponseDTO responseLoginDTOARestLoginDTO(UsuarioLoginResponseDTO dto);
}
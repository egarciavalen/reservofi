package com.uoc.reservofi.usuario.business.mapper;

import com.uoc.reservofi.usuario.business.dto.RestUsuarioConsultaDTO;
import com.uoc.reservofi.usuario.business.dto.RestUsuarioModificacionDTO;
import com.uoc.reservofi.usuario.business.dto.UsuarioDTO;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RestUsuarioMapper {

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
	UsuarioDTO restDTOADTO(RestUsuarioModificacionDTO restDTO);

	@Named("DTOArestDTO")
	@BeanMapping(ignoreByDefault = true)
	@Mapping(source = "id", target = "id")
	@Mapping(source = "nombre", target = "nombre")
	@Mapping(source = "primerApellido", target = "primerApellido")
	@Mapping(source = "segundoApellido", target = "segundoApellido")
	@Mapping(source = "correoCorporativo", target = "correoCorporativo")
	@Mapping(source = "activo", target = "activo")
	@Mapping(source = "idsRoles", target = "idsRoles")
	RestUsuarioConsultaDTO DTOArestDTO(UsuarioDTO dto);

	@IterableMapping(qualifiedByName = "DTOArestDTO")
	List<RestUsuarioConsultaDTO> DTOArestDTOList(List<UsuarioDTO> entidadList);

}
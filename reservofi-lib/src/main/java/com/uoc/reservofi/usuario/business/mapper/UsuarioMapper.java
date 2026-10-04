package com.uoc.reservofi.usuario.business.mapper;

import com.uoc.reservofi.usuario.business.dto.UsuarioDTO;
import com.uoc.reservofi.usuario.business.model.Usuario;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

	@Named("DTOAentidad")
	@BeanMapping(ignoreByDefault = true)
	@Mapping(source = "id", target = "id")
	@Mapping(source = "nombre", target = "nombre")
	@Mapping(source = "primerApellido", target = "primerApellido")
	@Mapping(source = "segundoApellido", target = "segundoApellido")
	@Mapping(source = "correoCorporativo", target = "correoCorporativo")
	@Mapping(source = "contraseniaHash", target = "contraseniaHash")
	@Mapping(source = "activo", target = "activo")
	Usuario DTOAentidad(UsuarioDTO dto);

	@Named("entidadADTO")
	@BeanMapping(ignoreByDefault = true)
	@Mapping(source = "id", target = "id")
	@Mapping(source = "nombre", target = "nombre")
	@Mapping(source = "primerApellido", target = "primerApellido")
	@Mapping(source = "segundoApellido", target = "segundoApellido")
	@Mapping(source = "correoCorporativo", target = "correoCorporativo")
	@Mapping(source = "contraseniaHash", target = "contraseniaHash")
	@Mapping(source = "activo", target = "activo")
	UsuarioDTO entidadADTO(Usuario entidad);

	@IterableMapping(qualifiedByName = "entidadADTO")
	List<UsuarioDTO> entidadADTOList(List<Usuario> entidadList);

	@IterableMapping(qualifiedByName = "DTOAentidad")
	List<Usuario> DTOAEntidadList(List<UsuarioDTO> dtoList);

}
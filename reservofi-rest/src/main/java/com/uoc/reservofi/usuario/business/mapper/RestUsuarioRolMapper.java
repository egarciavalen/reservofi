package com.uoc.reservofi.usuario.business.mapper;

import com.uoc.reservofi.usuario.business.dto.RestUsuarioConsultaRolDTO;
import com.uoc.reservofi.usuario.business.dto.UsuarioRolDTO;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RestUsuarioRolMapper {

	@Named("rolDTOArestRolDTO")
	@BeanMapping(ignoreByDefault = true)
	@Mapping(source = "id", target = "id")
	@Mapping(source = "nombre", target = "nombre")
	RestUsuarioConsultaRolDTO rolDTOArestRolDTO(UsuarioRolDTO dto);

	@Named("rolDTOArestRolDTOList")
	@IterableMapping(qualifiedByName = "rolDTOArestRolDTO")
	List<RestUsuarioConsultaRolDTO> rolDTOArestRolDTOList(List<UsuarioRolDTO> entidadList);

}
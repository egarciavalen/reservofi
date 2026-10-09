package com.uoc.reservofi.rol.business.mapper;

import com.uoc.reservofi.rol.business.dto.RestRolDTO;
import com.uoc.reservofi.rol.business.dto.RolDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface RestRolMapper {

	@Named("restDTOADTO")
	@BeanMapping(ignoreByDefault = true)
	@Mapping(source = "id", target = "id")
	@Mapping(source = "nombre", target = "nombre")
	RolDTO restDTOADTO(RestRolDTO restDTO);


	@Named("DTOArestDTO")
	@BeanMapping(ignoreByDefault = true)
	@Mapping(source = "id", target = "id")
	@Mapping(source = "nombre", target = "nombre")
	RestRolDTO DTOArestDTO(RolDTO dto);
}
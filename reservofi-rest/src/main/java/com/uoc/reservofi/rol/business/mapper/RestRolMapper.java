package com.uoc.reservofi.rol.business.mapper;

import com.uoc.reservofi.rol.business.dto.RestRolDTO;
import com.uoc.reservofi.rol.business.dto.RolDTO;
import org.mapstruct.*;

import java.util.List;

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

	@IterableMapping(qualifiedByName = "DTOArestDTO")
	List<RestRolDTO> DTOArestDTOList(List<RolDTO> entidadList);

}
package com.uoc.reservofi.rol.business.mapper;

import com.uoc.reservofi.rol.business.dto.RestRolConsultaDTO;
import com.uoc.reservofi.rol.business.dto.RestRolGuardadoDTO;
import com.uoc.reservofi.rol.business.dto.RolDTO;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RestRolMapper {

	@Named("restDTOADTO")
	@BeanMapping(ignoreByDefault = true)
	@Mapping(source = "id", target = "id")
	@Mapping(source = "nombre", target = "nombre")
	RolDTO restDTOADTO(RestRolGuardadoDTO restDTO);

	@Named("DTOArestDTO")
	@BeanMapping(ignoreByDefault = true)
	@Mapping(source = "id", target = "id")
	@Mapping(source = "nombre", target = "nombre")
	RestRolConsultaDTO DTOArestDTO(RolDTO dto);

	@IterableMapping(qualifiedByName = "DTOArestDTO")
	List<RestRolConsultaDTO> DTOArestDTOList(List<RolDTO> entidadList);

}
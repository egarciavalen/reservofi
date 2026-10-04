package com.uoc.reservofi.rol.business.mapper;

import com.uoc.reservofi.rol.business.dto.RolDTO;
import com.uoc.reservofi.rol.business.model.Rol;
import org.mapstruct.*;

import java.util.List;

@Mapper
public interface RolMapper {

	@Named("DTOaEntidad")
	@BeanMapping(ignoreByDefault = true)
	@Mapping(source = "id", target = "id")
	@Mapping(source = "nombre", target = "nombre")
	Rol DTOaEntidad(RolDTO dto);

	@Named("entidadADTO")
	@BeanMapping(ignoreByDefault = true)
	@Mapping(source = "id", target = "id")
	@Mapping(source = "nombre", target = "nombre")
	RolDTO entidadADTO(Rol entidad);

	@IterableMapping(qualifiedByName = "entidadADTO")
	List<RolDTO> entidadADTOList(List<Rol> entidadList);

	@IterableMapping(qualifiedByName = "DTOAentidad")
	List<Rol> DTOaEntidadList(List<RolDTO> dtoList);

}
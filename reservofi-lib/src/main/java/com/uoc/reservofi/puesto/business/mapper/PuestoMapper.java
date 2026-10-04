package com.uoc.reservofi.puesto.business.mapper;

import com.uoc.reservofi.puesto.business.dto.PuestoDTO;
import com.uoc.reservofi.puesto.business.model.Puesto;
import org.mapstruct.*;

import java.util.List;

@Mapper
@DecoratedWith(PuestoMapperDecorator.class)
public interface PuestoMapper {

	@Named("DTOaEntidad")
	@BeanMapping(ignoreByDefault = true)
	@Mapping(source = "id", target = "id")
	@Mapping(source = "coordenadaX", target = "coordenadaX")
	@Mapping(source = "coordenadaY", target = "coordenadaY")
	Puesto DTOaEntidad(PuestoDTO dto);

	@Named("entidadADTO")
	@BeanMapping(ignoreByDefault = true)
	@Mapping(source = "id", target = "id")
	@Mapping(source = "sala.id", target = "idSala")
	@Mapping(source = "coordenadaX", target = "coordenadaX")
	@Mapping(source = "coordenadaY", target = "coordenadaY")
	PuestoDTO entidadADTO(Puesto entidad);

	@IterableMapping(qualifiedByName = "entidadADTO")
	List<PuestoDTO> entidadADTOList(List<Puesto> entidadList);

	@IterableMapping(qualifiedByName = "DTOAentidad")
	List<Puesto> DTOaEntidadList(List<PuestoDTO> dtoList);

}
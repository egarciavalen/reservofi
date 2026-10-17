package com.uoc.reservofi.puesto.business.mapper;

import com.uoc.reservofi.puesto.business.dto.PuestoDTO;
import com.uoc.reservofi.puesto.business.model.Puesto;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring") // componentModel para inyección de dependencias
@DecoratedWith(PuestoMapperDecorator.class)
public interface PuestoMapper {

	@Named("DTOAentidad")
	@BeanMapping(ignoreByDefault = true)
	@Mapping(source = "id", target = "id")
	@Mapping(source = "nombre", target = "nombre")
	@Mapping(source = "coordenadaX", target = "coordenadaX")
	@Mapping(source = "coordenadaY", target = "coordenadaY")
	@Mapping(source = "ancho", target = "ancho")
	@Mapping(source = "alto", target = "alto")
	Puesto DTOAentidad(PuestoDTO dto);

	@InheritConfiguration(name = "DTOAentidad")
	Puesto DTOAentidad(PuestoDTO dto, @MappingTarget Puesto entidad);

	@Named("entidadADTO")
	@BeanMapping(ignoreByDefault = true)
	@Mapping(source = "id", target = "id")
	@Mapping(source = "nombre", target = "nombre")
	@Mapping(source = "sala.id", target = "idSala")
	@Mapping(source = "coordenadaX", target = "coordenadaX")
	@Mapping(source = "coordenadaY", target = "coordenadaY")
	@Mapping(source = "ancho", target = "ancho")
	@Mapping(source = "alto", target = "alto")
	PuestoDTO entidadADTO(Puesto entidad);

	@IterableMapping(qualifiedByName = "entidadADTO")
	List<PuestoDTO> entidadADTOList(List<Puesto> entidadList);

}
package com.uoc.reservofi.puesto.business.mapper;

import com.uoc.reservofi.puesto.business.dto.PuestoDTO;
import com.uoc.reservofi.puesto.business.dto.RestPuestoDTO;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring") // componentModel para inyección de dependencias
public interface RestPuestoMapper {

	@Named("restDTOADTO")
	@BeanMapping(ignoreByDefault = true)
	@Mapping(source = "id", target = "id")
	@Mapping(source = "nombre", target = "nombre")
	@Mapping(source = "coordenadaX", target = "coordenadaX")
	@Mapping(source = "coordenadaY", target = "coordenadaY")
	@Mapping(source = "ancho", target = "ancho")
	@Mapping(source = "alto", target = "alto")
	@Mapping(source = "idSala", target = "idSala")
	PuestoDTO restDTOADTO(RestPuestoDTO restDTO);

	@Named("DTOArestDTO")
	@BeanMapping(ignoreByDefault = true)
	@Mapping(source = "id", target = "id")
	@Mapping(source = "nombre", target = "nombre")
	@Mapping(source = "coordenadaX", target = "coordenadaX")
	@Mapping(source = "coordenadaY", target = "coordenadaY")
	@Mapping(source = "ancho", target = "ancho")
	@Mapping(source = "alto", target = "alto")
	@Mapping(source = "idSala", target = "idSala")
	RestPuestoDTO DTOArestDTO(PuestoDTO dto);

	@IterableMapping(qualifiedByName = "DTOArestDTO")
	List<RestPuestoDTO> DTOArestDTOList(List<PuestoDTO> entidadList);
}
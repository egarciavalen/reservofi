package com.uoc.reservofi.puesto.business.mapper;

import com.uoc.reservofi.puesto.business.dto.PuestoDTO;
import com.uoc.reservofi.puesto.business.dto.RestPuestoConsultaDTO;
import com.uoc.reservofi.puesto.business.dto.RestPuestoGuardadoDTO;
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
	PuestoDTO restDTOADTO(RestPuestoGuardadoDTO restDTO);

	@Named("DTOArestDTO")
	@BeanMapping(ignoreByDefault = true)
	@Mapping(source = "id", target = "id")
	@Mapping(source = "nombre", target = "nombre")
	@Mapping(source = "coordenadaX", target = "coordenadaX")
	@Mapping(source = "coordenadaY", target = "coordenadaY")
	@Mapping(source = "ancho", target = "ancho")
	@Mapping(source = "alto", target = "alto")
	@Mapping(source = "idSala", target = "idSala")
	RestPuestoConsultaDTO DTOArestDTO(PuestoDTO dto);

	@IterableMapping(qualifiedByName = "DTOArestDTO")
	List<RestPuestoConsultaDTO> DTOArestDTOList(List<PuestoDTO> entidadList);
}
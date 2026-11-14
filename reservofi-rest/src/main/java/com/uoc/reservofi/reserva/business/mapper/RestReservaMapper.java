package com.uoc.reservofi.reserva.business.mapper;

import com.uoc.reservofi.reserva.business.dto.ReservaDTO;
import com.uoc.reservofi.reserva.business.dto.RestReservaConsultaDTO;
import com.uoc.reservofi.reserva.business.dto.RestReservaGuardadoDTO;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RestReservaMapper {

	@Named("restDTOADTO")
	@BeanMapping(ignoreByDefault = true)
	@Mapping(source = "id", target = "id")
	@Mapping(source = "fechaInicio", target = "fechaInicio")
	@Mapping(source = "fechaFin", target = "fechaFin")
	@Mapping(source = "idUsuario", target = "idUsuario")
	@Mapping(source = "idPuesto", target = "idPuesto")
	ReservaDTO restDTOADTO(RestReservaGuardadoDTO restDTO);

	@Named("DTOArestDTO")
	@BeanMapping(ignoreByDefault = true)
	@Mapping(source = "id", target = "id")
	@Mapping(source = "fechaInicio", target = "fechaInicio")
	@Mapping(source = "fechaFin", target = "fechaFin")
	@Mapping(source = "idUsuario", target = "idUsuario")
	@Mapping(source = "correoCorporativo", target = "correoCorporativo")
	@Mapping(source = "idPuesto", target = "idPuesto")
	@Mapping(source = "nombreSala", target = "nombreSala")
	@Mapping(source = "nombrePuesto", target = "nombrePuesto")
	@Mapping(source = "rutaImagen", target = "rutaImagen")
	RestReservaConsultaDTO DTOArestDTO(ReservaDTO dto);

	@IterableMapping(qualifiedByName = "DTOArestDTO")
	List<RestReservaConsultaDTO> DTOArestDTOList(List<ReservaDTO> entidadList);

}
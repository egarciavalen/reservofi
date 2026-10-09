package com.uoc.reservofi.reserva.business.mapper;

import com.uoc.reservofi.reserva.business.dto.ReservaDTO;
import com.uoc.reservofi.reserva.business.dto.RestReservaDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface RestReservaMapper {

	@Named("restDTOADTO")
	@BeanMapping(ignoreByDefault = true)
	@Mapping(source = "id", target = "id")
	@Mapping(source = "fechaInicio", target = "fechaInicio")
	@Mapping(source = "fechaFin", target = "fechaFin")
	@Mapping(source = "idUsuario", target = "idUsuario")
	@Mapping(source = "idPuesto", target = "idPuesto")
	ReservaDTO restDTOADTO(RestReservaDTO restDTO);

	@Named("DTOArestDTO")
	@BeanMapping(ignoreByDefault = true)
	@Mapping(source = "id", target = "id")
	@Mapping(source = "fechaInicio", target = "fechaInicio")
	@Mapping(source = "fechaFin", target = "fechaFin")
	@Mapping(source = "idUsuario", target = "idUsuario")
	@Mapping(source = "idPuesto", target = "idPuesto")
	RestReservaDTO DTOArestDTO(ReservaDTO dto);
}
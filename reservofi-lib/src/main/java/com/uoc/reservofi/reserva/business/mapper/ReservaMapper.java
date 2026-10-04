package com.uoc.reservofi.reserva.business.mapper;

import com.uoc.reservofi.reserva.business.dto.ReservaDTO;
import com.uoc.reservofi.reserva.business.model.Reserva;
import org.mapstruct.*;

import java.util.List;

@Mapper
public interface ReservaMapper {

	@Named("DTOaEntidad")
	@BeanMapping(ignoreByDefault = true)
	@Mapping(source = "id", target = "id")
	@Mapping(source = "fechaInicio", target = "fechaInicio")
	@Mapping(source = "fechaFin", target = "fechaFin")
	Reserva DTOaEntidad(ReservaDTO dto);

	@Named("entidadADTO")
	@BeanMapping(ignoreByDefault = true)
	@Mapping(source = "id", target = "id")
	@Mapping(source = "usuario.id", target = "idUsuario")
	@Mapping(source = "fechaInicio", target = "fechaInicio")
	@Mapping(source = "fechaFin", target = "fechaFin")
	ReservaDTO entidadADTO(Reserva entidad);

	@IterableMapping(qualifiedByName = "entidadADTO")
	List<ReservaDTO> entidadADTOList(List<Reserva> entidadList);

	@IterableMapping(qualifiedByName = "DTOAentidad")
	List<Reserva> DTOaEntidadList(List<ReservaDTO> dtoList);

}
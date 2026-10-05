package com.uoc.reservofi.reserva.business.mapper;

import com.uoc.reservofi.reserva.business.dto.ReservaDTO;
import com.uoc.reservofi.reserva.business.model.Reserva;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
@DecoratedWith(ReservaMapperDecorator.class)
public interface ReservaMapper {

	@Named("DTOAentidad")
	@BeanMapping(ignoreByDefault = true)
	@Mapping(source = "id", target = "id")
	@Mapping(source = "fechaInicio", target = "fechaInicio")
	@Mapping(source = "fechaFin", target = "fechaFin")
	Reserva DTOAentidad(ReservaDTO dto);

	@InheritConfiguration(name = "DTOAentidad")
	Reserva DTOAentidad(ReservaDTO dto, @MappingTarget Reserva entidad);


	@Named("entidadADTO")
	@BeanMapping(ignoreByDefault = true)
	@Mapping(source = "id", target = "id")
	@Mapping(source = "usuario.id", target = "idUsuario")
	@Mapping(source = "puesto.id", target = "idPuesto")
	@Mapping(source = "fechaInicio", target = "fechaInicio")
	@Mapping(source = "fechaFin", target = "fechaFin")
	ReservaDTO entidadADTO(Reserva entidad);

	@IterableMapping(qualifiedByName = "entidadADTO")
	List<ReservaDTO> entidadADTOList(List<Reserva> entidadList);

	@IterableMapping(qualifiedByName = "DTOAentidad")
	List<Reserva> DTOAEntidadList(List<ReservaDTO> dtoList);

}
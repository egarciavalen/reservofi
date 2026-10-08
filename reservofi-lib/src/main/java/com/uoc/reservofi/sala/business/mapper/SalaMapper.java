package com.uoc.reservofi.sala.business.mapper;

import com.uoc.reservofi.sala.business.dto.SalaDTO;
import com.uoc.reservofi.sala.business.model.Sala;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SalaMapper {

	@Named("DTOAentidad")
	@BeanMapping(ignoreByDefault = true)
	@Mapping(source = "id", target = "id")
	@Mapping(source = "nombre", target = "nombre")
	Sala DTOAentidad(SalaDTO dto);

	@InheritConfiguration(name = "DTOAentidad")
	Sala DTOAentidad(SalaDTO dto, @MappingTarget Sala entidad);

	@Named("entidadADTO")
	@BeanMapping(ignoreByDefault = true)
	@Mapping(source = "id", target = "id")
	@Mapping(source = "nombre", target = "nombre")
	SalaDTO entidadADTO(Sala entidad);

	@IterableMapping(qualifiedByName = "entidadADTO")
	List<SalaDTO> entidadADTOList(List<Sala> entidadList);
}
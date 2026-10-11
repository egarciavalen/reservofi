package com.uoc.reservofi.sala.business.mapper;

import com.uoc.reservofi.sala.business.dto.RestSalaDTO;
import com.uoc.reservofi.sala.business.dto.SalaDTO;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RestSalaMapper {

	@Named("restDTOADTO")
	@BeanMapping(ignoreByDefault = true)
	@Mapping(source = "id", target = "id")
	@Mapping(source = "nombre", target = "nombre")
	SalaDTO restDTOADTO(RestSalaDTO restDTO);

	@Named("DTOArestDTO")
	@BeanMapping(ignoreByDefault = true)
	@Mapping(source = "id", target = "id")
	@Mapping(source = "nombre", target = "nombre")
	RestSalaDTO DTOArestDTO(SalaDTO dto);

	@IterableMapping(qualifiedByName = "DTOArestDTO")
	List<RestSalaDTO> DTOArestDTOList(List<SalaDTO> entidadList);

}
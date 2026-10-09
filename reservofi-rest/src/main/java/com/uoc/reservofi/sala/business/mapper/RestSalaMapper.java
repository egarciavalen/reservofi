package com.uoc.reservofi.sala.business.mapper;

import com.uoc.reservofi.sala.business.dto.RestSalaDTO;
import com.uoc.reservofi.sala.business.dto.SalaDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

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
}
package com.uoc.reservofi.sala.business.mapper;

import com.uoc.reservofi.sala.business.dto.SalaDTO;
import com.uoc.reservofi.sala.business.model.Sala;
import org.mapstruct.*;

import java.util.List;

@Mapper
public interface SalaMapper {

	@Named("DTOaEntidad")
	@BeanMapping(ignoreByDefault = true)
	@Mapping(source = "id", target = "id")
	@Mapping(source = "nombre", target = "nombre")
	Sala DTOaEntidad(SalaDTO dto);

	@Named("entidadADTO")
	@BeanMapping(ignoreByDefault = true)
	@Mapping(source = "id", target = "id")
	@Mapping(source = "nombre", target = "nombre")
	SalaDTO entidadADTO(Sala entidad);

	@IterableMapping(qualifiedByName = "entidadADTO")
	List<SalaDTO> entidadADTOList(List<Sala> entidadList);

	@IterableMapping(qualifiedByName = "DTOAentidad")
	List<Sala> DTOaEntidadList(List<SalaDTO> dtoList);

}
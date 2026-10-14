package com.uoc.reservofi.sala.business.mapper;

import com.uoc.reservofi.sala.business.dto.RestSalaConsultaDTO;
import com.uoc.reservofi.sala.business.dto.RestSalaGuardadoDTO;
import com.uoc.reservofi.sala.business.dto.SalaDTO;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RestSalaMapper {

	@Named("modificacionRestDTOADTO")
	@BeanMapping(ignoreByDefault = true)
	@Mapping(source = "id", target = "id")
	@Mapping(source = "nombre", target = "nombre")
	@Mapping(source = "imagen", target = "imagen")
	@Mapping(source = "rutaImagen", target = "rutaImagen")
	SalaDTO modificacionRestDTOADTO(RestSalaGuardadoDTO restDTO);

	@Named("consultaDTOArestDTO")
	@BeanMapping(ignoreByDefault = true)
	@Mapping(source = "id", target = "id")
	@Mapping(source = "nombre", target = "nombre")
	@Mapping(source = "rutaImagen", target = "rutaImagen")
	@Mapping(source = "nombreImagen", target = "nombreImagen")
	RestSalaConsultaDTO consultaDTOArestDTO(SalaDTO dto);

	@IterableMapping(qualifiedByName = "consultaDTOArestDTO")
	List<RestSalaConsultaDTO> consultaDTOArestDTOList(List<SalaDTO> entidadList);

}
package com.uoc.reservofi.puesto.business.mapper;

import com.uoc.reservofi.puesto.business.dto.PuestoDTO;
import com.uoc.reservofi.puesto.business.model.Puesto;
import com.uoc.reservofi.sala.business.model.Sala;
import com.uoc.reservofi.sala.business.service.base.BaseSalaService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class PuestoMapperDecorator implements PuestoMapper {

	private final PuestoMapper mapper;

	// Servicios de entidades asociadas
	private final BaseSalaService baseSalaService;

	@Override
	public Puesto DTOaEntidad(PuestoDTO dto) {
		Puesto entidad = mapper.DTOaEntidad(dto);

		// Sala
		if (dto.getIdSala() != null) {
			Sala sala = baseSalaService.findById(dto.getIdSala());
			entidad.setSala(sala);
		}
		return entidad;
	}

}

package com.uoc.reservofi.puesto.business.mapper;

import com.uoc.reservofi.puesto.business.dto.PuestoDTO;
import com.uoc.reservofi.puesto.business.model.Puesto;
import com.uoc.reservofi.sala.business.model.Sala;
import com.uoc.reservofi.sala.business.service.base.BaseSalaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@RequiredArgsConstructor
public abstract class PuestoMapperDecorator implements PuestoMapper {

	// Uso de @Autowired por problemas con el constructor por defecto

	@Autowired
	private PuestoMapper mapper;

	// Servicios de entidades asociadas
	@Autowired
	private BaseSalaService baseSalaService;

	@Override
	public Puesto DTOAentidad(PuestoDTO dto) {
		Puesto entidad = mapper.DTOAentidad(dto);

		// Sala
		if (dto.getIdSala() != null) {
			Sala sala = baseSalaService.findById(dto.getIdSala());
			entidad.setSala(sala);
		}
		return entidad;
	}

}

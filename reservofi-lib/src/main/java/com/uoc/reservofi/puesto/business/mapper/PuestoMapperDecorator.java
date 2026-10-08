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
		return this.gestionarRelaciones(dto, null);
	}

	@Override
	public Puesto DTOAentidad(PuestoDTO dto, Puesto entidad) {
		return this.gestionarRelaciones(dto, entidad);
	}

	private Puesto gestionarRelaciones(PuestoDTO dto, Puesto entidad) {

		Puesto puestoRelacion;

		if (entidad == null) {
			puestoRelacion = mapper.DTOAentidad(dto);
		} else {
			puestoRelacion = mapper.DTOAentidad(dto, entidad);
		}

		// Sala
		if (dto.getIdSala() != null) {
			Sala sala = baseSalaService.findById(dto.getIdSala());
			puestoRelacion.setSala(sala);
		}
		return puestoRelacion;
	}
}

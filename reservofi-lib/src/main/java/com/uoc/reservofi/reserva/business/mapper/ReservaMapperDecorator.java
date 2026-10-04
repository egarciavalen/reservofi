package com.uoc.reservofi.reserva.business.mapper;

import com.uoc.reservofi.puesto.business.model.Puesto;
import com.uoc.reservofi.puesto.business.service.base.BasePuestoService;
import com.uoc.reservofi.reserva.business.dto.ReservaDTO;
import com.uoc.reservofi.reserva.business.model.Reserva;
import com.uoc.reservofi.usuario.business.model.Usuario;
import com.uoc.reservofi.usuario.business.service.base.BaseUsuarioService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class ReservaMapperDecorator implements ReservaMapper {

	private final ReservaMapper mapper;

	// Servicios de entidades asociadas
	private final BaseUsuarioService baseUsuarioService;
	private final BasePuestoService basePuestoService;

	@Override
	public Reserva DTOaEntidad(ReservaDTO dto) {
		Reserva entidad = mapper.DTOaEntidad(dto);

		// Usuario
		if (dto.getIdUsuario() != null) {
			Usuario usuario = baseUsuarioService.findById(dto.getIdUsuario());
			entidad.setUsuario(usuario);
		}

		// Puesto
		if (dto.getIdPuesto() != null) {
			Puesto puesto = basePuestoService.findById(dto.getIdPuesto());
			entidad.setPuesto(puesto);
		}

		return entidad;
	}

}

package com.uoc.reservofi.reserva.business.mapper;

import com.uoc.reservofi.puesto.business.model.Puesto;
import com.uoc.reservofi.puesto.business.service.base.BasePuestoService;
import com.uoc.reservofi.reserva.business.dto.ReservaDTO;
import com.uoc.reservofi.reserva.business.model.Reserva;
import com.uoc.reservofi.usuario.business.model.Usuario;
import com.uoc.reservofi.usuario.business.service.base.BaseUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class ReservaMapperDecorator implements ReservaMapper {

	// Uso de @Autowired por problemas con el constructor por defecto

	@Autowired
	private ReservaMapper mapper;

	// Servicios de entidades asociadas
	@Autowired
	private BaseUsuarioService baseUsuarioService;

	@Autowired
	private BasePuestoService basePuestoService;

	@Override
	public Reserva DTOAentidad(ReservaDTO dto) {
		Reserva entidad = mapper.DTOAentidad(dto);

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

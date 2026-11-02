package com.uoc.reservofi.reserva.business.service.impl;

import com.uoc.reservofi.base.busines.exception.GestionException;
import com.uoc.reservofi.base.busines.service.impl.AbstractGestionEntidadServiceImpl;
import com.uoc.reservofi.reserva.business.dto.ReservaDTO;
import com.uoc.reservofi.reserva.business.mapper.ReservaMapper;
import com.uoc.reservofi.reserva.business.model.Reserva;
import com.uoc.reservofi.reserva.business.service.GestionReservaService;
import com.uoc.reservofi.reserva.business.service.base.BaseReservaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(GestionReservaService.BEAN_NAME)
@RequiredArgsConstructor
@Transactional(rollbackFor = GestionException.class)
public class GestionReservaServiceImpl extends AbstractGestionEntidadServiceImpl<ReservaDTO> implements GestionReservaService {

	private final BaseReservaService baseReservaService;
	private final ReservaMapper reservaMapper;

	@Override
	public ReservaDTO insert(@Valid ReservaDTO dto) {
		Reserva entidad = reservaMapper.DTOAentidad(dto);
		entidad = baseReservaService.insert(entidad);
		return reservaMapper.entidadADTO(entidad);
	}

	@Override
	public ReservaDTO update(@Valid ReservaDTO dto) {

		if (dto.getId() == null) {
			throw new GestionException("La entidad que se quiere actualizar ha de tener el campo identificador");
		}

		Reserva entidadBD = baseReservaService.findById(dto.getId());

		if (entidadBD == null) {
			throw new GestionException("Se está intentando actualizar una entidad no existente");
		}

		Reserva entidad = reservaMapper.DTOAentidad(dto, entidadBD);
		entidad = baseReservaService.update(entidad);
		return reservaMapper.entidadADTO(entidad);
	}

	@Override
	public void deleteById(Long id) {
		baseReservaService.deleteById(id);
	}

	@Override
	public List<ReservaDTO> findAll() {
		List<Reserva> entidadList = baseReservaService.findAll();
		return reservaMapper.entidadADTOList(entidadList);
	}

	@Override
	public ReservaDTO findById(Long id) {
		Reserva entidad = baseReservaService.findById(id);
		return reservaMapper.entidadADTO(entidad);
	}

	@Override
	public List<ReservaDTO> findAllById(List<Long> ids) {
		List<Reserva> entidadList = baseReservaService.findAllById(ids);
		return reservaMapper.entidadADTOList(entidadList);
	}

	@Override
	public boolean existsById(Long id) {
		return baseReservaService.existsById(id);
	}

	@Override
	public List<ReservaDTO> findByCreacionUsuario(String usuario) {
		List<Reserva> entidadList = baseReservaService.findByCreacionUsuario(usuario);
		return reservaMapper.entidadADTOList(entidadList);
	}

	@Override
	public List<ReservaDTO> findByActualizacionUsuario(String usuario) {
		List<Reserva> entidadList = baseReservaService.findByActualizacionUsuario(usuario);
		return reservaMapper.entidadADTOList(entidadList);
	}

	@Override
	public List<ReservaDTO> findByUsuarioId(Long idUsuario) {
		List<Reserva> entidadList = baseReservaService.findByUsuarioId(idUsuario);
		return reservaMapper.entidadADTOList(entidadList);
	}

	@Override
	public void deleteByPuestoId(Long idPuesto) {

		// Se hace de esta forma para facilitar la depuración en caso de ser necesario
		// Si se quisiera ser más óptimo, bastaría con hacer una consulta deleteByPuestoId directamente

		List<Reserva> reservaList = baseReservaService.findByPuestoId(idPuesto);

		for (Reserva reserva: reservaList) {
			this.baseReservaService.deleteById(reserva.id);
		}
	}
}

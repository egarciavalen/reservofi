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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.util.List;
import java.util.Objects;

@Service(GestionReservaService.BEAN_NAME)
@RequiredArgsConstructor
@Transactional(rollbackFor = GestionException.class)
public class GestionReservaServiceImpl extends AbstractGestionEntidadServiceImpl<ReservaDTO> implements GestionReservaService {

	private static final Logger LOGGER = LoggerFactory.getLogger(GestionReservaServiceImpl.class);
	private final BaseReservaService baseReservaService;
	private final ReservaMapper reservaMapper;

	@Override
	public ReservaDTO insert(@Valid ReservaDTO dto) {

		// Validaciones previas a reservar la entidad
		validateReserva(dto);
		Reserva entidad = reservaMapper.DTOAentidad(dto);
		entidad = baseReservaService.insert(entidad);
		return reservaMapper.entidadADTO(entidad);
	}

	@Override
	public ReservaDTO update(@Valid ReservaDTO dto) {

		if (dto.getId() == null) {
			LOGGER.error("Ha ocurrido un error al actualizar la reserva en el sistema. Identificador del usuario asociado: {}", dto.getIdUsuario());
			throw new GestionException("La entidad que se quiere actualizar ha de tener el campo identificador");
		}

		Reserva entidadBD = baseReservaService.findById(dto.getId());

		if (entidadBD == null) {
			LOGGER.error("Se está intentando actualizar una entidad no existente. Identificador de la reserva: {}", dto.getId());
			throw new GestionException("Se está intentando actualizar una entidad no existente");
		}

		// Validaciones previas a reservar la entidad
		validateReserva(dto);

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

		if (entidad == null) {
			return null;
		}

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

		if (reservaList != null && !reservaList.isEmpty()) {
			for (Reserva reserva : reservaList) {
				this.baseReservaService.deleteById(reserva.id);
			}
			LOGGER.debug("Se han borrado correctamente las reservas para el puesto con identificador: {}", idPuesto);
		} else {
			LOGGER.debug("No se han encontrado reservas para borrar para el puesto con identificador: {}", idPuesto);
		}
	}


	private void validateReserva(@Valid ReservaDTO dto) {

		// La fecha de fin no puede ser anterior a la fecha de inicio
		if (dto.getFechaInicio().isAfter(dto.getFechaFin())) {
			throw new GestionException("La fecha de fin no puede ser anterior a la fecha de inicio.");
		}

		// No se puede hacer una reserva con una diferencia superior a 7 días
		double milisegundosInicio = dto.getFechaInicio().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
		double milisegundosFin = dto.getFechaFin().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

		// 7 días son 604800000 milisegundos
		if (milisegundosInicio - milisegundosFin > 604800000) {
			throw new GestionException("No se pueden hacer reservas superiores a 7 días.");
		}

		// Listado de reservas para hacer validaciones
		List<ReservaDTO> reservaUsuarioDTOList = this.findByUsuarioId(dto.getIdUsuario());
		List<ReservaDTO> reservaDTOList = this.findAll();

		// No se puede reservar el mismo día y a la misma hora para un mismo usuario
		for (ReservaDTO reservaUsuario : reservaUsuarioDTOList) {
			if (dto.getFechaInicio().isAfter(reservaUsuario.getFechaInicio()) && dto.getFechaInicio().isBefore(reservaUsuario.getFechaFin())
					|| dto.getFechaFin().isAfter(reservaUsuario.getFechaInicio()) && dto.getFechaFin().isBefore(reservaUsuario.getFechaFin())) {
				throw new GestionException("La reserva que se quiere hacer coincide con otra reserva propia en el sistema.");
			}
		}

		// Dos usuarios no pueden tener reservado el mismo sitio a la misma hora en el mismo sitio
		for (ReservaDTO reservaTotal : reservaDTOList) {
			if (dto.getFechaInicio().isAfter(reservaTotal.getFechaInicio()) && dto.getFechaInicio().isBefore(reservaTotal.getFechaFin())
					|| dto.getFechaFin().isAfter(reservaTotal.getFechaInicio()) && dto.getFechaFin().isBefore(reservaTotal.getFechaFin())) {
				// Comprobación adicional: tiene que ser en el mismo puesto
				if (Objects.equals(dto.getIdPuesto(), reservaTotal.getIdPuesto())) {
					throw new GestionException("La reserva que se quiere hacer coincide con otra reserva hecha por el usuario.");
				}
			}
		}
	}

}

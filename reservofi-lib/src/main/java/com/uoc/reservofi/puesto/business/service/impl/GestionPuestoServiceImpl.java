package com.uoc.reservofi.puesto.business.service.impl;

import com.uoc.reservofi.base.busines.exception.GestionException;
import com.uoc.reservofi.base.busines.service.impl.AbstractGestionEntidadServiceImpl;
import com.uoc.reservofi.puesto.business.dto.PuestoDTO;
import com.uoc.reservofi.puesto.business.mapper.PuestoMapper;
import com.uoc.reservofi.puesto.business.model.Puesto;
import com.uoc.reservofi.puesto.business.service.GestionPuestoService;
import com.uoc.reservofi.puesto.business.service.base.BasePuestoService;
import com.uoc.reservofi.reserva.business.service.GestionReservaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(GestionPuestoService.BEAN_NAME)
@RequiredArgsConstructor
@Transactional(rollbackFor = GestionException.class)
public class GestionPuestoServiceImpl extends AbstractGestionEntidadServiceImpl<PuestoDTO> implements GestionPuestoService {

	private static final Logger LOGGER = LoggerFactory.getLogger(GestionPuestoServiceImpl.class);

	private final BasePuestoService basePuestoService;
	private final PuestoMapper puestoMapper;
	private final GestionReservaService gestionReservaService;

	@Override
	public PuestoDTO insert(@Valid PuestoDTO dto) {
		Puesto entidad = puestoMapper.DTOAentidad(dto);
		entidad = basePuestoService.insert(entidad);
		return puestoMapper.entidadADTO(entidad);
	}

	@Override
	public PuestoDTO update(@Valid PuestoDTO dto) {

		if (dto.getId() == null) {
			LOGGER.error("Ha ocurrido un error al actualizar el puesto en el sistema. Nombre del puesto: {}", dto.getNombre());
			throw new GestionException("No se ha podido procesar la actualización del elemento. Por favor, contacte con el administrador.");
		}

		Puesto entidadBD = basePuestoService.findById(dto.getId());

		if (entidadBD == null) {
			LOGGER.error("Se está intentando actualizar una entidad no existente. Identificador del puesto: {}", dto.getId());
			throw new GestionException("No se ha podido procesar la actualización del elemento. Por favor, contacte con el administrador.");
		}

		Puesto entidad = puestoMapper.DTOAentidad(dto, entidadBD);
		entidad = basePuestoService.update(entidad);
		return puestoMapper.entidadADTO(entidad);
	}

	@Override
	public void deleteById(Long id) {

		// Previamente a borrar el puesto, hay que borrar las reservas asociadas
		this.gestionReservaService.deleteByPuestoId(id);
		basePuestoService.deleteById(id);
	}

	@Override
	public List<PuestoDTO> findAll() {
		List<Puesto> entidadList = basePuestoService.findAll();
		return puestoMapper.entidadADTOList(entidadList);
	}

	@Override
	public PuestoDTO findById(Long id) {
		Puesto entidad = basePuestoService.findById(id);

		if (entidad == null) {
			return null;
		}

		return puestoMapper.entidadADTO(entidad);
	}

	@Override
	public List<PuestoDTO> findAllById(List<Long> ids) {
		List<Puesto> entidadList = basePuestoService.findAllById(ids);
		return puestoMapper.entidadADTOList(entidadList);
	}

	@Override
	public boolean existsById(Long id) {
		return basePuestoService.existsById(id);
	}

	@Override
	public List<PuestoDTO> findByCreacionUsuario(String usuario) {
		List<Puesto> entidadList = basePuestoService.findByCreacionUsuario(usuario);
		return puestoMapper.entidadADTOList(entidadList);
	}

	@Override
	public List<PuestoDTO> findByActualizacionUsuario(String usuario) {
		List<Puesto> entidadList = basePuestoService.findByActualizacionUsuario(usuario);
		return puestoMapper.entidadADTOList(entidadList);
	}

	@Override
	public void deleteBySalaId(Long idSala) {
		// Se hace de esta forma para facilitar la depuración en caso de ser necesario
		// Si se quisiera ser más óptimo, bastaría con hacer una consulta deleteBySalaId directamente

		List<Puesto> puestoList = basePuestoService.findBySalaId(idSala);

		if (puestoList != null && !puestoList.isEmpty()) {
			for (Puesto puesto : puestoList) {
				// OJO -> se llama al servicio de gestión para poder eliminar las reservas asociadas
				this.deleteById(puesto.id);
			}
			LOGGER.debug("Se han borrado correctamente los puestos para la sala con identificador: {}", idSala);
		} else {
			LOGGER.debug("No se han encontrado puestos para borrar para la sala con identificador: {}", idSala);
		}
	}
}

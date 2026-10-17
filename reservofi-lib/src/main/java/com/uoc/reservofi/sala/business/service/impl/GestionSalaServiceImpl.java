package com.uoc.reservofi.sala.business.service.impl;

import com.uoc.reservofi.base.busines.exception.GestionException;
import com.uoc.reservofi.base.busines.service.impl.AbstractGestionEntidadServiceImpl;
import com.uoc.reservofi.puesto.business.service.GestionPuestoService;
import com.uoc.reservofi.sala.business.dto.SalaDTO;
import com.uoc.reservofi.sala.business.mapper.SalaMapper;
import com.uoc.reservofi.sala.business.model.Sala;
import com.uoc.reservofi.sala.business.service.GestionSalaService;
import com.uoc.reservofi.sala.business.service.base.BaseSalaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;

@Service(GestionSalaService.BEAN_NAME)
@RequiredArgsConstructor
public class GestionSalaServiceImpl extends AbstractGestionEntidadServiceImpl<SalaDTO> implements GestionSalaService {

	private final BaseSalaService baseSalaService;
	private final SalaMapper salaMapper;

	private final GestionPuestoService gestionPuestoService;

	@Override
	public SalaDTO insert(@Valid SalaDTO dto) {
		Sala entidad = salaMapper.DTOAentidad(dto);
		entidad = baseSalaService.insert(entidad);
		return salaMapper.entidadADTO(entidad);
	}

	@Override
	public SalaDTO update(@Valid SalaDTO dto) {

		if (dto.getId() == null) {
			throw new GestionException("La entidad que se quiere actualizar ha de tener el campo identificador");
		}

		Sala entidadBD = baseSalaService.findById(dto.getId());

		if (entidadBD == null) {
			throw new GestionException("Se está intentando actualizar una entidad no existente");
		}

		// En el caso de una sala, solamente se actualizaría el campo nombre, manteniendo la imagen existente
		entidadBD.setNombre(dto.getNombre());
		entidadBD = baseSalaService.update(entidadBD);
		return salaMapper.entidadADTO(entidadBD);
	}

	@Override
	public void deleteById(Long id) {

		// Previamente a borrar la sala, hay que borrar los puestos asociados (que a su vez borrarán las reservas)
		this.gestionPuestoService.deleteBySalaId(id);

		baseSalaService.deleteById(id);
	}


	@Override
	public List<SalaDTO> findAll() {
		List<Sala> entidadList = baseSalaService.findAll();
		return salaMapper.entidadADTOList(entidadList);
	}

	@Override
	public SalaDTO findById(Long id) {
		Sala entidad = baseSalaService.findById(id);
		return salaMapper.entidadADTO(entidad);
	}

	@Override
	public List<SalaDTO> findAllById(List<Long> ids) {
		List<Sala> entidadList = baseSalaService.findAllById(ids);
		return salaMapper.entidadADTOList(entidadList);
	}

	@Override
	public boolean existsById(Long id) {
		return baseSalaService.existsById(id);
	}

	@Override
	public List<SalaDTO> findByCreacionUsuario(String usuario) {
		List<Sala> entidadList = baseSalaService.findByCreacionUsuario(usuario);
		return salaMapper.entidadADTOList(entidadList);
	}

	@Override
	public List<SalaDTO> findByActualizacionUsuario(String usuario) {
		List<Sala> entidadList = baseSalaService.findByActualizacionUsuario(usuario);
		return salaMapper.entidadADTOList(entidadList);
	}

	@Override
	public SalaDTO createAndSaveImage(SalaDTO dto) {

		try {
			// Validaciones previas
			if (dto.getImagen().getOriginalFilename() == null) {
				throw new GestionException("No se ha podido recuperar el nombre de la imagen");
			}

			if (dto.getImagen().getContentType() == null) {
				throw new GestionException("No se ha podido recuperar información del tipo de la imagen");
			}

			if (!(dto.getImagen().getContentType().equals("image/png") || dto.getImagen().getContentType().equals("image/jpeg"))) {
				throw new GestionException("Los formatos que se admiten son png y jpeg");
			}

			// Primero hay que crear el directorio (si no existe), porque de lo contrario dará un AccessDeniedException
			String rutaDirectorio = "reservofi-lib/resources/images/";
			Path directorio = Path.of(rutaDirectorio);
			if (!Files.exists(directorio)) {
				Files.createDirectories(directorio);
			}

			// Una vez creado el directorio, se puede proceder a indicar el path del fichero
			// Uso de UUID.randomUUID() para protegerse en caso de que se suban ficheros con nombres iguales
			String rutaFichero = rutaDirectorio + UUID.randomUUID() + "_" + dto.getImagen().getOriginalFilename();
			Path fichero = Path.of(rutaFichero);

			// Se escriben los bytes del fichero en la ruta destino
			Files.write(fichero, dto.getImagen().getBytes());

			// Asigna en el DTO la ruta de la imagen guardada y su nombre
			dto.setRutaImagen(rutaFichero);
			dto.setNombreImagen(dto.getImagen().getOriginalFilename());

			return this.insert(dto);

		} catch (IOException e) {
			throw new GestionException("Ha ocurrido un error guardando la imagen en la base de datos: " + e);
		}
	}

	@Override
	public void deleteWithImage(Long id) {
		try {
			// Búsqueda de la sala
			SalaDTO salaDTO = this.findById(id);

			// Validaciones
			if (salaDTO == null) {
				throw new GestionException("Se está intentando borrar una sala no existente");
			}
			if (salaDTO.getRutaImagen() != null) {
				Path ficheroAntiguo = Path.of(salaDTO.getRutaImagen());
				Files.delete(ficheroAntiguo);
			}
		} catch (IOException e) {
			throw new GestionException("Ha ocurrido un error interno al intentar borrar la imagen asociada a la sala");
		}

		// Todo OK -> procedemos a borrar la sala
		this.deleteById(id);
	}

}

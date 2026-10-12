package com.uoc.reservofi.sala.business.service.impl;

import com.uoc.reservofi.base.busines.exception.GestionException;
import com.uoc.reservofi.base.busines.service.impl.AbstractGestionEntidadServiceImpl;
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

		Sala entidad = salaMapper.DTOAentidad(dto, entidadBD);
		entidad = baseSalaService.update(entidad);
		return salaMapper.entidadADTO(entidad);
	}

	@Override
	public void deleteById(Long id) {
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

			String rutaDirectorio = "src/main/resources/static/images/";
			// Uso de UUID.randomUUID() para protegerse en caso de que se suban ficheros con nombres iguales
			String rutaFichero = rutaDirectorio + UUID.randomUUID() + "_" + dto.getImagen().getOriginalFilename();

			// Objeto Path obtenido de la ruta del fichero
			Path fichero = Path.of(rutaFichero);
			// Si NO existe la ruta del directorio, se crea
			if (!Files.exists(fichero)) {
				Files.createDirectories(fichero);
			}

			// Se escriben los bytes del fichero en la ruta destino
			Files.write(fichero, dto.getImagen().getBytes());

			// Asigna en el DTO la ruta de la imagen guardada
			dto.setRutaImagen(rutaFichero);

			// Finalmente, se inserta la sala a crear, guardando en base de datos la ruta relativa
			return this.insert(dto);

		} catch (IOException e) {
			throw new GestionException("Ha ocurrido un error guardando la imagen en la base de datos: " + e);
		}
	}
}

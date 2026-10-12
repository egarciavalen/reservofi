package com.uoc.reservofi.sala.business.dto;

import com.uoc.reservofi.base.busines.dto.GestionEntidadDTO;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class SalaDTO extends GestionEntidadDTO {

	@NotEmpty(message = "sala.nombre.obligatorio")
	@Size(max = 100, message = "sala.nombre.tamanio.maximo")
	private String nombre;

	@NotEmpty(message = "sala.rutaImagen.obligatorio")
	@Size(max = 255, message = "sala.rutaImagen.tamanio.maximo")
	private String rutaImagen;

	private MultipartFile imagen; // Gestión de la imagen del servicio rest
}

package com.uoc.reservofi.sala.business.dto;

import com.uoc.reservofi.base.business.dto.RestEntidadDTO;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class RestSalaGuardadoDTO extends RestEntidadDTO {

	@NotEmpty(message = "sala.nombre.obligatorio")
	@Size(max = 100, message = "sala.nombre.tamanio.maximo")
	private String nombre;

	@NotNull(message = "sala.imagen.obligatorio")
	private MultipartFile imagen;
}

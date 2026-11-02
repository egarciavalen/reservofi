package com.uoc.reservofi.autenticacion.business.dto;

import com.uoc.reservofi.base.business.dto.RestEntidadDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestLoginRequestDTO extends RestEntidadDTO {
	private String correoCorporativo;
	private String contrasenia;

}

package com.uoc.reservofi.autenticacion.business.dto;

import com.uoc.reservofi.base.business.dto.RestEntidadDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RestRegisterResponseDTO extends RestEntidadDTO {

	private String nombre;

	private String primerApellido;

	private String segundoApellido;
	
	private String correoCorporativo;

	private Boolean activo;

	private List<Long> idsRoles;
}

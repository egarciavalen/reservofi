package com.uoc.reservofi.usuario.business.dto;

import com.uoc.reservofi.base.business.dto.RestEntidadConsultaDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RestUsuarioConsultaDTO extends RestEntidadConsultaDTO {

	private String nombre;
	private String primerApellido;
	private String segundoApellido;
	private String correoCorporativo;
	private Boolean activo;
	private List<RestUsuarioConsultaRolDTO> roles;
}

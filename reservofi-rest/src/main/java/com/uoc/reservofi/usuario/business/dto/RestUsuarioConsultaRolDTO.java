package com.uoc.reservofi.usuario.business.dto;

import com.uoc.reservofi.base.business.dto.RestEntidadConsultaDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestUsuarioConsultaRolDTO extends RestEntidadConsultaDTO {

	private String nombre;
}

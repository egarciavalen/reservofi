package com.uoc.reservofi.rol.dto;

import com.uoc.reservofi.base.busines.dto.GestionEntidadActualizableDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RolDTO extends GestionEntidadActualizableDTO {

    private String nombre;
}

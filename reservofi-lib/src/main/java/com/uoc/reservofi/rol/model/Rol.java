package com.uoc.reservofi.rol.model;

import com.uoc.reservofi.base.busines.model.BaseEntidadActualizable;
import jakarta.persistence.*;

@Table(name="Rol",uniqueConstraints = @UniqueConstraint(
        name = "uq_rol_nombre",
        columnNames = "nombre")
)
public class Rol extends BaseEntidadActualizable {
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

}

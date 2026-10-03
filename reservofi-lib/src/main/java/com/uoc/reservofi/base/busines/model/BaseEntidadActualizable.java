package com.uoc.reservofi.base.busines.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

public class BaseEntidadActualizable extends BaseEntidad {

    @Column(name = "creacion_usuario", nullable = false, length = 100)
    public String creacionUsuario;

    @Column(name = "creacion_fecha", nullable = false)
    public LocalDateTime creacionFecha;

    @Column(name = "actualizacion_usuario", nullable = true, length = 100)
    @LastModifiedBy
    public String actualizacionUsuario;

    @Column(name = "actualizacion_fecha", nullable = true)
    @LastModifiedDate
    public LocalDateTime actualizacionFecha;

}

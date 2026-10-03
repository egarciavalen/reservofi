package com.uoc.reservofi.base.busines.model;

import jakarta.persistence.Column;

import java.time.LocalDateTime;

public class BaseEntidadActualizable extends BaseEntidad {

    @Column(name = "creacion_usuario", nullable = false, length = 100)
    private String creacionUsuario;

    @Column(name = "creacion_fecha", nullable = false)
    private LocalDateTime creacionFecha;

    @Column(name = "actualizacion_usuario", nullable = true, length = 100)
    private String actualizacionUsuario;

    @Column(name = "actualizacion_fecha", nullable = true)
    private LocalDateTime actualizacionFecha;

}

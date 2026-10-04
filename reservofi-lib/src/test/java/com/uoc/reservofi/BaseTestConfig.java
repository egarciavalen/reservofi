package com.uoc.reservofi;

import org.springframework.boot.autoconfigure.SpringBootApplication;


// Punto de entrada para que arranquen los tests
// Se tiene que indicar aquí porque el punto de entrada "real" está en el módulo core -> dependencia circular
// Se tiene que colocar en este paquete para que pueda escanear a partir de com.reservofi
@SpringBootApplication
public class BaseTestConfig {
}

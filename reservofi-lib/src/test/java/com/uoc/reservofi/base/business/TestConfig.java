package com.uoc.reservofi.base.business;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableAutoConfiguration // Evita tener que definir a mano todos los beans asociados a las pruebas
@ComponentScan("com.uoc.reservofi") // Escaneo de servicios y otros beans asociado
@EnableJpaRepositories("com.uoc.reservofi") // Escaneo de repositorios que extienden de JpaRepository
@EntityScan("com.uoc.reservofi") // Escaneo de entidades
public class TestConfig {
}

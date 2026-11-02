package com.uoc.reservofi.base.business;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

// Inicializa la configuración para las pruebas
@SpringBootTest(classes = TestConfig.class)
// Perfil de test para poder almacenar datos de auditoría
@ActiveProfiles("test")
@Sql(scripts = "/script_test_database.sql", executionPhase = BEFORE_TEST_METHOD)
public abstract class AbstractTest {
}

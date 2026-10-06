package com.uoc.reservofi.base.business;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

// Inicializa la configuración para las pruebas
@SpringBootTest(classes = TestConfig.class)
// Perfil de test para poder almacenar datos de auditoría
@ActiveProfiles("test")
// Limpieza de la base de datos entre pruebas
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public abstract class AbstractBaseTest {
}

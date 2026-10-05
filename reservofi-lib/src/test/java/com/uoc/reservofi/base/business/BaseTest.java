package com.uoc.reservofi.base.business;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = BaseTestConfig.class) // Inicializa la configuración para las pruebas
@ActiveProfiles("test") // Perfil de test para poder almacenar datos de auditoría
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
// Limpieza de la base de datos entre pruebas
public class BaseTest {
}

package com.uoc.reservofi.rol.business.base;

import com.uoc.reservofi.BaseTestConfig;
import com.uoc.reservofi.rol.business.model.Rol;
import com.uoc.reservofi.rol.business.service.base.BaseRolService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = BaseTestConfig.class)
public class BaseRolServiceTest {

	@Autowired
	private BaseRolService baseRolService;

	@Test
	void findById() {
		Rol rol = baseRolService.findById(1L);
		assertNotNull(rol);
	}
}

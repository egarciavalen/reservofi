package com.uoc.reservofi.rol.business.base;

import com.uoc.reservofi.BaseTestConfig;
import com.uoc.reservofi.rol.business.model.Rol;
import com.uoc.reservofi.rol.business.service.base.BaseRolService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = BaseTestConfig.class)
public class BaseRolServiceTest {

	@Autowired
	private BaseRolService baseRolService;

	// TODO: INSERT, UPDATE Y DELETE

	@Test
	void findByIdTest() {
		Rol rol = baseRolService.findById(1L);
		assertNotNull(rol);
		assertEquals(1, rol.getId());
		assertEquals("Administrador", rol.getNombre());
		assertEquals("admin", rol.getCreacionUsuario());
		assertNotNull(rol.getCreacionFecha());
		assertNull(rol.getActualizacionUsuario());
		assertNull(rol.getActualizacionFecha());
	}

	@Test
	void findAllTest() {
		List<Rol> roles = baseRolService.findAll();
		assertNotNull(roles);
		assertEquals(2, roles.size());

		Rol rol1 = roles.getFirst();
		assertEquals(1, rol1.getId());
		assertEquals("Administrador", rol1.getNombre());
		assertEquals("admin", rol1.getCreacionUsuario());
		assertNotNull(rol1.getCreacionFecha());
		assertNull(rol1.getActualizacionUsuario());
		assertNull(rol1.getActualizacionFecha());

		Rol rol2 = roles.get(1);
		assertEquals(2, rol2.getId());
		assertEquals("Empleado", rol2.getNombre());
		assertEquals("admin", rol2.getCreacionUsuario());
		assertNotNull(rol2.getCreacionFecha());
		assertEquals("admin", rol2.getActualizacionUsuario());
		assertNotNull(rol2.getActualizacionFecha());
	}

	@Test
	void findAllByIdTest() {
		List<Long> idsList = new ArrayList<>();
		idsList.add(1L);
		idsList.add(2L);

		List<Rol> roles = baseRolService.findAllById(idsList);
		assertNotNull(roles);
		assertEquals(2, roles.size());

		Rol rol1 = roles.getFirst();
		assertEquals(1, rol1.getId());
		assertEquals("Administrador", rol1.getNombre());
		assertEquals("admin", rol1.getCreacionUsuario());
		assertNotNull(rol1.getCreacionFecha());
		assertNull(rol1.getActualizacionUsuario());
		assertNull(rol1.getActualizacionFecha());

		Rol rol2 = roles.get(1);
		assertEquals(2, rol2.getId());
		assertEquals("Empleado", rol2.getNombre());
		assertEquals("admin", rol2.getCreacionUsuario());
		assertNotNull(rol2.getCreacionFecha());
		assertEquals("admin", rol2.getActualizacionUsuario());
		assertNotNull(rol2.getActualizacionFecha());
	}

	@Test
	void existsById() {
		boolean exists = baseRolService.existsById(1L);
		assertTrue(exists);

		boolean notExists = baseRolService.existsById(99L);
		assertFalse(notExists);
	}

	@Test
	void findByCreacionUsuarioTest() {
		List<Rol> roles = baseRolService.findByCreacionUsuario("admin");
		assertNotNull(roles);
		assertEquals(2, roles.size());

		Rol rol1 = roles.getFirst();
		assertEquals(1, rol1.getId());
		assertEquals("Administrador", rol1.getNombre());
		assertEquals("admin", rol1.getCreacionUsuario());
		assertNotNull(rol1.getCreacionFecha());
		assertNull(rol1.getActualizacionUsuario());
		assertNull(rol1.getActualizacionFecha());

		Rol rol2 = roles.get(1);
		assertEquals(2, rol2.getId());
		assertEquals("Empleado", rol2.getNombre());
		assertEquals("admin", rol2.getCreacionUsuario());
		assertNotNull(rol2.getCreacionFecha());
		assertEquals("admin", rol2.getActualizacionUsuario());
		assertNotNull(rol2.getActualizacionFecha());
	}

	@Test
	void findByActualizacionUsuarioTest() {
		List<Rol> roles = baseRolService.findByActualizacionUsuario("admin");
		assertNotNull(roles);
		assertEquals(1, roles.size());

		Rol rol = roles.getFirst();
		assertEquals(2, rol.getId());
		assertEquals("Empleado", rol.getNombre());
		assertEquals("admin", rol.getCreacionUsuario());
		assertNotNull(rol.getCreacionFecha());
		assertEquals("admin", rol.getActualizacionUsuario());
		assertNotNull(rol.getActualizacionFecha());
	}


}

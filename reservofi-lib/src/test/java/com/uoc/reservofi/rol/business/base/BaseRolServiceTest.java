package com.uoc.reservofi.rol.business.base;

import com.uoc.reservofi.base.busines.exception.BaseException;
import com.uoc.reservofi.base.busines.exception.GestionException;
import com.uoc.reservofi.base.business.BaseTest;
import com.uoc.reservofi.rol.business.model.Rol;
import com.uoc.reservofi.rol.business.service.base.BaseRolService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class BaseRolServiceTest extends BaseTest {

	@Autowired
	private BaseRolService baseRolService;


	@Test
	void insertTest() {
		Rol rol = new Rol();
		rol.setNombre("Rol prueba");

		Rol insertado = baseRolService.insert(rol);
		assertNotNull(insertado);
		assertEquals("Rol prueba", insertado.getNombre());
		assertEquals("adminTest", insertado.getCreacionUsuario());
		assertNotNull(insertado.getCreacionFecha());
		assertEquals("adminTest", insertado.getActualizacionUsuario());
		assertNotNull(insertado.getActualizacionFecha());
	}

	@Test
	void insertNotExistsTest() {
		try {
			baseRolService.insert(null);
			fail("Se esperaba una excepción BaseException");
		} catch (BaseException e) {
			assertEquals("Entidad no existente", e.getMessage());
		} catch (Exception e) {
			fail("Se esperaba una excepción BaseException");
		}

	}

	@Test
	void insertIdExistsTest() {
		Rol rol = new Rol();
		rol.setId(1L);
		rol.setNombre("Rol prueba");

		try {
			baseRolService.insert(rol);
			fail("Se esperaba una excepción BaseException");
		} catch (BaseException e) {
			assertEquals("No puede tener identificador asociado", e.getMessage());
		} catch (Exception e) {
			fail("Se esperaba una excepción BaseException");
		}

	}

	@Test
	void updateTest() {

		Rol rol = new Rol();
		rol.setId(1L);
		rol.setNombre("Rol prueba");

		// Para esta prueba, se va a suponer que se rellenó la información correctamente desde el servicio de gestión
		rol.setCreacionUsuario("adminTest");
		rol.setCreacionFecha(LocalDateTime.now());

		Rol actualizado = baseRolService.update(rol);
		assertNotNull(actualizado);
		assertEquals("Rol prueba", actualizado.getNombre());
		assertEquals("adminTest", actualizado.getCreacionUsuario());
		assertNotNull(actualizado.getCreacionFecha());
		assertEquals("adminTest", actualizado.getActualizacionUsuario());
		assertNotNull(actualizado.getActualizacionFecha());
	}

	@Test
	void updateCreateUserNotExistsTest() {
		Rol rol = new Rol();
		rol.setCreacionFecha(LocalDateTime.now());
		try {
			baseRolService.update(null);
			fail("Se esperaba una excepción BaseException");
		} catch (BaseException e) {
			assertEquals("Entidad no existente", e.getMessage());
		} catch (Exception e) {
			fail("Se esperaba una excepción BaseException");
		}
	}

	@Test
	void updateCreateDateNotExistsTest() {
		Rol rol = new Rol();
		rol.setCreacionUsuario("admin-test");

		try {
			baseRolService.update(rol);
			fail("Se esperaba una excepción GestionException");
		} catch (GestionException e) {
			assertEquals("Datos de auditoría no registrados: fecha de creación no existente", e.getMessage());
		} catch (Exception e) {
			fail("Se esperaba una excepción GestionException");
		}
	}

	@Test
	void updateNotExistsTest() {
		try {
			baseRolService.update(null);
			fail("Se esperaba una excepción BaseException");
		} catch (BaseException e) {
			assertEquals("Entidad no existente", e.getMessage());
		} catch (Exception e) {
			fail("Se esperaba una excepción BaseException");
		}
	}

	@Test
	void updateRolIdNotExistsTest() {
		Rol rol = new Rol();
		rol.setCreacionUsuario("adminTest");
		rol.setCreacionFecha(LocalDateTime.now());

		try {
			baseRolService.update(rol);
			fail("Se esperaba una excepción BaseException");
		} catch (BaseException e) {
			assertEquals("Ha de tener un identificador asociado", e.getMessage());
		} catch (Exception e) {
			fail("Se esperaba una excepción BaseException");
		}
	}

	@Test
	void deleteByIdTest() {
		try {
			baseRolService.deleteById(1L);
		} catch (Exception e) {
			fail("No se esperaba error");
		}
	}

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

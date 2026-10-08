package com.uoc.reservofi.sala.business.base;

import com.uoc.reservofi.base.busines.exception.BaseException;
import com.uoc.reservofi.base.business.AbstractTest;
import com.uoc.reservofi.sala.business.model.Sala;
import com.uoc.reservofi.sala.business.service.base.BaseSalaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class BaseSalaServiceTest extends AbstractTest {

	@Autowired
	private BaseSalaService baseSalaService;

	@Test
	void insertTest() {
		Sala sala = new Sala();
		sala.setNombre("Sala prueba");

		Sala insertado = baseSalaService.insert(sala);
		assertNotNull(insertado);
		assertEquals("Sala prueba", insertado.getNombre());
		assertEquals("adminTest", insertado.getCreacionUsuario());
		assertNotNull(insertado.getCreacionFecha());
		assertEquals("adminTest", insertado.getActualizacionUsuario());
		assertNotNull(insertado.getActualizacionFecha());
	}

	@Test
	void insertNotExistsTest() {
		try {
			baseSalaService.insert(null);
			fail("Se esperaba una excepción BaseException");
		} catch (BaseException e) {
			assertEquals("Entidad no existente", e.getMessage());
		} catch (Exception e) {
			fail("Se esperaba una excepción BaseException");
		}

	}

	@Test
	void insertIdExistsTest() {
		Sala sala = new Sala();
		sala.setId(1L);
		sala.setNombre("Sala prueba");

		try {
			baseSalaService.insert(sala);
			fail("Se esperaba una excepción BaseException");
		} catch (BaseException e) {
			assertEquals("No puede tener identificador asociado", e.getMessage());
		} catch (Exception e) {
			fail("Se esperaba una excepción BaseException");
		}

	}

	@Test
	void updateTest() {

		Sala Sala = new Sala();
		Sala.setId(1L);
		Sala.setNombre("Sala prueba");

		// Para esta prueba, se va a suponer que se rellenó la información correctamente desde el servicio de gestión
		Sala.setCreacionUsuario("adminTest");
		Sala.setCreacionFecha(LocalDateTime.now());

		Sala actualizado = baseSalaService.update(Sala);
		assertNotNull(actualizado);
		assertEquals("Sala prueba", actualizado.getNombre());
		assertEquals("adminTest", actualizado.getCreacionUsuario());
		assertNotNull(actualizado.getCreacionFecha());
		assertEquals("adminTest", actualizado.getActualizacionUsuario());
		assertNotNull(actualizado.getActualizacionFecha());
	}

	@Test
	void updateCreateUserNotExistsTest() {
		Sala sala = new Sala();
		sala.setId(1L);
		sala.setCreacionFecha(LocalDateTime.now());
		try {
			baseSalaService.update(sala);
			fail("Se esperaba una excepción BaseException");
		} catch (BaseException e) {
			assertEquals("Datos de auditoría no registrados: usuario creador no existente", e.getMessage());
		} catch (Exception e) {
			fail("Se esperaba una excepción BaseException");
		}
	}

	@Test
	void updateCreateDateNotExistsTest() {
		Sala sala = new Sala();
		sala.setId(1L);
		sala.setCreacionUsuario("admin-test");

		try {
			baseSalaService.update(sala);
			fail("Se esperaba una excepción BaseException");
		} catch (BaseException e) {
			assertEquals("Datos de auditoría no registrados: fecha de creación no existente", e.getMessage());
		} catch (Exception e) {
			fail("Se esperaba una excepción BaseException");
		}
	}

	@Test
	void updateNotExistsTest() {
		try {
			baseSalaService.update(null);
			fail("Se esperaba una excepción BaseException");
		} catch (BaseException e) {
			assertEquals("Entidad no existente", e.getMessage());
		} catch (Exception e) {
			fail("Se esperaba una excepción BaseException");
		}
	}

	@Test
	void updateSalaIdNotExistsTest() {
		Sala sala = new Sala();
		sala.setCreacionUsuario("adminTest");
		sala.setCreacionFecha(LocalDateTime.now());

		try {
			baseSalaService.update(sala);
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
			baseSalaService.deleteById(3L);
		} catch (Exception e) {
			fail("No se esperaba error");
		}
	}

	@Test
	void deleteByIdWithFKTest() {
		// Registro asociado a la tabla PUESTO
		try {
			baseSalaService.deleteById(1L);
			fail("Se esperaba una excepción DataIntegrityViolationException");
		} catch (DataIntegrityViolationException e) {
			assertNotNull(e.getMessage());
		} catch (Exception e) {
			fail("Se esperaba una excepción DataIntegrityViolationException");
		}
	}

	@Test
	void findByIdTest() {
		Sala sala = baseSalaService.findById(1L);
		assertNotNull(sala);
		assertEquals(1, sala.getId());
		assertEquals("Sala 1", sala.getNombre());
		assertEquals("admin", sala.getCreacionUsuario());
		assertNotNull(sala.getCreacionFecha());
		assertEquals("admin", sala.getActualizacionUsuario());
		assertNotNull(sala.getActualizacionFecha());
	}

	@Test
	void findAllTest() {
		List<Sala> salas = baseSalaService.findAll();
		assertNotNull(salas);
		assertEquals(3, salas.size());

		Sala sala1 = salas.getFirst();
		assertEquals(1, sala1.getId());
		assertEquals("Sala 1", sala1.getNombre());
		assertEquals("admin", sala1.getCreacionUsuario());
		assertNotNull(sala1.getCreacionFecha());
		assertEquals("admin", sala1.getActualizacionUsuario());
		assertNotNull(sala1.getActualizacionFecha());

		Sala sala2 = salas.get(1);
		assertEquals(2, sala2.getId());
		assertEquals("Sala 2", sala2.getNombre());
		assertEquals("admin", sala2.getCreacionUsuario());
		assertNotNull(sala2.getCreacionFecha());
		assertEquals("admin_update", sala2.getActualizacionUsuario());
		assertNotNull(sala2.getActualizacionFecha());

		Sala sala3 = salas.get(2);
		assertEquals(3, sala3.getId());
		assertEquals("Sala 3", sala3.getNombre());
		assertEquals("admin", sala3.getCreacionUsuario());
		assertNotNull(sala3.getCreacionFecha());
		assertEquals("admin", sala3.getActualizacionUsuario());
		assertNotNull(sala3.getActualizacionFecha());
	}

	@Test
	void findAllByIdTest() {
		List<Long> idsList = new ArrayList<>();
		idsList.add(1L);
		idsList.add(2L);

		List<Sala> salas = baseSalaService.findAllById(idsList);
		assertNotNull(salas);
		assertEquals(2, salas.size());

		Sala sala1 = salas.getFirst();
		assertEquals(1, sala1.getId());
		assertEquals("Sala 1", sala1.getNombre());
		assertEquals("admin", sala1.getCreacionUsuario());
		assertNotNull(sala1.getCreacionFecha());
		assertEquals("admin", sala1.getActualizacionUsuario());
		assertNotNull(sala1.getActualizacionFecha());

		Sala sala2 = salas.get(1);
		assertEquals(2, sala2.getId());
		assertEquals("Sala 2", sala2.getNombre());
		assertEquals("admin", sala2.getCreacionUsuario());
		assertNotNull(sala2.getCreacionFecha());
		assertEquals("admin_update", sala2.getActualizacionUsuario());
		assertNotNull(sala2.getActualizacionFecha());

	}

	@Test
	void existsById() {
		boolean exists = baseSalaService.existsById(1L);
		assertTrue(exists);

		boolean notExists = baseSalaService.existsById(99L);
		assertFalse(notExists);
	}

	@Test
	void findByCreacionUsuarioTest() {
		List<Sala> salas = baseSalaService.findByCreacionUsuario("admin");
		assertNotNull(salas);
		assertEquals(3, salas.size());

		Sala sala1 = salas.getFirst();
		assertEquals(1, sala1.getId());
		assertEquals("Sala 1", sala1.getNombre());
		assertEquals("admin", sala1.getCreacionUsuario());
		assertNotNull(sala1.getCreacionFecha());
		assertEquals("admin", sala1.getActualizacionUsuario());
		assertNotNull(sala1.getActualizacionFecha());

		Sala sala2 = salas.get(1);
		assertEquals(2, sala2.getId());
		assertEquals("Sala 2", sala2.getNombre());
		assertEquals("admin", sala2.getCreacionUsuario());
		assertNotNull(sala2.getCreacionFecha());
		assertEquals("admin_update", sala2.getActualizacionUsuario());
		assertNotNull(sala2.getActualizacionFecha());

		Sala sala3 = salas.get(2);
		assertEquals(3, sala3.getId());
		assertEquals("Sala 3", sala3.getNombre());
		assertEquals("admin", sala3.getCreacionUsuario());
		assertNotNull(sala3.getCreacionFecha());
		assertEquals("admin", sala3.getActualizacionUsuario());
		assertNotNull(sala3.getActualizacionFecha());
	}

	@Test
	void findByActualizacionUsuarioTest() {
		List<Sala> salas = baseSalaService.findByActualizacionUsuario("admin_update");
		assertNotNull(salas);
		assertEquals(1, salas.size());

		Sala sala = salas.getFirst();
		assertEquals(2, sala.getId());
		assertEquals("Sala 2", sala.getNombre());
		assertEquals("admin", sala.getCreacionUsuario());
		assertNotNull(sala.getCreacionFecha());
		assertEquals("admin_update", sala.getActualizacionUsuario());
		assertNotNull(sala.getActualizacionFecha());

	}

}

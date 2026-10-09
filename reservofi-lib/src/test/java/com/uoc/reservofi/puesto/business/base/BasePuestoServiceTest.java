package com.uoc.reservofi.puesto.business.base;

import com.uoc.reservofi.base.busines.exception.BaseException;
import com.uoc.reservofi.base.business.AbstractTest;
import com.uoc.reservofi.puesto.business.model.Puesto;
import com.uoc.reservofi.puesto.business.service.base.BasePuestoService;
import com.uoc.reservofi.sala.business.model.Sala;
import com.uoc.reservofi.sala.business.service.base.BaseSalaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class BasePuestoServiceTest extends AbstractTest {

	@Autowired
	private BasePuestoService basePuestoService;

	@Autowired
	private BaseSalaService baseSalaService;

	@Test
	void insertTest() {
		Puesto puesto = new Puesto();
		puesto.setNombre("Puesto prueba");
		puesto.setCoordenadaX(BigDecimal.valueOf(11.14));
		puesto.setCoordenadaY(BigDecimal.valueOf(22.25));

		Sala sala = baseSalaService.findById(1L);
		puesto.setSala(sala);

		Puesto insertado = basePuestoService.insert(puesto);
		assertNotNull(insertado);
		assertEquals("Puesto prueba", insertado.getNombre());
		assertEquals(BigDecimal.valueOf(11.14), insertado.getCoordenadaX());
		assertEquals(BigDecimal.valueOf(22.25), insertado.getCoordenadaY());
		assertEquals("adminTest", insertado.getCreacionUsuario());
		assertNotNull(insertado.getCreacionFecha());
		assertEquals("adminTest", insertado.getActualizacionUsuario());
		assertNotNull(insertado.getActualizacionFecha());
		assertNotNull(insertado.getSala());
		assertEquals(1L, insertado.getSala().getId());
	}

	@Test
	void insertNotExistsTest() {
		try {
			basePuestoService.insert(null);
			fail("Se esperaba una excepción BaseException");
		} catch (BaseException e) {
			assertEquals("Entidad no existente", e.getMessage());
		} catch (Exception e) {
			fail("Se esperaba una excepción BaseException");
		}

	}

	@Test
	void insertIdExistsTest() {
		Puesto puesto = new Puesto();
		puesto.setId(1L);
		puesto.setNombre("Puesto prueba");

		try {
			basePuestoService.insert(puesto);
			fail("Se esperaba una excepción BaseException");
		} catch (BaseException e) {
			assertEquals("No puede tener identificador asociado", e.getMessage());
		} catch (Exception e) {
			fail("Se esperaba una excepción BaseException");
		}

	}

	@Test
	void updateTest() {
		Puesto puesto = new Puesto();
		puesto.setId(1L);
		puesto.setNombre("Puesto prueba");

		// Para esta prueba, se va a suponer que se rellenó la información correctamente desde el servicio de gestión
		puesto.setCreacionUsuario("adminTest");
		puesto.setCreacionFecha(LocalDateTime.now());
		puesto.setCoordenadaX(BigDecimal.valueOf(55.55));
		puesto.setCoordenadaY(BigDecimal.valueOf(43.22));

		Sala sala = baseSalaService.findById(1L);
		puesto.setSala(sala);

		Puesto actualizado = basePuestoService.update(puesto);
		assertNotNull(actualizado);
		assertEquals("Puesto prueba", actualizado.getNombre());
		assertEquals(BigDecimal.valueOf(55.55), actualizado.getCoordenadaX());
		assertEquals(BigDecimal.valueOf(43.22), actualizado.getCoordenadaY());
		assertEquals("adminTest", actualizado.getCreacionUsuario());
		assertNotNull(actualizado.getCreacionFecha());
		assertEquals("adminTest", actualizado.getActualizacionUsuario());
		assertNotNull(actualizado.getActualizacionFecha());
		assertNotNull(actualizado.getSala());
		assertEquals(1L, actualizado.getSala().getId());
	}

	@Test
	void updateCreateUserNotExistsTest() {
		Puesto puesto = new Puesto();
		puesto.setId(1L);
		puesto.setCreacionFecha(LocalDateTime.now());
		try {
			basePuestoService.update(puesto);
			fail("Se esperaba una excepción BaseException");
		} catch (BaseException e) {
			assertEquals("Datos de auditoría no registrados: usuario creador no existente", e.getMessage());
		} catch (Exception e) {
			fail("Se esperaba una excepción BaseException");
		}
	}

	@Test
	void updateCreateDateNotExistsTest() {
		Puesto puesto = new Puesto();
		puesto.setId(1L);
		puesto.setCreacionUsuario("admin-test");

		try {
			basePuestoService.update(puesto);
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
			basePuestoService.update(null);
			fail("Se esperaba una excepción BaseException");
		} catch (BaseException e) {
			assertEquals("Entidad no existente", e.getMessage());
		} catch (Exception e) {
			fail("Se esperaba una excepción BaseException");
		}
	}

	@Test
	void updatePuestoIdNotExistsTest() {
		Puesto puesto = new Puesto();
		puesto.setCreacionUsuario("adminTest");
		puesto.setCreacionFecha(LocalDateTime.now());

		try {
			basePuestoService.update(puesto);
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
			basePuestoService.deleteById(3L);
		} catch (Exception e) {
			fail("No se esperaba error");
		}
	}

	@Test
	void deleteByIdWithFKTest() {
		// Registro asociado a la tabla RESERVA
		try {
			basePuestoService.deleteById(1L);
			fail("Se esperaba una excepción DataIntegrityViolationException");
		} catch (DataIntegrityViolationException e) {
			assertNotNull(e.getMessage());
		} catch (Exception e) {
			fail("Se esperaba una excepción DataIntegrityViolationException");
		}
	}

	@Test
	void findByIdTest() {
		Puesto puesto = basePuestoService.findById(1L);
		assertNotNull(puesto);
		assertEquals(1, puesto.getId());
		assertEquals("Puesto 1", puesto.getNombre());
		assertEquals(BigDecimal.valueOf(55.55), puesto.getCoordenadaX());
		assertEquals(BigDecimal.valueOf(43.22), puesto.getCoordenadaY());
		assertEquals("admin", puesto.getCreacionUsuario());
		assertNotNull(puesto.getCreacionFecha());
		assertEquals("admin", puesto.getActualizacionUsuario());
		assertNotNull(puesto.getActualizacionFecha());
		assertNotNull(puesto.getSala());
		assertEquals(1L, puesto.getSala().getId());
	}

	@Test
	void findAllTest() {
		List<Puesto> puestos = basePuestoService.findAll();
		assertNotNull(puestos);
		assertEquals(3, puestos.size());

		Puesto puesto1 = puestos.getFirst();
		assertNotNull(puesto1);
		assertEquals(1, puesto1.getId());
		assertEquals("Puesto 1", puesto1.getNombre());
		assertEquals(BigDecimal.valueOf(55.55), puesto1.getCoordenadaX());
		assertEquals(BigDecimal.valueOf(43.22), puesto1.getCoordenadaY());
		assertEquals("admin", puesto1.getCreacionUsuario());
		assertNotNull(puesto1.getCreacionFecha());
		assertEquals("admin", puesto1.getActualizacionUsuario());
		assertNotNull(puesto1.getActualizacionFecha());
		assertNotNull(puesto1.getSala());
		assertEquals(1L, puesto1.getSala().getId());

		Puesto puesto2 = puestos.get(1);
		assertEquals(2, puesto2.getId());
		assertEquals("Puesto 2", puesto2.getNombre());
		assertEquals(BigDecimal.valueOf(32.12), puesto2.getCoordenadaX());
		assertEquals(BigDecimal.valueOf(87.93), puesto2.getCoordenadaY());
		assertEquals("admin", puesto2.getCreacionUsuario());
		assertNotNull(puesto2.getCreacionFecha());
		assertEquals("admin_update", puesto2.getActualizacionUsuario());
		assertNotNull(puesto2.getActualizacionFecha());
		assertNotNull(puesto2.getSala());
		assertEquals(2L, puesto2.getSala().getId());

		Puesto puesto3 = puestos.get(2);
		assertNotNull(puesto3);
		assertEquals(3, puesto3.getId());
		assertEquals("Puesto 3", puesto3.getNombre());
		assertEquals(BigDecimal.valueOf(78.98), puesto3.getCoordenadaX());
		assertEquals(BigDecimal.valueOf(15.32), puesto3.getCoordenadaY());
		assertEquals("admin", puesto3.getCreacionUsuario());
		assertNotNull(puesto3.getCreacionFecha());
		assertEquals("admin", puesto3.getActualizacionUsuario());
		assertNotNull(puesto3.getActualizacionFecha());
		assertNotNull(puesto3.getSala());
		assertEquals(1L, puesto3.getSala().getId());
	}

	@Test
	void findAllByIdTest() {
		List<Long> idsList = new ArrayList<>();
		idsList.add(1L);
		idsList.add(2L);

		List<Puesto> puestos = basePuestoService.findAllById(idsList);
		assertNotNull(puestos);
		assertEquals(2, puestos.size());

		Puesto puesto1 = puestos.getFirst();
		assertNotNull(puesto1);
		assertEquals(1, puesto1.getId());
		assertEquals("Puesto 1", puesto1.getNombre());
		assertEquals(BigDecimal.valueOf(55.55), puesto1.getCoordenadaX());
		assertEquals(BigDecimal.valueOf(43.22), puesto1.getCoordenadaY());
		assertEquals("admin", puesto1.getCreacionUsuario());
		assertNotNull(puesto1.getCreacionFecha());
		assertEquals("admin", puesto1.getActualizacionUsuario());
		assertNotNull(puesto1.getActualizacionFecha());
		assertNotNull(puesto1.getSala());
		assertEquals(1L, puesto1.getSala().getId());

		Puesto puesto2 = puestos.get(1);
		assertEquals(2, puesto2.getId());
		assertEquals("Puesto 2", puesto2.getNombre());
		assertEquals(BigDecimal.valueOf(32.12), puesto2.getCoordenadaX());
		assertEquals(BigDecimal.valueOf(87.93), puesto2.getCoordenadaY());
		assertEquals("admin", puesto2.getCreacionUsuario());
		assertNotNull(puesto2.getCreacionFecha());
		assertEquals("admin_update", puesto2.getActualizacionUsuario());
		assertNotNull(puesto2.getActualizacionFecha());
		assertNotNull(puesto2.getSala());
		assertEquals(2L, puesto2.getSala().getId());
	}

	@Test
	void existsById() {
		boolean exists = basePuestoService.existsById(1L);
		assertTrue(exists);

		boolean notExists = basePuestoService.existsById(99L);
		assertFalse(notExists);
	}

	@Test
	void findByCreacionUsuarioTest() {
		List<Puesto> puestos = basePuestoService.findByCreacionUsuario("admin");
		assertNotNull(puestos);
		assertEquals(3, puestos.size());

		Puesto puesto1 = puestos.getFirst();
		assertNotNull(puesto1);
		assertEquals(1, puesto1.getId());
		assertEquals("Puesto 1", puesto1.getNombre());
		assertEquals(BigDecimal.valueOf(55.55), puesto1.getCoordenadaX());
		assertEquals(BigDecimal.valueOf(43.22), puesto1.getCoordenadaY());
		assertEquals("admin", puesto1.getCreacionUsuario());
		assertNotNull(puesto1.getCreacionFecha());
		assertEquals("admin", puesto1.getActualizacionUsuario());
		assertNotNull(puesto1.getActualizacionFecha());
		assertNotNull(puesto1.getSala());
		assertEquals(1L, puesto1.getSala().getId());

		Puesto puesto2 = puestos.get(1);
		assertEquals(2, puesto2.getId());
		assertEquals("Puesto 2", puesto2.getNombre());
		assertEquals(BigDecimal.valueOf(32.12), puesto2.getCoordenadaX());
		assertEquals(BigDecimal.valueOf(87.93), puesto2.getCoordenadaY());
		assertEquals("admin", puesto2.getCreacionUsuario());
		assertNotNull(puesto2.getCreacionFecha());
		assertEquals("admin_update", puesto2.getActualizacionUsuario());
		assertNotNull(puesto2.getActualizacionFecha());
		assertNotNull(puesto2.getSala());
		assertEquals(2L, puesto2.getSala().getId());

		Puesto puesto3 = puestos.get(2);
		assertNotNull(puesto3);
		assertEquals(3, puesto3.getId());
		assertEquals("Puesto 3", puesto3.getNombre());
		assertEquals(BigDecimal.valueOf(78.98), puesto3.getCoordenadaX());
		assertEquals(BigDecimal.valueOf(15.32), puesto3.getCoordenadaY());
		assertEquals("admin", puesto3.getCreacionUsuario());
		assertNotNull(puesto3.getCreacionFecha());
		assertEquals("admin", puesto3.getActualizacionUsuario());
		assertNotNull(puesto3.getActualizacionFecha());
		assertNotNull(puesto3.getSala());
		assertEquals(1L, puesto3.getSala().getId());
	}

	@Test
	void findByActualizacionUsuarioTest() {
		List<Puesto> puestos = basePuestoService.findByActualizacionUsuario("admin_update");
		assertNotNull(puestos);
		assertEquals(1, puestos.size());

		Puesto puesto = puestos.getFirst();
		assertEquals(2, puesto.getId());
		assertEquals("Puesto 2", puesto.getNombre());
		assertEquals(BigDecimal.valueOf(32.12), puesto.getCoordenadaX());
		assertEquals(BigDecimal.valueOf(87.93), puesto.getCoordenadaY());
		assertEquals("admin", puesto.getCreacionUsuario());
		assertNotNull(puesto.getCreacionFecha());
		assertEquals("admin_update", puesto.getActualizacionUsuario());
		assertNotNull(puesto.getActualizacionFecha());
		assertNotNull(puesto.getSala());
		assertEquals(2L, puesto.getSala().getId());
	}
}

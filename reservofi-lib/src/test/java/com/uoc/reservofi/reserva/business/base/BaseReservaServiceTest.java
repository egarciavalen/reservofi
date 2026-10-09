package com.uoc.reservofi.reserva.business.base;

import com.uoc.reservofi.base.busines.exception.BaseException;
import com.uoc.reservofi.base.business.AbstractTest;
import com.uoc.reservofi.puesto.business.model.Puesto;
import com.uoc.reservofi.puesto.business.service.base.BasePuestoService;
import com.uoc.reservofi.reserva.business.model.Reserva;
import com.uoc.reservofi.reserva.business.service.base.BaseReservaService;
import com.uoc.reservofi.usuario.business.model.Usuario;
import com.uoc.reservofi.usuario.business.service.base.BaseUsuarioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class BaseReservaServiceTest extends AbstractTest {

	@Autowired
	private BaseReservaService baseReservaService;

	@Autowired
	private BasePuestoService basePuestoService;

	@Autowired
	private BaseUsuarioService baseUsuarioService;

	@Test
	void insertTest() {
		Reserva reserva = new Reserva();
		reserva.setFechaInicio(LocalDateTime.of(2026, 10, 10, 0, 0));
		reserva.setFechaFin(LocalDateTime.of(2026, 10, 11, 0, 0));

		Puesto puesto = basePuestoService.findById(1L);
		reserva.setPuesto(puesto);

		Usuario usuario = baseUsuarioService.findById(1L);
		reserva.setUsuario(usuario);

		Reserva insertado = baseReservaService.insert(reserva);
		assertNotNull(insertado);
		assertEquals(LocalDateTime.of(2026, 10, 10, 0, 0), insertado.getFechaInicio());
		assertEquals(LocalDateTime.of(2026, 10, 11, 0, 0), insertado.getFechaFin());
		assertEquals("adminTest", insertado.getCreacionUsuario());
		assertNotNull(insertado.getCreacionFecha());
		assertEquals("adminTest", insertado.getActualizacionUsuario());
		assertNotNull(insertado.getActualizacionFecha());
		assertNotNull(insertado.getPuesto());
		assertEquals(1L, insertado.getPuesto().getId());
		assertNotNull(insertado.getUsuario());
		assertEquals(1L, insertado.getUsuario().getId());
	}

	@Test
	void insertNotExistsTest() {
		try {
			baseReservaService.insert(null);
			fail("Se esperaba una excepción BaseException");
		} catch (BaseException e) {
			assertEquals("Entidad no existente", e.getMessage());
		} catch (Exception e) {
			fail("Se esperaba una excepción BaseException");
		}

	}

	@Test
	void insertIdExistsTest() {
		Reserva reserva = new Reserva();
		reserva.setId(1L);
		try {
			baseReservaService.insert(reserva);
			fail("Se esperaba una excepción BaseException");
		} catch (BaseException e) {
			assertEquals("No puede tener identificador asociado", e.getMessage());
		} catch (Exception e) {
			fail("Se esperaba una excepción BaseException");
		}

	}

	@Test
	void updateTest() {
		Reserva reserva = new Reserva();
		reserva.setId(1L);
		reserva.setFechaFin(LocalDateTime.of(2026, 9, 11, 0, 0));

		// Para esta prueba, se va a suponer que se rellenó la información correctamente desde el servicio de gestión
		reserva.setCreacionUsuario("adminTest");
		reserva.setCreacionFecha(LocalDateTime.now());
		reserva.setFechaInicio(LocalDateTime.of(2026, 9, 6, 0, 0));

		Puesto puesto = basePuestoService.findById(1L);
		reserva.setPuesto(puesto);

		Usuario usuario = baseUsuarioService.findById(1L);
		reserva.setUsuario(usuario);


		Reserva actualizado = baseReservaService.update(reserva);
		assertNotNull(actualizado);
		assertEquals(LocalDateTime.of(2026, 9, 6, 0, 0), actualizado.getFechaInicio());
		assertEquals(LocalDateTime.of(2026, 9, 11, 0, 0), actualizado.getFechaFin());
		assertEquals("adminTest", actualizado.getCreacionUsuario());
		assertNotNull(actualizado.getCreacionFecha());
		assertEquals("adminTest", actualizado.getActualizacionUsuario());
		assertNotNull(actualizado.getActualizacionFecha());
		assertNotNull(actualizado.getPuesto());
		assertEquals(1L, actualizado.getPuesto().getId());
		assertNotNull(actualizado.getUsuario());
		assertEquals(1L, actualizado.getUsuario().getId());
	}

	@Test
	void updateCreateUserNotExistsTest() {
		Reserva reserva = new Reserva();
		reserva.setId(1L);
		reserva.setCreacionFecha(LocalDateTime.now());
		try {
			baseReservaService.update(reserva);
			fail("Se esperaba una excepción BaseException");
		} catch (BaseException e) {
			assertEquals("Datos de auditoría no registrados: usuario creador no existente", e.getMessage());
		} catch (Exception e) {
			fail("Se esperaba una excepción BaseException");
		}
	}

	@Test
	void updateCreateDateNotExistsTest() {
		Reserva reserva = new Reserva();
		reserva.setId(1L);
		reserva.setCreacionUsuario("admin-test");

		try {
			baseReservaService.update(reserva);
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
			baseReservaService.update(null);
			fail("Se esperaba una excepción BaseException");
		} catch (BaseException e) {
			assertEquals("Entidad no existente", e.getMessage());
		} catch (Exception e) {
			fail("Se esperaba una excepción BaseException");
		}
	}

	@Test
	void updateReservaIdNotExistsTest() {
		Reserva reserva = new Reserva();
		reserva.setCreacionUsuario("adminTest");
		reserva.setCreacionFecha(LocalDateTime.now());

		try {
			baseReservaService.update(reserva);
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
			baseReservaService.deleteById(1L);
		} catch (Exception e) {
			fail("No se esperaba error");
		}
	}

	@Test
	void findByIdTest() {
		Reserva reserva = baseReservaService.findById(1L);
		assertNotNull(reserva);
		assertEquals(LocalDateTime.of(2026, 10, 6, 0, 0), reserva.getFechaInicio());
		assertEquals(LocalDateTime.of(2026, 10, 7, 0, 0), reserva.getFechaFin());
		assertEquals("admin", reserva.getCreacionUsuario());
		assertNotNull(reserva.getCreacionFecha());
		assertEquals("admin", reserva.getActualizacionUsuario());
		assertNotNull(reserva.getActualizacionFecha());
		assertNotNull(reserva.getPuesto());
		assertEquals(1L, reserva.getPuesto().getId());
		assertNotNull(reserva.getUsuario());
		assertEquals(1L, reserva.getUsuario().getId());
	}

	@Test
	void findAllTest() {
		List<Reserva> reservas = baseReservaService.findAll();
		assertNotNull(reservas);
		assertEquals(2, reservas.size());

		Reserva reserva1 = reservas.getFirst();
		assertNotNull(reserva1);
		assertEquals(LocalDateTime.of(2026, 10, 6, 0, 0), reserva1.getFechaInicio());
		assertEquals(LocalDateTime.of(2026, 10, 7, 0, 0), reserva1.getFechaFin());
		assertEquals("admin", reserva1.getCreacionUsuario());
		assertNotNull(reserva1.getCreacionFecha());
		assertEquals("admin", reserva1.getActualizacionUsuario());
		assertNotNull(reserva1.getActualizacionFecha());
		assertNotNull(reserva1.getPuesto());
		assertEquals(1L, reserva1.getPuesto().getId());
		assertNotNull(reserva1.getUsuario());
		assertEquals(1L, reserva1.getUsuario().getId());

		Reserva reserva2 = reservas.get(1);
		assertNotNull(reserva2);
		assertEquals(LocalDateTime.of(2026, 10, 8, 0, 0), reserva2.getFechaInicio());
		assertEquals(LocalDateTime.of(2026, 10, 9, 0, 0), reserva2.getFechaFin());
		assertEquals("admin", reserva2.getCreacionUsuario());
		assertNotNull(reserva2.getCreacionFecha());
		assertEquals("admin_update", reserva2.getActualizacionUsuario());
		assertNotNull(reserva2.getActualizacionFecha());
		assertNotNull(reserva2.getPuesto());
		assertEquals(2L, reserva2.getPuesto().getId());
		assertNotNull(reserva2.getUsuario());
		assertEquals(2L, reserva2.getUsuario().getId());
	}

	@Test
	void findAllByIdTest() {
		List<Long> idsList = new ArrayList<>();
		idsList.add(1L);
		idsList.add(2L);

		List<Reserva> reservas = baseReservaService.findAllById(idsList);
		assertNotNull(reservas);
		assertEquals(2, reservas.size());

		Reserva reserva1 = reservas.getFirst();
		assertNotNull(reserva1);
		assertEquals(LocalDateTime.of(2026, 10, 6, 0, 0), reserva1.getFechaInicio());
		assertEquals(LocalDateTime.of(2026, 10, 7, 0, 0), reserva1.getFechaFin());
		assertEquals("admin", reserva1.getCreacionUsuario());
		assertNotNull(reserva1.getCreacionFecha());
		assertEquals("admin", reserva1.getActualizacionUsuario());
		assertNotNull(reserva1.getActualizacionFecha());
		assertNotNull(reserva1.getPuesto());
		assertEquals(1L, reserva1.getPuesto().getId());
		assertNotNull(reserva1.getUsuario());
		assertEquals(1L, reserva1.getUsuario().getId());

		Reserva reserva2 = reservas.get(1);
		assertNotNull(reserva2);
		assertEquals(LocalDateTime.of(2026, 10, 8, 0, 0), reserva2.getFechaInicio());
		assertEquals(LocalDateTime.of(2026, 10, 9, 0, 0), reserva2.getFechaFin());
		assertEquals("admin", reserva2.getCreacionUsuario());
		assertNotNull(reserva2.getCreacionFecha());
		assertEquals("admin_update", reserva2.getActualizacionUsuario());
		assertNotNull(reserva2.getActualizacionFecha());
		assertNotNull(reserva2.getPuesto());
		assertEquals(2L, reserva2.getPuesto().getId());
		assertNotNull(reserva2.getUsuario());
		assertEquals(2L, reserva2.getUsuario().getId());
	}

	@Test
	void existsById() {
		boolean exists = baseReservaService.existsById(1L);
		assertTrue(exists);

		boolean notExists = baseReservaService.existsById(99L);
		assertFalse(notExists);
	}

	@Test
	void findByCreacionUsuarioTest() {
		List<Reserva> reservas = baseReservaService.findByCreacionUsuario("admin");
		assertNotNull(reservas);
		assertEquals(2, reservas.size());

		Reserva reserva1 = reservas.getFirst();
		assertNotNull(reserva1);
		assertEquals(LocalDateTime.of(2026, 10, 6, 0, 0), reserva1.getFechaInicio());
		assertEquals(LocalDateTime.of(2026, 10, 7, 0, 0), reserva1.getFechaFin());
		assertEquals("admin", reserva1.getCreacionUsuario());
		assertNotNull(reserva1.getCreacionFecha());
		assertEquals("admin", reserva1.getActualizacionUsuario());
		assertNotNull(reserva1.getActualizacionFecha());
		assertNotNull(reserva1.getPuesto());
		assertEquals(1L, reserva1.getPuesto().getId());
		assertNotNull(reserva1.getUsuario());
		assertEquals(1L, reserva1.getUsuario().getId());

		Reserva reserva2 = reservas.get(1);
		assertNotNull(reserva2);
		assertEquals(LocalDateTime.of(2026, 10, 8, 0, 0), reserva2.getFechaInicio());
		assertEquals(LocalDateTime.of(2026, 10, 9, 0, 0), reserva2.getFechaFin());
		assertEquals("admin", reserva2.getCreacionUsuario());
		assertNotNull(reserva2.getCreacionFecha());
		assertEquals("admin_update", reserva2.getActualizacionUsuario());
		assertNotNull(reserva2.getActualizacionFecha());
		assertNotNull(reserva2.getPuesto());
		assertEquals(2L, reserva2.getPuesto().getId());
		assertNotNull(reserva2.getUsuario());
		assertEquals(2L, reserva2.getUsuario().getId());
	}

	@Test
	void findByActualizacionUsuarioTest() {
		List<Reserva> reservas = baseReservaService.findByActualizacionUsuario("admin_update");
		assertNotNull(reservas);
		assertEquals(1, reservas.size());

		Reserva reserva = reservas.getFirst();
		assertNotNull(reserva);
		assertEquals(LocalDateTime.of(2026, 10, 8, 0, 0), reserva.getFechaInicio());
		assertEquals(LocalDateTime.of(2026, 10, 9, 0, 0), reserva.getFechaFin());
		assertEquals("admin", reserva.getCreacionUsuario());
		assertNotNull(reserva.getCreacionFecha());
		assertEquals("admin_update", reserva.getActualizacionUsuario());
		assertNotNull(reserva.getActualizacionFecha());
		assertNotNull(reserva.getPuesto());
		assertEquals(2L, reserva.getPuesto().getId());
		assertNotNull(reserva.getUsuario());
		assertEquals(2L, reserva.getUsuario().getId());
	}
}

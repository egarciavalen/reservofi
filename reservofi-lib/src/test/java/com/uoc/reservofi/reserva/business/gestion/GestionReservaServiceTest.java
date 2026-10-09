package com.uoc.reservofi.reserva.business.gestion;

import com.uoc.reservofi.base.business.AbstractTest;
import com.uoc.reservofi.reserva.business.dto.ReservaDTO;
import com.uoc.reservofi.reserva.business.service.GestionReservaService;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class GestionReservaServiceTest extends AbstractTest {

	@Autowired
	private GestionReservaService gestionReservaService;

	@Test
	void insertTest() {
		ReservaDTO reservaDTO = new ReservaDTO();
		reservaDTO.setFechaInicio(LocalDateTime.of(2026, 10, 10, 0, 0));
		reservaDTO.setFechaFin(LocalDateTime.of(2026, 10, 11, 0, 0));
		reservaDTO.setIdPuesto(1L);
		reservaDTO.setIdUsuario(1L);

		ReservaDTO insertado = gestionReservaService.insert(reservaDTO);
		assertNotNull(insertado);
		assertNotNull(insertado.getId());
		assertEquals(LocalDateTime.of(2026, 10, 10, 0, 0), insertado.getFechaInicio());
		assertEquals(LocalDateTime.of(2026, 10, 11, 0, 0), insertado.getFechaFin());
		assertEquals(1L, insertado.getIdPuesto());
		assertEquals(1L, insertado.getIdUsuario());
	}

	@Test
	void validateUsuarioTest() {
		ReservaDTO reservaDTO = new ReservaDTO();
		reservaDTO.setFechaInicio(LocalDateTime.of(2026, 10, 10, 0, 0));
		reservaDTO.setFechaFin(LocalDateTime.of(2026, 10, 11, 0, 0));
		reservaDTO.setIdPuesto(1L);
		reservaDTO.setIdUsuario(1L);

		// Usuario nulo
		reservaDTO.setIdUsuario(null);
		try {
			gestionReservaService.insert(reservaDTO);
			fail("Se esperaba una ConstraintViolationException");
		} catch (ConstraintViolationException e) {
			assertEquals("insert.entidad.idUsuario: reserva.usuario.obligatorio", e.getMessage());

		} catch (Exception e) {
			fail("Se esperaba una ConstraintViolationException");
		}
	}

	@Test
	void validatePuestoTest() {
		ReservaDTO reservaDTO = new ReservaDTO();
		reservaDTO.setFechaInicio(LocalDateTime.of(2026, 10, 10, 0, 0));
		reservaDTO.setFechaFin(LocalDateTime.of(2026, 10, 11, 0, 0));
		reservaDTO.setIdPuesto(1L);
		reservaDTO.setIdUsuario(1L);

		// Puesto nulo
		reservaDTO.setIdPuesto(null);
		try {
			gestionReservaService.insert(reservaDTO);
			fail("Se esperaba una ConstraintViolationException");
		} catch (ConstraintViolationException e) {
			assertEquals("insert.entidad.idPuesto: reserva.puesto.obligatorio", e.getMessage());

		} catch (Exception e) {
			fail("Se esperaba una ConstraintViolationException");
		}
	}

	@Test
	void validateFechaInicioTest() {
		ReservaDTO reservaDTO = new ReservaDTO();
		reservaDTO.setFechaInicio(LocalDateTime.of(2026, 10, 10, 0, 0));
		reservaDTO.setFechaFin(LocalDateTime.of(2026, 10, 11, 0, 0));
		reservaDTO.setIdPuesto(1L);
		reservaDTO.setIdUsuario(1L);

		// Fecha inicio nula
		reservaDTO.setFechaInicio(null);
		try {
			gestionReservaService.insert(reservaDTO);
			fail("Se esperaba una ConstraintViolationException");
		} catch (ConstraintViolationException e) {
			assertEquals("insert.entidad.fechaInicio: reserva.fechaInicio.obligatorio", e.getMessage());

		} catch (Exception e) {
			fail("Se esperaba una ConstraintViolationException");
		}
	}

	@Test
	void validateFechaFinTest() {
		ReservaDTO reservaDTO = new ReservaDTO();
		reservaDTO.setFechaInicio(LocalDateTime.of(2026, 10, 10, 0, 0));
		reservaDTO.setFechaFin(LocalDateTime.of(2026, 10, 11, 0, 0));
		reservaDTO.setIdPuesto(1L);
		reservaDTO.setIdUsuario(1L);

		// Fecha fin nula
		reservaDTO.setFechaFin(null);
		try {
			gestionReservaService.insert(reservaDTO);
			fail("Se esperaba una ConstraintViolationException");
		} catch (ConstraintViolationException e) {
			assertEquals("insert.entidad.fechaFin: reserva.fechaFin.obligatorio", e.getMessage());

		} catch (Exception e) {
			fail("Se esperaba una ConstraintViolationException");
		}
	}

	@Test
	void updateTest() {
		ReservaDTO reservaDTO = new ReservaDTO();
		reservaDTO.setId(1L); // ID existente
		reservaDTO.setFechaInicio(LocalDateTime.of(2026, 10, 10, 0, 0));
		reservaDTO.setFechaFin(LocalDateTime.of(2026, 10, 11, 0, 0));
		reservaDTO.setIdPuesto(1L);
		reservaDTO.setIdUsuario(2L); // Cambio de usuario

		ReservaDTO actualizado = gestionReservaService.update(reservaDTO);
		assertNotNull(actualizado);
		assertNotNull(actualizado.getId());
		assertEquals(LocalDateTime.of(2026, 10, 10, 0, 0), reservaDTO.getFechaInicio());
		assertEquals(LocalDateTime.of(2026, 10, 11, 0, 0), reservaDTO.getFechaFin());
		assertEquals(1L, actualizado.getIdPuesto());
		assertEquals(2L, actualizado.getIdUsuario());
	}

	@Test
	void deleteByIdTest() {
		try {
			gestionReservaService.deleteById(1L);
		} catch (Exception e) {
			fail("No se esperaba error");
		}
	}

	@Test
	void findByIdTest() {
		ReservaDTO reservaDTO = gestionReservaService.findById(1L);
		assertNotNull(reservaDTO);
		assertNotNull(reservaDTO.getId());
		assertEquals(LocalDateTime.of(2026, 10, 6, 0, 0), reservaDTO.getFechaInicio());
		assertEquals(LocalDateTime.of(2026, 10, 7, 0, 0), reservaDTO.getFechaFin());
		assertEquals(1L, reservaDTO.getIdPuesto());
		assertEquals(1L, reservaDTO.getIdUsuario());
	}

	@Test
	void findAllTest() {
		List<ReservaDTO> reservasDTO = gestionReservaService.findAll();
		assertNotNull(reservasDTO);
		assertEquals(2, reservasDTO.size());

		ReservaDTO reservaDTO1 = reservasDTO.getFirst();
		assertNotNull(reservaDTO1);
		assertNotNull(reservaDTO1.getId());
		assertEquals(LocalDateTime.of(2026, 10, 6, 0, 0), reservaDTO1.getFechaInicio());
		assertEquals(LocalDateTime.of(2026, 10, 7, 0, 0), reservaDTO1.getFechaFin());
		assertEquals(1L, reservaDTO1.getIdPuesto());
		assertEquals(1L, reservaDTO1.getIdUsuario());

		ReservaDTO reservaDTO2 = reservasDTO.get(1);
		assertNotNull(reservaDTO2);
		assertNotNull(reservaDTO2.getId());
		assertEquals(LocalDateTime.of(2026, 10, 8, 0, 0), reservaDTO2.getFechaInicio());
		assertEquals(LocalDateTime.of(2026, 10, 9, 0, 0), reservaDTO2.getFechaFin());
		assertEquals(2L, reservaDTO2.getIdPuesto());
		assertEquals(2L, reservaDTO2.getIdUsuario());
	}

	@Test
	void findAllByIdTest() {
		List<Long> idsList = new ArrayList<>();
		idsList.add(1L);
		idsList.add(2L);

		List<ReservaDTO> reservasDTO = gestionReservaService.findAllById(idsList);
		assertNotNull(reservasDTO);
		assertEquals(2, reservasDTO.size());

		ReservaDTO reservaDTO1 = reservasDTO.getFirst();
		assertNotNull(reservaDTO1);
		assertNotNull(reservaDTO1.getId());
		assertEquals(LocalDateTime.of(2026, 10, 6, 0, 0), reservaDTO1.getFechaInicio());
		assertEquals(LocalDateTime.of(2026, 10, 7, 0, 0), reservaDTO1.getFechaFin());
		assertEquals(1L, reservaDTO1.getIdPuesto());
		assertEquals(1L, reservaDTO1.getIdUsuario());

		ReservaDTO reservaDTO2 = reservasDTO.get(1);
		assertNotNull(reservaDTO2);
		assertNotNull(reservaDTO2.getId());
		assertEquals(LocalDateTime.of(2026, 10, 8, 0, 0), reservaDTO2.getFechaInicio());
		assertEquals(LocalDateTime.of(2026, 10, 9, 0, 0), reservaDTO2.getFechaFin());
		assertEquals(2L, reservaDTO2.getIdPuesto());
		assertEquals(2L, reservaDTO2.getIdUsuario());
	}

	@Test
	void existsById() {
		boolean exists = gestionReservaService.existsById(1L);
		assertTrue(exists);

		boolean notExists = gestionReservaService.existsById(99L);
		assertFalse(notExists);
	}

	@Test
	void findByCreacionUsuarioTest() {
		List<ReservaDTO> reservasDTO = gestionReservaService.findByCreacionUsuario("admin");
		assertNotNull(reservasDTO);
		assertEquals(2, reservasDTO.size());


		ReservaDTO reservaDTO1 = reservasDTO.getFirst();
		assertNotNull(reservaDTO1);
		assertNotNull(reservaDTO1.getId());
		assertEquals(LocalDateTime.of(2026, 10, 6, 0, 0), reservaDTO1.getFechaInicio());
		assertEquals(LocalDateTime.of(2026, 10, 7, 0, 0), reservaDTO1.getFechaFin());
		assertEquals(1L, reservaDTO1.getIdPuesto());
		assertEquals(1L, reservaDTO1.getIdUsuario());

		ReservaDTO reservaDTO2 = reservasDTO.get(1);
		assertNotNull(reservaDTO2);
		assertNotNull(reservaDTO2.getId());
		assertEquals(LocalDateTime.of(2026, 10, 8, 0, 0), reservaDTO2.getFechaInicio());
		assertEquals(LocalDateTime.of(2026, 10, 9, 0, 0), reservaDTO2.getFechaFin());
		assertEquals(2L, reservaDTO2.getIdPuesto());
		assertEquals(2L, reservaDTO2.getIdUsuario());
	}

	@Test
	void findByActualizacionUsuarioTest() {
		List<ReservaDTO> reservasDTO = gestionReservaService.findByActualizacionUsuario("admin_update");
		assertNotNull(reservasDTO);
		assertEquals(1, reservasDTO.size());

		ReservaDTO reservaDTO = reservasDTO.getFirst();
		assertNotNull(reservaDTO);
		assertNotNull(reservaDTO.getId());
		assertEquals(LocalDateTime.of(2026, 10, 8, 0, 0), reservaDTO.getFechaInicio());
		assertEquals(LocalDateTime.of(2026, 10, 9, 0, 0), reservaDTO.getFechaFin());
		assertEquals(2L, reservaDTO.getIdPuesto());
		assertEquals(2L, reservaDTO.getIdUsuario());
	}
}

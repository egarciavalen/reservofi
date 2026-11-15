package com.uoc.reservofi.reserva.business.gestion;

import com.uoc.reservofi.base.busines.exception.GestionException;
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
		assertEquals("Puesto 1", insertado.getNombrePuesto());
		assertEquals("Sala 1", insertado.getNombreSala());
		assertEquals("src/main/resources/static/images/img1.png", insertado.getRutaImagen());

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
		assertEquals("Puesto 1", reservaDTO.getNombrePuesto());
		assertEquals("Sala 1", reservaDTO.getNombreSala());
		assertEquals("src/main/resources/static/images/img1.png", reservaDTO.getRutaImagen());

	}

	@Test
	void findAllTest() {
		List<ReservaDTO> reservasDTO = gestionReservaService.findAll();
		assertNotNull(reservasDTO);
		assertEquals(3, reservasDTO.size());

		ReservaDTO reservaDTO1 = reservasDTO.getFirst();
		assertNotNull(reservaDTO1);
		assertNotNull(reservaDTO1.getId());
		assertEquals(LocalDateTime.of(2026, 10, 6, 0, 0), reservaDTO1.getFechaInicio());
		assertEquals(LocalDateTime.of(2026, 10, 7, 0, 0), reservaDTO1.getFechaFin());
		assertEquals(1L, reservaDTO1.getIdPuesto());
		assertEquals(1L, reservaDTO1.getIdUsuario());
		assertEquals("Puesto 1", reservaDTO1.getNombrePuesto());
		assertEquals("Sala 1", reservaDTO1.getNombreSala());
		assertEquals("src/main/resources/static/images/img1.png", reservaDTO1.getRutaImagen());

		ReservaDTO reservaDTO2 = reservasDTO.get(1);
		assertNotNull(reservaDTO2);
		assertNotNull(reservaDTO2.getId());
		assertEquals(LocalDateTime.of(2026, 10, 8, 0, 0), reservaDTO2.getFechaInicio());
		assertEquals(LocalDateTime.of(2026, 10, 9, 0, 0), reservaDTO2.getFechaFin());
		assertEquals(2L, reservaDTO2.getIdPuesto());
		assertEquals(2L, reservaDTO2.getIdUsuario());
		assertEquals("Puesto 2", reservaDTO2.getNombrePuesto());
		assertEquals("Sala 2", reservaDTO2.getNombreSala());
		assertEquals("src/main/resources/static/images/img2.png", reservaDTO2.getRutaImagen());

		ReservaDTO reservaDTO3 = reservasDTO.get(2);
		assertNotNull(reservaDTO3);
		assertNotNull(reservaDTO3.getId());
		assertEquals(LocalDateTime.of(2026, 10, 10, 0, 0), reservaDTO3.getFechaInicio());
		assertEquals(LocalDateTime.of(2026, 10, 11, 0, 0), reservaDTO3.getFechaFin());
		assertEquals(2L, reservaDTO3.getIdPuesto());
		assertEquals(1L, reservaDTO3.getIdUsuario());
		assertEquals("Puesto 2", reservaDTO3.getNombrePuesto());
		assertEquals("Sala 2", reservaDTO3.getNombreSala());
		assertEquals("src/main/resources/static/images/img2.png", reservaDTO3.getRutaImagen());

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
		assertEquals("Puesto 1", reservaDTO1.getNombrePuesto());
		assertEquals("Sala 1", reservaDTO1.getNombreSala());
		assertEquals("src/main/resources/static/images/img1.png", reservaDTO1.getRutaImagen());

		ReservaDTO reservaDTO2 = reservasDTO.get(1);
		assertNotNull(reservaDTO2);
		assertNotNull(reservaDTO2.getId());
		assertEquals(LocalDateTime.of(2026, 10, 8, 0, 0), reservaDTO2.getFechaInicio());
		assertEquals(LocalDateTime.of(2026, 10, 9, 0, 0), reservaDTO2.getFechaFin());
		assertEquals(2L, reservaDTO2.getIdPuesto());
		assertEquals(2L, reservaDTO2.getIdUsuario());
		assertEquals("Puesto 2", reservaDTO2.getNombrePuesto());
		assertEquals("Sala 2", reservaDTO2.getNombreSala());
		assertEquals("src/main/resources/static/images/img2.png", reservaDTO2.getRutaImagen());
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
		assertEquals(3, reservasDTO.size());

		ReservaDTO reservaDTO1 = reservasDTO.getFirst();
		assertNotNull(reservaDTO1);
		assertNotNull(reservaDTO1.getId());
		assertEquals(LocalDateTime.of(2026, 10, 6, 0, 0), reservaDTO1.getFechaInicio());
		assertEquals(LocalDateTime.of(2026, 10, 7, 0, 0), reservaDTO1.getFechaFin());
		assertEquals(1L, reservaDTO1.getIdPuesto());
		assertEquals(1L, reservaDTO1.getIdUsuario());
		assertEquals("Puesto 1", reservaDTO1.getNombrePuesto());
		assertEquals("Sala 1", reservaDTO1.getNombreSala());
		assertEquals("src/main/resources/static/images/img1.png", reservaDTO1.getRutaImagen());

		ReservaDTO reservaDTO2 = reservasDTO.get(1);
		assertNotNull(reservaDTO2);
		assertNotNull(reservaDTO2.getId());
		assertEquals(LocalDateTime.of(2026, 10, 8, 0, 0), reservaDTO2.getFechaInicio());
		assertEquals(LocalDateTime.of(2026, 10, 9, 0, 0), reservaDTO2.getFechaFin());
		assertEquals(2L, reservaDTO2.getIdPuesto());
		assertEquals(2L, reservaDTO2.getIdUsuario());
		assertEquals("Puesto 2", reservaDTO2.getNombrePuesto());
		assertEquals("Sala 2", reservaDTO2.getNombreSala());
		assertEquals("src/main/resources/static/images/img2.png", reservaDTO2.getRutaImagen());

		ReservaDTO reservaDTO3 = reservasDTO.get(2);
		assertNotNull(reservaDTO3);
		assertNotNull(reservaDTO3.getId());
		assertEquals(LocalDateTime.of(2026, 10, 10, 0, 0), reservaDTO3.getFechaInicio());
		assertEquals(LocalDateTime.of(2026, 10, 11, 0, 0), reservaDTO3.getFechaFin());
		assertEquals(2L, reservaDTO3.getIdPuesto());
		assertEquals(1L, reservaDTO3.getIdUsuario());
		assertEquals("Puesto 2", reservaDTO3.getNombrePuesto());
		assertEquals("Sala 2", reservaDTO3.getNombreSala());
		assertEquals("src/main/resources/static/images/img2.png", reservaDTO3.getRutaImagen());
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
		assertEquals("Puesto 2", reservaDTO.getNombrePuesto());
		assertEquals("Sala 2", reservaDTO.getNombreSala());
		assertEquals("src/main/resources/static/images/img2.png", reservaDTO.getRutaImagen());
	}

	@Test
	void findByUsuarioIdTest() {
		List<ReservaDTO> reservasDTO = gestionReservaService.findByUsuarioId(1L);
		assertNotNull(reservasDTO);
		assertEquals(2, reservasDTO.size());

		ReservaDTO reservaDTO1 = reservasDTO.getFirst();
		assertNotNull(reservaDTO1);
		assertNotNull(reservaDTO1.getId());
		assertEquals(LocalDateTime.of(2026, 10, 6, 0, 0), reservaDTO1.getFechaInicio());
		assertEquals(LocalDateTime.of(2026, 10, 7, 0, 0), reservaDTO1.getFechaFin());
		assertEquals(1L, reservaDTO1.getIdPuesto());
		assertEquals(1L, reservaDTO1.getIdUsuario());
		assertEquals("Puesto 1", reservaDTO1.getNombrePuesto());
		assertEquals("Sala 1", reservaDTO1.getNombreSala());
		assertEquals("src/main/resources/static/images/img1.png", reservaDTO1.getRutaImagen());

		ReservaDTO reservaDTO2 = reservasDTO.get(1);
		assertNotNull(reservaDTO2);
		assertNotNull(reservaDTO2.getId());
		assertEquals(LocalDateTime.of(2026, 10, 10, 0, 0), reservaDTO2.getFechaInicio());
		assertEquals(LocalDateTime.of(2026, 10, 11, 0, 0), reservaDTO2.getFechaFin());
		assertEquals(2L, reservaDTO2.getIdPuesto());
		assertEquals(1L, reservaDTO2.getIdUsuario());
		assertEquals("Puesto 2", reservaDTO2.getNombrePuesto());
		assertEquals("Sala 2", reservaDTO2.getNombreSala());
		assertEquals("src/main/resources/static/images/img2.png", reservaDTO2.getRutaImagen());
	}

	/* VALIDACIONES ASOCIADAS A LAS RESERVAS */

	@Test
	void validarReservaTest() {

		// Caso de una reserva que NO entra en conflicto con las demás

		ReservaDTO reservaDTO = new ReservaDTO();
		reservaDTO.setFechaInicio(LocalDateTime.of(2026, 11, 10, 0, 0));
		reservaDTO.setFechaFin(LocalDateTime.of(2026, 11, 11, 0, 0));
		reservaDTO.setIdPuesto(1L);
		reservaDTO.setIdUsuario(1L);

		try {
			gestionReservaService.validarReserva(reservaDTO);
		} catch (GestionException e) {
			fail("No se esperaba una excepción GestionException");
		}
	}

	@Test
	void validarReservaFechaInicioPosteriorTest() {

		// Caso de una reserva en la que la fecha de inicio supera a la fecha de fin
		// Se hacen varias pruebas para verificar todos los escenarios

		ReservaDTO reservaDTO = new ReservaDTO();
		reservaDTO.setIdPuesto(1L);
		reservaDTO.setIdUsuario(1L);

		// Escenario 1: diferentes años
		reservaDTO.setFechaInicio(LocalDateTime.of(2027, 11, 11, 0, 0));
		reservaDTO.setFechaFin(LocalDateTime.of(2026, 11, 11, 0, 0));
		try {
			gestionReservaService.validarReserva(reservaDTO);
			fail("Se esperaba una excepción GestionException");
		} catch (GestionException e) {
			assertEquals("La fecha de fin no puede ser anterior a la fecha de inicio.", e.getMessage());
		}

		// Escenario 2: diferentes meses
		reservaDTO.setFechaInicio(LocalDateTime.of(2026, 12, 11, 0, 0));
		reservaDTO.setFechaFin(LocalDateTime.of(2026, 11, 11, 0, 0));
		try {
			gestionReservaService.validarReserva(reservaDTO);
			fail("Se esperaba una excepción GestionException");
		} catch (GestionException e) {
			assertEquals("La fecha de fin no puede ser anterior a la fecha de inicio.", e.getMessage());
		}

		// Escenario 3: diferentes días
		reservaDTO.setFechaInicio(LocalDateTime.of(2026, 11, 12, 0, 0));
		reservaDTO.setFechaFin(LocalDateTime.of(2026, 11, 11, 0, 0));
		try {
			gestionReservaService.validarReserva(reservaDTO);
			fail("Se esperaba una excepción GestionException");
		} catch (GestionException e) {
			assertEquals("La fecha de fin no puede ser anterior a la fecha de inicio.", e.getMessage());
		}

		// Escenario 4: diferentes horas
		reservaDTO.setFechaInicio(LocalDateTime.of(2026, 11, 11, 1, 0));
		reservaDTO.setFechaFin(LocalDateTime.of(2026, 11, 11, 0, 0));
		try {
			gestionReservaService.validarReserva(reservaDTO);
			fail("Se esperaba una excepción GestionException");
		} catch (GestionException e) {
			assertEquals("La fecha de fin no puede ser anterior a la fecha de inicio.", e.getMessage());
		}

		// Escenario 5: diferentes minutos
		reservaDTO.setFechaInicio(LocalDateTime.of(2026, 11, 11, 0, 1));
		reservaDTO.setFechaFin(LocalDateTime.of(2026, 11, 11, 0, 0));
		try {
			gestionReservaService.validarReserva(reservaDTO);
			fail("Se esperaba una excepción GestionException");
		} catch (GestionException e) {
			assertEquals("La fecha de fin no puede ser anterior a la fecha de inicio.", e.getMessage());
		}
	}

	@Test
	void validarReservaPlazoSemanalTest() {


		ReservaDTO reservaDTO = new ReservaDTO();
		reservaDTO.setIdPuesto(1L);
		reservaDTO.setIdUsuario(1L);

		// Se establece entre la fecha de inicio y la de fin una diferencia semanal menor de 7 días

		// Escenario 1: diferencia semanal por año
		reservaDTO.setFechaInicio(LocalDateTime.of(2026, 11, 11, 0, 0));
		reservaDTO.setFechaFin(LocalDateTime.of(2027, 11, 11, 0, 0));
		try {
			gestionReservaService.validarReserva(reservaDTO);
			fail("Se esperaba una excepción GestionException");
		} catch (GestionException e) {
			assertEquals("No se pueden hacer reservas superiores a 7 días.", e.getMessage());
		}

		// Escenario 2: diferencia semanal por mes
		reservaDTO.setFechaInicio(LocalDateTime.of(2026, 11, 11, 0, 0));
		reservaDTO.setFechaFin(LocalDateTime.of(2026, 12, 11, 0, 0));
		try {
			gestionReservaService.validarReserva(reservaDTO);
			fail("Se esperaba una excepción GestionException");
		} catch (GestionException e) {
			assertEquals("No se pueden hacer reservas superiores a 7 días.", e.getMessage());
		}

		// Escenario 3: diferencia semanal por días, horas y minutos
		reservaDTO.setFechaInicio(LocalDateTime.of(2026, 11, 11, 10, 32));
		reservaDTO.setFechaFin(LocalDateTime.of(2026, 11, 18, 10, 32));
		try {
			gestionReservaService.validarReserva(reservaDTO);
			fail("Se esperaba una excepción GestionException");
		} catch (GestionException e) {
			assertEquals("No se pueden hacer reservas superiores a 7 días.", e.getMessage());
		}

		// Escenario 4: se comprueba por 1 segundo que no salte la validación a los 7 días
		reservaDTO.setFechaInicio(LocalDateTime.of(2026, 11, 11, 10, 32));
		reservaDTO.setFechaFin(LocalDateTime.of(2026, 11, 18, 10, 31));
		try {
			gestionReservaService.validarReserva(reservaDTO);
		} catch (GestionException e) {
			fail("No se esperaba una excepción GestionException");
		}
	}

	@Test
	void validarReservaRangoMismoUsuarioTest() {

		ReservaDTO reservaDTO = new ReservaDTO();
		reservaDTO.setIdPuesto(1L);
		reservaDTO.setIdUsuario(1L);

		// Si se establece una reserva que entra en conflicto para un mismo puesto en un mismo usuario, salta la validación
		// Esto debería saltar INDEPENDIENTEMENTE de si es el mismo puesto o no; puesto que la persona no puede estar en dos sitios a la vez

		// Escenario 1: está en el rango de la fecha y la abarca por completo (para un mismo puesto)
		reservaDTO.setFechaInicio(LocalDateTime.of(2026, 10, 6, 10, 0));
		reservaDTO.setFechaFin(LocalDateTime.of(2026, 10, 6, 20, 0));
		try {
			gestionReservaService.validarReserva(reservaDTO);
			fail("Se esperaba una excepción GestionException");
		} catch (GestionException e) {
			assertEquals("La reserva que se quiere hacer coincide con otra reserva propia en el sistema.", e.getMessage());
		}

		// Escenario 2: está en el rango de la fecha, cubriendo la fecha de inicio (para un mismo puesto)
		reservaDTO.setFechaInicio(LocalDateTime.of(2026, 10, 6, 22, 29));
		reservaDTO.setFechaFin(LocalDateTime.of(2026, 10, 7, 0, 0));
		try {
			gestionReservaService.validarReserva(reservaDTO);
			fail("Se esperaba una excepción GestionException");
		} catch (GestionException e) {
			assertEquals("La reserva que se quiere hacer coincide con otra reserva propia en el sistema.", e.getMessage());
		}

		// Escenario 3: está en el rango de la fecha, cubriendo la fecha de fin (para un mismo puesto)
		reservaDTO.setFechaInicio(LocalDateTime.of(2026, 10, 4, 0, 0));
		reservaDTO.setFechaFin(LocalDateTime.of(2026, 10, 6, 10, 31));
		try {
			gestionReservaService.validarReserva(reservaDTO);
			fail("Se esperaba una excepción GestionException");
		} catch (GestionException e) {
			assertEquals("La reserva que se quiere hacer coincide con otra reserva propia en el sistema.", e.getMessage());
		}

		// Escenario 4: se pone una fecha que entra en conflicto con una reserva en otro puesto (tanto la fecha de inicio como de fin están en rango)
		reservaDTO.setFechaInicio(LocalDateTime.of(2026, 10, 10, 10, 35));
		reservaDTO.setFechaFin(LocalDateTime.of(2026, 10, 10, 15, 31));
		try {
			gestionReservaService.validarReserva(reservaDTO);
			fail("Se esperaba una excepción GestionException");
		} catch (GestionException e) {
			assertEquals("La reserva que se quiere hacer coincide con otra reserva propia en el sistema.", e.getMessage());
		}

		// Escenario 5: se pone una fecha que entra en conflicto con una reserva en otro puesto (la fecha de inicio está en rango)
		reservaDTO.setFechaInicio(LocalDateTime.of(2026, 10, 10, 10, 35));
		reservaDTO.setFechaFin(LocalDateTime.of(2026, 10, 11, 15, 31));
		try {
			gestionReservaService.validarReserva(reservaDTO);
			fail("Se esperaba una excepción GestionException");
		} catch (GestionException e) {
			assertEquals("La reserva que se quiere hacer coincide con otra reserva propia en el sistema.", e.getMessage());
		}

		// Escenario 6: se pone una fecha que entra en conflicto con una reserva en otro puesto (la fecha de fin está en rango)
		reservaDTO.setFechaInicio(LocalDateTime.of(2026, 10, 9, 10, 35));
		reservaDTO.setFechaFin(LocalDateTime.of(2026, 10, 10, 15, 31));
		try {
			gestionReservaService.validarReserva(reservaDTO);
			fail("Se esperaba una excepción GestionException");
		} catch (GestionException e) {
			assertEquals("La reserva que se quiere hacer coincide con otra reserva propia en el sistema.", e.getMessage());
		}

	}

	@Test
	void validarReservaRangoDistintoUsuarioTest() {

		ReservaDTO reservaDTO = new ReservaDTO();
		reservaDTO.setIdPuesto(2L); // OJO -> aquí se produce un cambio de puesto
		reservaDTO.setIdUsuario(1L);

		// En el caso de que se compare con otros usuarios, únicamente saltará la validación si se trata DEL MISMO PUESTO

		// Escenario 1: está en el rango de la fecha y la abarca por completo (para el mismo puesto)
		reservaDTO.setFechaInicio(LocalDateTime.of(2026, 10, 8, 10, 30));
		reservaDTO.setFechaFin(LocalDateTime.of(2026, 10, 8, 20, 45));
		try {
			gestionReservaService.validarReserva(reservaDTO);
			fail("Se esperaba una excepción GestionException");
		} catch (GestionException e) {
			assertEquals("La reserva que se quiere hacer coincide con otra reserva hecha por otro usuario.", e.getMessage());
		}

		// Escenario 2: está en el rango de la fecha, cubriendo la fecha de inicio (para el mismo puesto)
		reservaDTO.setFechaInicio(LocalDateTime.of(2026, 10, 8, 0, 30));
		reservaDTO.setFechaFin(LocalDateTime.of(2026, 10, 9, 20, 45));
		try {
			gestionReservaService.validarReserva(reservaDTO);
			fail("Se esperaba una excepción GestionException");
		} catch (GestionException e) {
			assertEquals("La reserva que se quiere hacer coincide con otra reserva hecha por otro usuario.", e.getMessage());
		}

		// Escenario 3: está en el rango de la fecha, cubriendo la fecha de fin (para el mismo puesto)
		reservaDTO.setFechaInicio(LocalDateTime.of(2026, 10, 4, 10, 30));
		reservaDTO.setFechaFin(LocalDateTime.of(2026, 10, 8, 20, 45));
		try {
			gestionReservaService.validarReserva(reservaDTO);
			fail("Se esperaba una excepción GestionException");
		} catch (GestionException e) {
			assertEquals("La reserva que se quiere hacer coincide con otra reserva hecha por otro usuario.", e.getMessage());
		}

		// Se repiten a continuación los mismos escenarios pero cambiando el puesto; NO debería saltar validaciones
		reservaDTO.setIdPuesto(1L); // OJO -> aquí se produce un cambio de puesto

		// Escenario 4: está en el rango de la fecha y la abarca por completo (para DISTINTO puesto)
		reservaDTO.setFechaInicio(LocalDateTime.of(2026, 10, 8, 10, 30));
		reservaDTO.setFechaFin(LocalDateTime.of(2026, 10, 8, 20, 45));
		try {
			gestionReservaService.validarReserva(reservaDTO);
		} catch (GestionException e) {
			fail("No se esperaba una excepción GestionException");
		}

		// Escenario 5: está en el rango de la fecha, cubriendo la fecha de inicio (para DISTINTO puesto)
		reservaDTO.setFechaInicio(LocalDateTime.of(2026, 10, 8, 10, 30));
		reservaDTO.setFechaFin(LocalDateTime.of(2026, 10, 9, 20, 45));
		try {
			gestionReservaService.validarReserva(reservaDTO);
		} catch (GestionException e) {
			fail("No se esperaba una excepción GestionException");
		}

		// Escenario 6: está en el rango de la fecha, cubriendo la fecha de fin (para DISTINTO puesto)
		reservaDTO.setFechaInicio(LocalDateTime.of(2026, 10, 4, 10, 30));
		reservaDTO.setFechaFin(LocalDateTime.of(2026, 10, 8, 20, 45));
		try {
			gestionReservaService.validarReserva(reservaDTO);
		} catch (GestionException e) {
			fail("No se esperaba una excepción GestionException");
		}
	}
}

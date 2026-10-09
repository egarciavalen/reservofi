package com.uoc.reservofi.puesto.business.gestion;

import com.uoc.reservofi.base.business.AbstractTest;
import com.uoc.reservofi.puesto.business.dto.PuestoDTO;
import com.uoc.reservofi.puesto.business.service.GestionPuestoService;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class GestionPuestoServiceTest extends AbstractTest {

	@Autowired
	private GestionPuestoService gestionPuestoService;

	@Test
	void insertTest() {
		PuestoDTO puestoDTO = new PuestoDTO();
		puestoDTO.setNombre("PuestoDTO prueba");
		puestoDTO.setCoordenadaX(BigDecimal.valueOf(11.14));
		puestoDTO.setCoordenadaY(BigDecimal.valueOf(22.25));
		puestoDTO.setIdSala(1L);

		PuestoDTO insertado = gestionPuestoService.insert(puestoDTO);
		assertNotNull(insertado);
		assertNotNull(insertado.getId());
		assertEquals("PuestoDTO prueba", insertado.getNombre());
		assertEquals(BigDecimal.valueOf(11.14), insertado.getCoordenadaX());
		assertEquals(BigDecimal.valueOf(22.25), insertado.getCoordenadaY());
		assertEquals(1L, insertado.getIdSala());
	}

	@Test
	void validateSalaTest() {
		PuestoDTO puestoDTO = new PuestoDTO();
		puestoDTO.setNombre("PuestoDTO prueba");
		puestoDTO.setCoordenadaX(BigDecimal.valueOf(11.14));
		puestoDTO.setCoordenadaY(BigDecimal.valueOf(22.25));
		puestoDTO.setIdSala(1L);

		// Sala nula
		puestoDTO.setIdSala(null);
		try {
			gestionPuestoService.insert(puestoDTO);
			fail("Se esperaba una ConstraintViolationException");
		} catch (ConstraintViolationException e) {
			assertEquals("insert.entidad.idSala: puesto.sala.obligatorio", e.getMessage());

		} catch (Exception e) {
			fail("Se esperaba una ConstraintViolationException");
		}
	}

	@Test
	void validateNombreTest() {
		PuestoDTO puestoDTO = new PuestoDTO();
		puestoDTO.setNombre("PuestoDTO prueba");
		puestoDTO.setCoordenadaX(BigDecimal.valueOf(11.14));
		puestoDTO.setCoordenadaY(BigDecimal.valueOf(22.25));
		puestoDTO.setIdSala(1L);

		// Nombre nulo
		puestoDTO.setNombre(null);
		try {
			gestionPuestoService.insert(puestoDTO);
			fail("Se esperaba una ConstraintViolationException");
		} catch (ConstraintViolationException e) {
			assertEquals("insert.entidad.nombre: puesto.nombre.obligatorio", e.getMessage());

		} catch (Exception e) {
			fail("Se esperaba una ConstraintViolationException");
		}

		// Nombre vacío
		puestoDTO.setNombre("");
		try {
			gestionPuestoService.insert(puestoDTO);
			fail("Se esperaba una ConstraintViolationException");
		} catch (ConstraintViolationException e) {
			assertEquals("insert.entidad.nombre: puesto.nombre.obligatorio", e.getMessage());

		} catch (Exception e) {
			fail("Se esperaba una ConstraintViolationException");
		}

		// Nombre tamanio maximo alcanzado
		puestoDTO.setNombre("1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890a");
		try {
			gestionPuestoService.insert(puestoDTO);
			fail("Se esperaba una ConstraintViolationException");
		} catch (ConstraintViolationException e) {
			assertEquals("insert.entidad.nombre: puesto.nombre.tamanio.maximo", e.getMessage());

		} catch (Exception e) {
			fail("Se esperaba una ConstraintViolationException");
		}
	}

	@Test
	void validateCoordenadaXTest() {
		PuestoDTO puestoDTO = new PuestoDTO();
		puestoDTO.setNombre("PuestoDTO prueba");
		puestoDTO.setCoordenadaX(BigDecimal.valueOf(11.14));
		puestoDTO.setCoordenadaY(BigDecimal.valueOf(22.25));
		puestoDTO.setIdSala(1L);

		// Coordenada X nula
		puestoDTO.setCoordenadaX(null);
		try {
			gestionPuestoService.insert(puestoDTO);
			fail("Se esperaba una ConstraintViolationException");
		} catch (ConstraintViolationException e) {
			assertEquals("insert.entidad.coordenadaX: puesto.coordenadaX.obligatorio", e.getMessage());

		} catch (Exception e) {
			fail("Se esperaba una ConstraintViolationException");
		}

		// Coordenada X tamanio maximo
		puestoDTO.setCoordenadaX(BigDecimal.valueOf(1000000));
		try {
			gestionPuestoService.insert(puestoDTO);
			fail("Se esperaba una ConstraintViolationException");
		} catch (ConstraintViolationException e) {
			assertEquals("insert.entidad.coordenadaX: puesto.coordenadaX.tamanio.maximo", e.getMessage());

		} catch (Exception e) {
			fail("Se esperaba una ConstraintViolationException");
		}
	}

	@Test
	void validateCoordenadaYTest() {
		PuestoDTO puestoDTO = new PuestoDTO();
		puestoDTO.setNombre("PuestoDTO prueba");
		puestoDTO.setCoordenadaX(BigDecimal.valueOf(11.14));
		puestoDTO.setCoordenadaY(BigDecimal.valueOf(22.25));
		puestoDTO.setIdSala(1L);

		// Coordenada Y nula
		puestoDTO.setCoordenadaY(null);
		try {
			gestionPuestoService.insert(puestoDTO);
			fail("Se esperaba una ConstraintViolationException");
		} catch (ConstraintViolationException e) {
			assertEquals("insert.entidad.coordenadaY: puesto.coordenadaY.obligatorio", e.getMessage());

		} catch (Exception e) {
			fail("Se esperaba una ConstraintViolationException");
		}

		// Coordenada X tamanio maximo
		puestoDTO.setCoordenadaY(BigDecimal.valueOf(1000000));
		try {
			gestionPuestoService.insert(puestoDTO);
			fail("Se esperaba una ConstraintViolationException");
		} catch (ConstraintViolationException e) {
			assertEquals("insert.entidad.coordenadaY: puesto.coordenadaY.tamanio.maximo", e.getMessage());

		} catch (Exception e) {
			fail("Se esperaba una ConstraintViolationException");
		}
	}


	@Test
	void updateTest() {

		PuestoDTO puestoDTO = new PuestoDTO();
		puestoDTO.setId(1L); // ID existente
		puestoDTO.setNombre("PuestoDTO prueba");
		puestoDTO.setCoordenadaX(BigDecimal.valueOf(55.55));
		puestoDTO.setCoordenadaY(BigDecimal.valueOf(43.22));
		puestoDTO.setIdSala(1L);

		PuestoDTO actualizado = gestionPuestoService.update(puestoDTO);
		assertNotNull(actualizado);
		assertEquals(1L, actualizado.getId());
		assertEquals("PuestoDTO prueba", actualizado.getNombre());
		assertEquals(BigDecimal.valueOf(55.55), actualizado.getCoordenadaX());
		assertEquals(BigDecimal.valueOf(43.22), actualizado.getCoordenadaY());
		assertEquals(1L, actualizado.getIdSala());


	}

	@Test
	void deleteByIdTest() {
		try {
			gestionPuestoService.deleteById(3L);
		} catch (Exception e) {
			fail("No se esperaba error");
		}
	}

	@Test
	void deleteByIdWithFKTest() {
		// Registro asociado a la tabla RESERVA
		try {
			gestionPuestoService.deleteById(1L);
			fail("Se esperaba una excepción DataIntegrityViolationException");
		} catch (DataIntegrityViolationException e) {
			assertNotNull(e.getMessage());
		} catch (Exception e) {
			fail("Se esperaba una excepción DataIntegrityViolationException");
		}
	}

	@Test
	void findByIdTest() {
		PuestoDTO puestoDTO = gestionPuestoService.findById(1L);
		assertNotNull(puestoDTO);
		assertEquals(1, puestoDTO.getId());
		assertEquals("Puesto 1", puestoDTO.getNombre());
		assertEquals(BigDecimal.valueOf(55.55), puestoDTO.getCoordenadaX());
		assertEquals(BigDecimal.valueOf(43.22), puestoDTO.getCoordenadaY());
		assertEquals(1L, puestoDTO.getIdSala());
	}

	@Test
	void findAllTest() {
		List<PuestoDTO> puestosDTO = gestionPuestoService.findAll();
		assertNotNull(puestosDTO);
		assertEquals(3, puestosDTO.size());

		PuestoDTO puestoDTO1 = puestosDTO.getFirst();
		assertEquals(1, puestoDTO1.getId());
		assertEquals("Puesto 1", puestoDTO1.getNombre());
		assertEquals(BigDecimal.valueOf(55.55), puestoDTO1.getCoordenadaX());
		assertEquals(BigDecimal.valueOf(43.22), puestoDTO1.getCoordenadaY());
		assertEquals(1L, puestoDTO1.getIdSala());

		PuestoDTO puestoDTO2 = puestosDTO.get(1);
		assertEquals(2, puestoDTO2.getId());
		assertEquals("Puesto 2", puestoDTO2.getNombre());
		assertEquals(BigDecimal.valueOf(32.12), puestoDTO2.getCoordenadaX());
		assertEquals(BigDecimal.valueOf(87.93), puestoDTO2.getCoordenadaY());
		assertEquals(2L, puestoDTO2.getIdSala());

		PuestoDTO puestoDTO3 = puestosDTO.get(2);
		assertEquals(3, puestoDTO3.getId());
		assertEquals("Puesto 3", puestoDTO3.getNombre());
		assertEquals(BigDecimal.valueOf(78.98), puestoDTO3.getCoordenadaX());
		assertEquals(BigDecimal.valueOf(15.32), puestoDTO3.getCoordenadaY());
		assertEquals(1L, puestoDTO3.getIdSala());
	}

	@Test
	void findAllByIdTest() {
		List<Long> idsList = new ArrayList<>();
		idsList.add(1L);
		idsList.add(2L);

		List<PuestoDTO> puestosDTO = gestionPuestoService.findAllById(idsList);
		assertNotNull(puestosDTO);
		assertEquals(2, puestosDTO.size());

		PuestoDTO puestoDTO1 = puestosDTO.getFirst();
		assertEquals(1, puestoDTO1.getId());
		assertEquals("Puesto 1", puestoDTO1.getNombre());
		assertEquals(BigDecimal.valueOf(55.55), puestoDTO1.getCoordenadaX());
		assertEquals(BigDecimal.valueOf(43.22), puestoDTO1.getCoordenadaY());
		assertEquals(1L, puestoDTO1.getIdSala());

		PuestoDTO puestoDTO2 = puestosDTO.get(1);
		assertEquals(2, puestoDTO2.getId());
		assertEquals("Puesto 2", puestoDTO2.getNombre());
		assertEquals(BigDecimal.valueOf(32.12), puestoDTO2.getCoordenadaX());
		assertEquals(BigDecimal.valueOf(87.93), puestoDTO2.getCoordenadaY());
		assertEquals(2L, puestoDTO2.getIdSala());
	}

	@Test
	void existsById() {
		boolean exists = gestionPuestoService.existsById(1L);
		assertTrue(exists);

		boolean notExists = gestionPuestoService.existsById(99L);
		assertFalse(notExists);
	}

	@Test
	void findByCreacionUsuarioTest() {
		List<PuestoDTO> puestosDTO = gestionPuestoService.findByCreacionUsuario("admin");
		assertNotNull(puestosDTO);
		assertEquals(3, puestosDTO.size());

		PuestoDTO puestoDTO1 = puestosDTO.getFirst();
		assertEquals(1, puestoDTO1.getId());
		assertEquals("Puesto 1", puestoDTO1.getNombre());
		assertEquals(BigDecimal.valueOf(55.55), puestoDTO1.getCoordenadaX());
		assertEquals(BigDecimal.valueOf(43.22), puestoDTO1.getCoordenadaY());
		assertEquals(1L, puestoDTO1.getIdSala());

		PuestoDTO puestoDTO2 = puestosDTO.get(1);
		assertEquals(2, puestoDTO2.getId());
		assertEquals("Puesto 2", puestoDTO2.getNombre());
		assertEquals(BigDecimal.valueOf(32.12), puestoDTO2.getCoordenadaX());
		assertEquals(BigDecimal.valueOf(87.93), puestoDTO2.getCoordenadaY());
		assertEquals(2L, puestoDTO2.getIdSala());

		PuestoDTO puestoDTO3 = puestosDTO.get(2);
		assertEquals(3, puestoDTO3.getId());
		assertEquals("Puesto 3", puestoDTO3.getNombre());
		assertEquals(BigDecimal.valueOf(78.98), puestoDTO3.getCoordenadaX());
		assertEquals(BigDecimal.valueOf(15.32), puestoDTO3.getCoordenadaY());
		assertEquals(1L, puestoDTO3.getIdSala());
	}

	@Test
	void findByActualizacionUsuarioTest() {
		List<PuestoDTO> puestosDTO = gestionPuestoService.findByActualizacionUsuario("admin_update");
		assertNotNull(puestosDTO);
		assertEquals(1, puestosDTO.size());

		PuestoDTO puestoDTO = puestosDTO.getFirst();
		assertEquals(2, puestoDTO.getId());
		assertEquals("Puesto 2", puestoDTO.getNombre());
		assertEquals(BigDecimal.valueOf(32.12), puestoDTO.getCoordenadaX());
		assertEquals(BigDecimal.valueOf(87.93), puestoDTO.getCoordenadaY());
		assertEquals(2L, puestoDTO.getIdSala());
	}


}

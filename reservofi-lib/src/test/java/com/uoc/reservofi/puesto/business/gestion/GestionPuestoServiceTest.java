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
		puestoDTO.setAncho(BigDecimal.valueOf(33.36));
		puestoDTO.setAlto(BigDecimal.valueOf(44.47));
		puestoDTO.setIdSala(1L);

		PuestoDTO insertado = gestionPuestoService.insert(puestoDTO);
		assertNotNull(insertado);
		assertNotNull(insertado.getId());
		assertEquals("PuestoDTO prueba", insertado.getNombre());
		assertEquals(BigDecimal.valueOf(11.14), insertado.getCoordenadaX());
		assertEquals(BigDecimal.valueOf(22.25), insertado.getCoordenadaY());
		assertEquals(BigDecimal.valueOf(33.36), insertado.getAncho());
		assertEquals(BigDecimal.valueOf(44.47), insertado.getAlto());
		assertEquals(1L, insertado.getIdSala());
	}

	@Test
	void validateSalaTest() {
		PuestoDTO puestoDTO = new PuestoDTO();
		puestoDTO.setNombre("PuestoDTO prueba");
		puestoDTO.setCoordenadaX(BigDecimal.valueOf(11.14));
		puestoDTO.setCoordenadaY(BigDecimal.valueOf(22.25));
		puestoDTO.setAncho(BigDecimal.valueOf(33.36));
		puestoDTO.setAlto(BigDecimal.valueOf(44.47));
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
		puestoDTO.setAncho(BigDecimal.valueOf(33.36));
		puestoDTO.setAlto(BigDecimal.valueOf(44.47));
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
		puestoDTO.setAncho(BigDecimal.valueOf(33.36));
		puestoDTO.setAlto(BigDecimal.valueOf(44.47));
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
		puestoDTO.setAncho(BigDecimal.valueOf(33.36));
		puestoDTO.setAlto(BigDecimal.valueOf(44.47));
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
	void validateAnchoTest() {
		PuestoDTO puestoDTO = new PuestoDTO();
		puestoDTO.setNombre("PuestoDTO prueba");
		puestoDTO.setCoordenadaX(BigDecimal.valueOf(11.14));
		puestoDTO.setCoordenadaY(BigDecimal.valueOf(22.25));
		puestoDTO.setAncho(BigDecimal.valueOf(33.36));
		puestoDTO.setAlto(BigDecimal.valueOf(44.47));
		puestoDTO.setIdSala(1L);

		// Ancho nulo
		puestoDTO.setAncho(null);
		try {
			gestionPuestoService.insert(puestoDTO);
			fail("Se esperaba una ConstraintViolationException");
		} catch (ConstraintViolationException e) {
			assertEquals("insert.entidad.ancho: puesto.ancho.obligatorio", e.getMessage());

		} catch (Exception e) {
			fail("Se esperaba una ConstraintViolationException");
		}

		// Ancho tamanio maximo
		puestoDTO.setAncho(BigDecimal.valueOf(1000000));
		try {
			gestionPuestoService.insert(puestoDTO);
			fail("Se esperaba una ConstraintViolationException");
		} catch (ConstraintViolationException e) {
			assertEquals("insert.entidad.ancho: puesto.ancho.tamanio.maximo", e.getMessage());

		} catch (Exception e) {
			fail("Se esperaba una ConstraintViolationException");
		}
	}

	@Test
	void validateAltoTest() {
		PuestoDTO puestoDTO = new PuestoDTO();
		puestoDTO.setNombre("PuestoDTO prueba");
		puestoDTO.setCoordenadaX(BigDecimal.valueOf(11.14));
		puestoDTO.setCoordenadaY(BigDecimal.valueOf(22.25));
		puestoDTO.setAncho(BigDecimal.valueOf(33.36));
		puestoDTO.setAlto(BigDecimal.valueOf(44.47));
		puestoDTO.setIdSala(1L);

		// Alto nulo
		puestoDTO.setAlto(null);
		try {
			gestionPuestoService.insert(puestoDTO);
			fail("Se esperaba una ConstraintViolationException");
		} catch (ConstraintViolationException e) {
			assertEquals("insert.entidad.alto: puesto.alto.obligatorio", e.getMessage());

		} catch (Exception e) {
			fail("Se esperaba una ConstraintViolationException");
		}

		// Alto tamanio maximo
		puestoDTO.setAlto(BigDecimal.valueOf(1000000));
		try {
			gestionPuestoService.insert(puestoDTO);
			fail("Se esperaba una ConstraintViolationException");
		} catch (ConstraintViolationException e) {
			assertEquals("insert.entidad.alto: puesto.alto.tamanio.maximo", e.getMessage());

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
		puestoDTO.setAncho(BigDecimal.valueOf(31.42));
		puestoDTO.setAlto(BigDecimal.valueOf(11.64));
		puestoDTO.setIdSala(1L);

		PuestoDTO actualizado = gestionPuestoService.update(puestoDTO);
		assertNotNull(actualizado);
		assertEquals(1L, actualizado.getId());
		assertEquals("PuestoDTO prueba", actualizado.getNombre());
		assertEquals(BigDecimal.valueOf(55.55), actualizado.getCoordenadaX());
		assertEquals(BigDecimal.valueOf(43.22), actualizado.getCoordenadaY());
		assertEquals(BigDecimal.valueOf(31.42), actualizado.getAncho());
		assertEquals(BigDecimal.valueOf(11.64), actualizado.getAlto());
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
		// Registro asociado a la tabla RESERVA, se borraría la FK correspondiente
		try {
			gestionPuestoService.deleteById(1L);
		} catch (DataIntegrityViolationException e) {
			fail("No se esperaba una excepción DataIntegrityViolationException");
		} catch (Exception e) {
			fail("Se esperaba una excepción genérica");
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
		assertEquals(BigDecimal.valueOf(31.42), puestoDTO.getAncho());
		assertEquals(BigDecimal.valueOf(11.64), puestoDTO.getAlto());
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
		assertEquals(BigDecimal.valueOf(31.42), puestoDTO1.getAncho());
		assertEquals(BigDecimal.valueOf(11.64), puestoDTO1.getAlto());
		assertEquals(1L, puestoDTO1.getIdSala());

		PuestoDTO puestoDTO2 = puestosDTO.get(1);
		assertEquals(2, puestoDTO2.getId());
		assertEquals("Puesto 2", puestoDTO2.getNombre());
		assertEquals(BigDecimal.valueOf(32.12), puestoDTO2.getCoordenadaX());
		assertEquals(BigDecimal.valueOf(87.93), puestoDTO2.getCoordenadaY());
		assertEquals(BigDecimal.valueOf(42.53), puestoDTO2.getAncho());
		assertEquals(BigDecimal.valueOf(22.75), puestoDTO2.getAlto());
		assertEquals(2L, puestoDTO2.getIdSala());

		PuestoDTO puestoDTO3 = puestosDTO.get(2);
		assertEquals(3, puestoDTO3.getId());
		assertEquals("Puesto 3", puestoDTO3.getNombre());
		assertEquals(BigDecimal.valueOf(78.98), puestoDTO3.getCoordenadaX());
		assertEquals(BigDecimal.valueOf(15.32), puestoDTO3.getCoordenadaY());
		assertEquals(BigDecimal.valueOf(53.64), puestoDTO3.getAncho());
		assertEquals(BigDecimal.valueOf(33.86), puestoDTO3.getAlto());
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
		assertEquals(BigDecimal.valueOf(31.42), puestoDTO1.getAncho());
		assertEquals(BigDecimal.valueOf(11.64), puestoDTO1.getAlto());
		assertEquals(1L, puestoDTO1.getIdSala());

		PuestoDTO puestoDTO2 = puestosDTO.get(1);
		assertEquals(2, puestoDTO2.getId());
		assertEquals("Puesto 2", puestoDTO2.getNombre());
		assertEquals(BigDecimal.valueOf(32.12), puestoDTO2.getCoordenadaX());
		assertEquals(BigDecimal.valueOf(87.93), puestoDTO2.getCoordenadaY());
		assertEquals(BigDecimal.valueOf(42.53), puestoDTO2.getAncho());
		assertEquals(BigDecimal.valueOf(22.75), puestoDTO2.getAlto());
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
		assertEquals(BigDecimal.valueOf(31.42), puestoDTO1.getAncho());
		assertEquals(BigDecimal.valueOf(11.64), puestoDTO1.getAlto());
		assertEquals(1L, puestoDTO1.getIdSala());

		PuestoDTO puestoDTO2 = puestosDTO.get(1);
		assertEquals(2, puestoDTO2.getId());
		assertEquals("Puesto 2", puestoDTO2.getNombre());
		assertEquals(BigDecimal.valueOf(32.12), puestoDTO2.getCoordenadaX());
		assertEquals(BigDecimal.valueOf(87.93), puestoDTO2.getCoordenadaY());
		assertEquals(BigDecimal.valueOf(42.53), puestoDTO2.getAncho());
		assertEquals(BigDecimal.valueOf(22.75), puestoDTO2.getAlto());
		assertEquals(2L, puestoDTO2.getIdSala());

		PuestoDTO puestoDTO3 = puestosDTO.get(2);
		assertEquals(3, puestoDTO3.getId());
		assertEquals("Puesto 3", puestoDTO3.getNombre());
		assertEquals(BigDecimal.valueOf(78.98), puestoDTO3.getCoordenadaX());
		assertEquals(BigDecimal.valueOf(15.32), puestoDTO3.getCoordenadaY());
		assertEquals(BigDecimal.valueOf(53.64), puestoDTO3.getAncho());
		assertEquals(BigDecimal.valueOf(33.86), puestoDTO3.getAlto());
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
		assertEquals(BigDecimal.valueOf(42.53), puestoDTO.getAncho());
		assertEquals(BigDecimal.valueOf(22.75), puestoDTO.getAlto());
		assertEquals(2L, puestoDTO.getIdSala());
	}
}

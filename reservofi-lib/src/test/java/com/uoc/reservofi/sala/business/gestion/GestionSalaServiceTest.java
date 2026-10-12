package com.uoc.reservofi.sala.business.gestion;

import com.uoc.reservofi.base.business.AbstractTest;
import com.uoc.reservofi.sala.business.dto.SalaDTO;
import com.uoc.reservofi.sala.business.service.GestionSalaService;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class GestionSalaServiceTest extends AbstractTest {

	@Autowired
	private GestionSalaService gestionSalaService;

	@Test
	void insertTest() {
		SalaDTO salaDTO = new SalaDTO();
		salaDTO.setNombre("SalaDTO prueba");
		salaDTO.setRutaImagen("src/main/resources/static/images/prueba.png");

		SalaDTO insertado = gestionSalaService.insert(salaDTO);
		assertNotNull(insertado);
		assertNotNull(insertado.getId());
		assertEquals("SalaDTO prueba", insertado.getNombre());
		assertEquals("src/main/resources/static/images/prueba.png", insertado.getRutaImagen());
	}

	@Test
	void validateNombreTest() {
		SalaDTO salaDTO = new SalaDTO();
		salaDTO.setNombre("SalaDTO prueba");
		salaDTO.setRutaImagen("src/main/resources/static/images/prueba.png");

		// Nombre nulo
		salaDTO.setNombre(null);
		try {
			gestionSalaService.insert(salaDTO);
			fail("Se esperaba una ConstraintViolationException");
		} catch (ConstraintViolationException e) {
			assertEquals("insert.entidad.nombre: sala.nombre.obligatorio", e.getMessage());

		} catch (Exception e) {
			fail("Se esperaba una ConstraintViolationException");
		}

		// Nombre vacío
		salaDTO.setNombre("");
		try {
			gestionSalaService.insert(salaDTO);
			fail("Se esperaba una ConstraintViolationException");
		} catch (ConstraintViolationException e) {
			assertEquals("insert.entidad.nombre: sala.nombre.obligatorio", e.getMessage());

		} catch (Exception e) {
			fail("Se esperaba una ConstraintViolationException");
		}

		// Nombre tamanio maximo alcanzado
		salaDTO.setNombre("1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890a");
		try {
			gestionSalaService.insert(salaDTO);
			fail("Se esperaba una ConstraintViolationException");
		} catch (ConstraintViolationException e) {
			assertEquals("insert.entidad.nombre: sala.nombre.tamanio.maximo", e.getMessage());

		} catch (Exception e) {
			fail("Se esperaba una ConstraintViolationException");
		}
	}

	@Test
	void validateRutaImagenTest() {
		SalaDTO salaDTO = new SalaDTO();
		salaDTO.setNombre("SalaDTO prueba");
		salaDTO.setRutaImagen("src/main/resources/static/images/prueba.png");

		// Ruta imagen nula
		salaDTO.setRutaImagen(null);
		try {
			gestionSalaService.insert(salaDTO);
			fail("Se esperaba una ConstraintViolationException");
		} catch (ConstraintViolationException e) {
			assertEquals("insert.entidad.rutaImagen: sala.rutaImagen.obligatorio", e.getMessage());

		} catch (Exception e) {
			fail("Se esperaba una ConstraintViolationException");
		}

		// Nombre vacío
		salaDTO.setRutaImagen("");
		try {
			gestionSalaService.insert(salaDTO);
			fail("Se esperaba una ConstraintViolationException");
		} catch (ConstraintViolationException e) {
			assertEquals("insert.entidad.rutaImagen: sala.rutaImagen.obligatorio", e.getMessage());

		} catch (Exception e) {
			fail("Se esperaba una ConstraintViolationException");
		}

		// Nombre tamanio maximo alcanzado
		salaDTO.setRutaImagen("1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890_abcde");
		try {
			gestionSalaService.insert(salaDTO);
			fail("Se esperaba una ConstraintViolationException");
		} catch (ConstraintViolationException e) {
			assertEquals("insert.entidad.rutaImagen: sala.rutaImagen.tamanio.maximo", e.getMessage());

		} catch (Exception e) {
			fail("Se esperaba una ConstraintViolationException");
		}
	}


	@Test
	void updateTest() {

		SalaDTO salaDTO = new SalaDTO();
		salaDTO.setId(1L); // ID existente
		salaDTO.setNombre("SalaDTO prueba");
		salaDTO.setRutaImagen("src/main/resources/static/images/prueba.png");

		SalaDTO actualizado = gestionSalaService.update(salaDTO);
		assertNotNull(actualizado);
		assertEquals(1L, actualizado.getId());
		assertEquals("SalaDTO prueba", actualizado.getNombre());
		assertEquals("src/main/resources/static/images/prueba.png", actualizado.getRutaImagen());

	}

	@Test
	void deleteByIdTest() {
		try {
			gestionSalaService.deleteById(3L);
		} catch (Exception e) {
			fail("No se esperaba error");
		}
	}

	@Test
	void deleteByIdWithFKTest() {
		// Registro asociado a la tabla USUARIO_Sala
		try {
			gestionSalaService.deleteById(1L);
			fail("Se esperaba una excepción DataIntegrityViolationException");
		} catch (DataIntegrityViolationException e) {
			assertNotNull(e.getMessage());
		} catch (Exception e) {
			fail("Se esperaba una excepción DataIntegrityViolationException");
		}
	}

	@Test
	void findByIdTest() {
		SalaDTO salaDTO = gestionSalaService.findById(1L);
		assertNotNull(salaDTO);
		assertEquals(1, salaDTO.getId());
		assertEquals("Sala 1", salaDTO.getNombre());
		assertEquals("src/main/resources/static/images/img1.png", salaDTO.getRutaImagen());
	}

	@Test
	void findAllTest() {
		List<SalaDTO> salasDTO = gestionSalaService.findAll();
		assertNotNull(salasDTO);
		assertEquals(3, salasDTO.size());

		SalaDTO salaDTO1 = salasDTO.getFirst();
		assertEquals(1, salaDTO1.getId());
		assertEquals("Sala 1", salaDTO1.getNombre());
		assertEquals("src/main/resources/static/images/img1.png", salaDTO1.getRutaImagen());

		SalaDTO salaDTO2 = salasDTO.get(1);
		assertEquals(2, salaDTO2.getId());
		assertEquals("Sala 2", salaDTO2.getNombre());
		assertEquals("src/main/resources/static/images/img2.png", salaDTO2.getRutaImagen());

		SalaDTO salaDTO3 = salasDTO.get(2);
		assertEquals(3, salaDTO3.getId());
		assertEquals("Sala 3", salaDTO3.getNombre());
		assertEquals("src/main/resources/static/images/img3.png", salaDTO3.getRutaImagen());

	}

	@Test
	void findAllByIdTest() {
		List<Long> idsList = new ArrayList<>();
		idsList.add(1L);
		idsList.add(2L);

		List<SalaDTO> salasDTO = gestionSalaService.findAllById(idsList);
		assertNotNull(salasDTO);
		assertEquals(2, salasDTO.size());

		SalaDTO salaDTO1 = salasDTO.getFirst();
		assertEquals(1, salaDTO1.getId());
		assertEquals("Sala 1", salaDTO1.getNombre());
		assertEquals("src/main/resources/static/images/img1.png", salaDTO1.getRutaImagen());

		SalaDTO salaDTO2 = salasDTO.get(1);
		assertEquals(2, salaDTO2.getId());
		assertEquals("Sala 2", salaDTO2.getNombre());
		assertEquals("src/main/resources/static/images/img2.png", salaDTO2.getRutaImagen());

	}

	@Test
	void existsById() {
		boolean exists = gestionSalaService.existsById(1L);
		assertTrue(exists);

		boolean notExists = gestionSalaService.existsById(99L);
		assertFalse(notExists);
	}

	@Test
	void findByCreacionUsuarioTest() {
		List<SalaDTO> salasDTO = gestionSalaService.findByCreacionUsuario("admin");
		assertNotNull(salasDTO);
		assertEquals(3, salasDTO.size());

		SalaDTO salaDTO1 = salasDTO.getFirst();
		assertEquals(1, salaDTO1.getId());
		assertEquals("Sala 1", salaDTO1.getNombre());
		assertEquals("src/main/resources/static/images/img1.png", salaDTO1.getRutaImagen());

		SalaDTO salaDTO2 = salasDTO.get(1);
		assertEquals(2, salaDTO2.getId());
		assertEquals("Sala 2", salaDTO2.getNombre());
		assertEquals("src/main/resources/static/images/img2.png", salaDTO2.getRutaImagen());

		SalaDTO salaDTO3 = salasDTO.get(2);
		assertEquals(3, salaDTO3.getId());
		assertEquals("Sala 3", salaDTO3.getNombre());
		assertEquals("src/main/resources/static/images/img3.png", salaDTO3.getRutaImagen());
	}

	@Test
	void findByActualizacionUsuarioTest() {
		List<SalaDTO> salasDTO = gestionSalaService.findByActualizacionUsuario("admin_update");
		assertNotNull(salasDTO);
		assertEquals(1, salasDTO.size());

		SalaDTO salaDTO = salasDTO.getFirst();
		assertEquals(2, salaDTO.getId());
		assertEquals("Sala 2", salaDTO.getNombre());
		assertEquals("src/main/resources/static/images/img2.png", salaDTO.getRutaImagen());
	}


}

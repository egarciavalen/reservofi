package com.uoc.reservofi.rol.business.gestion;

import com.uoc.reservofi.base.business.AbstractTest;
import com.uoc.reservofi.rol.business.dto.RolDTO;
import com.uoc.reservofi.rol.business.service.GestionRolService;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class GestionRolServiceTest extends AbstractTest {

	@Autowired
	private GestionRolService gestionRolService;

	@Test
	void insertTest() {
		RolDTO rolDTO = new RolDTO();
		rolDTO.setNombre("RolDTO prueba");

		RolDTO insertado = gestionRolService.insert(rolDTO);
		assertNotNull(insertado);
		assertNotNull(insertado.getId());
		assertEquals("RolDTO prueba", insertado.getNombre());
	}

	@Test
	void validateNombreTest() {
		RolDTO rolDTO = new RolDTO();

		// Nombre nulo
		try {
			gestionRolService.insert(rolDTO);
			fail("Se esperaba una ConstraintViolationException");
		} catch (ConstraintViolationException e) {
			assertEquals("insert.entidad.nombre: rol.nombre.obligatorio", e.getMessage());

		} catch (Exception e) {
			fail("Se esperaba una ConstraintViolationException");
		}

		// Nombre vacío
		rolDTO.setNombre("");
		try {
			gestionRolService.insert(rolDTO);
			fail("Se esperaba una ConstraintViolationException");
		} catch (ConstraintViolationException e) {
			assertEquals("insert.entidad.nombre: rol.nombre.obligatorio", e.getMessage());

		} catch (Exception e) {
			fail("Se esperaba una ConstraintViolationException");
		}

		// Nombre tamanio maximo alcanzado
		rolDTO.setNombre("1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890a");
		try {
			gestionRolService.insert(rolDTO);
			fail("Se esperaba una ConstraintViolationException");
		} catch (ConstraintViolationException e) {
			assertEquals("insert.entidad.nombre: rol.nombre.tamanio.maximo", e.getMessage());

		} catch (Exception e) {
			fail("Se esperaba una ConstraintViolationException");
		}
	}

	@Test
	void updateTest() {

		RolDTO rolDTO = new RolDTO();
		rolDTO.setId(1L); // ID existente
		rolDTO.setNombre("RolDTO prueba");

		RolDTO actualizado = gestionRolService.update(rolDTO);
		assertNotNull(actualizado);
		assertEquals(1L, actualizado.getId());
		assertEquals("RolDTO prueba", actualizado.getNombre());
	}

	@Test
	void deleteByIdTest() {
		try {
			gestionRolService.deleteById(3L);
		} catch (Exception e) {
			fail("No se esperaba error");
		}
	}

	@Test
	void deleteByIdWithFKTest() {
		// Registro asociado a la tabla USUARIO_ROL
		try {
			gestionRolService.deleteById(1L);
			fail("Se esperaba una excepción DataIntegrityViolationException");
		} catch (DataIntegrityViolationException e) {
			assertNotNull(e.getMessage());
		} catch (Exception e) {
			fail("Se esperaba una excepción DataIntegrityViolationException");
		}
	}

	@Test
	void findByIdTest() {
		RolDTO rolDTO = gestionRolService.findById(1L);
		assertNotNull(rolDTO);
		assertEquals(1, rolDTO.getId());
		assertEquals("Administrador", rolDTO.getNombre());
	}

	@Test
	void findAllTest() {
		List<RolDTO> rolesDTO = gestionRolService.findAll();
		assertNotNull(rolesDTO);
		assertEquals(3, rolesDTO.size());

		RolDTO rolDTO1 = rolesDTO.getFirst();
		assertEquals(1, rolDTO1.getId());
		assertEquals("Administrador", rolDTO1.getNombre());

		RolDTO rolDTO2 = rolesDTO.get(1);
		assertEquals(2, rolDTO2.getId());
		assertEquals("Empleado", rolDTO2.getNombre());

		RolDTO rolDTO3 = rolesDTO.get(2);
		assertEquals(3, rolDTO3.getId());
		assertEquals("Rol vacio", rolDTO3.getNombre());
	}

	@Test
	void findAllByIdTest() {
		List<Long> idsList = new ArrayList<>();
		idsList.add(1L);
		idsList.add(2L);

		List<RolDTO> rolesDTO = gestionRolService.findAllById(idsList);
		assertNotNull(rolesDTO);
		assertEquals(2, rolesDTO.size());

		RolDTO rolDTO1 = rolesDTO.getFirst();
		assertEquals(1, rolDTO1.getId());
		assertEquals("Administrador", rolDTO1.getNombre());

		RolDTO rolDTO2 = rolesDTO.get(1);
		assertEquals(2, rolDTO2.getId());
		assertEquals("Empleado", rolDTO2.getNombre());
	}

	@Test
	void existsById() {
		boolean exists = gestionRolService.existsById(1L);
		assertTrue(exists);

		boolean notExists = gestionRolService.existsById(99L);
		assertFalse(notExists);
	}

	@Test
	void findByCreacionUsuarioTest() {
		List<RolDTO> rolesDTO = gestionRolService.findByCreacionUsuario("admin");
		assertNotNull(rolesDTO);
		assertEquals(3, rolesDTO.size());

		RolDTO rolDTO1 = rolesDTO.getFirst();
		assertEquals(1, rolDTO1.getId());
		assertEquals("Administrador", rolDTO1.getNombre());

		RolDTO rolDTO2 = rolesDTO.get(1);
		assertEquals(2, rolDTO2.getId());
		assertEquals("Empleado", rolDTO2.getNombre());

		RolDTO rolDTO3 = rolesDTO.get(2);
		assertEquals(3, rolDTO3.getId());
		assertEquals("Rol vacio", rolDTO3.getNombre());
	}

	@Test
	void findByActualizacionUsuarioTest() {
		List<RolDTO> rolesDTO = gestionRolService.findByActualizacionUsuario("admin_update");
		assertNotNull(rolesDTO);
		assertEquals(1, rolesDTO.size());

		RolDTO rolDTO = rolesDTO.getFirst();
		assertEquals(2, rolDTO.getId());
		assertEquals("Empleado", rolDTO.getNombre());
	}

	@Test
	void getIdByNombreTest() {
		Long idRol = gestionRolService.getIdByNombre("Empleado");
		assertEquals(2, idRol);
	}

	@Test
	void getIdByNombreNotExistsTest() {
		Long idRol = gestionRolService.getIdByNombre("No existe");
		assertNull(idRol);
	}
}

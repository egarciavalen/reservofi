package com.uoc.reservofi.rol.business.gestion;

import com.uoc.reservofi.base.business.BaseTest;
import com.uoc.reservofi.rol.business.dto.RolDTO;
import com.uoc.reservofi.rol.business.service.GestionRolService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class GestionRolServiceTest extends BaseTest {

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
			gestionRolService.deleteById(1L);
		} catch (Exception e) {
			fail("No se esperaba error");
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
		assertEquals(2, rolesDTO.size());

		RolDTO rolDTO1 = rolesDTO.getFirst();
		assertEquals(1, rolDTO1.getId());
		assertEquals("Administrador", rolDTO1.getNombre());

		RolDTO rolDTO2 = rolesDTO.get(1);
		assertEquals(2, rolDTO2.getId());
		assertEquals("Empleado", rolDTO2.getNombre());
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
		assertEquals(2, rolesDTO.size());

		RolDTO rolDTO1 = rolesDTO.getFirst();
		assertEquals(1, rolDTO1.getId());
		assertEquals("Administrador", rolDTO1.getNombre());

		RolDTO rolDTO2 = rolesDTO.get(1);
		assertEquals(2, rolDTO2.getId());
		assertEquals("Empleado", rolDTO2.getNombre());
	}

	@Test
	void findByActualizacionUsuarioTest() {
		List<RolDTO> rolesDTO = gestionRolService.findByActualizacionUsuario("admin_update");
		assertNotNull(rolesDTO);
		assertEquals(1, rolesDTO.size());

		RolDTO rolDTO2 = rolesDTO.getFirst();
		assertEquals(2, rolDTO2.getId());
		assertEquals("Empleado", rolDTO2.getNombre());
	}


}

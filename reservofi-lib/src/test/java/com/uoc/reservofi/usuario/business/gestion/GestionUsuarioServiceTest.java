package com.uoc.reservofi.usuario.business.gestion;

import com.uoc.reservofi.base.business.AbstractBaseTest;
import com.uoc.reservofi.usuario.business.dto.UsuarioDTO;
import com.uoc.reservofi.usuario.business.service.GestionUsuarioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GestionUsuarioServiceTest extends AbstractBaseTest {

	// TODO: TESTS DE VALIDACION DE CAMPOS + GESTIÓN DE ROLES

	@Autowired
	private GestionUsuarioService gestionUsuarioService;

	@Test
	void insertTest() {
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setNombre("UsuarioDTO prueba");
		usuarioDTO.setPrimerApellido("Prueba primer apellido");
		usuarioDTO.setSegundoApellido("Prueba segundo apellido");
		usuarioDTO.setCorreoCorporativo("prueba@gmail.com");
		usuarioDTO.setContraseniaHash("789abc");
		usuarioDTO.setActivo(true);

		UsuarioDTO insertado = gestionUsuarioService.insert(usuarioDTO);
		assertNotNull(insertado);
		assertEquals("UsuarioDTO prueba", insertado.getNombre());
		assertEquals("Prueba primer apellido", insertado.getPrimerApellido());
		assertEquals("Prueba segundo apellido", insertado.getSegundoApellido());
		assertEquals("prueba@gmail.com", insertado.getCorreoCorporativo());
		assertEquals("789abc", insertado.getContraseniaHash());
		assertEquals(true, insertado.getActivo());
	}


	@Test
	@Transactional
		// Por temas de LazyException
	void updateTest() {
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setId(1L);
		usuarioDTO.setNombre("UsuarioDTO prueba"); // Cambio a realizar

		// Resto de campos a incluir por validaciones de DTO
		usuarioDTO.setPrimerApellido("Admin primer apellido");
		usuarioDTO.setSegundoApellido("Admin segundo apellido");
		usuarioDTO.setCorreoCorporativo("admin@gmail.com");
		usuarioDTO.setContraseniaHash("123hash");
		usuarioDTO.setActivo(true);

		UsuarioDTO actualizado = gestionUsuarioService.update(usuarioDTO);
		assertNotNull(actualizado);
		assertEquals("UsuarioDTO prueba", actualizado.getNombre());
		assertEquals("Admin primer apellido", actualizado.getPrimerApellido());
		assertEquals("Admin segundo apellido", actualizado.getSegundoApellido());
		assertEquals("admin@gmail.com", actualizado.getCorreoCorporativo());
		assertEquals("123hash", actualizado.getContraseniaHash());
		assertEquals(true, actualizado.getActivo());
	}

	@Test
	void deleteByIdTest() {
		try {
			gestionUsuarioService.deleteById(3L);
		} catch (Exception e) {
			fail("No se esperaba error");
		}
	}

	@Test
	void deleteByIdWithFKTest() {
		// Registro asociado a la tabla USUARIO_Usuario
		try {
			gestionUsuarioService.deleteById(1L);
			fail("Se esperaba una excepción DataIntegrityViolationException");
		} catch (DataIntegrityViolationException e) {
			assertNotNull(e.getMessage());
		} catch (Exception e) {
			fail("Se esperaba una excepción DataIntegrityViolationException");
		}
	}

	@Test
	@Transactional
		// Por temas de LazyException
	void findByIdTest() {
		UsuarioDTO usuarioDTO = gestionUsuarioService.findById(1L);
		assertNotNull(usuarioDTO);
		assertEquals("Admin", usuarioDTO.getNombre());
		assertEquals("Admin primer apellido", usuarioDTO.getPrimerApellido());
		assertEquals("Admin segundo apellido", usuarioDTO.getSegundoApellido());
		assertEquals("admin@gmail.com", usuarioDTO.getCorreoCorporativo());
		assertEquals("123hash", usuarioDTO.getContraseniaHash());
		assertEquals(true, usuarioDTO.getActivo());
	}

	@Test
	void findAllTest() {
		List<UsuarioDTO> usuariosDTO = gestionUsuarioService.findAll();
		assertNotNull(usuariosDTO);
		assertEquals(3, usuariosDTO.size());

		UsuarioDTO usuarioDTO1 = usuariosDTO.getFirst();
		assertNotNull(usuarioDTO1);
		assertEquals("Admin", usuarioDTO1.getNombre());
		assertEquals("Admin primer apellido", usuarioDTO1.getPrimerApellido());
		assertEquals("Admin segundo apellido", usuarioDTO1.getSegundoApellido());
		assertEquals("admin@gmail.com", usuarioDTO1.getCorreoCorporativo());
		assertEquals("123hash", usuarioDTO1.getContraseniaHash());
		assertEquals(true, usuarioDTO1.getActivo());

		UsuarioDTO usuarioDTO2 = usuariosDTO.get(1);
		assertNotNull(usuarioDTO2);
		assertEquals("Empleado", usuarioDTO2.getNombre());
		assertEquals("Empleado primer apellido", usuarioDTO2.getPrimerApellido());
		assertEquals("Empleado segundo apellido", usuarioDTO2.getSegundoApellido());
		assertEquals("empleado@gmail.com", usuarioDTO2.getCorreoCorporativo());
		assertEquals("456hash", usuarioDTO2.getContraseniaHash());
		assertEquals(true, usuarioDTO2.getActivo());

		UsuarioDTO usuarioDTO3 = usuariosDTO.get(2);
		assertNotNull(usuarioDTO3);
		assertEquals("Extra", usuarioDTO3.getNombre());
		assertEquals("Extra primer apellido", usuarioDTO3.getPrimerApellido());
		assertEquals("Extra segundo apellido", usuarioDTO3.getSegundoApellido());
		assertEquals("extra@gmail.com", usuarioDTO3.getCorreoCorporativo());
		assertEquals("789hash", usuarioDTO3.getContraseniaHash());
		assertEquals(true, usuarioDTO3.getActivo());
	}

	@Test
	void findAllByIdTest() {
		List<Long> idsList = new ArrayList<>();
		idsList.add(1L);
		idsList.add(2L);

		List<UsuarioDTO> usuariosDTO = gestionUsuarioService.findAllById(idsList);
		assertNotNull(usuariosDTO);
		assertEquals(2, usuariosDTO.size());

		UsuarioDTO usuarioDTO1 = usuariosDTO.getFirst();
		assertNotNull(usuarioDTO1);
		assertEquals("Admin", usuarioDTO1.getNombre());
		assertEquals("Admin primer apellido", usuarioDTO1.getPrimerApellido());
		assertEquals("Admin segundo apellido", usuarioDTO1.getSegundoApellido());
		assertEquals("admin@gmail.com", usuarioDTO1.getCorreoCorporativo());
		assertEquals("123hash", usuarioDTO1.getContraseniaHash());
		assertEquals(true, usuarioDTO1.getActivo());

		UsuarioDTO usuarioDTO2 = usuariosDTO.get(1);
		assertNotNull(usuarioDTO2);
		assertEquals("Empleado", usuarioDTO2.getNombre());
		assertEquals("Empleado primer apellido", usuarioDTO2.getPrimerApellido());
		assertEquals("Empleado segundo apellido", usuarioDTO2.getSegundoApellido());
		assertEquals("empleado@gmail.com", usuarioDTO2.getCorreoCorporativo());
		assertEquals("456hash", usuarioDTO2.getContraseniaHash());
		assertEquals(true, usuarioDTO2.getActivo());

	}

	@Test
	void existsById() {
		boolean exists = gestionUsuarioService.existsById(1L);
		assertTrue(exists);

		boolean notExists = gestionUsuarioService.existsById(99L);
		assertFalse(notExists);
	}

	@Test
	void findByCreacionUsuarioTest() {
		List<UsuarioDTO> usuariosDTO = gestionUsuarioService.findByCreacionUsuario("admin");
		assertNotNull(usuariosDTO);
		assertEquals(3, usuariosDTO.size());

		UsuarioDTO usuarioDTO1 = usuariosDTO.getFirst();
		assertNotNull(usuarioDTO1);
		assertEquals("Admin", usuarioDTO1.getNombre());
		assertEquals("Admin primer apellido", usuarioDTO1.getPrimerApellido());
		assertEquals("Admin segundo apellido", usuarioDTO1.getSegundoApellido());
		assertEquals("admin@gmail.com", usuarioDTO1.getCorreoCorporativo());
		assertEquals("123hash", usuarioDTO1.getContraseniaHash());
		assertEquals(true, usuarioDTO1.getActivo());

		UsuarioDTO usuarioDTO2 = usuariosDTO.get(1);
		assertNotNull(usuarioDTO2);
		assertEquals("Empleado", usuarioDTO2.getNombre());
		assertEquals("Empleado primer apellido", usuarioDTO2.getPrimerApellido());
		assertEquals("Empleado segundo apellido", usuarioDTO2.getSegundoApellido());
		assertEquals("empleado@gmail.com", usuarioDTO2.getCorreoCorporativo());
		assertEquals("456hash", usuarioDTO2.getContraseniaHash());
		assertEquals(true, usuarioDTO2.getActivo());

		UsuarioDTO usuarioDTO3 = usuariosDTO.get(2);
		assertNotNull(usuarioDTO3);
		assertEquals("Extra", usuarioDTO3.getNombre());
		assertEquals("Extra primer apellido", usuarioDTO3.getPrimerApellido());
		assertEquals("Extra segundo apellido", usuarioDTO3.getSegundoApellido());
		assertEquals("extra@gmail.com", usuarioDTO3.getCorreoCorporativo());
		assertEquals("789hash", usuarioDTO3.getContraseniaHash());
		assertEquals(true, usuarioDTO3.getActivo());
	}

	@Test
	void findByActualizacionUsuarioTest() {
		List<UsuarioDTO> usuariosDTO = gestionUsuarioService.findByActualizacionUsuario("admin_update");
		assertNotNull(usuariosDTO);
		assertEquals(1, usuariosDTO.size());

		UsuarioDTO usuarioDTO = usuariosDTO.getFirst();
		assertNotNull(usuarioDTO);
		assertEquals("Empleado", usuarioDTO.getNombre());
		assertEquals("Empleado primer apellido", usuarioDTO.getPrimerApellido());
		assertEquals("Empleado segundo apellido", usuarioDTO.getSegundoApellido());
		assertEquals("empleado@gmail.com", usuarioDTO.getCorreoCorporativo());
		assertEquals("456hash", usuarioDTO.getContraseniaHash());
		assertEquals(true, usuarioDTO.getActivo());
	}
}

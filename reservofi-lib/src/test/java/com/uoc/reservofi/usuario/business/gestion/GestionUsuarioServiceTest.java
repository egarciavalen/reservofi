package com.uoc.reservofi.usuario.business.gestion;

import com.uoc.reservofi.base.business.AbstractBaseTest;
import com.uoc.reservofi.usuario.business.dto.UsuarioDTO;
import com.uoc.reservofi.usuario.business.service.GestionUsuarioService;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GestionUsuarioServiceTest extends AbstractBaseTest {

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
	void insertWithRoleTest() {
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setNombre("UsuarioDTO prueba");
		usuarioDTO.setPrimerApellido("Prueba primer apellido");
		usuarioDTO.setSegundoApellido("Prueba segundo apellido");
		usuarioDTO.setCorreoCorporativo("prueba@gmail.com");
		usuarioDTO.setContraseniaHash("789abc");
		usuarioDTO.setActivo(true);

		// Inclusión de roles en la aplicación
		List<Long> idsRoles = new ArrayList<>();
		idsRoles.add(1L);
		idsRoles.add(2L);
		usuarioDTO.setIdsRoles(idsRoles);

		UsuarioDTO insertado = gestionUsuarioService.insert(usuarioDTO);
		assertNotNull(insertado);
		assertEquals("UsuarioDTO prueba", insertado.getNombre());
		assertEquals("Prueba primer apellido", insertado.getPrimerApellido());
		assertEquals("Prueba segundo apellido", insertado.getSegundoApellido());
		assertEquals("prueba@gmail.com", insertado.getCorreoCorporativo());
		assertEquals("789abc", insertado.getContraseniaHash());
		assertEquals(true, insertado.getActivo());
		assertNotNull(insertado.getIdsRoles());
		assertEquals(2, insertado.getIdsRoles().size()); // Dependiendo de como se ejecute el test pueden venir en un orden u otro
	}

	@Test
	void validateNombreTest() {
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setNombre("UsuarioDTO prueba");
		usuarioDTO.setPrimerApellido("Prueba primer apellido");
		usuarioDTO.setSegundoApellido("Prueba segundo apellido");
		usuarioDTO.setCorreoCorporativo("prueba@gmail.com");
		usuarioDTO.setContraseniaHash("789abc");
		usuarioDTO.setActivo(true);

		// Nombre nulo
		usuarioDTO.setNombre(null);
		try {
			gestionUsuarioService.insert(usuarioDTO);
			fail("Se esperaba una ConstraintViolationException");
		} catch (ConstraintViolationException e) {
			assertEquals("insert.entidad.nombre: usuario.nombre.obligatorio", e.getMessage());

		} catch (Exception e) {
			fail("Se esperaba una ConstraintViolationException");
		}

		// Nombre vacío
		usuarioDTO.setNombre("");
		try {
			gestionUsuarioService.insert(usuarioDTO);
			fail("Se esperaba una ConstraintViolationException");
		} catch (ConstraintViolationException e) {
			assertEquals("insert.entidad.nombre: usuario.nombre.obligatorio", e.getMessage());

		} catch (Exception e) {
			fail("Se esperaba una ConstraintViolationException");
		}

		// Nombre tamanio maximo alcanzado
		usuarioDTO.setNombre("1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890a");
		try {
			gestionUsuarioService.insert(usuarioDTO);
			fail("Se esperaba una ConstraintViolationException");
		} catch (ConstraintViolationException e) {
			assertEquals("insert.entidad.nombre: usuario.nombre.tamanio.maximo", e.getMessage());

		} catch (Exception e) {
			fail("Se esperaba una ConstraintViolationException");
		}
	}

	@Test
	void validatePrimerApellidoTest() {
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setNombre("UsuarioDTO prueba");
		usuarioDTO.setPrimerApellido("Prueba primer apellido");
		usuarioDTO.setSegundoApellido("Prueba segundo apellido");
		usuarioDTO.setCorreoCorporativo("prueba@gmail.com");
		usuarioDTO.setContraseniaHash("789abc");
		usuarioDTO.setActivo(true);

		// Primer apellido nulo
		usuarioDTO.setPrimerApellido(null);
		try {
			gestionUsuarioService.insert(usuarioDTO);
			fail("Se esperaba una ConstraintViolationException");
		} catch (ConstraintViolationException e) {
			assertEquals("insert.entidad.primerApellido: usuario.primerApellido.obligatorio", e.getMessage());

		} catch (Exception e) {
			fail("Se esperaba una ConstraintViolationException");
		}

		// Primer apellido tamanio maximo alcanzado
		usuarioDTO.setPrimerApellido("1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890a");
		try {
			gestionUsuarioService.insert(usuarioDTO);
			fail("Se esperaba una ConstraintViolationException");
		} catch (ConstraintViolationException e) {
			assertEquals("insert.entidad.primerApellido: usuario.primerApellido.tamanio.maximo", e.getMessage());

		} catch (Exception e) {
			fail("Se esperaba una ConstraintViolationException");
		}
	}

	@Test
	void validateSegundoApellidoTest() {
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setNombre("UsuarioDTO prueba");
		usuarioDTO.setPrimerApellido("Prueba primer apellido");
		usuarioDTO.setSegundoApellido("Prueba segundo apellido");
		usuarioDTO.setCorreoCorporativo("prueba@gmail.com");
		usuarioDTO.setContraseniaHash("789abc");
		usuarioDTO.setActivo(true);

		// Segundo apellido nulo
		usuarioDTO.setSegundoApellido(null);
		try {
			gestionUsuarioService.insert(usuarioDTO);
			fail("Se esperaba una ConstraintViolationException");
		} catch (ConstraintViolationException e) {
			assertEquals("insert.entidad.segundoApellido: usuario.segundoApellido.obligatorio", e.getMessage());

		} catch (Exception e) {
			fail("Se esperaba una ConstraintViolationException");
		}

		// Segundo apellido tamanio maximo alcanzado
		usuarioDTO.setSegundoApellido("1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890a");
		try {
			gestionUsuarioService.insert(usuarioDTO);
			fail("Se esperaba una ConstraintViolationException");
		} catch (ConstraintViolationException e) {
			assertEquals("insert.entidad.segundoApellido: usuario.segundoApellido.tamanio.maximo", e.getMessage());

		} catch (Exception e) {
			fail("Se esperaba una ConstraintViolationException");
		}
	}

	@Test
	void validateCorreoCorporativoTest() {
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setNombre("UsuarioDTO prueba");
		usuarioDTO.setPrimerApellido("Prueba primer apellido");
		usuarioDTO.setSegundoApellido("Prueba segundo apellido");
		usuarioDTO.setCorreoCorporativo("prueba@gmail.com");
		usuarioDTO.setContraseniaHash("789abc");
		usuarioDTO.setActivo(true);

		// Correo corporativo nulo
		usuarioDTO.setCorreoCorporativo(null);
		try {
			gestionUsuarioService.insert(usuarioDTO);
			fail("Se esperaba una ConstraintViolationException");
		} catch (ConstraintViolationException e) {
			assertEquals("insert.entidad.correoCorporativo: usuario.correoCorporativo.obligatorio", e.getMessage());

		} catch (Exception e) {
			fail("Se esperaba una ConstraintViolationException");
		}

		// Correo corporativo tamanio maximo alcanzado
		usuarioDTO.setCorreoCorporativo("1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890a");
		try {
			gestionUsuarioService.insert(usuarioDTO);
			fail("Se esperaba una ConstraintViolationException");
		} catch (ConstraintViolationException e) {
			assertEquals("insert.entidad.correoCorporativo: usuario.correoCorporativo.tamanio.maximo", e.getMessage());

		} catch (Exception e) {
			fail("Se esperaba una ConstraintViolationException");
		}
	}

	@Test
	void validateContraseniaHashTest() {
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setNombre("UsuarioDTO prueba");
		usuarioDTO.setPrimerApellido("Prueba primer apellido");
		usuarioDTO.setSegundoApellido("Prueba segundo apellido");
		usuarioDTO.setCorreoCorporativo("prueba@gmail.com");
		usuarioDTO.setContraseniaHash("789abc");
		usuarioDTO.setActivo(true);

		// Contrasenia hash nulo
		usuarioDTO.setContraseniaHash(null);
		try {
			gestionUsuarioService.insert(usuarioDTO);
			fail("Se esperaba una ConstraintViolationException");
		} catch (ConstraintViolationException e) {
			assertEquals("insert.entidad.contraseniaHash: usuario.contraseniaHash.obligatorio", e.getMessage());

		} catch (Exception e) {
			fail("Se esperaba una ConstraintViolationException");
		}

		// Contrasenia hash tamanio maximo alcanzado
		usuarioDTO.setContraseniaHash("1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456");
		try {
			gestionUsuarioService.insert(usuarioDTO);
			fail("Se esperaba una ConstraintViolationException");
		} catch (ConstraintViolationException e) {
			assertEquals("insert.entidad.contraseniaHash: usuario.contraseniaHash.tamanio.maximo", e.getMessage());

		} catch (Exception e) {
			fail("Se esperaba una ConstraintViolationException");
		}
	}

	@Test
	void validateActivoTest() {
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setNombre("UsuarioDTO prueba");
		usuarioDTO.setPrimerApellido("Prueba primer apellido");
		usuarioDTO.setSegundoApellido("Prueba segundo apellido");
		usuarioDTO.setCorreoCorporativo("prueba@gmail.com");
		usuarioDTO.setContraseniaHash("789abc");
		usuarioDTO.setActivo(true);

		// Activo nulo
		usuarioDTO.setActivo(null);
		try {
			gestionUsuarioService.insert(usuarioDTO);
			fail("Se esperaba una ConstraintViolationException");
		} catch (ConstraintViolationException e) {
			assertEquals("insert.entidad.activo: usuario.activo.obligatorio", e.getMessage());

		} catch (Exception e) {
			fail("Se esperaba una ConstraintViolationException");
		}
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
		List<Long> idsRoles = new ArrayList<>();
		idsRoles.add(1L);
		usuarioDTO.setIdsRoles(idsRoles);

		UsuarioDTO actualizado = gestionUsuarioService.update(usuarioDTO);
		assertNotNull(actualizado);
		assertEquals("UsuarioDTO prueba", actualizado.getNombre());
		assertEquals("Admin primer apellido", actualizado.getPrimerApellido());
		assertEquals("Admin segundo apellido", actualizado.getSegundoApellido());
		assertEquals("admin@gmail.com", actualizado.getCorreoCorporativo());
		assertEquals("123hash", actualizado.getContraseniaHash());
		assertEquals(true, actualizado.getActivo());
		assertNotNull(actualizado.getIdsRoles());
		assertEquals(1, actualizado.getIdsRoles().size());
		assertEquals(1L, actualizado.getIdsRoles().getFirst());
	}

	@Test
	@Transactional
	void updateWithRolesTest() {
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setId(1L);
		usuarioDTO.setNombre("UsuarioDTO prueba"); // Cambio a realizar

		// Resto de campos a incluir por validaciones de DTO
		usuarioDTO.setPrimerApellido("Admin primer apellido");
		usuarioDTO.setSegundoApellido("Admin segundo apellido");
		usuarioDTO.setCorreoCorporativo("admin@gmail.com");
		usuarioDTO.setContraseniaHash("123hash");
		usuarioDTO.setActivo(true);

		// Inclusión de roles en la aplicación
		List<Long> idsRoles = new ArrayList<>();
		idsRoles.add(2L); // OJO: se sobreescriben los roles existentes
		usuarioDTO.setIdsRoles(idsRoles);

		UsuarioDTO actualizado = gestionUsuarioService.update(usuarioDTO);
		assertNotNull(actualizado);
		assertEquals("UsuarioDTO prueba", actualizado.getNombre());
		assertEquals("Admin primer apellido", actualizado.getPrimerApellido());
		assertEquals("Admin segundo apellido", actualizado.getSegundoApellido());
		assertEquals("admin@gmail.com", actualizado.getCorreoCorporativo());
		assertEquals("123hash", actualizado.getContraseniaHash());
		assertEquals(true, actualizado.getActivo());
		assertNotNull(actualizado.getIdsRoles());
		assertEquals(1, actualizado.getIdsRoles().size());
		assertEquals(2L, actualizado.getIdsRoles().getFirst());
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
		assertNotNull(usuarioDTO.getIdsRoles());
		assertEquals(1, usuarioDTO.getIdsRoles().size());
		assertEquals(1L, usuarioDTO.getIdsRoles().getFirst());
	}

	@Test
	@Transactional
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
		assertNotNull(usuarioDTO1.getIdsRoles());
		assertEquals(1, usuarioDTO1.getIdsRoles().size());
		assertEquals(1L, usuarioDTO1.getIdsRoles().getFirst());

		UsuarioDTO usuarioDTO2 = usuariosDTO.get(1);
		assertNotNull(usuarioDTO2);
		assertEquals("Empleado", usuarioDTO2.getNombre());
		assertEquals("Empleado primer apellido", usuarioDTO2.getPrimerApellido());
		assertEquals("Empleado segundo apellido", usuarioDTO2.getSegundoApellido());
		assertEquals("empleado@gmail.com", usuarioDTO2.getCorreoCorporativo());
		assertEquals("456hash", usuarioDTO2.getContraseniaHash());
		assertEquals(true, usuarioDTO2.getActivo());
		assertNotNull(usuarioDTO2.getIdsRoles());
		assertEquals(1, usuarioDTO2.getIdsRoles().size());
		assertEquals(2L, usuarioDTO2.getIdsRoles().getFirst());

		UsuarioDTO usuarioDTO3 = usuariosDTO.get(2);
		assertNotNull(usuarioDTO3);
		assertEquals("Extra", usuarioDTO3.getNombre());
		assertEquals("Extra primer apellido", usuarioDTO3.getPrimerApellido());
		assertEquals("Extra segundo apellido", usuarioDTO3.getSegundoApellido());
		assertEquals("extra@gmail.com", usuarioDTO3.getCorreoCorporativo());
		assertEquals("789hash", usuarioDTO3.getContraseniaHash());
		assertEquals(true, usuarioDTO3.getActivo());
		assertNull(usuarioDTO3.getIdsRoles());

	}

	@Test
	@Transactional
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
		assertNotNull(usuarioDTO1.getIdsRoles());
		assertEquals(1, usuarioDTO1.getIdsRoles().size());
		assertEquals(1L, usuarioDTO1.getIdsRoles().getFirst());

		UsuarioDTO usuarioDTO2 = usuariosDTO.get(1);
		assertNotNull(usuarioDTO2);
		assertEquals("Empleado", usuarioDTO2.getNombre());
		assertEquals("Empleado primer apellido", usuarioDTO2.getPrimerApellido());
		assertEquals("Empleado segundo apellido", usuarioDTO2.getSegundoApellido());
		assertEquals("empleado@gmail.com", usuarioDTO2.getCorreoCorporativo());
		assertEquals("456hash", usuarioDTO2.getContraseniaHash());
		assertEquals(true, usuarioDTO2.getActivo());
		assertNotNull(usuarioDTO2.getIdsRoles());
		assertEquals(1, usuarioDTO2.getIdsRoles().size());
		assertEquals(2L, usuarioDTO2.getIdsRoles().getFirst());
	}

	@Test
	void existsById() {
		boolean exists = gestionUsuarioService.existsById(1L);
		assertTrue(exists);

		boolean notExists = gestionUsuarioService.existsById(99L);
		assertFalse(notExists);
	}

	@Test
	@Transactional
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
		assertNotNull(usuarioDTO1.getIdsRoles());
		assertEquals(1, usuarioDTO1.getIdsRoles().size());
		assertEquals(1L, usuarioDTO1.getIdsRoles().getFirst());

		UsuarioDTO usuarioDTO2 = usuariosDTO.get(1);
		assertNotNull(usuarioDTO2);
		assertEquals("Empleado", usuarioDTO2.getNombre());
		assertEquals("Empleado primer apellido", usuarioDTO2.getPrimerApellido());
		assertEquals("Empleado segundo apellido", usuarioDTO2.getSegundoApellido());
		assertEquals("empleado@gmail.com", usuarioDTO2.getCorreoCorporativo());
		assertEquals("456hash", usuarioDTO2.getContraseniaHash());
		assertEquals(true, usuarioDTO2.getActivo());
		assertNotNull(usuarioDTO2.getIdsRoles());
		assertEquals(1, usuarioDTO2.getIdsRoles().size());
		assertEquals(2L, usuarioDTO2.getIdsRoles().getFirst());

		UsuarioDTO usuarioDTO3 = usuariosDTO.get(2);
		assertNotNull(usuarioDTO3);
		assertEquals("Extra", usuarioDTO3.getNombre());
		assertEquals("Extra primer apellido", usuarioDTO3.getPrimerApellido());
		assertEquals("Extra segundo apellido", usuarioDTO3.getSegundoApellido());
		assertEquals("extra@gmail.com", usuarioDTO3.getCorreoCorporativo());
		assertEquals("789hash", usuarioDTO3.getContraseniaHash());
		assertEquals(true, usuarioDTO3.getActivo());
		assertNull(usuarioDTO3.getIdsRoles());
	}

	@Test
	@Transactional
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
		assertNotNull(usuarioDTO.getIdsRoles());
		assertEquals(1, usuarioDTO.getIdsRoles().size());
		assertEquals(2L, usuarioDTO.getIdsRoles().getFirst());
	}
}

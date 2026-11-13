package com.uoc.reservofi.usuario.business.gestion;

import com.uoc.reservofi.base.busines.exception.GestionException;
import com.uoc.reservofi.base.business.AbstractTest;
import com.uoc.reservofi.usuario.business.dto.UsuarioDTO;
import com.uoc.reservofi.usuario.business.dto.UsuarioLoginRequestDTO;
import com.uoc.reservofi.usuario.business.dto.UsuarioRolDTO;
import com.uoc.reservofi.usuario.business.service.GestionUsuarioService;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GestionUsuarioServiceTest extends AbstractTest {

	@Autowired
	private GestionUsuarioService gestionUsuarioService;

	@Test
	void insertTest() {
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setNombre("UsuarioDTO prueba");
		usuarioDTO.setPrimerApellido("Prueba primer apellido");
		usuarioDTO.setSegundoApellido("Prueba segundo apellido");
		usuarioDTO.setCorreoCorporativo("prueba@gmail.com");
		usuarioDTO.setContrasenia("pass12345");
		usuarioDTO.setActivo(true);

		UsuarioDTO insertado = gestionUsuarioService.insert(usuarioDTO);
		assertNotNull(insertado);
		assertEquals("UsuarioDTO prueba", insertado.getNombre());
		assertEquals("Prueba primer apellido", insertado.getPrimerApellido());
		assertEquals("Prueba segundo apellido", insertado.getSegundoApellido());
		assertEquals("prueba@gmail.com", insertado.getCorreoCorporativo());
		assertNotEquals("pass12345", insertado.getContrasenia()); // NO debería haberse insertado en plano
		assertEquals(true, insertado.getActivo());
	}

	@Test
	void insertWithRoleTest() {
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setNombre("UsuarioDTO prueba");
		usuarioDTO.setPrimerApellido("Prueba primer apellido");
		usuarioDTO.setSegundoApellido("Prueba segundo apellido");
		usuarioDTO.setCorreoCorporativo("prueba@gmail.com");
		usuarioDTO.setContrasenia("pass12345");
		usuarioDTO.setActivo(true);

		// Inclusión de roles en la aplicación
		List<UsuarioRolDTO> roles = new ArrayList<>();

		UsuarioRolDTO rol1 = new UsuarioRolDTO();
		rol1.setId(1L);
		rol1.setNombre("Empleado");

		UsuarioRolDTO rol2 = new UsuarioRolDTO();
		rol2.setId(2L);
		rol2.setNombre("Administrador");

		roles.add(rol1);
		roles.add(rol2);
		usuarioDTO.setRoles(roles);

		UsuarioDTO insertado = gestionUsuarioService.insert(usuarioDTO);
		assertNotNull(insertado);
		assertEquals("UsuarioDTO prueba", insertado.getNombre());
		assertEquals("Prueba primer apellido", insertado.getPrimerApellido());
		assertEquals("Prueba segundo apellido", insertado.getSegundoApellido());
		assertEquals("prueba@gmail.com", insertado.getCorreoCorporativo());
		assertNotEquals("pass12345", insertado.getContrasenia());
		assertEquals(true, insertado.getActivo());
		assertNotNull(insertado.getRoles());
		assertEquals(2, insertado.getRoles().size()); // Dependiendo de como se ejecute el test pueden venir en un orden u otro
	}

	@Test
	void validateNombreTest() {
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setNombre("UsuarioDTO prueba");
		usuarioDTO.setPrimerApellido("Prueba primer apellido");
		usuarioDTO.setSegundoApellido("Prueba segundo apellido");
		usuarioDTO.setCorreoCorporativo("prueba@gmail.com");
		usuarioDTO.setContrasenia("pass12345");
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
		usuarioDTO.setContrasenia("pass12345");
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
		usuarioDTO.setContrasenia("pass12345");
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
		usuarioDTO.setContrasenia("pass12345");
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
	void validateActivoTest() {
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setNombre("UsuarioDTO prueba");
		usuarioDTO.setPrimerApellido("Prueba primer apellido");
		usuarioDTO.setSegundoApellido("Prueba segundo apellido");
		usuarioDTO.setCorreoCorporativo("prueba@gmail.com");
		usuarioDTO.setContrasenia("pass12345");
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
		usuarioDTO.setContrasenia("pass123");
		usuarioDTO.setActivo(true);


		// Inclusión de roles en la aplicación
		List<UsuarioRolDTO> roles = new ArrayList<>();

		UsuarioRolDTO rol1 = new UsuarioRolDTO();
		rol1.setId(1L);
		rol1.setNombre("Empleado");

		roles.add(rol1);

		usuarioDTO.setRoles(roles);

		UsuarioDTO actualizado = gestionUsuarioService.update(usuarioDTO);
		assertNotNull(actualizado);
		assertEquals("UsuarioDTO prueba", actualizado.getNombre());
		assertEquals("Admin primer apellido", actualizado.getPrimerApellido());
		assertEquals("Admin segundo apellido", actualizado.getSegundoApellido());
		assertEquals("admin@gmail.com", actualizado.getCorreoCorporativo());
		assertNotEquals("pass123", actualizado.getContrasenia());
		assertEquals(true, actualizado.getActivo());
		assertNotNull(actualizado.getRoles());
		assertEquals(1, actualizado.getRoles().size());
		assertEquals(1, actualizado.getRoles().getFirst().getId());
		assertEquals("Empleado", actualizado.getRoles().getFirst().getNombre());
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
		usuarioDTO.setContrasenia("pass123");
		usuarioDTO.setActivo(true);

		// Inclusión de roles en la aplicación
		List<UsuarioRolDTO> roles = new ArrayList<>();

		UsuarioRolDTO rol1 = new UsuarioRolDTO();
		rol1.setId(1L);
		rol1.setNombre("Empleado");

		UsuarioRolDTO rol2 = new UsuarioRolDTO();
		rol2.setId(2L);
		rol2.setNombre("Administrador");

		roles.add(rol1);
		roles.add(rol2);
		usuarioDTO.setRoles(roles);

		UsuarioDTO actualizado = gestionUsuarioService.update(usuarioDTO);
		assertNotNull(actualizado);
		assertEquals("UsuarioDTO prueba", actualizado.getNombre());
		assertEquals("Admin primer apellido", actualizado.getPrimerApellido());
		assertEquals("Admin segundo apellido", actualizado.getSegundoApellido());
		assertEquals("admin@gmail.com", actualizado.getCorreoCorporativo());
		assertNotEquals("pass123", actualizado.getContrasenia());
		assertEquals(true, actualizado.getActivo());
		assertNotNull(actualizado.getRoles());
		assertEquals(2, actualizado.getRoles().size());
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
		assertEquals("$2a$10$31NgYxiuSX0FZpgvFcfAgOpRKMAZzvR67QzzAn0ArpGiOV477TyDD", usuarioDTO.getContrasenia());
		assertEquals(true, usuarioDTO.getActivo());
		assertNotNull(usuarioDTO.getRoles());
		assertEquals(2, usuarioDTO.getRoles().size());
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
		assertEquals("$2a$10$31NgYxiuSX0FZpgvFcfAgOpRKMAZzvR67QzzAn0ArpGiOV477TyDD", usuarioDTO1.getContrasenia());
		assertEquals(true, usuarioDTO1.getActivo());
		assertNotNull(usuarioDTO1.getRoles());
		assertEquals(2, usuarioDTO1.getRoles().size());

		UsuarioDTO usuarioDTO2 = usuariosDTO.get(1);
		assertNotNull(usuarioDTO2);
		assertEquals("Empleado", usuarioDTO2.getNombre());
		assertEquals("Empleado primer apellido", usuarioDTO2.getPrimerApellido());
		assertEquals("Empleado segundo apellido", usuarioDTO2.getSegundoApellido());
		assertEquals("empleado@gmail.com", usuarioDTO2.getCorreoCorporativo());
		assertEquals("$2a$10$78lGYxiuSX0FZpgvFcfAgOpRKMAZzvR67QzzAn0ArpGiOV477TyDD", usuarioDTO2.getContrasenia());
		assertEquals(true, usuarioDTO2.getActivo());
		assertNotNull(usuarioDTO2.getRoles());
		assertEquals(1, usuarioDTO2.getRoles().getFirst().getId());
		assertEquals("Empleado", usuarioDTO2.getRoles().getFirst().getNombre());

		UsuarioDTO usuarioDTO3 = usuariosDTO.get(2);
		assertNotNull(usuarioDTO3);
		assertEquals("Extra", usuarioDTO3.getNombre());
		assertEquals("Extra primer apellido", usuarioDTO3.getPrimerApellido());
		assertEquals("Extra segundo apellido", usuarioDTO3.getSegundoApellido());
		assertEquals("extra@gmail.com", usuarioDTO3.getCorreoCorporativo());
		assertEquals("$2a$10$572WYxiuSX0FZpgvFcfAgOpRKMAZzvR67QzzAn0ArpGiOV477TyDD", usuarioDTO3.getContrasenia());
		assertEquals(true, usuarioDTO3.getActivo());
		assertEquals(1, usuarioDTO3.getRoles().getFirst().getId());
		assertEquals("Empleado", usuarioDTO3.getRoles().getFirst().getNombre());
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
		assertEquals("$2a$10$31NgYxiuSX0FZpgvFcfAgOpRKMAZzvR67QzzAn0ArpGiOV477TyDD", usuarioDTO1.getContrasenia());
		assertEquals(true, usuarioDTO1.getActivo());
		assertNotNull(usuarioDTO1.getRoles());
		assertEquals(2, usuarioDTO1.getRoles().size());

		UsuarioDTO usuarioDTO2 = usuariosDTO.get(1);
		assertNotNull(usuarioDTO2);
		assertEquals("Empleado", usuarioDTO2.getNombre());
		assertEquals("Empleado primer apellido", usuarioDTO2.getPrimerApellido());
		assertEquals("Empleado segundo apellido", usuarioDTO2.getSegundoApellido());
		assertEquals("empleado@gmail.com", usuarioDTO2.getCorreoCorporativo());
		assertEquals("$2a$10$78lGYxiuSX0FZpgvFcfAgOpRKMAZzvR67QzzAn0ArpGiOV477TyDD", usuarioDTO2.getContrasenia());
		assertEquals(true, usuarioDTO2.getActivo());
		assertNotNull(usuarioDTO2.getRoles());
		assertEquals(1, usuarioDTO2.getRoles().getFirst().getId());
		assertEquals("Empleado", usuarioDTO2.getRoles().getFirst().getNombre());
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
		assertEquals("$2a$10$31NgYxiuSX0FZpgvFcfAgOpRKMAZzvR67QzzAn0ArpGiOV477TyDD", usuarioDTO1.getContrasenia());
		assertEquals(true, usuarioDTO1.getActivo());
		assertNotNull(usuarioDTO1.getRoles());
		assertEquals(2, usuarioDTO1.getRoles().size());

		UsuarioDTO usuarioDTO2 = usuariosDTO.get(1);
		assertNotNull(usuarioDTO2);
		assertEquals("Empleado", usuarioDTO2.getNombre());
		assertEquals("Empleado primer apellido", usuarioDTO2.getPrimerApellido());
		assertEquals("Empleado segundo apellido", usuarioDTO2.getSegundoApellido());
		assertEquals("empleado@gmail.com", usuarioDTO2.getCorreoCorporativo());
		assertEquals("$2a$10$78lGYxiuSX0FZpgvFcfAgOpRKMAZzvR67QzzAn0ArpGiOV477TyDD", usuarioDTO2.getContrasenia());
		assertEquals(true, usuarioDTO2.getActivo());
		assertNotNull(usuarioDTO2.getRoles());
		assertEquals(1, usuarioDTO2.getRoles().getFirst().getId());
		assertEquals("Empleado", usuarioDTO2.getRoles().getFirst().getNombre());

		UsuarioDTO usuarioDTO3 = usuariosDTO.get(2);
		assertNotNull(usuarioDTO3);
		assertEquals("Extra", usuarioDTO3.getNombre());
		assertEquals("Extra primer apellido", usuarioDTO3.getPrimerApellido());
		assertEquals("Extra segundo apellido", usuarioDTO3.getSegundoApellido());
		assertEquals("extra@gmail.com", usuarioDTO3.getCorreoCorporativo());
		assertEquals("$2a$10$572WYxiuSX0FZpgvFcfAgOpRKMAZzvR67QzzAn0ArpGiOV477TyDD", usuarioDTO3.getContrasenia());
		assertEquals(true, usuarioDTO3.getActivo());
		assertEquals(1, usuarioDTO3.getRoles().getFirst().getId());
		assertEquals("Empleado", usuarioDTO3.getRoles().getFirst().getNombre());
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
		assertEquals("$2a$10$78lGYxiuSX0FZpgvFcfAgOpRKMAZzvR67QzzAn0ArpGiOV477TyDD", usuarioDTO.getContrasenia());
		assertEquals(true, usuarioDTO.getActivo());
		assertNotNull(usuarioDTO.getRoles());
		assertEquals(1, usuarioDTO.getRoles().getFirst().getId());
		assertEquals("Empleado", usuarioDTO.getRoles().getFirst().getNombre());
	}

	@Test
	@Transactional
	void findByCorreoCorporativoTest() {
		UsuarioDTO usuarioDTO = gestionUsuarioService.findByCorreoCorporativo("empleado@gmail.com");

		assertNotNull(usuarioDTO);
		assertEquals("Empleado", usuarioDTO.getNombre());
		assertEquals("Empleado primer apellido", usuarioDTO.getPrimerApellido());
		assertEquals("Empleado segundo apellido", usuarioDTO.getSegundoApellido());
		assertEquals("empleado@gmail.com", usuarioDTO.getCorreoCorporativo());
		assertEquals("$2a$10$78lGYxiuSX0FZpgvFcfAgOpRKMAZzvR67QzzAn0ArpGiOV477TyDD", usuarioDTO.getContrasenia());
		assertEquals(true, usuarioDTO.getActivo());
		assertNotNull(usuarioDTO.getRoles());
		assertEquals(1, usuarioDTO.getRoles().getFirst().getId());
		assertEquals("Empleado", usuarioDTO.getRoles().getFirst().getNombre());
	}

	@Test
	void loginNotExistsTest() {
		UsuarioLoginRequestDTO usuarioRequestDTO = new UsuarioLoginRequestDTO();
		usuarioRequestDTO.setCorreoCorporativo("notExists@gmail.com");
		usuarioRequestDTO.setContrasenia("$2a$10$31NgYxiuSX0FZpgvFcfAgOpRKMAZzvR67QzzAn0ArpGiOV477TyDD");

		try {
			gestionUsuarioService.login(usuarioRequestDTO);
			fail("Se esperaba una excepción BadCredentialsException");
		} catch (BadCredentialsException e) {
			assertEquals("Bad credentials", e.getMessage());
		} catch (Exception e) {
			fail("Se esperaba una excepción BadCredentialsException");

		}
	}

	@Test
	void registerTest() {
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setNombre("UsuarioDTO prueba");
		usuarioDTO.setPrimerApellido("Prueba primer apellido");
		usuarioDTO.setSegundoApellido("Prueba segundo apellido");
		usuarioDTO.setCorreoCorporativo("prueba@gmail.com");
		usuarioDTO.setContrasenia("pass12345");
		usuarioDTO.setActivo(true);

		UsuarioDTO registrado = gestionUsuarioService.register(usuarioDTO);
		assertNotNull(registrado);
		assertEquals("UsuarioDTO prueba", registrado.getNombre());
		assertEquals("Prueba primer apellido", registrado.getPrimerApellido());
		assertEquals("Prueba segundo apellido", registrado.getSegundoApellido());
		assertEquals("prueba@gmail.com", registrado.getCorreoCorporativo());
		assertNotEquals("pass12345", registrado.getContrasenia()); // NO debería haberse insertado en plano
		assertEquals(true, registrado.getActivo());
		assertNotNull(usuarioDTO.getRoles());
		assertEquals(1, usuarioDTO.getRoles().getFirst().getId());
		assertEquals("Empleado", usuarioDTO.getRoles().getFirst().getNombre());
	}

	@Test
	void toggleActivoTest() {
		UsuarioDTO usuarioDTO = gestionUsuarioService.findById(1L);
		assertNotNull(usuarioDTO);
		assertTrue(usuarioDTO.getActivo());

		// Caso 1 -> TRUE a FALSE
		usuarioDTO = gestionUsuarioService.toggleActivo(1L);
		assertNotNull(usuarioDTO);
		assertFalse(usuarioDTO.getActivo());

		// Caso 2 -> FALSE a TRUE
		usuarioDTO = gestionUsuarioService.toggleActivo(1L);
		assertNotNull(usuarioDTO);
		assertTrue(usuarioDTO.getActivo());
	}

	@Test
	void toggleAdminTest() {
		UsuarioDTO usuarioDTO = gestionUsuarioService.findById(1L);
		assertNotNull(usuarioDTO);
		assertEquals(2, usuarioDTO.getRoles().size());

		// Caso 1 -> se quita admin
		usuarioDTO = gestionUsuarioService.toggleAdmin(1L);
		assertNotNull(usuarioDTO);
		assertEquals(1, usuarioDTO.getRoles().size());
		assertEquals(1, usuarioDTO.getRoles().getFirst().getId());
		assertEquals("Empleado", usuarioDTO.getRoles().getFirst().getNombre());

		// Caso 2 -> se vuelve a poner ADMIN
		usuarioDTO = gestionUsuarioService.toggleAdmin(1L);
		assertNotNull(usuarioDTO);
		assertEquals(2, usuarioDTO.getRoles().size());
	}

	@Test
	void checkAdminNotExistsTest() {
		try {
			gestionUsuarioService.checkAdmin(999L);
		} catch (GestionException e) {
			assertEquals("Se está intentando determinar el rol de una entidad no existente", e.getMessage());

		} catch (Exception e) {
			fail("Se esperaba una GestionException");
		}
	}

	@Test
	void checkAdminTest() {
		// Es un usuario administrador
		assertTrue(gestionUsuarioService.checkAdmin(1L));
		// No es un usuario administrador
		assertFalse(gestionUsuarioService.checkAdmin(2L));
	}
}

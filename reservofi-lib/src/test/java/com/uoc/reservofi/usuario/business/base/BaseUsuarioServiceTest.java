package com.uoc.reservofi.usuario.business.base;

import com.uoc.reservofi.base.busines.exception.BaseException;
import com.uoc.reservofi.base.business.AbstractTest;
import com.uoc.reservofi.rol.business.model.Rol;
import com.uoc.reservofi.rol.business.service.base.BaseRolService;
import com.uoc.reservofi.usuario.business.model.Usuario;
import com.uoc.reservofi.usuario.business.service.base.BaseUsuarioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class BaseUsuarioServiceTest extends AbstractTest {

	@Autowired
	private BaseUsuarioService baseUsuarioService;

	@Autowired
	private BaseRolService baseRolService;

	@Test
	void insertTest() {
		Usuario usuario = new Usuario();
		usuario.setNombre("Usuario prueba");
		usuario.setPrimerApellido("Prueba primer apellido");
		usuario.setSegundoApellido("Prueba segundo apellido");
		usuario.setCorreoCorporativo("prueba@gmail.com");
		usuario.setContraseniaHash("789abc");
		usuario.setActivo(true);

		Usuario insertado = baseUsuarioService.insert(usuario);
		assertNotNull(insertado);
		assertEquals("Usuario prueba", insertado.getNombre());
		assertEquals("Prueba primer apellido", insertado.getPrimerApellido());
		assertEquals("Prueba segundo apellido", insertado.getSegundoApellido());
		assertEquals("prueba@gmail.com", insertado.getCorreoCorporativo());
		assertEquals("789abc", insertado.getContraseniaHash());
		assertEquals(true, insertado.getActivo());
		assertEquals("adminTest", insertado.getCreacionUsuario());
		assertNotNull(insertado.getCreacionFecha());
		assertEquals("adminTest", insertado.getActualizacionUsuario());
		assertNotNull(insertado.getActualizacionFecha());
		assertEquals(0, insertado.getRoles().size());
	}

	@Test
	void insertWithRoleTest() {
		Usuario usuario = new Usuario();
		usuario.setNombre("Usuario prueba");
		usuario.setPrimerApellido("Prueba primer apellido");
		usuario.setSegundoApellido("Prueba segundo apellido");
		usuario.setCorreoCorporativo("prueba@gmail.com");
		usuario.setContraseniaHash("789abc");
		usuario.setActivo(true);

		// Inclusión de un rol nuevo
		List<Rol> rolesList = new ArrayList<>();
		Rol rol = baseRolService.findById(1L);
		rolesList.add(rol);
		usuario.setRoles(new HashSet<>(rolesList));

		Usuario insertado = baseUsuarioService.insert(usuario);
		assertNotNull(insertado);
		assertEquals("Usuario prueba", insertado.getNombre());
		assertEquals("Prueba primer apellido", insertado.getPrimerApellido());
		assertEquals("Prueba segundo apellido", insertado.getSegundoApellido());
		assertEquals("prueba@gmail.com", insertado.getCorreoCorporativo());
		assertEquals("789abc", insertado.getContraseniaHash());
		assertEquals(true, insertado.getActivo());
		assertEquals("adminTest", insertado.getCreacionUsuario());
		assertNotNull(insertado.getCreacionFecha());
		assertEquals("adminTest", insertado.getActualizacionUsuario());
		assertNotNull(insertado.getActualizacionFecha());
		assertNotNull(insertado.getRoles());
		assertEquals(1, insertado.getRoles().size());

		Rol rolInsertado = insertado.getRoles().stream().findFirst().orElse(null);
		assertNotNull(rolInsertado);
		assertEquals(1L, rolInsertado.getId());
	}

	@Test
	void insertNotExistsTest() {
		try {
			baseUsuarioService.insert(null);
			fail("Se esperaba una excepción BaseException");
		} catch (BaseException e) {
			assertEquals("Entidad no existente", e.getMessage());
		} catch (Exception e) {
			fail("Se esperaba una excepción BaseException");
		}

	}

	@Test
	void insertIdExistsTest() {
		Usuario usuario = new Usuario();
		usuario.setId(1L);
		usuario.setNombre("Usuario prueba");

		try {
			baseUsuarioService.insert(usuario);
			fail("Se esperaba una excepción BaseException");
		} catch (BaseException e) {
			assertEquals("No puede tener identificador asociado", e.getMessage());
		} catch (Exception e) {
			fail("Se esperaba una excepción BaseException");
		}

	}

	@Test
	void updateTest() {

		Usuario usuario = new Usuario();
		usuario.setId(1L);
		usuario.setNombre("Usuario prueba"); // Cambio a realizar

		// Para esta prueba, se va a suponer que se rellenó la información correctamente desde el servicio de gestión
		usuario.setPrimerApellido("Admin primer apellido");
		usuario.setSegundoApellido("Admin segundo apellido");
		usuario.setCorreoCorporativo("admin@gmail.com");
		usuario.setContraseniaHash("123hash");
		usuario.setActivo(true);
		usuario.setCreacionUsuario("admin");
		usuario.setCreacionFecha(LocalDateTime.now());
		// Gestión del rol ya existente
		List<Rol> rolesList = new ArrayList<>();
		Rol rol = baseRolService.findById(1L);
		rolesList.add(rol);
		usuario.setRoles(new HashSet<>(rolesList));

		Usuario actualizado = baseUsuarioService.update(usuario);
		assertNotNull(actualizado);
		assertEquals("Usuario prueba", actualizado.getNombre());
		assertEquals("Admin primer apellido", actualizado.getPrimerApellido());
		assertEquals("Admin segundo apellido", actualizado.getSegundoApellido());
		assertEquals("admin@gmail.com", actualizado.getCorreoCorporativo());
		assertEquals("123hash", actualizado.getContraseniaHash());
		assertEquals(true, actualizado.getActivo());
		assertEquals("admin", actualizado.getCreacionUsuario());
		assertNotNull(actualizado.getCreacionFecha());
		assertEquals("adminTest", actualizado.getActualizacionUsuario());
		assertNotNull(actualizado.getActualizacionFecha());
		assertNotNull(usuario.getRoles());
		assertEquals(1, actualizado.getRoles().size());

		Rol rolInsertado = actualizado.getRoles().stream().findFirst().orElse(null);
		assertNotNull(rolInsertado);
		assertEquals(1L, rolInsertado.getId());
	}


	@Test
	void updateWithRoleTest() {

		Usuario usuario = new Usuario();
		usuario.setId(1L);
		usuario.setNombre("Usuario prueba"); // Cambio a realizar

		// Para esta prueba, se va a suponer que se rellenó la información correctamente desde el servicio de gestión
		usuario.setPrimerApellido("Admin primer apellido");
		usuario.setSegundoApellido("Admin segundo apellido");
		usuario.setCorreoCorporativo("admin@gmail.com");
		usuario.setContraseniaHash("123hash");
		usuario.setActivo(true);
		usuario.setCreacionUsuario("admin");
		usuario.setCreacionFecha(LocalDateTime.now());
		// Gestión del rol ya existente
		List<Rol> rolesList = new ArrayList<>();
		Rol rol = baseRolService.findById(2L); // Cambio de rol a insertar
		rolesList.add(rol);
		usuario.setRoles(new HashSet<>(rolesList));

		Usuario actualizado = baseUsuarioService.update(usuario);
		assertNotNull(actualizado);
		assertEquals("Usuario prueba", actualizado.getNombre());
		assertEquals("Admin primer apellido", actualizado.getPrimerApellido());
		assertEquals("Admin segundo apellido", actualizado.getSegundoApellido());
		assertEquals("admin@gmail.com", actualizado.getCorreoCorporativo());
		assertEquals("123hash", actualizado.getContraseniaHash());
		assertEquals(true, actualizado.getActivo());
		assertEquals("admin", actualizado.getCreacionUsuario());
		assertNotNull(actualizado.getCreacionFecha());
		assertEquals("adminTest", actualizado.getActualizacionUsuario());
		assertNotNull(actualizado.getActualizacionFecha());
		assertNotNull(usuario.getRoles());
		assertEquals(1, actualizado.getRoles().size());

		Rol rolInsertado = actualizado.getRoles().stream().findFirst().orElse(null);
		assertNotNull(rolInsertado);
		assertEquals(2L, rolInsertado.getId());
	}

	@Test
	void updateCreateUserNotExistsTest() {
		Usuario usuario = new Usuario();
		usuario.setId(1L);
		usuario.setCreacionFecha(LocalDateTime.now());
		try {
			baseUsuarioService.update(usuario);
			fail("Se esperaba una excepción BaseException");
		} catch (BaseException e) {
			assertEquals("Datos de auditoría no registrados: usuario creador no existente", e.getMessage());
		} catch (Exception e) {
			fail("Se esperaba una excepción BaseException");
		}
	}

	@Test
	void updateCreateDateNotExistsTest() {
		Usuario usuario = new Usuario();
		usuario.setId(1L);
		usuario.setCreacionUsuario("admin-test");

		try {
			baseUsuarioService.update(usuario);
			fail("Se esperaba una excepción BaseException");
		} catch (BaseException e) {
			assertEquals("Datos de auditoría no registrados: fecha de creación no existente", e.getMessage());
		} catch (Exception e) {
			fail("Se esperaba una excepción BaseException");
		}
	}

	@Test
	void updateNotExistsTest() {
		try {
			baseUsuarioService.update(null);
			fail("Se esperaba una excepción BaseException");
		} catch (BaseException e) {
			assertEquals("Entidad no existente", e.getMessage());
		} catch (Exception e) {
			fail("Se esperaba una excepción BaseException");
		}
	}

	@Test
	void updateUsuarioIdNotExistsTest() {
		Usuario usuario = new Usuario();
		usuario.setCreacionUsuario("adminTest");
		usuario.setCreacionFecha(LocalDateTime.now());

		try {
			baseUsuarioService.update(usuario);
			fail("Se esperaba una excepción BaseException");
		} catch (BaseException e) {
			assertEquals("Ha de tener un identificador asociado", e.getMessage());
		} catch (Exception e) {
			fail("Se esperaba una excepción BaseException");
		}
	}

	@Test
	void deleteByIdTest() {
		try {
			baseUsuarioService.deleteById(3L);
		} catch (Exception e) {
			fail("No se esperaba error");
		}
	}

	@Test
	void deleteByIdWithFKTest() {
		// Registro asociado a la tabla USUARIO_Usuario
		try {
			baseUsuarioService.deleteById(1L);
			fail("Se esperaba una excepción DataIntegrityViolationException");
		} catch (DataIntegrityViolationException e) {
			assertNotNull(e.getMessage());
		} catch (Exception e) {
			fail("Se esperaba una excepción DataIntegrityViolationException");
		}
	}

	@Test
	@Transactional
	void findByIdTest() {
		Usuario usuario = baseUsuarioService.findById(1L);
		assertNotNull(usuario);
		assertEquals("Admin", usuario.getNombre());
		assertEquals("Admin primer apellido", usuario.getPrimerApellido());
		assertEquals("Admin segundo apellido", usuario.getSegundoApellido());
		assertEquals("admin@gmail.com", usuario.getCorreoCorporativo());
		assertEquals("123hash", usuario.getContraseniaHash());
		assertEquals(true, usuario.getActivo());
		assertEquals("admin", usuario.getCreacionUsuario());
		assertNotNull(usuario.getCreacionFecha());
		assertEquals("admin", usuario.getActualizacionUsuario());
		assertNotNull(usuario.getActualizacionFecha());
		assertNotNull(usuario.getRoles());
		assertEquals(1, usuario.getRoles().size());

		Rol rol = usuario.getRoles().stream().findFirst().orElse(null);
		assertNotNull(rol);
		assertEquals(1L, rol.getId());
	}

	@Test
	@Transactional
	void findAllTest() {
		List<Usuario> usuarios = baseUsuarioService.findAll();
		assertNotNull(usuarios);
		assertEquals(3, usuarios.size());

		Usuario usuario1 = usuarios.getFirst();
		assertNotNull(usuario1);
		assertEquals("Admin", usuario1.getNombre());
		assertEquals("Admin primer apellido", usuario1.getPrimerApellido());
		assertEquals("Admin segundo apellido", usuario1.getSegundoApellido());
		assertEquals("admin@gmail.com", usuario1.getCorreoCorporativo());
		assertEquals("123hash", usuario1.getContraseniaHash());
		assertEquals(true, usuario1.getActivo());
		assertEquals("admin", usuario1.getCreacionUsuario());
		assertNotNull(usuario1.getCreacionFecha());
		assertEquals("admin", usuario1.getActualizacionUsuario());
		assertNotNull(usuario1.getActualizacionFecha());
		assertEquals(1, usuario1.getRoles().size());

		Rol rol1 = usuario1.getRoles().stream().findFirst().orElse(null);
		assertNotNull(rol1);
		assertEquals(1L, rol1.getId());

		Usuario usuario2 = usuarios.get(1);
		assertNotNull(usuario2);
		assertEquals("Empleado", usuario2.getNombre());
		assertEquals("Empleado primer apellido", usuario2.getPrimerApellido());
		assertEquals("Empleado segundo apellido", usuario2.getSegundoApellido());
		assertEquals("empleado@gmail.com", usuario2.getCorreoCorporativo());
		assertEquals("456hash", usuario2.getContraseniaHash());
		assertEquals(true, usuario2.getActivo());
		assertEquals("admin", usuario2.getCreacionUsuario());
		assertNotNull(usuario2.getCreacionFecha());
		assertEquals("admin_update", usuario2.getActualizacionUsuario());
		assertNotNull(usuario2.getActualizacionFecha());
		assertEquals(1, usuario2.getRoles().size());

		Rol rol2 = usuario2.getRoles().stream().findFirst().orElse(null);
		assertNotNull(rol2);
		assertEquals(2L, rol2.getId());

		Usuario usuario3 = usuarios.get(2);
		assertNotNull(usuario3);
		assertEquals("Extra", usuario3.getNombre());
		assertEquals("Extra primer apellido", usuario3.getPrimerApellido());
		assertEquals("Extra segundo apellido", usuario3.getSegundoApellido());
		assertEquals("extra@gmail.com", usuario3.getCorreoCorporativo());
		assertEquals("789hash", usuario3.getContraseniaHash());
		assertEquals(true, usuario3.getActivo());
		assertEquals("admin", usuario3.getCreacionUsuario());
		assertNotNull(usuario3.getCreacionFecha());
		assertEquals("admin", usuario3.getActualizacionUsuario());
		assertNotNull(usuario3.getActualizacionFecha());
		assertEquals(0, usuario3.getRoles().size());
	}

	@Test
	@Transactional
	void findAllByIdTest() {
		List<Long> idsList = new ArrayList<>();
		idsList.add(1L);
		idsList.add(2L);

		List<Usuario> usuarios = baseUsuarioService.findAllById(idsList);
		assertNotNull(usuarios);
		assertEquals(2, usuarios.size());

		Usuario usuario1 = usuarios.getFirst();
		assertNotNull(usuario1);
		assertEquals("Admin", usuario1.getNombre());
		assertEquals("Admin primer apellido", usuario1.getPrimerApellido());
		assertEquals("Admin segundo apellido", usuario1.getSegundoApellido());
		assertEquals("admin@gmail.com", usuario1.getCorreoCorporativo());
		assertEquals("123hash", usuario1.getContraseniaHash());
		assertEquals(true, usuario1.getActivo());
		assertEquals("admin", usuario1.getCreacionUsuario());
		assertNotNull(usuario1.getCreacionFecha());
		assertEquals("admin", usuario1.getActualizacionUsuario());
		assertNotNull(usuario1.getActualizacionFecha());
		assertEquals(1, usuario1.getRoles().size());

		Rol rol1 = usuario1.getRoles().stream().findFirst().orElse(null);
		assertNotNull(rol1);
		assertEquals(1L, rol1.getId());

		Usuario usuario2 = usuarios.get(1);
		assertNotNull(usuario2);
		assertEquals("Empleado", usuario2.getNombre());
		assertEquals("Empleado primer apellido", usuario2.getPrimerApellido());
		assertEquals("Empleado segundo apellido", usuario2.getSegundoApellido());
		assertEquals("empleado@gmail.com", usuario2.getCorreoCorporativo());
		assertEquals("456hash", usuario2.getContraseniaHash());
		assertEquals(true, usuario2.getActivo());
		assertEquals("admin", usuario2.getCreacionUsuario());
		assertNotNull(usuario2.getCreacionFecha());
		assertEquals("admin_update", usuario2.getActualizacionUsuario());
		assertNotNull(usuario2.getActualizacionFecha());
		assertEquals(1, usuario2.getRoles().size());

		Rol rol2 = usuario2.getRoles().stream().findFirst().orElse(null);
		assertNotNull(rol2);
		assertEquals(2L, rol2.getId());
	}

	@Test
	void existsById() {
		boolean exists = baseUsuarioService.existsById(1L);
		assertTrue(exists);

		boolean notExists = baseUsuarioService.existsById(99L);
		assertFalse(notExists);
	}

	@Test
	@Transactional
	void findByCreacionUsuarioTest() {
		List<Usuario> usuarios = baseUsuarioService.findByCreacionUsuario("admin");
		assertNotNull(usuarios);
		assertEquals(3, usuarios.size());

		Usuario usuario1 = usuarios.getFirst();
		assertNotNull(usuario1);
		assertEquals("Admin", usuario1.getNombre());
		assertEquals("Admin primer apellido", usuario1.getPrimerApellido());
		assertEquals("Admin segundo apellido", usuario1.getSegundoApellido());
		assertEquals("admin@gmail.com", usuario1.getCorreoCorporativo());
		assertEquals("123hash", usuario1.getContraseniaHash());
		assertEquals(true, usuario1.getActivo());
		assertEquals("admin", usuario1.getCreacionUsuario());
		assertNotNull(usuario1.getCreacionFecha());
		assertEquals("admin", usuario1.getActualizacionUsuario());
		assertNotNull(usuario1.getActualizacionFecha());
		assertEquals(1, usuario1.getRoles().size());

		Rol rol1 = usuario1.getRoles().stream().findFirst().orElse(null);
		assertNotNull(rol1);
		assertEquals(1L, rol1.getId());

		Usuario usuario2 = usuarios.get(1);
		assertNotNull(usuario2);
		assertEquals("Empleado", usuario2.getNombre());
		assertEquals("Empleado primer apellido", usuario2.getPrimerApellido());
		assertEquals("Empleado segundo apellido", usuario2.getSegundoApellido());
		assertEquals("empleado@gmail.com", usuario2.getCorreoCorporativo());
		assertEquals("456hash", usuario2.getContraseniaHash());
		assertEquals(true, usuario2.getActivo());
		assertEquals("admin", usuario2.getCreacionUsuario());
		assertNotNull(usuario2.getCreacionFecha());
		assertEquals("admin_update", usuario2.getActualizacionUsuario());
		assertNotNull(usuario2.getActualizacionFecha());
		assertEquals(1, usuario2.getRoles().size());

		Rol rol2 = usuario2.getRoles().stream().findFirst().orElse(null);
		assertNotNull(rol2);
		assertEquals(2L, rol2.getId());

		Usuario usuario3 = usuarios.get(2);
		assertNotNull(usuario3);
		assertEquals("Extra", usuario3.getNombre());
		assertEquals("Extra primer apellido", usuario3.getPrimerApellido());
		assertEquals("Extra segundo apellido", usuario3.getSegundoApellido());
		assertEquals("extra@gmail.com", usuario3.getCorreoCorporativo());
		assertEquals("789hash", usuario3.getContraseniaHash());
		assertEquals(true, usuario3.getActivo());
		assertEquals("admin", usuario3.getCreacionUsuario());
		assertNotNull(usuario3.getCreacionFecha());
		assertEquals("admin", usuario3.getActualizacionUsuario());
		assertNotNull(usuario3.getActualizacionFecha());
		assertEquals(0, usuario3.getRoles().size());
	}

	@Test
	@Transactional
	void findByActualizacionUsuarioTest() {
		List<Usuario> usuarios = baseUsuarioService.findByActualizacionUsuario("admin_update");
		assertNotNull(usuarios);
		assertEquals(1, usuarios.size());

		Usuario usuario = usuarios.getFirst();
		assertNotNull(usuario);
		assertEquals("Empleado", usuario.getNombre());
		assertEquals("Empleado primer apellido", usuario.getPrimerApellido());
		assertEquals("Empleado segundo apellido", usuario.getSegundoApellido());
		assertEquals("empleado@gmail.com", usuario.getCorreoCorporativo());
		assertEquals("456hash", usuario.getContraseniaHash());
		assertEquals(true, usuario.getActivo());
		assertEquals("admin", usuario.getCreacionUsuario());
		assertNotNull(usuario.getCreacionFecha());
		assertEquals("admin_update", usuario.getActualizacionUsuario());
		assertNotNull(usuario.getActualizacionFecha());
		assertNotNull(usuario.getActualizacionFecha());
		assertEquals(1, usuario.getRoles().size());

		Rol rol = usuario.getRoles().stream().findFirst().orElse(null);
		assertNotNull(rol);
		assertEquals(2L, rol.getId());
	}

}

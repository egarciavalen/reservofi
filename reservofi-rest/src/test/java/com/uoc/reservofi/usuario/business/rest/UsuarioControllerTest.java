package com.uoc.reservofi.usuario.business.rest;

import com.uoc.reservofi.base.business.controller.RestExceptionHandler;
import com.uoc.reservofi.usuario.business.controller.UsuarioController;
import com.uoc.reservofi.usuario.business.dto.*;
import com.uoc.reservofi.usuario.business.mapper.RestUsuarioMapperImpl;
import com.uoc.reservofi.usuario.business.mapper.RestUsuarioRolMapperImpl;
import com.uoc.reservofi.usuario.business.service.GestionUsuarioService;
import com.uoc.reservofi.usuario.business.service.impl.RestUsuarioServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.json.JsonCompareMode;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest()
@ContextConfiguration(classes = {UsuarioController.class, RestUsuarioMapperImpl.class, RestUsuarioRolMapperImpl.class, RestUsuarioServiceImpl.class, RestExceptionHandler.class})
public class UsuarioControllerTest {

	@Captor
	private ArgumentCaptor<UsuarioDTO> usuarioCaptor = ArgumentCaptor.forClass(UsuarioDTO.class);
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	@MockitoBean
	private GestionUsuarioService gestionUsuarioService;

	@Test
	void updateTest() throws Exception {

		// Roles gestion DTO
		List<UsuarioRolDTO> gestionRoles = new ArrayList<>();

		UsuarioRolDTO gestionRol1 = new UsuarioRolDTO();
		gestionRol1.setId(1L);
		gestionRol1.setNombre("Empleado");

		UsuarioRolDTO gestionRol2 = new UsuarioRolDTO();
		gestionRol2.setId(2L);
		gestionRol2.setNombre("Administrador");

		gestionRoles.add(gestionRol1);
		gestionRoles.add(gestionRol2);

		// Roles rol DTO
		List<RestUsuarioConsultaRolDTO> restRoles = new ArrayList<>();
		RestUsuarioConsultaRolDTO restRol1 = new RestUsuarioConsultaRolDTO();
		restRol1.setId(1L);
		restRol1.setNombre("Empleado");

		RestUsuarioConsultaRolDTO restRol2 = new RestUsuarioConsultaRolDTO();
		restRol2.setId(2L);
		restRol2.setNombre("Administrador");

		restRoles.add(restRol1);
		restRoles.add(restRol2);

		long idUsuario = 1L;

		// DTO de entrada
		RestUsuarioGuardadoDTO restUpdateDTO = new RestUsuarioGuardadoDTO();
		restUpdateDTO.setNombre("Usuario prueba");
		restUpdateDTO.setPrimerApellido("Prueba primer apellido");
		restUpdateDTO.setSegundoApellido("Prueba segundo apellido");
		restUpdateDTO.setCorreoCorporativo("prueba@gmail.com");
		restUpdateDTO.setContrasenia("pass12345");
		restUpdateDTO.setActivo(true);

		// DTO que se espera obtener al final
		RestUsuarioConsultaDTO restReturnDTO = new RestUsuarioConsultaDTO();
		restReturnDTO.setId(1L);
		restReturnDTO.setNombre("Usuario prueba");
		restReturnDTO.setPrimerApellido("Prueba primer apellido");
		restReturnDTO.setSegundoApellido("Prueba segundo apellido");
		restReturnDTO.setCorreoCorporativo("prueba@gmail.com");
		restReturnDTO.setActivo(true);
		restReturnDTO.setRoles(restRoles);

		// DTO con el que se invoca al servicio de gestión
		UsuarioDTO updateDTO = new UsuarioDTO();
		updateDTO.setId(1L);
		updateDTO.setNombre("Usuario prueba");
		updateDTO.setPrimerApellido("Prueba primer apellido");
		updateDTO.setSegundoApellido("Prueba segundo apellido");
		updateDTO.setCorreoCorporativo("prueba@gmail.com");
		updateDTO.setContrasenia("pass12345");
		updateDTO.setActivo(true);

		// DTO que devuelve el servicio de gestión
		UsuarioDTO mockReturnDTO = new UsuarioDTO();
		mockReturnDTO.setId(1L);
		mockReturnDTO.setNombre("Usuario prueba");
		mockReturnDTO.setPrimerApellido("Prueba primer apellido");
		mockReturnDTO.setSegundoApellido("Prueba segundo apellido");
		mockReturnDTO.setCorreoCorporativo("prueba@gmail.com");
		mockReturnDTO.setContrasenia("pass12345");
		mockReturnDTO.setActivo(true);

		mockReturnDTO.setRoles(gestionRoles);

		// Mock del servicio de gestión
		when(gestionUsuarioService.update(any(UsuarioDTO.class))).thenReturn(mockReturnDTO);

		mockMvc.perform(put("/api/usuario/" + idUsuario).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(restUpdateDTO)))
				.andExpect(status().isOk()).andExpect(content().json(objectMapper.writeValueAsString(restReturnDTO), JsonCompareMode.STRICT));

		// Se verifica que se haya llamado al servicio de gestión 1 vez
		verify(gestionUsuarioService).update(usuarioCaptor.capture());

		// Se verifica que lo que se manda al servicio de gestión coincide con lo esperado
		UsuarioDTO capturedDTO = usuarioCaptor.getValue();
		assertEquals(updateDTO.getId(), capturedDTO.getId());
		assertEquals(updateDTO.getNombre(), capturedDTO.getNombre());
		assertEquals(updateDTO.getPrimerApellido(), capturedDTO.getPrimerApellido());
		assertEquals(updateDTO.getSegundoApellido(), capturedDTO.getSegundoApellido());
		assertEquals(updateDTO.getCorreoCorporativo(), capturedDTO.getCorreoCorporativo());
		assertEquals(updateDTO.getContrasenia(), capturedDTO.getContrasenia());
		assertEquals(updateDTO.getActivo(), capturedDTO.getActivo());
	}

	@Test
	void deleteByIdTest() throws Exception {
		// Parámetro de entrada
		Long idTest = 1L;

		// El deleteById no va a devolver nada
		doNothing().when(gestionUsuarioService).deleteById(idTest);

		mockMvc.perform(delete("/api/usuario/{id}", idTest))
				.andExpect(status().isNoContent());

		verify(gestionUsuarioService).deleteById(1L);
	}

	@Test
	void findByIdTest() throws Exception {

		// Roles gestion DTO
		List<UsuarioRolDTO> gestionRoles = new ArrayList<>();

		UsuarioRolDTO gestionRol1 = new UsuarioRolDTO();
		gestionRol1.setId(1L);
		gestionRol1.setNombre("Empleado");

		UsuarioRolDTO gestionRol2 = new UsuarioRolDTO();
		gestionRol2.setId(2L);
		gestionRol2.setNombre("Administrador");

		gestionRoles.add(gestionRol1);
		gestionRoles.add(gestionRol2);

		// Roles rol DTO
		List<RestUsuarioConsultaRolDTO> restRoles = new ArrayList<>();
		RestUsuarioConsultaRolDTO restRol1 = new RestUsuarioConsultaRolDTO();
		restRol1.setId(1L);
		restRol1.setNombre("Empleado");

		RestUsuarioConsultaRolDTO restRol2 = new RestUsuarioConsultaRolDTO();
		restRol2.setId(2L);
		restRol2.setNombre("Administrador");

		restRoles.add(restRol1);
		restRoles.add(restRol2);

		// Parámetro de entrada
		Long idTest = 1L;

		// DTO que devuelve el servicio de gestión
		UsuarioDTO mockReturnDTO = new UsuarioDTO();
		mockReturnDTO.setId(1L);
		mockReturnDTO.setNombre("Usuario prueba");
		mockReturnDTO.setPrimerApellido("Prueba primer apellido");
		mockReturnDTO.setSegundoApellido("Prueba segundo apellido");
		mockReturnDTO.setCorreoCorporativo("prueba@gmail.com");
		mockReturnDTO.setContrasenia("pass12345");
		mockReturnDTO.setActivo(true);
		mockReturnDTO.setRoles(gestionRoles);

		// DTO que se espera obtener al final
		RestUsuarioConsultaDTO restReturnDTO = new RestUsuarioConsultaDTO();
		restReturnDTO.setId(1L);
		restReturnDTO.setNombre("Usuario prueba");
		restReturnDTO.setPrimerApellido("Prueba primer apellido");
		restReturnDTO.setSegundoApellido("Prueba segundo apellido");
		restReturnDTO.setCorreoCorporativo("prueba@gmail.com");
		restReturnDTO.setActivo(true);
		restReturnDTO.setRoles(restRoles);

		// Mock del servicio de gestión
		when(gestionUsuarioService.findById(idTest)).thenReturn(mockReturnDTO);

		mockMvc.perform(get("/api/usuario/{id}", idTest)).
				andExpect(status().isOk()).andExpect(content().json(objectMapper.writeValueAsString(restReturnDTO), JsonCompareMode.STRICT));


		// Se verifica que se hayan llamado al servicio de gestión 1 vez
		verify(gestionUsuarioService).findById(idTest);
	}

	@Test
	void findByIdNotFoundTest() throws Exception {
		// Parámetro de entrada
		Long idTest = 99999L;

		// Mock del servicio de gestión
		when(gestionUsuarioService.findById(idTest)).thenReturn(null);

		// Respuesta de un error interno
		mockMvc.perform(get("/api/usuario/{id}", idTest)).
				andExpect(status().isInternalServerError()).andExpect(content().string("Entidad no encontrada"));
	}

	@Test
	void findAllTest() throws Exception {

		// Roles gestion DTO
		List<UsuarioRolDTO> gestionRoles = new ArrayList<>();

		UsuarioRolDTO gestionRol1 = new UsuarioRolDTO();
		gestionRol1.setId(1L);
		gestionRol1.setNombre("Empleado");

		UsuarioRolDTO gestionRol2 = new UsuarioRolDTO();
		gestionRol2.setId(2L);
		gestionRol2.setNombre("Administrador");

		gestionRoles.add(gestionRol1);
		gestionRoles.add(gestionRol2);

		// Roles rol DTO
		List<RestUsuarioConsultaRolDTO> restRoles = new ArrayList<>();
		RestUsuarioConsultaRolDTO restRol1 = new RestUsuarioConsultaRolDTO();
		restRol1.setId(1L);
		restRol1.setNombre("Empleado");

		RestUsuarioConsultaRolDTO restRol2 = new RestUsuarioConsultaRolDTO();
		restRol2.setId(2L);
		restRol2.setNombre("Administrador");

		restRoles.add(restRol1);
		restRoles.add(restRol2);

		// Lista de DTOs que devuelve el servicio de gestión
		UsuarioDTO mockReturnDTO1 = new UsuarioDTO();
		mockReturnDTO1.setId(1L);
		mockReturnDTO1.setNombre("Usuario prueba 1");
		mockReturnDTO1.setPrimerApellido("Prueba primer apellido 1");
		mockReturnDTO1.setSegundoApellido("Prueba segundo apellido 1");
		mockReturnDTO1.setCorreoCorporativo("prueba@gmail1.com");
		mockReturnDTO1.setContrasenia("pass12345_1");
		mockReturnDTO1.setActivo(true);
		mockReturnDTO1.setRoles(gestionRoles);

		UsuarioDTO mockReturnDTO2 = new UsuarioDTO();
		mockReturnDTO2.setId(2L);
		mockReturnDTO2.setNombre("Usuario prueba 2");
		mockReturnDTO2.setPrimerApellido("Prueba primer apellido 2");
		mockReturnDTO2.setSegundoApellido("Prueba segundo apellido 2");
		mockReturnDTO2.setCorreoCorporativo("prueba@gmail2.com");
		mockReturnDTO2.setContrasenia("pass12345_2");
		mockReturnDTO2.setActivo(false);
		mockReturnDTO2.setRoles(gestionRoles);

		List<UsuarioDTO> mockReturnDTOList = new ArrayList<>();
		mockReturnDTOList.add(mockReturnDTO1);
		mockReturnDTOList.add(mockReturnDTO2);

		// Lista de DTOs que se espera obtener al final
		RestUsuarioConsultaDTO restReturnDTO1 = new RestUsuarioConsultaDTO();
		restReturnDTO1.setId(1L);
		restReturnDTO1.setNombre("Usuario prueba 1");
		restReturnDTO1.setPrimerApellido("Prueba primer apellido 1");
		restReturnDTO1.setSegundoApellido("Prueba segundo apellido 1");
		restReturnDTO1.setCorreoCorporativo("prueba@gmail1.com");
		restReturnDTO1.setActivo(true);
		restReturnDTO1.setRoles(restRoles);

		RestUsuarioConsultaDTO restReturnDTO2 = new RestUsuarioConsultaDTO();
		restReturnDTO2.setId(2L);
		restReturnDTO2.setNombre("Usuario prueba 2");
		restReturnDTO2.setPrimerApellido("Prueba primer apellido 2");
		restReturnDTO2.setSegundoApellido("Prueba segundo apellido 2");
		restReturnDTO2.setCorreoCorporativo("prueba@gmail2.com");
		restReturnDTO2.setActivo(false);
		restReturnDTO2.setRoles(restRoles);

		List<RestUsuarioConsultaDTO> restReturnDTOList = new ArrayList<>();
		restReturnDTOList.add(restReturnDTO1);
		restReturnDTOList.add(restReturnDTO2);

		// Mock del servicio de gestión
		when(gestionUsuarioService.findAll()).thenReturn(mockReturnDTOList);

		mockMvc.perform(get("/api/usuario")).
				andExpect(status().isOk()).andExpect(content().json(objectMapper.writeValueAsString(restReturnDTOList), JsonCompareMode.STRICT));

		// Se verifica que se hayan llamado al servicio de gestión 1 vez
		verify(gestionUsuarioService).findAll();
	}

	@Test
	void toggleActivoTest() throws Exception {

		// Roles gestion DTO
		List<UsuarioRolDTO> gestionRoles = new ArrayList<>();

		UsuarioRolDTO gestionRol1 = new UsuarioRolDTO();
		gestionRol1.setId(1L);
		gestionRol1.setNombre("Empleado");

		UsuarioRolDTO gestionRol2 = new UsuarioRolDTO();
		gestionRol2.setId(2L);
		gestionRol2.setNombre("Administrador");

		gestionRoles.add(gestionRol1);
		gestionRoles.add(gestionRol2);

		// Roles rol DTO
		List<RestUsuarioConsultaRolDTO> restRoles = new ArrayList<>();
		RestUsuarioConsultaRolDTO restRol1 = new RestUsuarioConsultaRolDTO();
		restRol1.setId(1L);
		restRol1.setNombre("Empleado");

		RestUsuarioConsultaRolDTO restRol2 = new RestUsuarioConsultaRolDTO();
		restRol2.setId(2L);
		restRol2.setNombre("Administrador");

		restRoles.add(restRol1);
		restRoles.add(restRol2);

		// Parámetro de entrada
		Long idTest = 1L;

		// DTO que devuelve el servicio de gestión
		UsuarioDTO mockReturnDTO = new UsuarioDTO();
		mockReturnDTO.setId(1L);
		mockReturnDTO.setNombre("Usuario prueba");
		mockReturnDTO.setPrimerApellido("Prueba primer apellido");
		mockReturnDTO.setSegundoApellido("Prueba segundo apellido");
		mockReturnDTO.setCorreoCorporativo("prueba@gmail.com");
		mockReturnDTO.setContrasenia("pass12345");
		mockReturnDTO.setActivo(false); // Se marcaría a FALSE en la base de datos
		mockReturnDTO.setRoles(gestionRoles);

		// DTO que se espera obtener al final
		RestUsuarioConsultaDTO restReturnDTO = new RestUsuarioConsultaDTO();
		restReturnDTO.setId(1L);
		restReturnDTO.setNombre("Usuario prueba");
		restReturnDTO.setPrimerApellido("Prueba primer apellido");
		restReturnDTO.setSegundoApellido("Prueba segundo apellido");
		restReturnDTO.setCorreoCorporativo("prueba@gmail.com");
		restReturnDTO.setActivo(false);
		restReturnDTO.setRoles(restRoles);

		// Mock del servicio de gestión
		when(gestionUsuarioService.toggleActivo(idTest)).thenReturn(mockReturnDTO);

		mockMvc.perform(post("/api/usuario/{id}/toggleActivo", idTest)).
				andExpect(status().isOk()).andExpect(content().json(objectMapper.writeValueAsString(restReturnDTO), JsonCompareMode.STRICT));

		// Se verifica que se hayan llamado al servicio de gestión 1 vez
		verify(gestionUsuarioService).toggleActivo(idTest);
	}

	@Test
	void toggleAdminTest() throws Exception {

		// Roles gestion DTO
		List<UsuarioRolDTO> gestionRoles = new ArrayList<>();

		UsuarioRolDTO gestionRol1 = new UsuarioRolDTO();
		gestionRol1.setId(1L);
		gestionRol1.setNombre("Empleado");

		gestionRoles.add(gestionRol1);

		// Roles rol DTO
		List<RestUsuarioConsultaRolDTO> restRoles = new ArrayList<>();
		RestUsuarioConsultaRolDTO restRol1 = new RestUsuarioConsultaRolDTO();
		restRol1.setId(1L);
		restRol1.setNombre("Empleado");

		restRoles.add(restRol1);

		// Parámetro de entrada
		Long idTest = 1L;

		// DTO que devuelve el servicio de gestión
		UsuarioDTO mockReturnDTO = new UsuarioDTO();
		mockReturnDTO.setId(1L);
		mockReturnDTO.setNombre("Usuario prueba");
		mockReturnDTO.setPrimerApellido("Prueba primer apellido");
		mockReturnDTO.setSegundoApellido("Prueba segundo apellido");
		mockReturnDTO.setCorreoCorporativo("prueba@gmail.com");
		mockReturnDTO.setContrasenia("pass12345");
		mockReturnDTO.setActivo(true);
		mockReturnDTO.setRoles(gestionRoles); // Se quitaría el permiso de administrador

		// DTO que se espera obtener al final
		RestUsuarioConsultaDTO restReturnDTO = new RestUsuarioConsultaDTO();
		restReturnDTO.setId(1L);
		restReturnDTO.setNombre("Usuario prueba");
		restReturnDTO.setPrimerApellido("Prueba primer apellido");
		restReturnDTO.setSegundoApellido("Prueba segundo apellido");
		restReturnDTO.setCorreoCorporativo("prueba@gmail.com");
		restReturnDTO.setActivo(true);
		restReturnDTO.setRoles(restRoles);

		// Mock del servicio de gestión
		when(gestionUsuarioService.toggleAdmin(idTest)).thenReturn(mockReturnDTO);

		mockMvc.perform(post("/api/usuario/{id}/toggleAdmin", idTest)).
				andExpect(status().isOk()).andExpect(content().json(objectMapper.writeValueAsString(restReturnDTO), JsonCompareMode.STRICT));

		// Se verifica que se hayan llamado al servicio de gestión 1 vez
		verify(gestionUsuarioService).toggleAdmin(idTest);
	}
}

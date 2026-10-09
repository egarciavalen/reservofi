package com.uoc.reservofi.usuario.business.rest;

import com.uoc.reservofi.base.business.controller.RestControllerAdvice;
import com.uoc.reservofi.usuario.business.controller.UsuarioController;
import com.uoc.reservofi.usuario.business.dto.RestUsuarioDTO;
import com.uoc.reservofi.usuario.business.dto.UsuarioDTO;
import com.uoc.reservofi.usuario.business.mapper.RestUsuarioMapperImpl;
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

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest()
@ContextConfiguration(classes = {UsuarioController.class, RestUsuarioMapperImpl.class, RestUsuarioServiceImpl.class, RestControllerAdvice.class})
public class UsuarioControllerTest {

	@Captor
	private ArgumentCaptor<UsuarioDTO> UsuarioCaptor = ArgumentCaptor.forClass(UsuarioDTO.class);
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	@MockitoBean
	private GestionUsuarioService gestionUsuarioService;

	@Test
	void insertTest() throws Exception {
		// DTO de entrada
		RestUsuarioDTO restInsertDTO = new RestUsuarioDTO();
		restInsertDTO.setNombre("Usuario prueba");
		restInsertDTO.setPrimerApellido("Prueba primer apellido");
		restInsertDTO.setSegundoApellido("Prueba segundo apellido");
		restInsertDTO.setCorreoCorporativo("prueba@gmail.com");
		restInsertDTO.setContraseniaHash("789abc");
		restInsertDTO.setActivo(true);
		restInsertDTO.setIdsRoles(List.of(1L, 2L));

		// DTO que se espera obtener al final
		RestUsuarioDTO restReturnDTO = new RestUsuarioDTO();
		restReturnDTO.setId(1L);
		restReturnDTO.setNombre("Usuario prueba");
		restReturnDTO.setPrimerApellido("Prueba primer apellido");
		restReturnDTO.setSegundoApellido("Prueba segundo apellido");
		restReturnDTO.setCorreoCorporativo("prueba@gmail.com");
		restReturnDTO.setContraseniaHash("789abc");
		restReturnDTO.setActivo(true);
		restReturnDTO.setIdsRoles(List.of(1L, 2L));

		// DTO con el que se invoca al servicio de gestión
		UsuarioDTO insertDTO = new UsuarioDTO();
		insertDTO.setNombre("Usuario prueba");
		insertDTO.setPrimerApellido("Prueba primer apellido");
		insertDTO.setSegundoApellido("Prueba segundo apellido");
		insertDTO.setCorreoCorporativo("prueba@gmail.com");
		insertDTO.setContraseniaHash("789abc");
		insertDTO.setActivo(true);
		insertDTO.setIdsRoles(List.of(1L, 2L));

		// DTO que devuelve el servicio de gestión
		UsuarioDTO mockReturnDTO = new UsuarioDTO();
		mockReturnDTO.setId(1L);
		mockReturnDTO.setNombre("Usuario prueba");
		mockReturnDTO.setPrimerApellido("Prueba primer apellido");
		mockReturnDTO.setSegundoApellido("Prueba segundo apellido");
		mockReturnDTO.setCorreoCorporativo("prueba@gmail.com");
		mockReturnDTO.setContraseniaHash("789abc");
		mockReturnDTO.setActivo(true);
		mockReturnDTO.setIdsRoles(List.of(1L, 2L));

		// Mock del servicio de gestión
		when(gestionUsuarioService.insert(any(UsuarioDTO.class))).thenReturn(mockReturnDTO);

		// Llamada a realizar
		mockMvc.perform(post("/api/usuario").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(restInsertDTO)))
				.andExpect(status().isCreated()).andExpect(content().json(objectMapper.writeValueAsString(restReturnDTO), JsonCompareMode.STRICT));

		// Se verifica que se haya llamado al servicio de gestión 1 vez
		verify(gestionUsuarioService).insert(UsuarioCaptor.capture());

		// Se verifica que lo que se manda al servicio de gestión coincide con lo esperado
		UsuarioDTO capturedDTO = UsuarioCaptor.getValue();
		assertEquals(insertDTO.getId(), capturedDTO.getId());
		assertEquals(insertDTO.getNombre(), capturedDTO.getNombre());
		assertEquals(insertDTO.getPrimerApellido(), capturedDTO.getPrimerApellido());
		assertEquals(insertDTO.getSegundoApellido(), capturedDTO.getSegundoApellido());
		assertEquals(insertDTO.getCorreoCorporativo(), capturedDTO.getCorreoCorporativo());
		assertEquals(insertDTO.getContraseniaHash(), capturedDTO.getContraseniaHash());
		assertEquals(insertDTO.getIdsRoles(), capturedDTO.getIdsRoles());
	}

	@Test
	void updateTest() throws Exception {
		// DTO de entrada
		RestUsuarioDTO restUpdateDTO = new RestUsuarioDTO();
		restUpdateDTO.setId(1L);
		restUpdateDTO.setNombre("Usuario prueba");
		restUpdateDTO.setPrimerApellido("Prueba primer apellido");
		restUpdateDTO.setSegundoApellido("Prueba segundo apellido");
		restUpdateDTO.setCorreoCorporativo("prueba@gmail.com");
		restUpdateDTO.setContraseniaHash("789abc");
		restUpdateDTO.setActivo(true);
		restUpdateDTO.setIdsRoles(List.of(1L, 2L));

		// DTO que se espera obtener al final
		RestUsuarioDTO restReturnDTO = new RestUsuarioDTO();
		restReturnDTO.setId(1L);
		restReturnDTO.setNombre("Usuario prueba");
		restReturnDTO.setPrimerApellido("Prueba primer apellido");
		restReturnDTO.setSegundoApellido("Prueba segundo apellido");
		restReturnDTO.setCorreoCorporativo("prueba@gmail.com");
		restReturnDTO.setContraseniaHash("789abc");
		restReturnDTO.setActivo(true);
		restReturnDTO.setIdsRoles(List.of(1L, 2L));

		// DTO con el que se invoca al servicio de gestión
		UsuarioDTO updateDTO = new UsuarioDTO();
		updateDTO.setId(1L);
		updateDTO.setNombre("Usuario prueba");
		updateDTO.setPrimerApellido("Prueba primer apellido");
		updateDTO.setSegundoApellido("Prueba segundo apellido");
		updateDTO.setCorreoCorporativo("prueba@gmail.com");
		updateDTO.setContraseniaHash("789abc");
		updateDTO.setActivo(true);
		updateDTO.setIdsRoles(List.of(1L, 2L));

		// DTO que devuelve el servicio de gestión
		UsuarioDTO mockReturnDTO = new UsuarioDTO();
		mockReturnDTO.setId(1L);
		mockReturnDTO.setNombre("Usuario prueba");
		mockReturnDTO.setPrimerApellido("Prueba primer apellido");
		mockReturnDTO.setSegundoApellido("Prueba segundo apellido");
		mockReturnDTO.setCorreoCorporativo("prueba@gmail.com");
		mockReturnDTO.setContraseniaHash("789abc");
		mockReturnDTO.setActivo(true);
		mockReturnDTO.setIdsRoles(List.of(1L, 2L));

		// Mock del servicio de gestión
		when(gestionUsuarioService.update(any(UsuarioDTO.class))).thenReturn(mockReturnDTO);

		mockMvc.perform(put("/api/usuario").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(restUpdateDTO)))
				.andExpect(status().isOk()).andExpect(content().json(objectMapper.writeValueAsString(restReturnDTO), JsonCompareMode.STRICT));

		// Se verifica que se haya llamado al servicio de gestión 1 vez
		verify(gestionUsuarioService).update(UsuarioCaptor.capture());

		// Se verifica que lo que se manda al servicio de gestión coincide con lo esperado
		UsuarioDTO capturedDTO = UsuarioCaptor.getValue();
		assertEquals(updateDTO.getId(), capturedDTO.getId());
		assertEquals(updateDTO.getNombre(), capturedDTO.getNombre());
		assertEquals(updateDTO.getPrimerApellido(), capturedDTO.getPrimerApellido());
		assertEquals(updateDTO.getSegundoApellido(), capturedDTO.getSegundoApellido());
		assertEquals(updateDTO.getCorreoCorporativo(), capturedDTO.getCorreoCorporativo());
		assertEquals(updateDTO.getContraseniaHash(), capturedDTO.getContraseniaHash());
		assertEquals(updateDTO.getIdsRoles(), capturedDTO.getIdsRoles());
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
		// Parámetro de entrada
		Long idTest = 1L;

		// DTO que devuelve el servicio de gestión
		UsuarioDTO mockReturnDTO = new UsuarioDTO();
		mockReturnDTO.setId(1L);
		mockReturnDTO.setNombre("Usuario prueba");
		mockReturnDTO.setPrimerApellido("Prueba primer apellido");
		mockReturnDTO.setSegundoApellido("Prueba segundo apellido");
		mockReturnDTO.setCorreoCorporativo("prueba@gmail.com");
		mockReturnDTO.setContraseniaHash("789abc");
		mockReturnDTO.setActivo(true);
		mockReturnDTO.setIdsRoles(List.of(1L, 2L));

		// DTO que se espera obtener al final
		RestUsuarioDTO restReturnDTO = new RestUsuarioDTO();
		restReturnDTO.setId(1L);
		restReturnDTO.setNombre("Usuario prueba");
		restReturnDTO.setPrimerApellido("Prueba primer apellido");
		restReturnDTO.setSegundoApellido("Prueba segundo apellido");
		restReturnDTO.setCorreoCorporativo("prueba@gmail.com");
		restReturnDTO.setContraseniaHash("789abc");
		restReturnDTO.setActivo(true);
		restReturnDTO.setIdsRoles(List.of(1L, 2L));

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
}

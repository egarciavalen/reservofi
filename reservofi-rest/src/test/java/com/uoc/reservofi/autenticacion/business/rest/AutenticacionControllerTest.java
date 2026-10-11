package com.uoc.reservofi.autenticacion.business.rest;

import com.uoc.reservofi.autenticacion.business.controller.AutenticacionController;
import com.uoc.reservofi.autenticacion.business.dto.RestLoginRequestDTO;
import com.uoc.reservofi.autenticacion.business.dto.RestLoginResponseDTO;
import com.uoc.reservofi.autenticacion.business.dto.RestRegisterRequestDTO;
import com.uoc.reservofi.autenticacion.business.dto.RestRegisterResponseDTO;
import com.uoc.reservofi.autenticacion.business.mapper.RestAutenticacionMapperImpl;
import com.uoc.reservofi.autenticacion.business.service.impl.RestAutenticacionServiceImpl;
import com.uoc.reservofi.base.business.controller.RestExceptionHandler;
import com.uoc.reservofi.usuario.business.dto.UsuarioDTO;
import com.uoc.reservofi.usuario.business.dto.UsuarioLoginRequestDTO;
import com.uoc.reservofi.usuario.business.dto.UsuarioLoginResponseDTO;
import com.uoc.reservofi.usuario.business.service.GestionUsuarioService;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest()
@ContextConfiguration(classes = {AutenticacionController.class, RestAutenticacionMapperImpl.class, RestAutenticacionServiceImpl.class, RestExceptionHandler.class})
public class AutenticacionControllerTest {

	@Captor
	private ArgumentCaptor<UsuarioLoginRequestDTO> usuarioLoginCaptor = ArgumentCaptor.forClass(UsuarioLoginRequestDTO.class);
	@Captor
	private ArgumentCaptor<UsuarioDTO> usuarioCaptor = ArgumentCaptor.forClass(UsuarioDTO.class);
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	@MockitoBean
	private GestionUsuarioService gestionUsuarioService;

	@Test
	void registerTest() throws Exception {
		// DTO de entrada
		RestRegisterRequestDTO restRegisterDTO = new RestRegisterRequestDTO();
		restRegisterDTO.setNombre("Usuario prueba");
		restRegisterDTO.setPrimerApellido("Prueba primer apellido");
		restRegisterDTO.setSegundoApellido("Prueba segundo apellido");
		restRegisterDTO.setCorreoCorporativo("prueba@gmail.com");
		restRegisterDTO.setContrasenia("pass12345");
		restRegisterDTO.setActivo(true);

		// DTO que se espera obtener al final
		RestRegisterResponseDTO restReturnDTO = new RestRegisterResponseDTO();
		restReturnDTO.setId(1L);
		restReturnDTO.setNombre("Usuario prueba");
		restReturnDTO.setPrimerApellido("Prueba primer apellido");
		restReturnDTO.setSegundoApellido("Prueba segundo apellido");
		restReturnDTO.setCorreoCorporativo("prueba@gmail.com");
		restReturnDTO.setActivo(true);
		restReturnDTO.setIdsRoles(List.of(1L));

		// DTO con el que se invoca al servicio de gestión
		UsuarioDTO registerDTO = new UsuarioDTO();
		registerDTO.setNombre("Usuario prueba");
		registerDTO.setPrimerApellido("Prueba primer apellido");
		registerDTO.setSegundoApellido("Prueba segundo apellido");
		registerDTO.setCorreoCorporativo("prueba@gmail.com");
		registerDTO.setContrasenia("pass12345");
		registerDTO.setActivo(true);
		registerDTO.setIdsRoles(null);

		// DTO que devuelve el servicio de gestión
		UsuarioDTO mockReturnDTO = new UsuarioDTO();
		mockReturnDTO.setId(1L);
		mockReturnDTO.setNombre("Usuario prueba");
		mockReturnDTO.setPrimerApellido("Prueba primer apellido");
		mockReturnDTO.setSegundoApellido("Prueba segundo apellido");
		mockReturnDTO.setCorreoCorporativo("prueba@gmail.com");
		mockReturnDTO.setContrasenia("pass12345");
		mockReturnDTO.setActivo(true);
		mockReturnDTO.setIdsRoles(List.of(1L));

		// Mock del servicio de gestión
		when(gestionUsuarioService.register(any(UsuarioDTO.class))).thenReturn(mockReturnDTO);

		// Llamada a realizar
		mockMvc.perform(post("/api/auth/register").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(restRegisterDTO)))
				.andExpect(status().isCreated()).andExpect(content().json(objectMapper.writeValueAsString(restReturnDTO), JsonCompareMode.STRICT));

		// Se verifica que se haya llamado al servicio de gestión 1 vez
		verify(gestionUsuarioService).register(usuarioCaptor.capture());

		// Se verifica que lo que se manda al servicio de gestión coincide con lo esperado
		UsuarioDTO capturedDTO = usuarioCaptor.getValue();
		assertEquals(registerDTO.getId(), capturedDTO.getId());
		assertEquals(registerDTO.getNombre(), capturedDTO.getNombre());
		assertEquals(registerDTO.getPrimerApellido(), capturedDTO.getPrimerApellido());
		assertEquals(registerDTO.getSegundoApellido(), capturedDTO.getSegundoApellido());
		assertEquals(registerDTO.getCorreoCorporativo(), capturedDTO.getCorreoCorporativo());
		assertEquals(registerDTO.getContrasenia(), capturedDTO.getContrasenia());
		assertEquals(registerDTO.getIdsRoles(), capturedDTO.getIdsRoles());
	}

	@Test
	void loginTest() throws Exception {

		// DTO de entrada
		RestLoginRequestDTO restLoginRequestDTO = new RestLoginRequestDTO();
		restLoginRequestDTO.setCorreoCorporativo("prueba@gmail.com");
		restLoginRequestDTO.setContrasenia("pass12345");


		// DTO que se espera obtener al final
		RestLoginResponseDTO restLoginResponseDTO = new RestLoginResponseDTO();
		restLoginResponseDTO.setToken("token");
		restLoginResponseDTO.setIdUsuario(1L);
		restLoginResponseDTO.setExpiresIn(1000);

		// DTO con el que se invoca al servicio de gestión
		UsuarioLoginRequestDTO requestDTO = new UsuarioLoginRequestDTO();
		requestDTO.setCorreoCorporativo("prueba@gmail.com");
		requestDTO.setContrasenia("pass12345");

		// DTO que devuelve el servicio de gestión
		UsuarioLoginResponseDTO mockResponseDTO = new UsuarioLoginResponseDTO();
		mockResponseDTO.setToken("token");
		mockResponseDTO.setIdUsuario(1L);
		mockResponseDTO.setExpiresIn(1000);

		// Mock del servicio de gestión
		when(gestionUsuarioService.login(any(UsuarioLoginRequestDTO.class))).thenReturn(mockResponseDTO);

		// Llamada a realizar
		mockMvc.perform(post("/api/auth/login").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(restLoginRequestDTO)))
				.andExpect(status().isOk()).andExpect(content().json(objectMapper.writeValueAsString(restLoginResponseDTO), JsonCompareMode.STRICT));

		// Se verifica que se haya llamado al servicio de gestión 1 vez
		verify(gestionUsuarioService).login(usuarioLoginCaptor.capture());

		// Se verifica que lo que se manda al servicio de gestión coincide con lo esperado
		UsuarioLoginRequestDTO capturedDTO = usuarioLoginCaptor.getValue();
		assertEquals(requestDTO.getCorreoCorporativo(), capturedDTO.getCorreoCorporativo());
		assertEquals(requestDTO.getContrasenia(), capturedDTO.getContrasenia());
	}
}

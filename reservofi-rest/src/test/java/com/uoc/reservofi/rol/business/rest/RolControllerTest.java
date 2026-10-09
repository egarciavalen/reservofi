package com.uoc.reservofi.rol.business.rest;

import com.uoc.reservofi.base.business.controller.RestControllerAdvice;
import com.uoc.reservofi.rol.business.controller.RolController;
import com.uoc.reservofi.rol.business.dto.RestRolDTO;
import com.uoc.reservofi.rol.business.dto.RolDTO;
import com.uoc.reservofi.rol.business.mapper.RestRolMapperImpl;
import com.uoc.reservofi.rol.business.service.GestionRolService;
import com.uoc.reservofi.rol.business.service.impl.RestRolServiceImpl;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// Anotación de springboot para las pruebas con controladores (autoconfiguración de MVC y ejecución más rápida)
@WebMvcTest()
// Indica la configuración a levantar en la ejecución de la prueba
@ContextConfiguration(classes = {RolController.class, RestRolMapperImpl.class, RestRolServiceImpl.class, RestControllerAdvice.class})
public class RolControllerTest {

	@Captor // Captura de los argumentos
	private ArgumentCaptor<RolDTO> rolCaptor = ArgumentCaptor.forClass(RolDTO.class);
	@Autowired
	private MockMvc mockMvc; // Permite simular peticiones HTTP
	@Autowired
	private ObjectMapper objectMapper; // Mapear objetos JAVA a JSON y viceversa
	@MockitoBean // Crea y sustituye un bean real (inyección automática)
	private GestionRolService gestionRolService; // Servicio que se va a mockear

	@Test
	void insertTest() throws Exception {
		// DTO de entrada
		RestRolDTO restInsertDTO = new RestRolDTO();
		restInsertDTO.setNombre("Rol prueba");

		// DTO que se espera obtener al final
		RestRolDTO restReturnDTO = new RestRolDTO();
		restReturnDTO.setId(1L);
		restReturnDTO.setNombre("Rol prueba");

		// DTO con el que se invoca al servicio de gestión
		RolDTO insertDTO = new RolDTO();
		insertDTO.setNombre("Rol prueba");

		// DTO que devuelve el servicio de gestión
		RolDTO mockReturnDTO = new RolDTO();
		mockReturnDTO.setId(1L);
		mockReturnDTO.setNombre("Rol prueba");

		// Mock del servicio de gestión
		when(gestionRolService.insert(any(RolDTO.class))).thenReturn(mockReturnDTO);

		// Llamada a realizar
		// Aquí se verifica que devuelva OK y que la respuesta del controlador coincide con lo esperado
		// Con JsonCompareMode.STRICT se verifica que devuelva exactamente los campos indicados
		mockMvc.perform(post("/api/rol").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(restInsertDTO)))
				.andExpect(status().isCreated()).andExpect(content().json(objectMapper.writeValueAsString(restReturnDTO), JsonCompareMode.STRICT));

		// Se verifica que se haya llamado al servicio de gestión 1 vez
		verify(gestionRolService).insert(rolCaptor.capture());

		// Se verifica que lo que se manda al servicio de gestión coincide con lo esperado
		RolDTO capturedDTO = rolCaptor.getValue();
		assertEquals(insertDTO.getId(), capturedDTO.getId());
		assertEquals(insertDTO.getNombre(), capturedDTO.getNombre());
	}

	@Test
	void updateTest() throws Exception {
		// DTO de entrada
		RestRolDTO restUpdateDTO = new RestRolDTO();
		restUpdateDTO.setId(1L);
		restUpdateDTO.setNombre("Rol prueba");

		// DTO que se espera obtener al final
		RestRolDTO restReturnDTO = new RestRolDTO();
		restReturnDTO.setId(1L);
		restReturnDTO.setNombre("Rol prueba");

		// DTO con el que se invoca al servicio de gestión
		RolDTO updateDTO = new RolDTO();
		updateDTO.setId(1L);
		updateDTO.setNombre("Rol prueba");

		// DTO que devuelve el servicio de gestión
		RolDTO mockReturnDTO = new RolDTO();
		mockReturnDTO.setId(1L);
		mockReturnDTO.setNombre("Rol prueba");

		// Mock del servicio de gestión
		when(gestionRolService.update(any(RolDTO.class))).thenReturn(mockReturnDTO);

		mockMvc.perform(put("/api/rol").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(restUpdateDTO)))
				.andExpect(status().isOk()).andExpect(content().json(objectMapper.writeValueAsString(restReturnDTO), JsonCompareMode.STRICT));

		// Se verifica que se haya llamado al servicio de gestión 1 vez
		verify(gestionRolService).update(rolCaptor.capture());

		// Se verifica que lo que se manda al servicio de gestión coincide con lo esperado
		RolDTO capturedDTO = rolCaptor.getValue();
		assertEquals(updateDTO.getId(), capturedDTO.getId());
		assertEquals(updateDTO.getNombre(), capturedDTO.getNombre());
	}

	@Test
	void deleteByIdTest() throws Exception {
		// Parámetro de entrada
		Long idTest = 1L;

		// El deleteById no va a devolver nada
		doNothing().when(gestionRolService).deleteById(idTest);

		mockMvc.perform(delete("/api/rol/{id}", idTest))
				.andExpect(status().isNoContent());

		verify(gestionRolService).deleteById(1L);
	}

	@Test
	void findByIdTest() throws Exception {
		// Parámetro de entrada
		Long idTest = 1L;

		// DTO que devuelve el servicio de gestión
		RolDTO mockReturnDTO = new RolDTO();
		mockReturnDTO.setId(1L);
		mockReturnDTO.setNombre("Rol prueba");

		// DTO que se espera obtener al final
		RestRolDTO restReturnDTO = new RestRolDTO();
		restReturnDTO.setId(1L);
		restReturnDTO.setNombre("Rol prueba");

		// Mock del servicio de gestión
		when(gestionRolService.findById(idTest)).thenReturn(mockReturnDTO);

		mockMvc.perform(get("/api/rol/{id}", idTest)).
				andExpect(status().isOk()).andExpect(content().json(objectMapper.writeValueAsString(restReturnDTO), JsonCompareMode.STRICT));


		// Se verifica que se hayan llamado al servicio de gestión 1 vez
		verify(gestionRolService).findById(idTest);
	}

	@Test
	void findByIdNotFoundTest() throws Exception {
		// Parámetro de entrada
		Long idTest = 99999L;

		// Mock del servicio de gestión
		when(gestionRolService.findById(idTest)).thenReturn(null);

		// Respuesta de un error interno
		// Con esto se verifica el funcionamiento del ControllerAdvice
		mockMvc.perform(get("/api/rol/{id}", idTest)).
				andExpect(status().isInternalServerError()).andExpect(content().string("Entidad no encontrada"));
	}
}

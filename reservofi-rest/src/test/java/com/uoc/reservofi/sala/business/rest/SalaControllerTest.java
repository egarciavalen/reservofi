package com.uoc.reservofi.sala.business.rest;

import com.uoc.reservofi.base.business.controller.RestExceptionHandler;
import com.uoc.reservofi.sala.business.controller.SalaController;
import com.uoc.reservofi.sala.business.dto.RestSalaDTO;
import com.uoc.reservofi.sala.business.dto.SalaDTO;
import com.uoc.reservofi.sala.business.mapper.RestSalaMapperImpl;
import com.uoc.reservofi.sala.business.service.GestionSalaService;
import com.uoc.reservofi.sala.business.service.impl.RestSalaServiceImpl;
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

@WebMvcTest()
@ContextConfiguration(classes = {SalaController.class, RestSalaMapperImpl.class, RestSalaServiceImpl.class, RestExceptionHandler.class})
public class SalaControllerTest {

	@Captor
	private ArgumentCaptor<SalaDTO> salaCaptor = ArgumentCaptor.forClass(SalaDTO.class);
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	@MockitoBean
	private GestionSalaService gestionSalaService;

	@Test
	void insertTest() throws Exception {
		// DTO de entrada
		RestSalaDTO restInsertDTO = new RestSalaDTO();
		restInsertDTO.setNombre("Sala prueba");

		// DTO que se espera obtener al final
		RestSalaDTO restReturnDTO = new RestSalaDTO();
		restReturnDTO.setId(1L);
		restReturnDTO.setNombre("Sala prueba");

		// DTO con el que se invoca al servicio de gestión
		SalaDTO insertDTO = new SalaDTO();
		insertDTO.setNombre("Sala prueba");

		// DTO que devuelve el servicio de gestión
		SalaDTO mockReturnDTO = new SalaDTO();
		mockReturnDTO.setId(1L);
		mockReturnDTO.setNombre("Sala prueba");

		// Mock del servicio de gestión
		when(gestionSalaService.insert(any(SalaDTO.class))).thenReturn(mockReturnDTO);

		// Llamada a realizar
		mockMvc.perform(post("/api/sala").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(restInsertDTO)))
				.andExpect(status().isCreated()).andExpect(content().json(objectMapper.writeValueAsString(restReturnDTO), JsonCompareMode.STRICT));

		// Se verifica que se haya llamado al servicio de gestión 1 vez
		verify(gestionSalaService).insert(salaCaptor.capture());

		// Se verifica que lo que se manda al servicio de gestión coincide con lo esperado
		SalaDTO capturedDTO = salaCaptor.getValue();
		assertEquals(insertDTO.getId(), capturedDTO.getId());
		assertEquals(insertDTO.getNombre(), capturedDTO.getNombre());
	}

	@Test
	void updateTest() throws Exception {
		// DTO de entrada
		RestSalaDTO restUpdateDTO = new RestSalaDTO();
		restUpdateDTO.setId(1L);
		restUpdateDTO.setNombre("Sala prueba");

		// DTO que se espera obtener al final
		RestSalaDTO restReturnDTO = new RestSalaDTO();
		restReturnDTO.setId(1L);
		restReturnDTO.setNombre("Sala prueba");

		// DTO con el que se invoca al servicio de gestión
		SalaDTO updateDTO = new SalaDTO();
		updateDTO.setId(1L);
		updateDTO.setNombre("Sala prueba");

		// DTO que devuelve el servicio de gestión
		SalaDTO mockReturnDTO = new SalaDTO();
		mockReturnDTO.setId(1L);
		mockReturnDTO.setNombre("Sala prueba");

		// Mock del servicio de gestión
		when(gestionSalaService.update(any(SalaDTO.class))).thenReturn(mockReturnDTO);

		mockMvc.perform(put("/api/sala").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(restUpdateDTO)))
				.andExpect(status().isOk()).andExpect(content().json(objectMapper.writeValueAsString(restReturnDTO), JsonCompareMode.STRICT));

		// Se verifica que se haya llamado al servicio de gestión 1 vez
		verify(gestionSalaService).update(salaCaptor.capture());

		// Se verifica que lo que se manda al servicio de gestión coincide con lo esperado
		SalaDTO capturedDTO = salaCaptor.getValue();
		assertEquals(updateDTO.getId(), capturedDTO.getId());
		assertEquals(updateDTO.getNombre(), capturedDTO.getNombre());
	}

	@Test
	void deleteByIdTest() throws Exception {
		// Parámetro de entrada
		Long idTest = 1L;

		// El deleteById no va a devolver nada
		doNothing().when(gestionSalaService).deleteById(idTest);

		mockMvc.perform(delete("/api/sala/{id}", idTest))
				.andExpect(status().isNoContent());

		verify(gestionSalaService).deleteById(1L);
	}

	@Test
	void findByIdTest() throws Exception {
		// Parámetro de entrada
		Long idTest = 1L;

		// DTO que devuelve el servicio de gestión
		SalaDTO mockReturnDTO = new SalaDTO();
		mockReturnDTO.setId(1L);
		mockReturnDTO.setNombre("Sala prueba");

		// DTO que se espera obtener al final
		RestSalaDTO restReturnDTO = new RestSalaDTO();
		restReturnDTO.setId(1L);
		restReturnDTO.setNombre("Sala prueba");

		// Mock del servicio de gestión
		when(gestionSalaService.findById(idTest)).thenReturn(mockReturnDTO);

		mockMvc.perform(get("/api/sala/{id}", idTest)).
				andExpect(status().isOk()).andExpect(content().json(objectMapper.writeValueAsString(restReturnDTO), JsonCompareMode.STRICT));


		// Se verifica que se hayan llamado al servicio de gestión 1 vez
		verify(gestionSalaService).findById(idTest);
	}

	@Test
	void findByIdNotFoundTest() throws Exception {
		// Parámetro de entrada
		Long idTest = 99999L;

		// Mock del servicio de gestión
		when(gestionSalaService.findById(idTest)).thenReturn(null);

		// Respuesta de un error interno
		mockMvc.perform(get("/api/sala/{id}", idTest)).
				andExpect(status().isInternalServerError()).andExpect(content().string("Entidad no encontrada"));
	}
}

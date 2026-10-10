package com.uoc.reservofi.puesto.business.rest;

import com.uoc.reservofi.base.business.controller.RestExceptionHandler;
import com.uoc.reservofi.puesto.business.controller.PuestoController;
import com.uoc.reservofi.puesto.business.dto.PuestoDTO;
import com.uoc.reservofi.puesto.business.dto.RestPuestoDTO;
import com.uoc.reservofi.puesto.business.mapper.RestPuestoMapperImpl;
import com.uoc.reservofi.puesto.business.service.GestionPuestoService;
import com.uoc.reservofi.puesto.business.service.impl.RestPuestoServiceImpl;
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

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest()
@ContextConfiguration(classes = {PuestoController.class, RestPuestoMapperImpl.class, RestPuestoServiceImpl.class, RestExceptionHandler.class})
public class PuestoControllerTest {

	@Captor
	private ArgumentCaptor<PuestoDTO> puestoCaptor = ArgumentCaptor.forClass(PuestoDTO.class);
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	@MockitoBean
	private GestionPuestoService gestionPuestoService;

	@Test
	void insertTest() throws Exception {
		// DTO de entrada
		RestPuestoDTO restInsertDTO = new RestPuestoDTO();
		restInsertDTO.setNombre("Puesto prueba");
		restInsertDTO.setCoordenadaX(BigDecimal.valueOf(11.14));
		restInsertDTO.setCoordenadaY(BigDecimal.valueOf(22.25));
		restInsertDTO.setIdSala(1L);

		// DTO que se espera obtener al final
		RestPuestoDTO restReturnDTO = new RestPuestoDTO();
		restReturnDTO.setId(1L);
		restReturnDTO.setNombre("Puesto prueba");
		restReturnDTO.setCoordenadaX(BigDecimal.valueOf(11.14));
		restReturnDTO.setCoordenadaY(BigDecimal.valueOf(22.25));
		restReturnDTO.setIdSala(1L);

		// DTO con el que se invoca al servicio de gestión
		PuestoDTO insertDTO = new PuestoDTO();
		insertDTO.setNombre("Puesto prueba");
		insertDTO.setCoordenadaX(BigDecimal.valueOf(11.14));
		insertDTO.setCoordenadaY(BigDecimal.valueOf(22.25));
		insertDTO.setIdSala(1L);

		// DTO que devuelve el servicio de gestión
		PuestoDTO mockReturnDTO = new PuestoDTO();
		mockReturnDTO.setId(1L);
		mockReturnDTO.setNombre("Puesto prueba");
		mockReturnDTO.setCoordenadaX(BigDecimal.valueOf(11.14));
		mockReturnDTO.setCoordenadaY(BigDecimal.valueOf(22.25));
		mockReturnDTO.setIdSala(1L);

		// Mock del servicio de gestión
		when(gestionPuestoService.insert(any(PuestoDTO.class))).thenReturn(mockReturnDTO);

		// Llamada a realizar
		mockMvc.perform(post("/api/puesto").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(restInsertDTO)))
				.andExpect(status().isCreated()).andExpect(content().json(objectMapper.writeValueAsString(restReturnDTO), JsonCompareMode.STRICT));

		// Se verifica que se haya llamado al servicio de gestión 1 vez
		verify(gestionPuestoService).insert(puestoCaptor.capture());

		// Se verifica que lo que se manda al servicio de gestión coincide con lo esperado
		PuestoDTO capturedDTO = puestoCaptor.getValue();
		assertEquals(insertDTO.getId(), capturedDTO.getId());
		assertEquals(insertDTO.getNombre(), capturedDTO.getNombre());
		assertEquals(insertDTO.getCoordenadaX(), capturedDTO.getCoordenadaX());
		assertEquals(insertDTO.getCoordenadaY(), capturedDTO.getCoordenadaY());
		assertEquals(insertDTO.getIdSala(), capturedDTO.getIdSala());
	}

	@Test
	void updateTest() throws Exception {
		// DTO de entrada
		RestPuestoDTO restUpdateDTO = new RestPuestoDTO();
		restUpdateDTO.setId(1L);
		restUpdateDTO.setNombre("Puesto prueba");
		restUpdateDTO.setCoordenadaX(BigDecimal.valueOf(11.14));
		restUpdateDTO.setCoordenadaY(BigDecimal.valueOf(22.25));
		restUpdateDTO.setIdSala(1L);

		// DTO que se espera obtener al final
		RestPuestoDTO restReturnDTO = new RestPuestoDTO();
		restReturnDTO.setId(1L);
		restReturnDTO.setNombre("Puesto prueba");
		restReturnDTO.setCoordenadaX(BigDecimal.valueOf(11.14));
		restReturnDTO.setCoordenadaY(BigDecimal.valueOf(22.25));
		restReturnDTO.setIdSala(1L);

		// DTO con el que se invoca al servicio de gestión
		PuestoDTO updateDTO = new PuestoDTO();
		updateDTO.setId(1L);
		updateDTO.setNombre("Puesto prueba");
		updateDTO.setCoordenadaX(BigDecimal.valueOf(11.14));
		updateDTO.setCoordenadaY(BigDecimal.valueOf(22.25));
		updateDTO.setIdSala(1L);

		// DTO que devuelve el servicio de gestión
		PuestoDTO mockReturnDTO = new PuestoDTO();
		mockReturnDTO.setId(1L);
		mockReturnDTO.setNombre("Puesto prueba");
		mockReturnDTO.setCoordenadaX(BigDecimal.valueOf(11.14));
		mockReturnDTO.setCoordenadaY(BigDecimal.valueOf(22.25));
		mockReturnDTO.setIdSala(1L);

		// Mock del servicio de gestión
		when(gestionPuestoService.update(any(PuestoDTO.class))).thenReturn(mockReturnDTO);

		mockMvc.perform(put("/api/puesto").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(restUpdateDTO)))
				.andExpect(status().isOk()).andExpect(content().json(objectMapper.writeValueAsString(restReturnDTO), JsonCompareMode.STRICT));

		// Se verifica que se haya llamado al servicio de gestión 1 vez
		verify(gestionPuestoService).update(puestoCaptor.capture());

		// Se verifica que lo que se manda al servicio de gestión coincide con lo esperado
		PuestoDTO capturedDTO = puestoCaptor.getValue();
		assertEquals(updateDTO.getId(), capturedDTO.getId());
		assertEquals(updateDTO.getNombre(), capturedDTO.getNombre());
		assertEquals(updateDTO.getCoordenadaX(), capturedDTO.getCoordenadaX());
		assertEquals(updateDTO.getCoordenadaY(), capturedDTO.getCoordenadaY());
		assertEquals(updateDTO.getIdSala(), capturedDTO.getIdSala());
	}

	@Test
	void deleteByIdTest() throws Exception {
		// Parámetro de entrada
		Long idTest = 1L;

		// El deleteById no va a devolver nada
		doNothing().when(gestionPuestoService).deleteById(idTest);

		mockMvc.perform(delete("/api/puesto/{id}", idTest))
				.andExpect(status().isNoContent());

		verify(gestionPuestoService).deleteById(1L);
	}

	@Test
	void findByIdTest() throws Exception {
		// Parámetro de entrada
		Long idTest = 1L;

		// DTO que devuelve el servicio de gestión
		PuestoDTO mockReturnDTO = new PuestoDTO();
		mockReturnDTO.setId(1L);
		mockReturnDTO.setNombre("Puesto prueba");
		mockReturnDTO.setCoordenadaX(BigDecimal.valueOf(11.14));
		mockReturnDTO.setCoordenadaY(BigDecimal.valueOf(22.25));
		mockReturnDTO.setIdSala(1L);

		// DTO que se espera obtener al final
		RestPuestoDTO restReturnDTO = new RestPuestoDTO();
		restReturnDTO.setId(1L);
		restReturnDTO.setNombre("Puesto prueba");
		restReturnDTO.setCoordenadaX(BigDecimal.valueOf(11.14));
		restReturnDTO.setCoordenadaY(BigDecimal.valueOf(22.25));
		restReturnDTO.setIdSala(1L);

		// Mock del servicio de gestión
		when(gestionPuestoService.findById(idTest)).thenReturn(mockReturnDTO);

		mockMvc.perform(get("/api/puesto/{id}", idTest)).
				andExpect(status().isOk()).andExpect(content().json(objectMapper.writeValueAsString(restReturnDTO), JsonCompareMode.STRICT));


		// Se verifica que se hayan llamado al servicio de gestión 1 vez
		verify(gestionPuestoService).findById(idTest);
	}

	@Test
	void findByIdNotFoundTest() throws Exception {
		// Parámetro de entrada
		Long idTest = 99999L;

		// Mock del servicio de gestión
		when(gestionPuestoService.findById(idTest)).thenReturn(null);

		// Respuesta de un error interno
		mockMvc.perform(get("/api/puesto/{id}", idTest)).
				andExpect(status().isInternalServerError()).andExpect(content().string("Entidad no encontrada"));
	}
}

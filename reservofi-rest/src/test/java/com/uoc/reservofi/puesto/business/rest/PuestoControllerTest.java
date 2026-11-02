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
import java.util.ArrayList;
import java.util.List;

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
		restInsertDTO.setAncho(BigDecimal.valueOf(33.36));
		restInsertDTO.setAlto(BigDecimal.valueOf(44.47));
		restInsertDTO.setIdSala(1L);

		// DTO que se espera obtener al final
		RestPuestoDTO restReturnDTO = new RestPuestoDTO();
		restReturnDTO.setId(1L);
		restReturnDTO.setNombre("Puesto prueba");
		restReturnDTO.setCoordenadaX(BigDecimal.valueOf(11.14));
		restReturnDTO.setCoordenadaY(BigDecimal.valueOf(22.25));
		restReturnDTO.setAncho(BigDecimal.valueOf(33.36));
		restReturnDTO.setAlto(BigDecimal.valueOf(44.47));
		restReturnDTO.setIdSala(1L);

		// DTO con el que se invoca al servicio de gestión
		PuestoDTO insertDTO = new PuestoDTO();
		insertDTO.setNombre("Puesto prueba");
		insertDTO.setCoordenadaX(BigDecimal.valueOf(11.14));
		insertDTO.setCoordenadaY(BigDecimal.valueOf(22.25));
		insertDTO.setAncho(BigDecimal.valueOf(33.36));
		insertDTO.setAlto(BigDecimal.valueOf(44.47));
		insertDTO.setIdSala(1L);

		// DTO que devuelve el servicio de gestión
		PuestoDTO mockReturnDTO = new PuestoDTO();
		mockReturnDTO.setId(1L);
		mockReturnDTO.setNombre("Puesto prueba");
		mockReturnDTO.setCoordenadaX(BigDecimal.valueOf(11.14));
		mockReturnDTO.setCoordenadaY(BigDecimal.valueOf(22.25));
		mockReturnDTO.setAncho(BigDecimal.valueOf(33.36));
		mockReturnDTO.setAlto(BigDecimal.valueOf(44.47));
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
		assertEquals(insertDTO.getAncho(), capturedDTO.getAncho());
		assertEquals(insertDTO.getAlto(), capturedDTO.getAlto());
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
		restUpdateDTO.setAncho(BigDecimal.valueOf(33.36));
		restUpdateDTO.setAlto(BigDecimal.valueOf(44.47));

		restUpdateDTO.setIdSala(1L);

		// DTO que se espera obtener al final
		RestPuestoDTO restReturnDTO = new RestPuestoDTO();
		restReturnDTO.setId(1L);
		restReturnDTO.setNombre("Puesto prueba");
		restReturnDTO.setCoordenadaX(BigDecimal.valueOf(11.14));
		restReturnDTO.setCoordenadaY(BigDecimal.valueOf(22.25));
		restReturnDTO.setAncho(BigDecimal.valueOf(33.36));
		restReturnDTO.setAlto(BigDecimal.valueOf(44.47));
		restReturnDTO.setIdSala(1L);

		// DTO con el que se invoca al servicio de gestión
		PuestoDTO updateDTO = new PuestoDTO();
		updateDTO.setId(1L);
		updateDTO.setNombre("Puesto prueba");
		updateDTO.setCoordenadaX(BigDecimal.valueOf(11.14));
		updateDTO.setCoordenadaY(BigDecimal.valueOf(22.25));
		updateDTO.setAncho(BigDecimal.valueOf(33.36));
		updateDTO.setAlto(BigDecimal.valueOf(44.47));
		updateDTO.setIdSala(1L);

		// DTO que devuelve el servicio de gestión
		PuestoDTO mockReturnDTO = new PuestoDTO();
		mockReturnDTO.setId(1L);
		mockReturnDTO.setNombre("Puesto prueba");
		mockReturnDTO.setCoordenadaX(BigDecimal.valueOf(11.14));
		mockReturnDTO.setCoordenadaY(BigDecimal.valueOf(22.25));
		mockReturnDTO.setAncho(BigDecimal.valueOf(33.36));
		mockReturnDTO.setAlto(BigDecimal.valueOf(44.47));
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
		assertEquals(updateDTO.getAncho(), capturedDTO.getAncho());
		assertEquals(updateDTO.getAlto(), capturedDTO.getAlto());
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
		mockReturnDTO.setAncho(BigDecimal.valueOf(33.36));
		mockReturnDTO.setAlto(BigDecimal.valueOf(44.47));
		mockReturnDTO.setIdSala(1L);

		// DTO que se espera obtener al final
		RestPuestoDTO restReturnDTO = new RestPuestoDTO();
		restReturnDTO.setId(1L);
		restReturnDTO.setNombre("Puesto prueba");
		restReturnDTO.setCoordenadaX(BigDecimal.valueOf(11.14));
		restReturnDTO.setCoordenadaY(BigDecimal.valueOf(22.25));
		restReturnDTO.setAncho(BigDecimal.valueOf(33.36));
		restReturnDTO.setAlto(BigDecimal.valueOf(44.47));
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

	@Test
	void findAllTest() throws Exception {

		// Lista de DTOs que devuelve el servicio de gestión
		PuestoDTO mockReturnDTO1 = new PuestoDTO();
		mockReturnDTO1.setId(1L);
		mockReturnDTO1.setNombre("Puesto prueba");
		mockReturnDTO1.setCoordenadaX(BigDecimal.valueOf(11.14));
		mockReturnDTO1.setCoordenadaY(BigDecimal.valueOf(22.25));
		mockReturnDTO1.setAncho(BigDecimal.valueOf(33.36));
		mockReturnDTO1.setAlto(BigDecimal.valueOf(44.47));
		mockReturnDTO1.setIdSala(1L);

		PuestoDTO mockReturnDTO2 = new PuestoDTO();
		mockReturnDTO2.setId(2L);
		mockReturnDTO2.setNombre("Puesto prueba 2");
		mockReturnDTO2.setCoordenadaX(BigDecimal.valueOf(22.35));
		mockReturnDTO2.setCoordenadaY(BigDecimal.valueOf(33.36));
		mockReturnDTO2.setAncho(BigDecimal.valueOf(55.58));
		mockReturnDTO2.setAlto(BigDecimal.valueOf(66.69));
		mockReturnDTO2.setIdSala(2L);

		List<PuestoDTO> mockReturnDTOList = new ArrayList<>();
		mockReturnDTOList.add(mockReturnDTO1);
		mockReturnDTOList.add(mockReturnDTO2);

		// Lista de DTOs que se espera obtener al final
		RestPuestoDTO restReturnDTO1 = new RestPuestoDTO();
		restReturnDTO1.setId(1L);
		restReturnDTO1.setNombre("Puesto prueba");
		restReturnDTO1.setCoordenadaX(BigDecimal.valueOf(11.14));
		restReturnDTO1.setCoordenadaY(BigDecimal.valueOf(22.25));
		restReturnDTO1.setAncho(BigDecimal.valueOf(33.36));
		restReturnDTO1.setAlto(BigDecimal.valueOf(44.47));
		restReturnDTO1.setIdSala(1L);

		RestPuestoDTO restReturnDTO2 = new RestPuestoDTO();
		restReturnDTO2.setId(2L);
		restReturnDTO2.setNombre("Puesto prueba 2");
		restReturnDTO2.setCoordenadaX(BigDecimal.valueOf(22.35));
		restReturnDTO2.setCoordenadaY(BigDecimal.valueOf(33.36));
		restReturnDTO2.setAncho(BigDecimal.valueOf(55.58));
		restReturnDTO2.setAlto(BigDecimal.valueOf(66.69));
		restReturnDTO2.setIdSala(2L);

		List<RestPuestoDTO> restReturnDTOList = new ArrayList<>();
		restReturnDTOList.add(restReturnDTO1);
		restReturnDTOList.add(restReturnDTO2);

		// Mock del servicio de gestión
		when(gestionPuestoService.findAll()).thenReturn(mockReturnDTOList);

		mockMvc.perform(get("/api/puesto")).
				andExpect(status().isOk()).andExpect(content().json(objectMapper.writeValueAsString(restReturnDTOList), JsonCompareMode.STRICT));

		// Se verifica que se hayan llamado al servicio de gestión 1 vez
		verify(gestionPuestoService).findAll();
	}

}

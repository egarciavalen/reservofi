package com.uoc.reservofi.reserva.business.rest;

import com.uoc.reservofi.base.business.controller.RestControllerAdvice;
import com.uoc.reservofi.reserva.business.controller.ReservaController;
import com.uoc.reservofi.reserva.business.dto.ReservaDTO;
import com.uoc.reservofi.reserva.business.dto.RestReservaDTO;
import com.uoc.reservofi.reserva.business.mapper.RestReservaMapperImpl;
import com.uoc.reservofi.reserva.business.service.GestionReservaService;
import com.uoc.reservofi.reserva.business.service.impl.RestReservaServiceImpl;
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

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest()
@ContextConfiguration(classes = {ReservaController.class, RestReservaMapperImpl.class, RestReservaServiceImpl.class, RestControllerAdvice.class})
public class ReservaControllerTest {

	@Captor
	private ArgumentCaptor<ReservaDTO> reservaCaptor = ArgumentCaptor.forClass(ReservaDTO.class);
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	@MockitoBean
	private GestionReservaService gestionReservaService;

	@Test
	void insertTest() throws Exception {
		// DTO de entrada
		RestReservaDTO restInsertDTO = new RestReservaDTO();
		restInsertDTO.setFechaInicio(LocalDateTime.of(2026, 10, 10, 0, 0));
		restInsertDTO.setFechaFin(LocalDateTime.of(2026, 10, 11, 0, 0));
		restInsertDTO.setIdPuesto(1L);
		restInsertDTO.setIdUsuario(1L);

		// DTO que se espera obtener al final
		RestReservaDTO restReturnDTO = new RestReservaDTO();
		restReturnDTO.setId(1L);
		restReturnDTO.setFechaInicio(LocalDateTime.of(2026, 10, 10, 0, 0));
		restReturnDTO.setFechaFin(LocalDateTime.of(2026, 10, 11, 0, 0));
		restReturnDTO.setIdPuesto(1L);
		restReturnDTO.setIdUsuario(1L);

		// DTO con el que se invoca al servicio de gestión
		ReservaDTO insertDTO = new ReservaDTO();
		insertDTO.setFechaInicio(LocalDateTime.of(2026, 10, 10, 0, 0));
		insertDTO.setFechaFin(LocalDateTime.of(2026, 10, 11, 0, 0));
		insertDTO.setIdPuesto(1L);
		insertDTO.setIdUsuario(1L);

		// DTO que devuelve el servicio de gestión
		ReservaDTO mockReturnDTO = new ReservaDTO();
		mockReturnDTO.setId(1L);
		mockReturnDTO.setFechaInicio(LocalDateTime.of(2026, 10, 10, 0, 0));
		mockReturnDTO.setFechaFin(LocalDateTime.of(2026, 10, 11, 0, 0));
		mockReturnDTO.setIdPuesto(1L);
		mockReturnDTO.setIdUsuario(1L);

		// Mock del servicio de gestión
		when(gestionReservaService.insert(any(ReservaDTO.class))).thenReturn(mockReturnDTO);

		// Llamada a realizar
		mockMvc.perform(post("/api/reserva").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(restInsertDTO)))
				.andExpect(status().isCreated()).andExpect(content().json(objectMapper.writeValueAsString(restReturnDTO), JsonCompareMode.STRICT));

		// Se verifica que se haya llamado al servicio de gestión 1 vez
		verify(gestionReservaService).insert(reservaCaptor.capture());

		// Se verifica que lo que se manda al servicio de gestión coincide con lo esperado
		ReservaDTO capturedDTO = reservaCaptor.getValue();
		assertEquals(insertDTO.getId(), capturedDTO.getId());
		assertEquals(insertDTO.getFechaInicio(), capturedDTO.getFechaInicio());
		assertEquals(insertDTO.getFechaFin(), capturedDTO.getFechaFin());
		assertEquals(insertDTO.getIdPuesto(), capturedDTO.getIdPuesto());
		assertEquals(insertDTO.getIdUsuario(), capturedDTO.getIdUsuario());
	}

	@Test
	void updateTest() throws Exception {
		// DTO de entrada
		RestReservaDTO restUpdateDTO = new RestReservaDTO();
		restUpdateDTO.setId(1L);
		restUpdateDTO.setFechaInicio(LocalDateTime.of(2026, 10, 10, 0, 0));
		restUpdateDTO.setFechaFin(LocalDateTime.of(2026, 10, 11, 0, 0));
		restUpdateDTO.setIdPuesto(1L);
		restUpdateDTO.setIdUsuario(1L);

		// DTO que se espera obtener al final
		RestReservaDTO restReturnDTO = new RestReservaDTO();
		restReturnDTO.setId(1L);
		restReturnDTO.setFechaInicio(LocalDateTime.of(2026, 10, 10, 0, 0));
		restReturnDTO.setFechaFin(LocalDateTime.of(2026, 10, 11, 0, 0));
		restReturnDTO.setIdPuesto(1L);
		restReturnDTO.setIdUsuario(1L);

		// DTO con el que se invoca al servicio de gestión
		ReservaDTO updateDTO = new ReservaDTO();
		updateDTO.setId(1L);
		updateDTO.setFechaInicio(LocalDateTime.of(2026, 10, 10, 0, 0));
		updateDTO.setFechaFin(LocalDateTime.of(2026, 10, 11, 0, 0));
		updateDTO.setIdPuesto(1L);
		updateDTO.setIdUsuario(1L);

		// DTO que devuelve el servicio de gestión
		ReservaDTO mockReturnDTO = new ReservaDTO();
		mockReturnDTO.setId(1L);
		mockReturnDTO.setFechaInicio(LocalDateTime.of(2026, 10, 10, 0, 0));
		mockReturnDTO.setFechaFin(LocalDateTime.of(2026, 10, 11, 0, 0));
		mockReturnDTO.setIdPuesto(1L);
		mockReturnDTO.setIdUsuario(1L);

		// Mock del servicio de gestión
		when(gestionReservaService.update(any(ReservaDTO.class))).thenReturn(mockReturnDTO);

		mockMvc.perform(put("/api/reserva").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(restUpdateDTO)))
				.andExpect(status().isOk()).andExpect(content().json(objectMapper.writeValueAsString(restReturnDTO), JsonCompareMode.STRICT));

		// Se verifica que se haya llamado al servicio de gestión 1 vez
		verify(gestionReservaService).update(reservaCaptor.capture());

		// Se verifica que lo que se manda al servicio de gestión coincide con lo esperado
		ReservaDTO capturedDTO = reservaCaptor.getValue();
		assertEquals(updateDTO.getId(), capturedDTO.getId());
		assertEquals(updateDTO.getFechaInicio(), capturedDTO.getFechaInicio());
		assertEquals(updateDTO.getFechaFin(), capturedDTO.getFechaFin());
		assertEquals(updateDTO.getIdPuesto(), capturedDTO.getIdPuesto());
		assertEquals(updateDTO.getIdUsuario(), capturedDTO.getIdUsuario());
	}

	@Test
	void deleteByIdTest() throws Exception {
		// Parámetro de entrada
		Long idTest = 1L;

		// El deleteById no va a devolver nada
		doNothing().when(gestionReservaService).deleteById(idTest);

		mockMvc.perform(delete("/api/reserva/{id}", idTest))
				.andExpect(status().isNoContent());

		verify(gestionReservaService).deleteById(1L);
	}

	@Test
	void findByIdTest() throws Exception {
		// Parámetro de entrada
		Long idTest = 1L;

		// DTO que devuelve el servicio de gestión
		ReservaDTO mockReturnDTO = new ReservaDTO();
		mockReturnDTO.setId(1L);
		mockReturnDTO.setFechaInicio(LocalDateTime.of(2026, 10, 10, 0, 0));
		mockReturnDTO.setFechaFin(LocalDateTime.of(2026, 10, 11, 0, 0));
		mockReturnDTO.setIdPuesto(1L);
		mockReturnDTO.setIdUsuario(1L);

		// DTO que se espera obtener al final
		RestReservaDTO restReturnDTO = new RestReservaDTO();
		restReturnDTO.setId(1L);
		restReturnDTO.setFechaInicio(LocalDateTime.of(2026, 10, 10, 0, 0));
		restReturnDTO.setFechaFin(LocalDateTime.of(2026, 10, 11, 0, 0));
		restReturnDTO.setIdPuesto(1L);
		restReturnDTO.setIdUsuario(1L);

		// Mock del servicio de gestión
		when(gestionReservaService.findById(idTest)).thenReturn(mockReturnDTO);

		mockMvc.perform(get("/api/reserva/{id}", idTest)).
				andExpect(status().isOk()).andExpect(content().json(objectMapper.writeValueAsString(restReturnDTO), JsonCompareMode.STRICT));


		// Se verifica que se hayan llamado al servicio de gestión 1 vez
		verify(gestionReservaService).findById(idTest);
	}

	@Test
	void findByIdNotFoundTest() throws Exception {
		// Parámetro de entrada
		Long idTest = 99999L;

		// Mock del servicio de gestión
		when(gestionReservaService.findById(idTest)).thenReturn(null);

		// Respuesta de un error interno
		mockMvc.perform(get("/api/reserva/{id}", idTest)).
				andExpect(status().isInternalServerError()).andExpect(content().string("Entidad no encontrada"));
	}
}

package com.uoc.reservofi.reserva.business.rest;

import com.uoc.reservofi.base.business.controller.RestExceptionHandler;
import com.uoc.reservofi.reserva.business.controller.ReservaController;
import com.uoc.reservofi.reserva.business.dto.ReservaDTO;
import com.uoc.reservofi.reserva.business.dto.RestReservaConsultaDTO;
import com.uoc.reservofi.reserva.business.dto.RestReservaGuardadoDTO;
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
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest()
@ContextConfiguration(classes = {ReservaController.class, RestReservaMapperImpl.class, RestReservaServiceImpl.class, RestExceptionHandler.class})
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
		RestReservaGuardadoDTO restInsertDTO = new RestReservaGuardadoDTO();
		restInsertDTO.setFechaInicio(LocalDateTime.of(2026, 10, 10, 0, 0));
		restInsertDTO.setFechaFin(LocalDateTime.of(2026, 10, 11, 0, 0));
		restInsertDTO.setIdPuesto(1L);
		restInsertDTO.setIdUsuario(1L);

		// DTO que se espera obtener al final
		RestReservaConsultaDTO restReturnDTO = new RestReservaConsultaDTO();
		restReturnDTO.setId(1L);
		restReturnDTO.setFechaInicio(LocalDateTime.of(2026, 10, 10, 0, 0));
		restReturnDTO.setFechaFin(LocalDateTime.of(2026, 10, 11, 0, 0));
		restReturnDTO.setIdPuesto(1L);
		restReturnDTO.setIdUsuario(1L);
		restReturnDTO.setNombrePuesto("Puesto 1");
		restReturnDTO.setNombreSala("Sala 1");
		restReturnDTO.setRutaImagen("src/main/resources/static/images/img1.png");

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
		mockReturnDTO.setNombrePuesto("Puesto 1");
		mockReturnDTO.setNombreSala("Sala 1");
		mockReturnDTO.setRutaImagen("src/main/resources/static/images/img1.png");

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
		assertNull(capturedDTO.getNombrePuesto());
		assertNull(capturedDTO.getNombreSala());
		assertNull(capturedDTO.getRutaImagen());
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
		mockReturnDTO.setNombrePuesto("Puesto 1");
		mockReturnDTO.setNombreSala("Sala 1");
		mockReturnDTO.setRutaImagen("src/main/resources/static/images/img1.png");

		// DTO que se espera obtener al final
		RestReservaConsultaDTO restReturnDTO = new RestReservaConsultaDTO();
		restReturnDTO.setId(1L);
		restReturnDTO.setFechaInicio(LocalDateTime.of(2026, 10, 10, 0, 0));
		restReturnDTO.setFechaFin(LocalDateTime.of(2026, 10, 11, 0, 0));
		restReturnDTO.setIdPuesto(1L);
		restReturnDTO.setIdUsuario(1L);
		restReturnDTO.setNombrePuesto("Puesto 1");
		restReturnDTO.setNombreSala("Sala 1");
		restReturnDTO.setRutaImagen("src/main/resources/static/images/img1.png");

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

	@Test
	void findAllTest() throws Exception {

		// Lista de DTOs que devuelve el servicio de gestión
		ReservaDTO mockReturnDTO1 = new ReservaDTO();
		mockReturnDTO1.setId(1L);
		mockReturnDTO1.setFechaInicio(LocalDateTime.of(2026, 10, 10, 0, 0));
		mockReturnDTO1.setFechaFin(LocalDateTime.of(2026, 10, 11, 0, 0));
		mockReturnDTO1.setIdPuesto(1L);
		mockReturnDTO1.setIdUsuario(1L);
		mockReturnDTO1.setNombrePuesto("Puesto 1");
		mockReturnDTO1.setNombreSala("Sala 1");
		mockReturnDTO1.setRutaImagen("src/main/resources/static/images/img1.png");

		ReservaDTO mockReturnDTO2 = new ReservaDTO();
		mockReturnDTO2.setId(2L);
		mockReturnDTO2.setFechaInicio(LocalDateTime.of(2026, 10, 11, 0, 0));
		mockReturnDTO2.setFechaFin(LocalDateTime.of(2026, 10, 12, 0, 0));
		mockReturnDTO2.setIdPuesto(2L);
		mockReturnDTO2.setIdUsuario(2L);
		mockReturnDTO2.setNombrePuesto("Puesto 2");
		mockReturnDTO2.setNombreSala("Sala 2");
		mockReturnDTO2.setRutaImagen("src/main/resources/static/images/img2.png");

		List<ReservaDTO> mockReturnDTOList = new ArrayList<>();
		mockReturnDTOList.add(mockReturnDTO1);
		mockReturnDTOList.add(mockReturnDTO2);

		// Lista de DTOs que se espera obtener al final
		RestReservaConsultaDTO restReturnDTO1 = new RestReservaConsultaDTO();
		restReturnDTO1.setId(1L);
		restReturnDTO1.setFechaInicio(LocalDateTime.of(2026, 10, 10, 0, 0));
		restReturnDTO1.setFechaFin(LocalDateTime.of(2026, 10, 11, 0, 0));
		restReturnDTO1.setIdPuesto(1L);
		restReturnDTO1.setIdUsuario(1L);
		restReturnDTO1.setNombrePuesto("Puesto 1");
		restReturnDTO1.setNombreSala("Sala 1");
		restReturnDTO1.setRutaImagen("src/main/resources/static/images/img1.png");

		RestReservaConsultaDTO restReturnDTO2 = new RestReservaConsultaDTO();
		restReturnDTO2.setId(2L);
		restReturnDTO2.setFechaInicio(LocalDateTime.of(2026, 10, 11, 0, 0));
		restReturnDTO2.setFechaFin(LocalDateTime.of(2026, 10, 12, 0, 0));
		restReturnDTO2.setIdPuesto(2L);
		restReturnDTO2.setIdUsuario(2L);
		restReturnDTO2.setNombrePuesto("Puesto 2");
		restReturnDTO2.setNombreSala("Sala 2");
		restReturnDTO2.setRutaImagen("src/main/resources/static/images/img2.png");

		List<RestReservaConsultaDTO> restReturnDTOList = new ArrayList<>();
		restReturnDTOList.add(restReturnDTO1);
		restReturnDTOList.add(restReturnDTO2);

		// Mock del servicio de gestión
		when(gestionReservaService.findAll()).thenReturn(mockReturnDTOList);

		mockMvc.perform(get("/api/reserva")).
				andExpect(status().isOk()).andExpect(content().json(objectMapper.writeValueAsString(restReturnDTOList), JsonCompareMode.STRICT));

		// Se verifica que se hayan llamado al servicio de gestión 1 vez
		verify(gestionReservaService).findAll();
	}

	@Test
	void findByUsuarioIdTest() throws Exception {
		// Parámetro de entrada
		Long idUsuario = 1L;

		// Lista de DTOs que devuelve el servicio de gestión
		ReservaDTO mockReturnDTO1 = new ReservaDTO();
		mockReturnDTO1.setId(1L);
		mockReturnDTO1.setFechaInicio(LocalDateTime.of(2026, 10, 10, 0, 0));
		mockReturnDTO1.setFechaFin(LocalDateTime.of(2026, 10, 11, 0, 0));
		mockReturnDTO1.setIdPuesto(1L);
		mockReturnDTO1.setIdUsuario(idUsuario);
		mockReturnDTO1.setNombrePuesto("Puesto 1");
		mockReturnDTO1.setNombreSala("Sala 1");
		mockReturnDTO1.setRutaImagen("src/main/resources/static/images/img1.png");

		ReservaDTO mockReturnDTO2 = new ReservaDTO();
		mockReturnDTO2.setId(2L);
		mockReturnDTO2.setFechaInicio(LocalDateTime.of(2026, 10, 11, 0, 0));
		mockReturnDTO2.setFechaFin(LocalDateTime.of(2026, 10, 12, 0, 0));
		mockReturnDTO2.setIdPuesto(2L);
		mockReturnDTO2.setIdUsuario(idUsuario);
		mockReturnDTO2.setNombrePuesto("Puesto 2");
		mockReturnDTO2.setNombreSala("Sala 2");
		mockReturnDTO2.setRutaImagen("src/main/resources/static/images/img2.png");

		List<ReservaDTO> mockReturnDTOList = new ArrayList<>();
		mockReturnDTOList.add(mockReturnDTO1);
		mockReturnDTOList.add(mockReturnDTO2);

		// Lista de DTOs que se espera obtener al final
		RestReservaConsultaDTO restReturnDTO1 = new RestReservaConsultaDTO();
		restReturnDTO1.setId(1L);
		restReturnDTO1.setFechaInicio(LocalDateTime.of(2026, 10, 10, 0, 0));
		restReturnDTO1.setFechaFin(LocalDateTime.of(2026, 10, 11, 0, 0));
		restReturnDTO1.setIdPuesto(1L);
		restReturnDTO1.setIdUsuario(idUsuario);
		restReturnDTO1.setNombrePuesto("Puesto 1");
		restReturnDTO1.setNombreSala("Sala 1");
		restReturnDTO1.setRutaImagen("src/main/resources/static/images/img1.png");

		RestReservaConsultaDTO restReturnDTO2 = new RestReservaConsultaDTO();
		restReturnDTO2.setId(2L);
		restReturnDTO2.setFechaInicio(LocalDateTime.of(2026, 10, 11, 0, 0));
		restReturnDTO2.setFechaFin(LocalDateTime.of(2026, 10, 12, 0, 0));
		restReturnDTO2.setIdPuesto(2L);
		restReturnDTO2.setIdUsuario(idUsuario);
		restReturnDTO2.setNombrePuesto("Puesto 2");
		restReturnDTO2.setNombreSala("Sala 2");
		restReturnDTO2.setRutaImagen("src/main/resources/static/images/img2.png");

		List<RestReservaConsultaDTO> restReturnDTOList = new ArrayList<>();
		restReturnDTOList.add(restReturnDTO1);
		restReturnDTOList.add(restReturnDTO2);

		// Mock del servicio de gestión
		when(gestionReservaService.findByUsuarioId(idUsuario)).thenReturn(mockReturnDTOList);

		mockMvc.perform(get("/api/reserva/usuario/{id}", idUsuario)).
				andExpect(status().isOk()).andExpect(content().json(objectMapper.writeValueAsString(restReturnDTOList), JsonCompareMode.STRICT));

		// Se verifica que se hayan llamado al servicio de gestión 1 vez
		verify(gestionReservaService).findByUsuarioId(idUsuario);
	}
}

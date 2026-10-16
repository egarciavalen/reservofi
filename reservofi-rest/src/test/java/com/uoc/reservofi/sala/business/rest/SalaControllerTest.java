package com.uoc.reservofi.sala.business.rest;

import com.uoc.reservofi.base.business.controller.RestExceptionHandler;
import com.uoc.reservofi.sala.business.controller.SalaController;
import com.uoc.reservofi.sala.business.dto.RestSalaConsultaDTO;
import com.uoc.reservofi.sala.business.dto.RestSalaGuardadoDTO;
import com.uoc.reservofi.sala.business.dto.SalaDTO;
import com.uoc.reservofi.sala.business.mapper.RestSalaMapperImpl;
import com.uoc.reservofi.sala.business.service.GestionSalaService;
import com.uoc.reservofi.sala.business.service.impl.RestSalaServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.HttpMethod;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.json.JsonCompareMode;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

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
	void createTest() throws Exception {

		// IMPORTANTE que coincida el nombre con el parámetro de entrada del dto: "imagen"
		MockMultipartFile mockFile = new MockMultipartFile("imagen", new byte[1]);

		// DTO de entrada
		RestSalaGuardadoDTO restInsertDTO = new RestSalaGuardadoDTO();
		restInsertDTO.setNombre("Sala prueba");
		restInsertDTO.setImagen(mockFile);

		// DTO que se espera obtener al final
		RestSalaConsultaDTO restReturnDTO = new RestSalaConsultaDTO();
		restReturnDTO.setId(1L);
		restReturnDTO.setNombre("Sala prueba");
		restReturnDTO.setRutaImagen("src/main/resources/static/images/img.png");

		// DTO con el que se invoca al servicio de gestión
		SalaDTO insertDTO = new SalaDTO();
		insertDTO.setNombre("Sala prueba");
		insertDTO.setImagen(mockFile);

		// DTO que devuelve el servicio de gestión
		SalaDTO mockReturnDTO = new SalaDTO();
		mockReturnDTO.setId(1L);
		mockReturnDTO.setNombre("Sala prueba");
		mockReturnDTO.setRutaImagen("src/main/resources/static/images/img.png");

		// Mock del servicio de gestión
		when(gestionSalaService.createAndSaveImage(any(SalaDTO.class))).thenReturn(mockReturnDTO);


		// Llamada a realizar
		// Al incluir un fichero se usa multipart + param + file
		mockMvc.perform(multipart("/api/sala").param("nombre", restInsertDTO.getNombre()).file(mockFile))
				.andExpect(status().isCreated()).andExpect(content().json(objectMapper.writeValueAsString(restReturnDTO), JsonCompareMode.STRICT));

		// Se verifica que se haya llamado al servicio de gestión 1 vez
		verify(gestionSalaService).createAndSaveImage(salaCaptor.capture());

		// Se verifica que lo que se manda al servicio de gestión coincide con lo esperado
		SalaDTO capturedDTO = salaCaptor.getValue();
		assertEquals(insertDTO.getId(), capturedDTO.getId());
		assertEquals(insertDTO.getNombre(), capturedDTO.getNombre());
		assertEquals(insertDTO.getImagen(), capturedDTO.getImagen());
		assertNull(capturedDTO.getRutaImagen()); // NO se le pasa la ruta de la imagen desde el rest

	}

	@Test
	void updateTest() throws Exception {

		// IMPORTANTE que coincida el nombre con el parámetro de entrada del dto: "imagen"
		MockMultipartFile mockFile = new MockMultipartFile("imagen", new byte[1]);
		long idSala = 1L;

		// DTO de entrada
		RestSalaGuardadoDTO restInsertDTO = new RestSalaGuardadoDTO();
		restInsertDTO.setNombre("Sala prueba");
		restInsertDTO.setImagen(mockFile);

		// DTO que se espera obtener al final
		RestSalaConsultaDTO restReturnDTO = new RestSalaConsultaDTO();
		restReturnDTO.setId(1L);
		restReturnDTO.setNombre("Sala prueba");
		restReturnDTO.setRutaImagen("src/main/resources/static/images/img.png");

		// DTO con el que se invoca al servicio de gestión
		SalaDTO insertDTO = new SalaDTO();
		insertDTO.setId(1L);
		insertDTO.setNombre("Sala prueba");
		insertDTO.setImagen(mockFile);

		// DTO que devuelve el servicio de gestión
		SalaDTO mockReturnDTO = new SalaDTO();
		mockReturnDTO.setId(1L);
		mockReturnDTO.setNombre("Sala prueba");
		mockReturnDTO.setRutaImagen("src/main/resources/static/images/img.png");

		// Mock del servicio de gestión
		when(gestionSalaService.update(any(SalaDTO.class))).thenReturn(mockReturnDTO);


		// Llamada a realizar
		// Al incluir un fichero se usa multipart + param + file
		mockMvc.perform(multipart(HttpMethod.PUT, "/api/sala/" + idSala).param("nombre", restInsertDTO.getNombre()).file(mockFile))
				.andExpect(status().isOk()).andExpect(content().json(objectMapper.writeValueAsString(restReturnDTO), JsonCompareMode.STRICT));

		// Se verifica que se haya llamado al servicio de gestión 1 vez
		verify(gestionSalaService).update(salaCaptor.capture());

		// Se verifica que lo que se manda al servicio de gestión coincide con lo esperado
		SalaDTO capturedDTO = salaCaptor.getValue();
		assertEquals(insertDTO.getId(), capturedDTO.getId());
		assertEquals(insertDTO.getNombre(), capturedDTO.getNombre());
		assertEquals(insertDTO.getImagen(), capturedDTO.getImagen());
		assertNull(capturedDTO.getRutaImagen()); // NO se le pasa la ruta de la imagen desde el rest
	}

	@Test
	void deleteByIdTest() throws Exception {
		// Parámetro de entrada
		Long idTest = 1L;

		// El deleteWithImage no va a devolver nada
		doNothing().when(gestionSalaService).deleteWithImage(idTest);

		mockMvc.perform(delete("/api/sala/{id}", idTest))
				.andExpect(status().isNoContent());

		verify(gestionSalaService).deleteWithImage(1L);
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
		RestSalaConsultaDTO restReturnDTO = new RestSalaConsultaDTO();
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

	@Test
	void findAllTest() throws Exception {

		// Lista de DTOs que devuelve el servicio de gestión
		SalaDTO mockReturnDTO1 = new SalaDTO();
		mockReturnDTO1.setId(1L);
		mockReturnDTO1.setNombre("Sala prueba");

		SalaDTO mockReturnDTO2 = new SalaDTO();
		mockReturnDTO2.setId(2L);
		mockReturnDTO2.setNombre("Sala prueba 2");

		List<SalaDTO> mockReturnDTOList = new ArrayList<>();
		mockReturnDTOList.add(mockReturnDTO1);
		mockReturnDTOList.add(mockReturnDTO2);

		// Lista de DTOs que se espera obtener al final
		RestSalaConsultaDTO restReturnDTO1 = new RestSalaConsultaDTO();
		restReturnDTO1.setId(1L);
		restReturnDTO1.setNombre("Sala prueba");

		RestSalaConsultaDTO restReturnDTO2 = new RestSalaConsultaDTO();
		restReturnDTO2.setId(2L);
		restReturnDTO2.setNombre("Sala prueba 2");

		List<RestSalaConsultaDTO> restReturnDTOList = new ArrayList<>();
		restReturnDTOList.add(restReturnDTO1);
		restReturnDTOList.add(restReturnDTO2);

		// Mock del servicio de gestión
		when(gestionSalaService.findAll()).thenReturn(mockReturnDTOList);

		mockMvc.perform(get("/api/sala")).
				andExpect(status().isOk()).andExpect(content().json(objectMapper.writeValueAsString(restReturnDTOList), JsonCompareMode.STRICT));

		// Se verifica que se hayan llamado al servicio de gestión 1 vez
		verify(gestionSalaService).findAll();
	}
}

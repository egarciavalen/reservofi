package com.uoc.reservofi.sala.business.service.impl;

import com.uoc.reservofi.base.business.service.impl.AbstractRestEntidadServiceImpl;
import com.uoc.reservofi.sala.business.dto.RestSalaConsultaDTO;
import com.uoc.reservofi.sala.business.dto.RestSalaGuardadoDTO;
import com.uoc.reservofi.sala.business.dto.SalaDTO;
import com.uoc.reservofi.sala.business.mapper.RestSalaMapper;
import com.uoc.reservofi.sala.business.service.GestionSalaService;
import com.uoc.reservofi.sala.business.service.RestSalaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(RestSalaService.BEAN_NAME)
@RequiredArgsConstructor
public class RestSalaServiceImpl extends AbstractRestEntidadServiceImpl<RestSalaConsultaDTO> implements RestSalaService {

	private final GestionSalaService gestionSalaService;
	private final RestSalaMapper restSalaMapper;

	// En principio se asume que NO se modificará la sala una vez creada

	@Override
	public RestSalaConsultaDTO create(RestSalaGuardadoDTO restDTO) {
		SalaDTO dto = restSalaMapper.modificacionRestDTOADTO(restDTO);
		dto = gestionSalaService.createAndSaveImage(dto);
		return restSalaMapper.consultaDTOArestDTO(dto);
	}

	@Override
	public RestSalaConsultaDTO update(RestSalaGuardadoDTO restDTO, Long idSala) {
		// Se guarda el ID en el DTO para trasladarlo en el mapper
		restDTO.setId(idSala);
		SalaDTO dto = restSalaMapper.modificacionRestDTOADTO(restDTO);
		dto = gestionSalaService.update(dto);
		return restSalaMapper.consultaDTOArestDTO(dto);
	}


	@Override
	public void deleteById(Long id) {
		// Interesa borrar también la imagen del disco para no acumular datos que no se usen
		gestionSalaService.deleteWithImage(id);
	}

	@Override
	public RestSalaConsultaDTO findById(Long id) {
		SalaDTO dto = gestionSalaService.findById(id);
		return restSalaMapper.consultaDTOArestDTO(dto);
	}

	@Override
	public List<RestSalaConsultaDTO> findAll() {
		List<SalaDTO> dtoList = gestionSalaService.findAll();
		return restSalaMapper.consultaDTOArestDTOList(dtoList);
	}

}

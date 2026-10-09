package com.uoc.reservofi.usuario.business.service.base.impl;

import com.uoc.reservofi.base.busines.repository.BaseEntidadRepository;
import com.uoc.reservofi.base.busines.service.base.impl.AbstractBaseEntidadServiceImpl;
import com.uoc.reservofi.usuario.business.model.Usuario;
import com.uoc.reservofi.usuario.business.repository.UsuarioRepository;
import com.uoc.reservofi.usuario.business.service.base.BaseUsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service(BaseUsuarioService.BEAN_NAME)
@RequiredArgsConstructor
public class BaseUsuarioServiceImpl extends AbstractBaseEntidadServiceImpl<Usuario> implements BaseUsuarioService {

	private final UsuarioRepository UsuarioRepository;

	@Override
	public BaseEntidadRepository<Usuario> getRepository() {
		return UsuarioRepository;
	}
}

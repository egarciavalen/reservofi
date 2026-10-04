package com.uoc.reservofi.usuario.business.repository;

import com.uoc.reservofi.base.busines.repository.BaseEntidadActualizableRepository;
import com.uoc.reservofi.usuario.business.model.Usuario;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends BaseEntidadActualizableRepository<Usuario> {

}

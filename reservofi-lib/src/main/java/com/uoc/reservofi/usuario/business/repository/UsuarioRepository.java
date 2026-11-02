package com.uoc.reservofi.usuario.business.repository;

import com.uoc.reservofi.base.busines.repository.BaseEntidadRepository;
import com.uoc.reservofi.usuario.business.model.Usuario;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends BaseEntidadRepository<Usuario> {

	Usuario findByCorreoCorporativo(@Param("correoCorporativo") String correoCorporativo);
}

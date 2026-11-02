package com.uoc.reservofi.rol.business.repository;

import com.uoc.reservofi.base.busines.repository.BaseEntidadRepository;
import com.uoc.reservofi.rol.business.model.Rol;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends BaseEntidadRepository<Rol> {

	@Query("SELECT id FROM Rol WHERE nombre=:nombre")
	Long getIdByNombre(@Param("nombre") String nombre);
}

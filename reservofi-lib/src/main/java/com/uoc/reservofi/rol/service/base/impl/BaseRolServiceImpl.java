package com.uoc.reservofi.rol.service.base.impl;

import com.uoc.reservofi.base.busines.repository.BaseEntidadRepository;
import com.uoc.reservofi.base.busines.service.base.impl.BaseEntidadActualizableServiceImpl;
import com.uoc.reservofi.rol.model.Rol;
import com.uoc.reservofi.rol.repository.RolRepository;
import com.uoc.reservofi.rol.service.base.BaseRolService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service(BaseRolService.BEAN_NAME)
@RequiredArgsConstructor
public class BaseRolServiceImpl extends BaseEntidadActualizableServiceImpl<Rol> implements BaseRolService {

    private final RolRepository rolRepository;

    @Override
    public BaseEntidadRepository<Rol> getRepository() {
        return rolRepository;
    }
}

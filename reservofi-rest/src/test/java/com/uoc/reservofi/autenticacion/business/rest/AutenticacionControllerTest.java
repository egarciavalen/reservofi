package com.uoc.reservofi.autenticacion.business.rest;

import com.uoc.reservofi.base.business.controller.RestControllerAdvice;
import com.uoc.reservofi.puesto.business.controller.PuestoController;
import com.uoc.reservofi.puesto.business.mapper.RestPuestoMapperImpl;
import com.uoc.reservofi.puesto.business.service.impl.RestPuestoServiceImpl;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;

@WebMvcTest()
@ContextConfiguration(classes = {PuestoController.class, RestPuestoMapperImpl.class, RestPuestoServiceImpl.class, RestControllerAdvice.class})
public class AutenticacionControllerTest {

	// TODO: VOLVER AQUÍ CUANDO ESTÉ IMPLEMENTADA LA AUTENTICACIÓN POR TOKEN JWT

}

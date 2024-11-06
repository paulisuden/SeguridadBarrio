package com.is.servidor_barrio.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.is.servidor_barrio.business.domain.entity.Empleado;
import com.is.servidor_barrio.business.logic.service.EmpleadoServiceImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/empleado")
public class EmpleadoController extends BaseControllerImpl<Empleado, EmpleadoServiceImpl> {

}

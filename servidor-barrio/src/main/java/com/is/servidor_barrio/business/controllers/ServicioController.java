package com.is.servidor_barrio.business.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.is.servidor_barrio.business.domain.entity.Servicio;
import com.is.servidor_barrio.business.logic.service.ServicioServiceImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/servicio")
public class ServicioController extends BaseControllerImpl<Servicio, ServicioServiceImpl> {

}
package com.is.servidor_barrio.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.is.servidor_barrio.business.domain.dto.servicio.ServicioCreateDto;
import com.is.servidor_barrio.business.domain.dto.servicio.ServicioDto;
import com.is.servidor_barrio.business.domain.entity.Servicio;
import com.is.servidor_barrio.business.facade.impl.ServicioFacadeImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/servicio")
public class ServicioController
        extends BaseControllerImpl<Servicio, ServicioDto, ServicioCreateDto, ServicioCreateDto, ServicioFacadeImpl> {

}
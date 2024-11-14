package com.is.servidor_barrio.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.is.servidor_barrio.business.domain.dto.inmueble.InmuebleCreateDto;
import com.is.servidor_barrio.business.domain.dto.inmueble.InmuebleDto;
import com.is.servidor_barrio.business.domain.entity.Inmueble;
import com.is.servidor_barrio.business.facade.impl.InmuebleFacadeImp;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/inmueble")
public class InmuebleController
    extends BaseControllerImpl<Inmueble, InmuebleDto, InmuebleCreateDto, InmuebleCreateDto, InmuebleFacadeImp> {
}

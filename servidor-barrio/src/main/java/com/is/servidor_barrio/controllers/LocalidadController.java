package com.is.servidor_barrio.controllers;

import com.is.servidor_barrio.business.domain.entity.Localidad;
import com.is.servidor_barrio.business.logic.service.LocalidadServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/localidad")
public class LocalidadController extends BaseControllerImpl<Localidad, LocalidadServiceImpl> {
}


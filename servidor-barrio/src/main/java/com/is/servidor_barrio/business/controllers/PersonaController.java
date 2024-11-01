package com.is.servidor_barrio.business.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.is.servidor_barrio.business.domain.entity.Persona;
import com.is.servidor_barrio.business.logic.service.PersonaServiceImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/persona")
public class PersonaController extends BaseControllerImpl<Persona, PersonaServiceImpl> {

}

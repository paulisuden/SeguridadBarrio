package com.is.servidor_barrio.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.is.servidor_barrio.business.domain.dto.persona.PersonaCreateDto;
import com.is.servidor_barrio.business.domain.dto.persona.PersonaDto;
import com.is.servidor_barrio.business.domain.entity.Persona;
import com.is.servidor_barrio.business.facade.impl.PersonaFacadeImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/persona")
public class PersonaController
    extends BaseControllerImpl<Persona, PersonaDto, PersonaCreateDto, PersonaCreateDto, PersonaFacadeImpl> {

}

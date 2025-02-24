package com.is.servidor_barrio.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.is.servidor_barrio.business.domain.dto.contacto.ContactoCreateDto;
import com.is.servidor_barrio.business.domain.dto.contacto.ContactoDto;
import com.is.servidor_barrio.business.domain.entity.Contacto;
import com.is.servidor_barrio.business.facade.impl.ContactoFacade;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/contacto")
public class ContactoController extends
        BaseControllerImpl<Contacto, ContactoDto, ContactoCreateDto, ContactoCreateDto, ContactoFacade> {

}
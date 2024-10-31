package com.is.servidor_barrio.business.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.is.servidor_barrio.business.domain.entity.Contacto;
import com.is.servidor_barrio.business.logic.service.ContactoServiceImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/contacto")
public class ContactoController extends BaseContollerImpl<Contacto, ContactoServiceImpl> {

}

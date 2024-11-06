package com.is.servidor_barrio.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.is.servidor_barrio.business.domain.entity.Pais;
import com.is.servidor_barrio.business.logic.service.PaisServiceImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/pais")
public class PaisController extends BaseControllerImpl<Pais, PaisServiceImpl> {

}

package com.is.servidor_barrio.controllers;

import com.is.servidor_barrio.business.domain.entity.Provincia;
import com.is.servidor_barrio.business.logic.service.ProvinciaServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/provincia")
public class ProvinciaController extends BaseControllerImpl<Provincia, ProvinciaServiceImpl>{

}

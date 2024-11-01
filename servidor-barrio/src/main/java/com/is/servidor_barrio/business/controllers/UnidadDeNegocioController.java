package com.is.servidor_barrio.business.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.is.servidor_barrio.business.domain.entity.UnidadDeNegocio;
import com.is.servidor_barrio.business.logic.service.UnidadDeNegocioServiceImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/unidadNegocio")
public class UnidadDeNegocioController extends BaseControllerImpl<UnidadDeNegocio, UnidadDeNegocioServiceImpl> {

}
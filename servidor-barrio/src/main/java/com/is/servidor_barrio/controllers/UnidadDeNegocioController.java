package com.is.servidor_barrio.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.is.servidor_barrio.business.domain.dto.unidadDeNegocio.UnidadDeNegocioCreateDto;
import com.is.servidor_barrio.business.domain.dto.unidadDeNegocio.UnidadDeNegocioDto;
import com.is.servidor_barrio.business.domain.entity.UnidadDeNegocio;
import com.is.servidor_barrio.business.facade.impl.UnidadDeNegocioFacadeImp;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/unidadNegocio")
public class UnidadDeNegocioController extends
    BaseControllerImpl<UnidadDeNegocio, UnidadDeNegocioDto, UnidadDeNegocioCreateDto, UnidadDeNegocioCreateDto, UnidadDeNegocioFacadeImp> {

}
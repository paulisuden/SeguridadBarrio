package com.is.servidor_barrio.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.is.servidor_barrio.business.domain.dto.visitante.VisitanteCreateDto;
import com.is.servidor_barrio.business.domain.dto.visitante.VisitanteDto;
import com.is.servidor_barrio.business.domain.entity.Visitante;
import com.is.servidor_barrio.business.facade.impl.VisitanteFacadeImp;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/visitante")
public class VisitanteController
    extends BaseControllerImpl<Visitante, VisitanteDto, VisitanteCreateDto, VisitanteCreateDto, VisitanteFacadeImp> {

}

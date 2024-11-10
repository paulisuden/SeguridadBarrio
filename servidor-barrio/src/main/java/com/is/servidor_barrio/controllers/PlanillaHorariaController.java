package com.is.servidor_barrio.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.is.servidor_barrio.business.domain.dto.planillaHoraria.PlanillaHorariaCreateDto;
import com.is.servidor_barrio.business.domain.dto.planillaHoraria.PlanillaHorariaDto;
import com.is.servidor_barrio.business.domain.entity.PlanillaHoraria;
import com.is.servidor_barrio.business.facade.impl.PlanillaHorariaFacadeImp;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/planilla")
public class PlanillaHorariaController
    extends
    BaseControllerImpl<PlanillaHoraria, PlanillaHorariaDto, PlanillaHorariaCreateDto, PlanillaHorariaCreateDto, PlanillaHorariaFacadeImp> {

}
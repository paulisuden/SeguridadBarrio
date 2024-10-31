package com.is.servidor_barrio.business.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.is.servidor_barrio.business.domain.entity.PlanillaHoraria;
import com.is.servidor_barrio.business.logic.service.PlanillaHorariaServiceImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/planilla")
public class PlanillaHorariaController extends BaseControllerImpl<PlanillaHoraria, PlanillaHorariaServiceImpl> {

}

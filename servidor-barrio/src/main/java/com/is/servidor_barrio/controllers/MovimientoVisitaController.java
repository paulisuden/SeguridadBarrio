package com.is.servidor_barrio.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.is.servidor_barrio.business.domain.entity.MovimientoVisita;
import com.is.servidor_barrio.business.logic.service.MovimientoVisitaServiceImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/movimientoVisita")
public class MovimientoVisitaController extends BaseControllerImpl<MovimientoVisita, MovimientoVisitaServiceImpl> {

}

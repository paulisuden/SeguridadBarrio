package com.is.servidor_barrio.controllers;

import com.is.servidor_barrio.business.domain.dto.movimientoVisita.MovimientoVisitaCreateDTO;
import com.is.servidor_barrio.business.domain.dto.movimientoVisita.MovimientoVisitaDTO;
import com.is.servidor_barrio.business.domain.entity.MovimientoVisita;
import com.is.servidor_barrio.business.facade.impl.MovimientoVisitaFacadeImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/movimientoVisita")
public class MovimientoVisitaController extends
    BaseControllerImpl<MovimientoVisita, MovimientoVisitaDTO, MovimientoVisitaCreateDTO, MovimientoVisitaCreateDTO, MovimientoVisitaFacadeImpl> {

}

package com.is.servidor_barrio.controllers;

import com.is.servidor_barrio.business.domain.dto.direccion.DireccionCreateDto;
import com.is.servidor_barrio.business.domain.dto.direccion.DireccionDto;
import com.is.servidor_barrio.business.domain.entity.Direccion;
import com.is.servidor_barrio.business.facade.impl.DireccionFacadeImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/direccion")
public class DireccionController extends
    BaseControllerImpl<Direccion, DireccionDto, DireccionCreateDto, DireccionCreateDto, DireccionFacadeImpl> {

}

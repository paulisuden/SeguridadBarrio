package com.is.servidor_barrio.controllers;

import org.springframework.web.bind.annotation.*;

import com.is.servidor_barrio.business.domain.dto.provincia.ProvinciaCreateDto;
import com.is.servidor_barrio.business.domain.dto.provincia.ProvinciaDto;
import com.is.servidor_barrio.business.domain.entity.Provincia;
import com.is.servidor_barrio.business.facade.impl.ProvinciaFacadeImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/provincia")
public class ProvinciaController extends
        BaseControllerImpl<Provincia, ProvinciaDto, ProvinciaCreateDto, ProvinciaCreateDto, ProvinciaFacadeImpl> {

}

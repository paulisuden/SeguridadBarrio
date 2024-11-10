package com.is.servidor_barrio.controllers;

import com.is.servidor_barrio.business.domain.dto.pais.PaisCreateDto;
import com.is.servidor_barrio.business.domain.dto.pais.PaisDto;
import com.is.servidor_barrio.business.domain.entity.Pais;
import com.is.servidor_barrio.business.facade.impl.PaisFacadeImp;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/pais")
public class PaisController extends BaseControllerImpl<Pais, PaisDto, PaisCreateDto, PaisCreateDto, PaisFacadeImp> {

}

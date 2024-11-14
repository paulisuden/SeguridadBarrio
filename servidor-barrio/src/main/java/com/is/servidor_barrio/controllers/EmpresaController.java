package com.is.servidor_barrio.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.is.servidor_barrio.business.domain.dto.empresa.EmpresaCreateDto;
import com.is.servidor_barrio.business.domain.dto.empresa.EmpresaDto;
import com.is.servidor_barrio.business.domain.entity.Empresa;
import com.is.servidor_barrio.business.facade.impl.EmpresaFacadeImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/empresa")
public class EmpresaController
        extends BaseControllerImpl<Empresa, EmpresaDto, EmpresaCreateDto, EmpresaCreateDto, EmpresaFacadeImpl> {

}
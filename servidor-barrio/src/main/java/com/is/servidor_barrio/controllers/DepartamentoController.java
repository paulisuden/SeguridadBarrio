package com.is.servidor_barrio.controllers;

import com.is.servidor_barrio.business.domain.dto.departamento.DepartamentoCreateDto;
import com.is.servidor_barrio.business.domain.dto.departamento.DepartamentoDto;
import com.is.servidor_barrio.business.domain.entity.Departamento;
import com.is.servidor_barrio.business.facade.impl.DepartamentoFacadeImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/departamento")
public class DepartamentoController extends
    BaseControllerImpl<Departamento, DepartamentoDto, DepartamentoCreateDto, DepartamentoCreateDto, DepartamentoFacadeImpl> {

}

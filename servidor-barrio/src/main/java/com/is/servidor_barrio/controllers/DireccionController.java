package com.is.servidor_barrio.controllers;

import com.is.servidor_barrio.business.domain.entity.Direccion;
import com.is.servidor_barrio.business.logic.service.DireccionServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/direccion")
public class DireccionController extends BaseControllerImpl<Direccion, DireccionServiceImpl> {
}

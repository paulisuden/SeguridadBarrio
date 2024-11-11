package com.is.servidor_barrio.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.is.servidor_barrio.business.domain.dto.imagen.ImagenCreateDto;
import com.is.servidor_barrio.business.domain.dto.imagen.ImagenDto;
import com.is.servidor_barrio.business.domain.entity.Imagen;
import com.is.servidor_barrio.business.facade.base.ImagenFacade;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/imagen")
public class ImagenController
                extends BaseControllerImpl<Imagen, ImagenDto, ImagenCreateDto, ImagenCreateDto, ImagenFacade> {

}
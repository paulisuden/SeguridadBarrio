package com.is.servidor_barrio.controllers;

import com.is.servidor_barrio.business.domain.entity.Provincia;
import com.is.servidor_barrio.business.logic.service.ProvinciaService;
import com.is.servidor_barrio.business.logic.service.ProvinciaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/provincia")
public class ProvinciaController extends BaseControllerImpl<Provincia, ProvinciaServiceImpl>{

    @Autowired
    private ProvinciaServiceImpl servicio;

    @PostMapping("")
    @Override
    public ResponseEntity<?> save(@RequestBody Provincia entity) {
        try {
            // ResponseEntity contiene el status de la respuesta
            return ResponseEntity.status(HttpStatus.OK).body(servicio.save(entity));
        } catch (Exception e) {
            // formato de respuesta json
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente màs tarde.\"}");
        }
    }

}

package com.is.servidor_barrio.controllers;

import com.is.servidor_barrio.business.domain.dto.movimientoVisita.MovimientoVisitaCreateDTO;
import com.is.servidor_barrio.business.domain.dto.movimientoVisita.MovimientoVisitaDTO;
import com.is.servidor_barrio.business.domain.entity.MovimientoVisita;
import com.is.servidor_barrio.business.facade.impl.MovimientoVisitaFacadeImpl;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/movimientoVisita")
public class MovimientoVisitaController extends
    BaseControllerImpl<MovimientoVisita, MovimientoVisitaDTO, MovimientoVisitaCreateDTO, MovimientoVisitaCreateDTO, MovimientoVisitaFacadeImpl> {

    @GetMapping("/listarPorInmueble/{id}")
    public ResponseEntity<?> listarPorInmuebleId(@PathVariable String id) {
        try {
            System.out.println(facade.listarPorInmuebleId(id));
            // ResponseEntity contiene el status de la respuesta
            return ResponseEntity.status(HttpStatus.OK).body(facade.listarPorInmuebleId(id));
        } catch (Exception e) {
            // formato de respuesta json
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Intente màs tarde.\"}");

        }
    }
}

package com.is.servidor_barrio.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.is.servidor_barrio.business.domain.dto.persona.PersonaCreateDto;
import com.is.servidor_barrio.business.domain.dto.persona.PersonaDto;
import com.is.servidor_barrio.business.domain.entity.Persona;
import com.is.servidor_barrio.business.facade.impl.PersonaFacadeImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/persona")
public class PersonaController
        extends BaseControllerImpl<Persona, PersonaDto, PersonaCreateDto, PersonaCreateDto, PersonaFacadeImpl> {

    @GetMapping("/empleados") // solicitudes HTTP GET
    // metodo que se ejecutará cuando se haga una solicitud GET a la ruta
    // especificada.
    public ResponseEntity<?> getAllEmpleados() {
        try {
            // ResponseEntity contiene el status de la respuesta
            return ResponseEntity.status(HttpStatus.OK).body(facade.findAllEmpleados());
        } catch (Exception e) {
            // formato de respuesta json
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Intente màs tarde.\"}");

        }
    }

    @GetMapping("/habitantes") // solicitudes HTTP GET
    // metodo que se ejecutará cuando se haga una solicitud GET a la ruta
    // especificada.
    public ResponseEntity<?> getAllHabitantes() {
        try {
            // ResponseEntity contiene el status de la respuesta
            return ResponseEntity.status(HttpStatus.OK).body(facade.findAllHabitantes());
        } catch (Exception e) {
            // formato de respuesta json
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Intente màs tarde.\"}");

        }
    }
}

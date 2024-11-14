package com.is.servidor_barrio.controllers;

import com.is.servidor_barrio.business.domain.entity.Habitante;
import com.is.servidor_barrio.business.domain.entity.Visitante;
import com.is.servidor_barrio.business.logic.service.EnvioCorreoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/email")
public class MailController {

    private final EnvioCorreoService emailService;

    public MailController(EnvioCorreoService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send-email")
    public ResponseEntity<?> sendEmail(@RequestParam Habitante habitante, @RequestParam Visitante visitante) {
        try {
            emailService.sendEmail(habitante, visitante);
            return new ResponseEntity<>(true, HttpStatus.OK);
        }catch (Exception e) {
            // formato de respuesta json
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Intente màs tarde.\"}");
        
        }
    }
}
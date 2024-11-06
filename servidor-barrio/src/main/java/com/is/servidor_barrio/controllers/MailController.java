package com.is.servidor_barrio.controllers;

import com.is.servidor_barrio.business.domain.entity.Habitante;
import com.is.servidor_barrio.business.domain.entity.Visitante;
import com.is.servidor_barrio.business.logic.service.EnvioCorreoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailController {

    private final EnvioCorreoService emailService;

    @Autowired
    public MailController(EnvioCorreoService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/send-email")
    public String sendEmail(@RequestParam Habitante habitante, @RequestParam Visitante visitante) {
        emailService.sendEmail(habitante, visitante);
        return "Email enviado a " + habitante.getApellido() + ", " + habitante.getNombre();
    }
}

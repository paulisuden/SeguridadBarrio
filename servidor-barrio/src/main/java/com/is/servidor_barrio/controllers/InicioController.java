package com.is.servidor_barrio.controllers;

import com.is.servidor_barrio.business.domain.entity.Usuario;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class InicioController {

    @GetMapping("/")
    public String index() {
        return "inicio.html";
    }

    //@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/inicio")
    public String inicio(HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuariosession");

        /*
        if (usuario != null) {
            if (usuario.getRol().toString().equals("ADMIN")) {
                return "redirect:/inicio";
            }else {
                return "inicio";
            }
        }else {
            return "index";
        } */
        return "inicio";
    }

    @GetMapping("/regresoPage")
    public String regreso() {
        return "redirect:/inicio";
    }
}
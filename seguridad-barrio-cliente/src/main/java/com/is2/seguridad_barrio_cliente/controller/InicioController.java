package com.is2.seguridad_barrio_cliente.controller;

import com.is2.seguridad_barrio_cliente.error.ErrorServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@Controller
public class InicioController {

    @GetMapping("/inicio")
    public String inicio(
            Model model,
            Authentication authentication,
            @RequestParam(name = "error", required = false) String error) throws Exception {
        try {
            if (error != null) {
                error = URLDecoder.decode(error, StandardCharsets.UTF_8);
                model.addAttribute("mensajeError", error);
            }
            return "redirect:/empresa/listarEmpresa";
        } catch (Exception e) {
            model.addAttribute("mensajeError", e.getMessage());
            return "redirect:/empresa/listarEmpresa";
        }
    }
}

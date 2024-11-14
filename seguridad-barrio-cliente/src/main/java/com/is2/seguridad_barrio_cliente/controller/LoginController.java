package com.is2.seguridad_barrio_cliente.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    private String viewLogin = "/login";
    private String redInicio = "redirect:/inicio";

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, ModelMap modelo) {

        if (error != null) {
            modelo.put("mensajeError", "Usuario o contraseña incorrecta");
        }
        return viewLogin;
    }

    @GetMapping("/login/send")
    public String loginForm(@RequestParam(value = "email") String email, @RequestParam(value = "password") String clave,
                            HttpSession session, ModelMap modelo) {

        return redInicio;
    }
}
package com.landing.landing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.landing.landing.error.ErrorServiceException;
import com.landing.landing.service.EmpresaService;
import com.landing.landing.service.NegocioService;

@Controller
public class LandingController {

    @Autowired
    private NegocioService negocioService;

    @Autowired
    private EmpresaService empresaService;

    @GetMapping("/")
    public String enter() {
        return "redirect:/landing";
    }

    @GetMapping("/landing")
    public String landing(Model model) {
        try {
            var negocios = negocioService.listar();
            model.addAttribute("negocios", negocios);
            model.addAttribute("cantidadNegocios", negocios.size());
            model.addAttribute("empresa", empresaService.buscarEmpresa());
        } catch (ErrorServiceException e) {
            return "error.html";
        }
        return "index.html";
    }
}

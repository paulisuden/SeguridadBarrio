package com.landing.landing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.landing.landing.error.ErrorServiceException;
import com.landing.landing.service.EmpresaService;
import com.landing.landing.service.NegocioService;

@Controller
@RequestMapping("/negocio")
public class NegocioController {

    @Autowired
    private NegocioService negocioService;

    @Autowired
    private EmpresaService empresaService;

    @GetMapping("/{id}")
    public String unidadDeNegocio(
            Model model,
            @PathVariable("id") String id) {
        try {
            var negocio = negocioService.buscar(id);
            var servicios = negocio.getServicios();
            model.addAttribute("negocio", negocio);
            model.addAttribute("servicios", servicios);
            model.addAttribute("empresa", empresaService.buscarEmpresa());
        } catch (ErrorServiceException e) {
            System.out.println(e.toString());
            return "error.html";
        }
        return "consultaUnidadDeNegocio.html";
    }
}

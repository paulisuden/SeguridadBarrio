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

    @Autowired
    private MovimientoVisitaController movimientoVisitaController;

    @Autowired
    private PlanillaHorariaController planillaHorariaController;

    @Autowired
    private EmpleadoController empleadoController;

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
            if (authentication != null) {

                boolean hasAdminRole = authentication.getAuthorities().stream()
                        .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));
                boolean hasPersonalRole = authentication.getAuthorities().stream()
                        .anyMatch(authority -> authority.getAuthority().equals("ROLE_PERSONAL"));
                boolean hasHabitanteRole = authentication.getAuthorities().stream()
                        .anyMatch(authority -> authority.getAuthority().equals("ROLE_HABITANTE"));
                if (hasPersonalRole) {
                    return "inicio";
                } else if (hasHabitanteRole) {
                    return "habitante/inicio";
                } else {
                    return "inicio";
                }
            } else {
                throw new ErrorServiceException("El usuario no se encuentra logueado");
            }
        } catch (Exception e) {
            model.addAttribute("mensajeError", e.getMessage());
            return "visita/listarMovimientoVisita";
        }
    }

    @GetMapping("/")
    public String ini2(Model model) throws Exception {
        return "redirect:/inicio";
    }
}

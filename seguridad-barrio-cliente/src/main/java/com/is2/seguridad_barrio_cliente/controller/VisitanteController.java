package com.is2.seguridad_barrio_cliente.controller;

import com.is2.seguridad_barrio_cliente.dto.VisitanteDTO;
import com.is2.seguridad_barrio_cliente.enumeration.TipoVisita;
import com.is2.seguridad_barrio_cliente.error.ErrorServiceException;
import com.is2.seguridad_barrio_cliente.service.VisitanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.net.Authenticator;
import java.util.List;

@Controller
@RequestMapping("/visitante")
public class VisitanteController {

    @Autowired
    private VisitanteService visitanteService;

    private String viewList = "visita/listarVisitante.html";
    private String redirectList = "redirect:/visitante/listarVisitante";
    private String viewEdit = "visita/editarVisitante.html";

    @GetMapping("/altaVisitante")
    public String alta(VisitanteDTO visitante, Model model, Authentication authentication) {

        visitante = new VisitanteDTO();
        if (authentication != null) {
            boolean hasHabitanteRole = authentication.getAuthorities().stream()
                    .anyMatch(authority -> authority.getAuthority().equals("ROLE_HABITANTE"));
            if (hasHabitanteRole) {
            }
        }

        model.addAttribute("visitante", visitante);
        model.addAttribute("isDisabled", false);

        return viewEdit;
    }

    @PostMapping("/baja")
    public String eliminarServicio(@RequestParam("id") String id, RedirectAttributes redirectAttributes, Model model) {

        try {

            visitanteService.eliminar(id);
            redirectAttributes.addFlashAttribute("msgExito", "Visitante #" + id + " eliminado correctamente.");
            return redirectList;

        } catch (ErrorServiceException e) {
            model.addAttribute("msgError", e.getMessage());
            return redirectList;
        }
    }

    @GetMapping("/modificar")
    public String modificar(@RequestParam String id, Model model) {

        try {

            VisitanteDTO visitante = visitanteService.buscar(id);
            model.addAttribute("visitante", visitante);
            model.addAttribute("isDisabled", false);

            return viewEdit;

        } catch (ErrorServiceException e) {
            model.addAttribute("msgError", e.getMessage());
            return viewList;
        }
    }

    @GetMapping("/consultar")
    public String consultar(@RequestParam String id, Model model) {

        try {

            VisitanteDTO visitante = visitanteService.buscar(id);
            model.addAttribute("visitante", visitante);
            model.addAttribute("isDisabled", true);

            return viewEdit;

        } catch (ErrorServiceException e) {
            model.addAttribute("msgError", e.getMessage());
            return viewList;
        }
    }

    @GetMapping("/listarVisitante")
    public String listarVisitante(Model model) {
        try {
            List<VisitanteDTO> listaVisitante = visitanteService.listar();
            model.addAttribute("listaVisitantes", listaVisitante);

        } catch (ErrorServiceException e) {
            model.addAttribute("msgError", e.getMessage());
        } catch (Exception e) {
            model.addAttribute("msgError", "Error de Sistema");
        }
        return viewList;
    }

    @PostMapping("/aceptarEditVisitante")
    public String aceptarEdit(@RequestParam(required = false, defaultValue = "0") String id,
            @RequestParam String nombre, @RequestParam String apellido,
            @RequestParam String numeroDeDocumento, @RequestParam TipoVisita tipoVisita,
            RedirectAttributes attributes, Model model) {
        try {

            if ("0".equals(id)) {

                visitanteService.crear(nombre, apellido, numeroDeDocumento, tipoVisita);
                attributes.addFlashAttribute("msgExito", "Visitante creado correctamente.");

            } else {

                visitanteService.modificar(id, nombre, apellido, numeroDeDocumento, tipoVisita);
                attributes.addFlashAttribute("msgExito", "Visitante #" + id + " editado correctamente.");

            }
            return redirectList;

        } catch (ErrorServiceException e) {
            return error(e.getMessage(), model, id, nombre, apellido, numeroDeDocumento, tipoVisita);

        } catch (Exception e) {
            return error("Error de Sistema", model, id, nombre, apellido, numeroDeDocumento, tipoVisita);
        }

    }

    @GetMapping("/cancelarEditVisitante")
    public String cancelarEdit() {

        return redirectList;
    }

    private String error(String mensaje, Model model, String id, String nombre, String apellido,
            String numeroDeDocumento,
            TipoVisita tipoVisita) {
        try {

            model.addAttribute("msgError", mensaje);
            if (id != "0") {
                model.addAttribute("visitante", visitanteService.buscar(id));
            } else {
                VisitanteDTO visitante = new VisitanteDTO();
                visitante.setNombre(nombre);
                model.addAttribute("visitante", visitante);
            }

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("msgError", "Error inesperado al procesar la solicitud.");
        }
        return viewEdit;
    }

}

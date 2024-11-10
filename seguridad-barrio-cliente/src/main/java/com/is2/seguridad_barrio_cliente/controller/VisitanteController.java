package com.is2.seguridad_barrio_cliente.controller;

import com.is2.seguridad_barrio_cliente.dto.VisitanteDTO;
import com.is2.seguridad_barrio_cliente.enumeration.TipoVisita;
import com.is2.seguridad_barrio_cliente.error.ErrorServiceException;
import com.is2.seguridad_barrio_cliente.service.VisitanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String alta(VisitanteDTO visitante, Model model) {

        visitante = new VisitanteDTO();

        model.addAttribute("visitante", visitante);
        model.addAttribute("isDisabled", false);

        return viewEdit;
    }

    @GetMapping("/baja")
    public String baja(@RequestParam Long id, RedirectAttributes attributes, Model model) {

        try {

            visitanteService.eliminar(id);
            attributes.addFlashAttribute("msgExito", "La acción fue realizada correctamente.");
            return redirectList;

        } catch (ErrorServiceException e) {
            model.addAttribute("msgError", e.getMessage());
            return redirectList;
        }
    }

    @GetMapping("/modificar")
    public String modificar(@RequestParam Long id, Model model) {

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
    public String consultar(@RequestParam long id, Model model) {

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
    public String aceptarEdit(@RequestParam(required = false, defaultValue = "0") Long id,
            @RequestParam String nombre, @RequestParam String apellido,
            @RequestParam String numeroDeDocumento, @RequestParam TipoVisita tipoVisita,
            RedirectAttributes attributes, Model model) {
        try {

            if (id == 0)
                visitanteService.crear(nombre, apellido, numeroDeDocumento, tipoVisita);
            else
                visitanteService.modificar(id, nombre, apellido, numeroDeDocumento, tipoVisita);

            attributes.addFlashAttribute("msgExito", "La acción fue realizada correctamente.");
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

    private String error(String mensaje, Model model, Long id, String nombre, String apellido, String numeroDeDocumento,
            TipoVisita tipoVisita) {
        try {

            model.addAttribute("msgError", mensaje);
            if (id != 0) {
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
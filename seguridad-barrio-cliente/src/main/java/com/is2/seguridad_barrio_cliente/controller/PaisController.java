package com.is2.seguridad_barrio_cliente.controller;

import com.is2.seguridad_barrio_cliente.dto.PaisDTO;
import com.is2.seguridad_barrio_cliente.error.ErrorServiceException;
import com.is2.seguridad_barrio_cliente.service.PaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/pais")
public class PaisController {

    @Autowired
    private PaisService paisService;

    private String viewList = "direccion/listarPais.html";
    private String redirectList = "redirect:/pais/listarPais";
    private String viewEdit = "direccion/editarPais.html";

    @GetMapping("/altaPais")
    public String alta(PaisDTO pais, Model model) {

        pais = new PaisDTO();
        pais.setNombre("");

        model.addAttribute("pais", pais);
        model.addAttribute("isDisabled", false);

        return viewEdit;
    }

    @PostMapping("/baja")
    public String eliminarServicio(@RequestParam("id") String id, RedirectAttributes redirectAttributes, Model model) {

        try {

            paisService.eliminar(id);
            redirectAttributes.addFlashAttribute("msgExito", "Pais #" + id + " eliminado correctamente.");
            return redirectList;

        } catch (ErrorServiceException e) {
            model.addAttribute("msgError", e.getMessage());
            return redirectList;
        }
    }

    @GetMapping("/modificar")
    public String modificar(@RequestParam String id, Model model) {

        try {

            PaisDTO pais = paisService.buscar(id);
            model.addAttribute("pais", pais);
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

            PaisDTO pais = paisService.buscar(id);
            model.addAttribute("pais", pais);
            model.addAttribute("isDisabled", true);

            return viewEdit;

        } catch (ErrorServiceException e) {
            model.addAttribute("msgError", e.getMessage());
            return viewList;
        }
    }

    @GetMapping("/listarPais")
    public String listarPais(Model model) {
        try {
            List<PaisDTO> listaPais = paisService.listar();
            model.addAttribute("listaPais", listaPais);
        } catch (ErrorServiceException e) {
            model.addAttribute("msgError", e.getMessage());
        } catch (Exception e) {
            model.addAttribute("msgError", "Error de Sistema");
        }
        return viewList;
    }

    @PostMapping("/aceptarEditPais")
    public String aceptarEdit(@RequestParam(required = false, defaultValue = "0") String id,

            @RequestParam String nombre,
            RedirectAttributes attributes, Model model) {
        try {

            if ("0".equals(id)) {

                paisService.crear(nombre);
                attributes.addFlashAttribute("msgExito", "Pais creado correctamente");

            } else {
                paisService.modificar(id, nombre);
                attributes.addFlashAttribute("msgExito", "Pais #" + id + " editado correctamente");

            }
            return redirectList;

        } catch (ErrorServiceException e) {
            return error(e.getMessage(), model, id, nombre);

        } catch (Exception e) {
            return error("Error de Sistema", model, id, nombre);
        }

    }

    @GetMapping("/cancelar")
    public String cancelarEdit() {

        return redirectList;
    }

    private String error(String mensaje, Model model, String id, String nombre) {

        try {

            model.addAttribute("msgError", mensaje);
            if (id != "0") {
                model.addAttribute("pais", paisService.buscar(id));
            } else {
                PaisDTO pais = new PaisDTO();
                pais.setNombre(nombre);
                model.addAttribute("pais", pais);
            }

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("msgError", "Error inesperado al procesar la solicitud.");
        }
        return viewEdit;
    }

}

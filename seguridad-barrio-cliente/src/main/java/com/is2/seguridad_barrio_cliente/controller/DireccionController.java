package com.is2.seguridad_barrio_cliente.controller;

import com.is2.seguridad_barrio_cliente.dto.LocalidadDTO;
import com.is2.seguridad_barrio_cliente.dto.DireccionDTO;
import com.is2.seguridad_barrio_cliente.error.ErrorServiceException;
import com.is2.seguridad_barrio_cliente.service.LocalidadService;
import com.is2.seguridad_barrio_cliente.service.DireccionService;
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
@RequestMapping("/direccion")
public class DireccionController {

    @Autowired
    private LocalidadService localidadService;
    @Autowired
    private DireccionService direccionService;

    private String viewList = "direccion/listarDireccion.html";
    private String redirectList = "redirect:/direccion/listarDireccion";
    private String viewEdit = "direccion/editarDireccion.html";

    @GetMapping("/altaDireccion")
    public String alta(DireccionDTO direccion, Model model) throws ErrorServiceException {

        direccion = new DireccionDTO();

        List<LocalidadDTO> localidades = localidadService.listar();
        model.addAttribute("direccion", direccion);
        model.addAttribute("localidades", localidades);
        model.addAttribute("isDisabled", false);

        return viewEdit;
    }

    @PostMapping("/baja")
    public String eliminarServicio(@RequestParam("id") Long id, RedirectAttributes redirectAttributes, Model model) {

        try {

            direccionService.eliminar(id);
            redirectAttributes.addFlashAttribute("msgExito", "Direccion #" + id + " eliminada correctamente.");
            return redirectList;

        } catch (ErrorServiceException e) {
            model.addAttribute("msgError", e.getMessage());
            return redirectList;
        }
    }

    @GetMapping("/modificar")
    public String modificar(@RequestParam Long id, Model model) {

        try {

            DireccionDTO direccion = direccionService.buscar(id);
            List<LocalidadDTO> localidades = localidadService.listar();
            model.addAttribute("direccion", direccion);
            model.addAttribute("localidades", localidades);
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

            DireccionDTO direccion = direccionService.buscar(id);
            List<LocalidadDTO> localidades = localidadService.listar();
            model.addAttribute("direccion", direccion);
            model.addAttribute("localidades", localidades);
            model.addAttribute("isDisabled", true);

            return viewEdit;

        } catch (ErrorServiceException e) {
            model.addAttribute("msgError", e.getMessage());
            return viewList;
        }
    }

    @GetMapping("/listarDireccion")
    public String listarDireccion(Model model) {
        try {
            List<DireccionDTO> listaDireccion = direccionService.listar();
            model.addAttribute("listaDireccion", listaDireccion);
            List<LocalidadDTO> listaLocalidad = localidadService.listar();
            model.addAttribute("listaLocalidad", listaLocalidad);
        } catch (ErrorServiceException e) {
            model.addAttribute("msgError", e.getMessage());
        } catch (Exception e) {
            model.addAttribute("msgError", "Error de Sistema");
        }
        return viewList;
    }

    @PostMapping("/aceptarEditDireccion")
    public String aceptarEdit(@RequestParam(required = false, defaultValue = "0") Long id,
            @RequestParam String calle, @RequestParam String numeracion, @RequestParam String barrio,
            @RequestParam String observacion, @RequestParam Long idLocalidad, RedirectAttributes attributes,
            Model model) {

        try {

            if (id == 0) {
                direccionService.crear(calle, numeracion, barrio, observacion, idLocalidad);
                attributes.addFlashAttribute("msgExito", "Direccion creada correctamente.");

            } else {
                direccionService.modificar(id, calle, numeracion, barrio, observacion, idLocalidad);
                attributes.addFlashAttribute("msgExito", "Direccion #" + id + " editada correctamente.");

            }

            return redirectList;

        } catch (ErrorServiceException e) {
            return error(e.getMessage(), model, id, calle, numeracion, barrio, observacion, idLocalidad);
        } catch (Exception e) {
            return error("Error de Sistema", model, id, calle, numeracion, barrio, observacion, idLocalidad);
        }

    }

    @GetMapping("/cancelarEditDireccion")
    public String cancelarEdit() {

        return redirectList;
    }

    private String error(String mensaje, Model model, Long id, String calle, String numeracion, String barrio,
            String observacion, Long idLocalidad) {
        try {
            model.addAttribute("msgError", mensaje);

            DireccionDTO direccion = new DireccionDTO();

            if (id != 0) {
                direccion = direccionService.buscar(id);
            } else {
                direccion.setCalle(calle);
                direccion.setBarrio(barrio);
                direccion.setNumeracion(numeracion);
                direccion.setObservacion(observacion);
                direccion.setLocalidadId(idLocalidad);
            }

            model.addAttribute("direccion", direccion);

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("msgError", "Error inesperado al procesar la solicitud.");
        }

        return viewEdit;
    }

}

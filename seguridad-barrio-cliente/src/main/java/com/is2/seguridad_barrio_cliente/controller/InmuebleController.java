package com.is2.seguridad_barrio_cliente.controller;

import com.is2.seguridad_barrio_cliente.dto.InmuebleDTO;
import com.is2.seguridad_barrio_cliente.dto.NegocioDTO;
import com.is2.seguridad_barrio_cliente.error.ErrorServiceException;
import com.is2.seguridad_barrio_cliente.service.InmuebleService;
import com.is2.seguridad_barrio_cliente.service.NegocioService;

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
@RequestMapping("/inmueble")
public class InmuebleController {

    @Autowired
    private InmuebleService inmuebleService;
    @Autowired
    private NegocioService unidadDeNegocioService;

    private String viewList = "inmueble/listarInmueble.html";
    private String redirectList = "redirect:/inmueble/listarInmueble";
    private String viewEdit = "inmueble/editarInmueble.html";

    @GetMapping("/altaInmueble")
    public String alta(InmuebleDTO inmueble, Model model) throws ErrorServiceException {

        inmueble = new InmuebleDTO();
        List<NegocioDTO> unidadesDeNegocio = unidadDeNegocioService.listar();
        model.addAttribute("unidadesDeNegocio", unidadesDeNegocio);
        model.addAttribute("inmueble", inmueble);
        model.addAttribute("isDisabled", false);

        return viewEdit;
    }

    @PostMapping("/baja")
    public String baja(@RequestParam("id") String id, RedirectAttributes redirectAttributes, Model model) {

        try {

            inmuebleService.eliminar(id);
            redirectAttributes.addFlashAttribute("msgExito", "Inmueble #" + id + " eliminado correctamente.");
            return redirectList;

        } catch (ErrorServiceException e) {
            model.addAttribute("msgError", e.getMessage());
            return redirectList;
        }
    }

    @GetMapping("/modificar")
    public String modificar(@RequestParam String id, Model model) {

        try {

            InmuebleDTO inmueble = inmuebleService.buscar(id);
            model.addAttribute("inmueble", inmueble);
            List<NegocioDTO> unidadesDeNegocio = unidadDeNegocioService.listar();
            model.addAttribute("unidadesDeNegocio", unidadesDeNegocio);
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

            InmuebleDTO inmueble = inmuebleService.buscar(id);
            List<NegocioDTO> unidadesDeNegocio = unidadDeNegocioService.listar();
            model.addAttribute("unidadesDeNegocio", unidadesDeNegocio);
            model.addAttribute("inmueble", inmueble);
            model.addAttribute("isDisabled", true);

            return viewEdit;

        } catch (ErrorServiceException e) {
            model.addAttribute("msgError", e.getMessage());
            return viewList;
        }
    }

    @GetMapping("/listarInmueble")
    public String listarInmueble(Model model) {
        try {
            List<InmuebleDTO> listaInmueble = inmuebleService.listar();
            model.addAttribute("listaInmueble", listaInmueble);
        } catch (ErrorServiceException e) {
            model.addAttribute("msgError", e.getMessage());
        } catch (Exception e) {
            model.addAttribute("msgError", "Error de Sistema");
        }
        return viewList;
    }

    @PostMapping("/aceptarEditInmueble")
    public String aceptarEdit(@RequestParam(required = false, defaultValue = "0") String id,
            @RequestParam String calle,
            @RequestParam String manzana,
            @RequestParam String numeracion,
            @RequestParam String idNegocio,
            RedirectAttributes attributes, Model model) {
        try {
            if ("0".equals(id)) {

                inmuebleService.crear(numeracion, calle, manzana, idNegocio);
                attributes.addFlashAttribute("msgExito", "Inmueble creado correctamente.");
            } else {
                inmuebleService.modificar(id, numeracion, calle, manzana, idNegocio);
                attributes.addFlashAttribute("msgExito", "Inmueble #" + id + " editado correctamente.");
            }
            return redirectList;

        } catch (ErrorServiceException e) {
            return error(e.getMessage(), model, id, numeracion, calle, manzana, idNegocio);

        } catch (Exception e) {

            return error("Error de Sistema", model, id, numeracion, calle, manzana, idNegocio);
        }
    }

    @GetMapping("/cancelar")
    public String cancelarEdit() {

        return redirectList;
    }

    private String error(
            String mensaje,
            Model model,
            String id,
            String numeracion,
            String calle,
            String manzana,
            String idNegocio) {

        try {

            model.addAttribute("msgError", mensaje);
            if (!"0".equals(id)) {
                model.addAttribute("inmueble", inmuebleService.buscar(id));
            } else {
                NegocioDTO unidadDeNegocio = unidadDeNegocioService.buscar(idNegocio);
                InmuebleDTO inmueble = new InmuebleDTO();
                inmueble.setCalle(calle);
                inmueble.setManzana(manzana);
                inmueble.setNumeracion(numeracion);
                inmueble.setIdUnidadDeNegocio(idNegocio);
                inmueble.setUnidadDeNegocio(unidadDeNegocio);
                model.addAttribute("inmueble", inmueble);
            }

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("msgError", "Error inesperado al procesar la solicitud.");
        }
        return viewEdit;
    }

}

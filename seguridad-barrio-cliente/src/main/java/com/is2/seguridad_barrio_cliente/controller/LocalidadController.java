package com.is2.seguridad_barrio_cliente.controller;

import com.is2.seguridad_barrio_cliente.dto.DepartamentoDTO;
import com.is2.seguridad_barrio_cliente.dto.LocalidadDTO;
import com.is2.seguridad_barrio_cliente.error.ErrorServiceException;
import com.is2.seguridad_barrio_cliente.service.DepartamentoService;
import com.is2.seguridad_barrio_cliente.service.LocalidadService;
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
@RequestMapping("/localidad")
public class LocalidadController {

    @Autowired
    private DepartamentoService departamentoService;
    @Autowired
    private LocalidadService localidadService;

    private String viewList = "direccion/listarLocalidad.html";
    private String redirectList = "redirect:/localidad/listarLocalidad";
    private String viewEdit = "direccion/editarLocalidad.html";

    @GetMapping("/altaLocalidad")
    public String alta(LocalidadDTO localidad, Model model) throws ErrorServiceException {

        localidad = new LocalidadDTO();

        List<DepartamentoDTO> departamentos = departamentoService.listar();
        model.addAttribute("localidad", localidad);
        model.addAttribute("departamentos", departamentos);
        model.addAttribute("isDisabled", false);

        return viewEdit;
    }

    @GetMapping("/baja")
    public String baja(@RequestParam String id, RedirectAttributes attributes, Model model) {

        try {

            localidadService.eliminar(id);
            attributes.addFlashAttribute("msgExito", "La acción fue realizada correctamente.");
            return redirectList;

        } catch (ErrorServiceException e) {
            model.addAttribute("msgError", e.getMessage());
            return redirectList;
        }
    }

    @PostMapping("/baja")
    public String eliminarServicio(@RequestParam("id") Long id, RedirectAttributes redirectAttributes, Model model) {

        try {

            localidadService.eliminar(id);
            redirectAttributes.addFlashAttribute("msgExito", "Localidad #" + id + " eliminada correctamente.");
            return redirectList;

        } catch (ErrorServiceException e) {
            model.addAttribute("msgError", e.getMessage());
            return redirectList;
        }
    }

    @GetMapping("/modificar")
    public String modificar(@RequestParam String id, Model model) {

        try {

            LocalidadDTO localidad = localidadService.buscar(id);
            List<DepartamentoDTO> departamentos = departamentoService.listar();
            model.addAttribute("localidad", localidad);
            model.addAttribute("departamentos", departamentos);
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

            LocalidadDTO localidad = localidadService.buscar(id);
            List<DepartamentoDTO> departamentos = departamentoService.listar();
            model.addAttribute("localidad", localidad);
            model.addAttribute("departamentos", departamentos);
            model.addAttribute("isDisabled", true);

            return viewEdit;

        } catch (ErrorServiceException e) {
            model.addAttribute("msgError", e.getMessage());
            return viewList;
        }
    }

    @GetMapping("/listarLocalidad")
    public String listarLocalidad(Model model) {
        try {
            List<LocalidadDTO> listaLocalidad = localidadService.listar();
            model.addAttribute("listaLocalidad", listaLocalidad);
            List<DepartamentoDTO> listaDepartamento = departamentoService.listar();
            model.addAttribute("listaDepartamento", listaDepartamento);
        } catch (ErrorServiceException e) {
            model.addAttribute("msgError", e.getMessage());
        } catch (Exception e) {
            model.addAttribute("msgError", "Error de Sistema");
        }
        return viewList;
    }

    @PostMapping("/aceptarEditLocalidad")
    public String aceptarEdit(@RequestParam(required = false, defaultValue = "0") String id,
            @RequestParam String nombre,
            @RequestParam String codigoPostal,
            @RequestParam String idDepartamento,
            RedirectAttributes attributes, Model model) {

        try {

            if ("0".equals(id)) {
                localidadService.crear(nombre, codigoPostal, idDepartamento);
                attributes.addFlashAttribute("msgExito", "Localidad creada correctamente.");

            } else {
                localidadService.modificar(id, nombre, codigoPostal, idDepartamento);
                attributes.addFlashAttribute("msgExito", "Localidad #" + id + " editada correctamente.");
            }

            return redirectList;

        } catch (ErrorServiceException e) {
            return error(e.getMessage(), model, id, nombre, idDepartamento);
        } catch (Exception e) {
            return error("Error de Sistema", model, id, nombre, idDepartamento);
        }

    }

    @GetMapping("/cancelarEditLocalidad")
    public String cancelarEdit() {

        return redirectList;
    }

    private String error(String mensaje, Model model, String id, String nombre, String idDepartamento) {
        try {
            model.addAttribute("msgError", mensaje);

            LocalidadDTO localidad = new LocalidadDTO();

            if (id != "0") {
                localidad = localidadService.buscar(id);
            } else {
                localidad.setNombre(nombre);
                localidad.setDepartamentoId(idDepartamento);
            }

            model.addAttribute("localidad", localidad);

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("msgError", "Error inesperado al procesar la solicitud.");
        }

        return viewEdit;
    }

}

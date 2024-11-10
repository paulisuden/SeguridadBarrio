package com.is2.seguridad_barrio_cliente.controller;

import com.is2.seguridad_barrio_cliente.dto.ProvinciaDTO;
import com.is2.seguridad_barrio_cliente.dto.DepartamentoDTO;
import com.is2.seguridad_barrio_cliente.error.ErrorServiceException;
import com.is2.seguridad_barrio_cliente.service.ProvinciaService;
import com.is2.seguridad_barrio_cliente.service.DepartamentoService;
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
@RequestMapping("/departamento")
public class DepartamentoController {

    @Autowired
    private DepartamentoService departamentoService;
    @Autowired
    private ProvinciaService provinciaService;

    private String viewList = "direccion/listarDepartamento.html";
    private String redirectList = "redirect:/departamento/listarDepartamento";
    private String viewEdit = "direccion/editarDepartamento.html";

    @GetMapping("/altaDepartamento")
    public String alta(DepartamentoDTO departamento, Model model) throws ErrorServiceException {

        departamento = new DepartamentoDTO();

        List<ProvinciaDTO> provincias = provinciaService.listar();
        model.addAttribute("departamento", departamento);
        model.addAttribute("provincias", provincias);
        model.addAttribute("isDisabled", false);

        return viewEdit;
    }

    @GetMapping("/baja")
    public String baja(@RequestParam Long id, RedirectAttributes attributes, Model model) {

        try {

            departamentoService.eliminar(id);
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

            DepartamentoDTO departamento = departamentoService.buscar(id);
            List<ProvinciaDTO> provincias = provinciaService.listar();
            model.addAttribute("departamento", departamento);
            model.addAttribute("provincias", provincias);
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

            DepartamentoDTO departamento = departamentoService.buscar(id);
            List<ProvinciaDTO> provincias = provinciaService.listar();
            model.addAttribute("departamento", departamento);
            model.addAttribute("provincias", provincias);
            model.addAttribute("isDisabled", true);

            return viewEdit;

        } catch (ErrorServiceException e) {
            model.addAttribute("msgError", e.getMessage());
            return viewList;
        }
    }

    @GetMapping("/listarDepartamento")
    public String listarDepartamento(Model model) {
        try {
            List<DepartamentoDTO> listaDepartamento = departamentoService.listar();
            model.addAttribute("listaDepartamento", listaDepartamento);
            List<ProvinciaDTO> listaProvincia = provinciaService.listar();
            model.addAttribute("listaProvincia", listaProvincia);
        } catch (ErrorServiceException e) {
            model.addAttribute("msgError", e.getMessage());
        } catch (Exception e) {
            model.addAttribute("msgError", "Error de Sistema");
        }
        return viewList;
    }

    @PostMapping("/aceptarEditDepartamento")
    public String aceptarEdit(@RequestParam(required = false, defaultValue = "0") Long id,
            @RequestParam String nombre,
            @RequestParam Long idProvincia,
            RedirectAttributes attributes, Model model) {

        try {

            if (id == 0) {
                departamentoService.crear(nombre, idProvincia);
            } else {
                departamentoService.modificar(id, nombre, idProvincia);
            }

            attributes.addFlashAttribute("msgExito", "La acción fue realizada correctamente.");
            return redirectList;

        } catch (ErrorServiceException e) {
            return error(e.getMessage(), model, id, nombre, idProvincia);
        } catch (Exception e) {
            return error("Error de Sistema", model, id, nombre, idProvincia);
        }

    }

    @GetMapping("/cancelarEditDepartamento")
    public String cancelarEdit() {

        return redirectList;
    }

    private String error(String mensaje, Model model, Long id, String nombre, Long idProvincia) {
        try {
            model.addAttribute("msgError", mensaje);

            DepartamentoDTO departamento = new DepartamentoDTO();

            if (id != 0) {
                departamento = departamentoService.buscar(id);
            } else {
                departamento.setNombre(nombre);
                departamento.setProvinciaId(idProvincia);
            }

            model.addAttribute("departamento", departamento);

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("msgError", "Error inesperado al procesar la solicitud.");
        }

        return viewEdit;
    }

}
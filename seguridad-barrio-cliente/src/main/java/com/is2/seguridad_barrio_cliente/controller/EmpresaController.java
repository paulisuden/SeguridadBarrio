package com.is2.seguridad_barrio_cliente.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.is2.seguridad_barrio_cliente.dto.DireccionDTO;
import com.is2.seguridad_barrio_cliente.dto.EmpresaDTO;
import com.is2.seguridad_barrio_cliente.error.ErrorServiceException;
import com.is2.seguridad_barrio_cliente.service.DireccionService;
import com.is2.seguridad_barrio_cliente.service.EmpresaService;

@Controller
@RequestMapping("/empresa")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private DireccionService direccionService;

    private String viewList = "listarEmpresa.html";
    private String redirectList = "redirect:/listarEmpresa";
    private String viewEdit = "editarEmpresa.html";

    @GetMapping("/altaEmpresa")
    public String alta(
            EmpresaDTO empresa,
            Model model) throws ErrorServiceException {

        empresa = new EmpresaDTO();
        empresa.setNombre("");
        empresa.setDescripcion("");
        List<DireccionDTO> direcciones = direccionService.listar();
        model.addAttribute("direcciones", direcciones);
        model.addAttribute("empresa", empresa);
        model.addAttribute("isDisabled", false);

        return viewEdit;
    }

    @PostMapping("/baja")
    public String eliminarServicio(
            @RequestParam("id") String id,
            RedirectAttributes redirectAttributes,
            Model model) {

        try {

            empresaService.eliminar(id);
            redirectAttributes.addFlashAttribute("msgExito", "Empresa #" + id + " eliminada correctamente.");
            return redirectList;

        } catch (ErrorServiceException e) {
            model.addAttribute("msgError", e.getMessage());
            return redirectList;
        }
    }

    @GetMapping("/modificar")
    public String modificar(
            @RequestParam String id,
            Model model) {

        try {
            EmpresaDTO empresa = empresaService.buscar(id);
            List<DireccionDTO> direcciones = direccionService.listar();
            model.addAttribute("empresa", empresa);
            model.addAttribute("direcciones", direcciones);
            model.addAttribute("isDisabled", false);
            return viewEdit;
        } catch (ErrorServiceException e) {
            model.addAttribute("msgError", e.getMessage());
            return viewList;
        }
    }

    @GetMapping("/consultar")
    public String consultar(
            @RequestParam String id,
            Model model) {
        try {
            EmpresaDTO empresa = empresaService.buscar(id);
            List<DireccionDTO> direcciones = direccionService.listar();
            model.addAttribute("empresa", empresa);
            model.addAttribute("direcciones", direcciones);
            model.addAttribute("isDisabled", true);
            return viewEdit;
        } catch (ErrorServiceException e) {
            model.addAttribute("msgError", e.getMessage());
            return viewList;
        }
    }

    @GetMapping("/listarEmpresa")
    public String listarEmpresa(Model model) {
        try {
            List<EmpresaDTO> listaEmpresa = empresaService.listar();
            if (listaEmpresa.size() >= 1) {
                model.addAttribute("empresa", listaEmpresa.get(0));
            } else {
                // Se crea una empresa vacia
                EmpresaDTO empresa = empresaService.crear(
                        "Sin nombre",
                        "Sin descipcion",
                        null,
                        null);
                model.addAttribute("empresa", empresa);
            }

        } catch (ErrorServiceException e) {
            model.addAttribute("msgError", e.getMessage());
        } catch (Exception e) {
            model.addAttribute("msgError", "Error de Sistema");
        }
        return viewList;
    }

    @PostMapping("/aceptarEditEmpresa")
    public String aceptarEdit(
            @RequestParam MultipartFile archivo,
            @RequestParam(required = false, defaultValue = "0") String id,
            @RequestParam String nombre,
            @RequestParam String descripcion,
            @RequestParam String idDireccion,
            RedirectAttributes attributes, Model model) {
        try {

            if ("0".equals(id)) {
                empresaService.crear(nombre, descripcion, idDireccion, archivo);
                attributes.addFlashAttribute("msgExito", "Empresa creada correctamente");
            } else {
                empresaService.modificar(id, nombre, descripcion, idDireccion, archivo);
                attributes.addFlashAttribute("msgExito", "Empresa #" + id + " editada correctamente");
            }
            return redirectList;

        } catch (ErrorServiceException e) {
            return error(e.getMessage(), model, id, nombre, descripcion, idDireccion);

        } catch (Exception e) {
            return error("Error de Sistema", model, id, nombre, descripcion, idDireccion);
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
            String nombre,
            String descripcion,
            String direccionId) {
        try {

            model.addAttribute("msgError", mensaje);
            if (id != "0") {
                model.addAttribute("empresa", empresaService.buscar(id));
            } else {
                EmpresaDTO empresa = new EmpresaDTO();
                empresa.setNombre(nombre);
                empresa.setDescripcion(descripcion);
                empresa.setDireccionId(direccionId);
                model.addAttribute("empresa", empresa);
            }

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("msgError", "Error inesperado al procesar la solicitud.");
        }
        return viewEdit;
    }

}
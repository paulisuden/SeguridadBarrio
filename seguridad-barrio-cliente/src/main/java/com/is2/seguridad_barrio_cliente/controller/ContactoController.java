package com.is2.seguridad_barrio_cliente.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.is2.seguridad_barrio_cliente.dto.ContactoDTO;
import com.is2.seguridad_barrio_cliente.enumeration.TipoContacto;
import com.is2.seguridad_barrio_cliente.enumeration.TipoTelefono;
import com.is2.seguridad_barrio_cliente.error.ErrorServiceException;
import com.is2.seguridad_barrio_cliente.service.ContactoService;

@Controller
@RequestMapping("/contacto")
public class ContactoController {

    @Autowired
    private ContactoService contactoService;

    private String viewList = "direccion/listarContacto.html";
    private String redirectList = "redirect:/contacto/listarContacto";
    private String viewEdit = "direccion/editarContacto.html";

    @GetMapping("/altaContacto")
    public String alta(ContactoDTO contacto, Model model) {
        contacto = new ContactoDTO();

        cargarContenido(contacto, model);
        model.addAttribute("contacto", contacto);
        model.addAttribute("isDisabled", false);
        return viewEdit;
    }

    @PostMapping("/baja")
    public String baja(@RequestParam("id") String id, RedirectAttributes redirectAttributes, Model model) {
        try {
            contactoService.eliminar(id);
            redirectAttributes.addFlashAttribute("msgExito", "Contacto #" + id + " eliminado correctamente");
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
            ContactoDTO contacto = contactoService.buscar(id);
            cargarContenido(contacto, model);

            model.addAttribute("contacto", contacto);
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
            ContactoDTO contacto = contactoService.buscar(id);
            model.addAttribute("contacto", contacto);
            model.addAttribute("isDisabled", true);
            return viewEdit;

        } catch (ErrorServiceException e) {
            model.addAttribute("msgError", e.getMessage());
            return viewList;
        }
    }

    @GetMapping("/listarContacto")
    public String listarcontacto(Model model) {
        try {
            List<ContactoDTO> listacontacto = contactoService.listar();
            model.addAttribute("listaContacto", listacontacto);
        } catch (ErrorServiceException e) {
            model.addAttribute("msgError", e.getMessage());
        } catch (Exception e) {
            model.addAttribute("msgError", "Error de Sistema");
        }
        return viewList;
    }

    @PostMapping("/aceptarEditContacto")
    public String aceptarEdit(
            @RequestParam(required = false, defaultValue = "0") String id,
            @RequestParam TipoContacto tipoContacto,
            @RequestParam String observacion,
            @RequestParam(required = false) String email,
            @RequestParam(required = false, defaultValue = "") String telefono,
            @RequestParam(required = false) TipoTelefono tipoTelefono,
            RedirectAttributes attributes, Model model) {
        try {
            if ("0".equals(id)) {
                contactoService.crear(
                        tipoContacto,
                        observacion,
                        email,
                        telefono,
                        tipoTelefono);
                attributes.addFlashAttribute("msgExito", "Contacto creado correctamente");
            }

            else {
                contactoService.modificar(
                        id,
                        tipoContacto,
                        observacion,
                        email,
                        telefono,
                        tipoTelefono);

                attributes.addFlashAttribute("msgExito", "Contacto #" + id + " editado correctamente");
            }

            return redirectList;

        } catch (ErrorServiceException e) {
            return error(e.getMessage(), model, id, tipoContacto, observacion, email, telefono, tipoTelefono);

        } catch (Exception e) {
            return error("Error de sistema", model, id, tipoContacto, observacion, email, telefono, tipoTelefono);
        }

    }

    @GetMapping("/cancelarEditContacto")
    public String cancelarEdit() {

        return redirectList;
    }

    private void cargarContenido(ContactoDTO contacto, Model model) {

        boolean hasEmail = contacto.getEmail() != null && !contacto.getEmail().isEmpty();
        boolean hasPhone = contacto.getTelefono() != null && !contacto.getTelefono().isEmpty();
        String metodoContacto = hasEmail || !hasPhone ? "email" : "telefono";
        model.addAttribute("metodoContacto", metodoContacto);

        model.addAttribute("tiposContacto", List.of(TipoContacto.values()));
        model.addAttribute("tiposTelefono", List.of(TipoTelefono.values()));
    }

    private String error(
            String mensaje,
            Model model,
            String id,
            TipoContacto tipo,
            String observacion,
            String email,
            String telefono,
            TipoTelefono tipoTelefono) {
        try {
            model.addAttribute("msgError", mensaje);
            if (!"0".equals(id)) {
                model.addAttribute("contacto", contactoService.buscar(id));
            } else {
                ContactoDTO contacto = new ContactoDTO();
                contacto.setTipoContacto(tipo);
                contacto.setObservacion(observacion);
                contacto.setEmail(email);
                contacto.setTelefono(telefono);
                contacto.setTipoTelefono(tipoTelefono);
                model.addAttribute("contacto", contacto);
            }

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("msgError", "Error inesperado al procesar la solicitud.");
        }
        return viewEdit;
    }

}

package com.is2.seguridad_barrio_cliente.controller;

import com.is2.seguridad_barrio_cliente.dto.ImagenDTO;
import com.is2.seguridad_barrio_cliente.dto.ServicioDTO;
import com.is2.seguridad_barrio_cliente.error.ErrorServiceException;
import com.is2.seguridad_barrio_cliente.service.ServicioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/servicio")
public class ServicioController {

    @Autowired
    private ServicioService servicioService;

    private String viewList = "servicio/listarServicio.html";
    private String redirectList = "redirect:/servicio/listarServicio";
    private String viewEdit = "servicio/editarServicio.html";

    @GetMapping("/altaServicio")
    public String alta(ServicioDTO servicio, Model model) {

        servicio = new ServicioDTO();
        servicio.setNombre("");

        model.addAttribute("servicio", servicio);
        model.addAttribute("isDisabled", false);

        return viewEdit;
    }

    @GetMapping("/baja")
    public String baja(@RequestParam String id, RedirectAttributes attributes, Model model) {

        try {

            servicioService.eliminar(id);
            attributes.addFlashAttribute("msgExito", "La acción fue realizada correctamente.");
            return redirectList;

        } catch (ErrorServiceException e) {
            model.addAttribute("msgError", e.getMessage());
            return redirectList;
        }
    }

    @GetMapping("/modificar")
    public String modificar(@RequestParam String id, Model model) {

        try {

            ServicioDTO servicio = servicioService.buscar(id);
            model.addAttribute("servicio", servicio);
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

            ServicioDTO servicio = servicioService.buscar(id);
            model.addAttribute("servicio", servicio);
            model.addAttribute("isDisabled", true);

            return viewEdit;

        } catch (ErrorServiceException e) {
            model.addAttribute("msgError", e.getMessage());
            return viewList;
        }
    }

    @GetMapping("/listarServicio")
    public String listarServicio(Model model) {
        try {
            List<ServicioDTO> listaservicio = servicioService.listar();
            model.addAttribute("listaServicio", listaservicio);
        } catch (ErrorServiceException e) {
            model.addAttribute("msgError", e.getMessage());
        } catch (Exception e) {
            model.addAttribute("msgError", "Error de Sistema");
        }
        return viewList;
    }

    @PostMapping("/aceptarEditServicio")
    public String aceptarEdit(
            @RequestParam MultipartFile archivo,
            @RequestParam(required = false, defaultValue = "0") String id,
            @RequestParam String nombre,
            RedirectAttributes attributes, Model model) {
        try {
            if ("0".equals(id))
                servicioService.crear(nombre, archivo);
            else
                servicioService.modificar(id, nombre, archivo);

            attributes.addFlashAttribute("msgExito", "La acción fue realizada correctamente.");
            return redirectList;

        } catch (ErrorServiceException e) {
            return error(e.getMessage(), model, id, nombre);

        } catch (Exception e) {
            return error("Error de Sistema. " + e.toString(), model, id, nombre);
        }

    }

    @GetMapping("/cancelarEditServicio")
    public String cancelarEdit() {

        return redirectList;
    }

    private String error(String mensaje, Model model, String id, String nombre) {
        try {

            model.addAttribute("msgError", mensaje);
            if (id != "0") {
                model.addAttribute("servicio", servicioService.buscar(id));
            } else {
                ServicioDTO servicio = new ServicioDTO();
                servicio.setNombre(nombre);
                model.addAttribute("servicio", servicio);
            }

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("msgError", "Error inesperado al procesar la solicitud.");
        }
        return viewEdit;
    }

    @GetMapping("/imagen/{id}")
    public ResponseEntity<byte[]> fotoServicio(
            @PathVariable String id,
            Model model) {

        try {
            ServicioDTO servicio = servicioService.buscar(id);
            if (servicio.getImagen() == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            byte[] imgContenido = servicio.getImagen().getContenido();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity<>(imgContenido, headers, HttpStatus.OK);

        } catch (Exception ex) {
            model.addAttribute("msgError", "Error inesperado al procesar la solicitud.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

}

package com.is2.seguridad_barrio_cliente.controller;

import com.is2.seguridad_barrio_cliente.dto.PaisDTO;
import com.is2.seguridad_barrio_cliente.dto.ProvinciaDTO;
import com.is2.seguridad_barrio_cliente.error.ErrorServiceException;
import com.is2.seguridad_barrio_cliente.service.PaisService;
import com.is2.seguridad_barrio_cliente.service.ProvinciaService;
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
@RequestMapping("/provincia")
public class ProvinciaController {

    @Autowired
    private ProvinciaService provinciaService;
    @Autowired
    private PaisService paisService;

    private String viewList = "direccion/listarProvincia.html";
    private String redirectList = "redirect:/provincia/listarProvincia";
    private String viewEdit = "direccion/editarProvincia.html";

    @GetMapping("/altaProvincia")
    public String alta(ProvinciaDTO provincia, Model model) throws ErrorServiceException {

        provincia = new ProvinciaDTO();

        List<PaisDTO> paises = paisService.listar();

        model.addAttribute("provincia", provincia);
        model.addAttribute("paises", paises);
        model.addAttribute("isDisabled", false);

        return viewEdit;
    }

    @GetMapping("/baja")
    public String baja(@RequestParam Long id, RedirectAttributes attributes, Model model) {

        try {

            provinciaService.eliminar(id);
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

            ProvinciaDTO provincia = provinciaService.buscar(id);
            List<PaisDTO> paises = paisService.listar();
            model.addAttribute("provincia", provincia);
            model.addAttribute("paises", paises);
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

            ProvinciaDTO provincia = provinciaService.buscar(id);
            List<PaisDTO> paises = paisService.listar();
            model.addAttribute("provincia", provincia);
            model.addAttribute("paises", paises);
            model.addAttribute("isDisabled", true);

            return viewEdit;

        } catch (ErrorServiceException e) {
            model.addAttribute("msgError", e.getMessage());
            return viewList;
        }
    }

    @GetMapping("/listarProvincia")
    public String listarProvincia(Model model) {
        try {
            List<ProvinciaDTO> listaProvincia = provinciaService.listar();
            model.addAttribute("listaProvincia", listaProvincia);
            List<PaisDTO> listaPais = paisService.listar();
            model.addAttribute("listaPais", listaPais);
        } catch (ErrorServiceException e) {
            model.addAttribute("msgError", e.getMessage());
        } catch (Exception e) {
            model.addAttribute("msgError", "Error de Sistema");
        }
        return viewList;
    }

    @PostMapping("/aceptarEditProvincia")
    public String aceptarEdit(@RequestParam(required = false, defaultValue = "0") Long id,
                              @RequestParam String nombre,
                              @RequestParam Long idPais,
                              RedirectAttributes attributes, Model model) {

        try {

            System.out.println(idPais);

            if (id == 0) {
                provinciaService.crear(nombre,idPais);
            }else{
                provinciaService.modificar(id, nombre, idPais);
            }

            attributes.addFlashAttribute("msgExito", "La acción fue realizada correctamente.");
            return redirectList;

        } catch (ErrorServiceException e) {
            return error(e.getMessage(), model, id, nombre, idPais);
        } catch (Exception e) {
            return error("Error de Sistema", model, id, nombre, idPais);
        }

    }

    @GetMapping("/cancelarEditProvincia")
    public String cancelarEdit() {

        return redirectList;
    }

    private String error(String mensaje, Model model, Long id, String nombre, Long idPais) {
        try {
            model.addAttribute("msgError", mensaje);

            ProvinciaDTO provincia = new ProvinciaDTO();

            if (id != 0) {
                provincia = provinciaService.buscar(id);
            } else {
                provincia.setNombre(nombre);
                PaisDTO pais = paisService.buscar(idPais);
                provincia.setPaisDTO(pais);
            }

            model.addAttribute("provincia", provincia);

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("msgError", "Error inesperado al procesar la solicitud.");
        }

        return viewEdit;
    }

}

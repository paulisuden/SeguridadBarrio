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

import com.is2.seguridad_barrio_cliente.dto.DireccionDTO;
import com.is2.seguridad_barrio_cliente.dto.NegocioDTO;
import com.is2.seguridad_barrio_cliente.dto.ServicioDTO;
import com.is2.seguridad_barrio_cliente.error.ErrorServiceException;
import com.is2.seguridad_barrio_cliente.service.DireccionService;
import com.is2.seguridad_barrio_cliente.service.NegocioService;
import com.is2.seguridad_barrio_cliente.service.ServicioService;

@Controller
@RequestMapping("/negocio")
public class NegocioController {

  @Autowired
  private NegocioService negocioService;

  @Autowired
  private ServicioService servicioService;

  @Autowired
  private DireccionService direccionService;

  private String viewList = "unidadDeNegocio/listarNegocio.html";
  private String redirectList = "redirect:/negocio/listarNegocio";
  private String viewEdit = "unidadDeNegocio/editarNegocio.html";

  @GetMapping("/altaNegocio")
  public String alta(NegocioDTO negocio, Model model) throws ErrorServiceException {
    negocio = new NegocioDTO();
    List<DireccionDTO> direcciones = direccionService.listar();
    List<ServicioDTO> servicios = servicioService.listar();
    model.addAttribute("negocio", negocio);
    model.addAttribute("direcciones", direcciones);
    model.addAttribute("servicios", servicios);
    model.addAttribute("isDisabled", false);

    return viewEdit;
  }

  @GetMapping("/baja")
  public String baja(@RequestParam String id, RedirectAttributes attributes, Model model) {
    try {
      negocioService.eliminar(id);
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
      NegocioDTO negocio = negocioService.buscar(id);
      List<DireccionDTO> direcciones = direccionService.listar();
      List<ServicioDTO> servicios = servicioService.listar();
      model.addAttribute("negocio", negocio);
      model.addAttribute("direcciones", direcciones);
      model.addAttribute("servicios", servicios);
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
      NegocioDTO negocio = negocioService.buscar(id);
      List<DireccionDTO> direcciones = direccionService.listar();
      List<ServicioDTO> servicios = servicioService.listar();

      model.addAttribute("negocio", negocio);
      model.addAttribute("direcciones", direcciones);
      model.addAttribute("servicios", servicios);
      model.addAttribute("isDisabled", true);

      return viewEdit;
    } catch (ErrorServiceException e) {
      model.addAttribute("msgError", e.getMessage());
      return viewList;
    }
  }

  @GetMapping("/listarNegocio")
  public String listarNegocio(Model model) {
    try {
      List<NegocioDTO> listaNegocio = negocioService.listar();
      model.addAttribute("listaNegocio", listaNegocio);
    } catch (ErrorServiceException e) {
      model.addAttribute("msgError", e.getMessage());
    } catch (Exception e) {
      model.addAttribute("msgError", "Error de Sistema");
    }
    return viewList;
  }

  @PostMapping("/aceptarEditNegocio")
  public String aceptarEdit(@RequestParam(required = false, defaultValue = "0") String id,
      @RequestParam String nombre,
      @RequestParam String idDireccion,
      @RequestParam(name = "idServicio[]", required = false) List<String> idServicios,
      RedirectAttributes attributes, Model model) {
    try {
      if ("0".equals(id)) {
        negocioService.crear(nombre, idDireccion, idServicios);
      } else {
        negocioService.modificar(id, nombre, idDireccion, idServicios);
      }
      attributes.addFlashAttribute("msgExito", "La acción fue realizada correctamente.");
      return redirectList;
    } catch (ErrorServiceException e) {
      model.addAttribute("msgError", e.getMessage());
      return viewEdit;
    } catch (Exception e) {
      model.addAttribute("msgError", "Error de Sistema");
      return viewEdit;
    }
  }

  @GetMapping("/cancelarEditNegocio")
  public String cancelarEdit() {
    return redirectList;
  }
}

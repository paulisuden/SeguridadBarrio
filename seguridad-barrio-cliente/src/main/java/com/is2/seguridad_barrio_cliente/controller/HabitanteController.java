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

import com.is2.seguridad_barrio_cliente.dto.InmuebleDTO;
import com.is2.seguridad_barrio_cliente.dto.PersonaDTO;
import com.is2.seguridad_barrio_cliente.error.ErrorServiceException;
import com.is2.seguridad_barrio_cliente.service.HabitanteService;
import com.is2.seguridad_barrio_cliente.service.InmuebleService;

@Controller
@RequestMapping("/habitante")
public class HabitanteController {

  @Autowired
  private HabitanteService habitanteService;
  @Autowired
  private InmuebleService inmuebleService;

  private String viewList = "persona/listarHabitante.html";
  private String redirectList = "redirect:/habitante/listarHabitante";
  private String viewEdit = "persona/editarHabitante.html";

  @GetMapping("/altaHabitante")
  public String alta(PersonaDTO habitante, Model model) throws ErrorServiceException {

    habitante = new PersonaDTO();

    List<InmuebleDTO> inmuebles = inmuebleService.listar();

    model.addAttribute("habitante", habitante);
    model.addAttribute("inmuebles", inmuebles);
    model.addAttribute("isDisabled", false);

    return viewEdit;
  }

  @GetMapping("/baja")
  public String baja(@RequestParam String id, RedirectAttributes attributes, Model model) {

    try {

      habitanteService.eliminar(id);
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

      PersonaDTO habitante = habitanteService.buscar(id);

      List<InmuebleDTO> inmuebles = inmuebleService.listar();

      model.addAttribute("habitante", habitante);
      model.addAttribute("inmuebles", inmuebles);
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

      PersonaDTO habitante = habitanteService.buscar(id);

      List<InmuebleDTO> inmuebles = inmuebleService.listar();

      model.addAttribute("habitante", habitante);
      model.addAttribute("inmuebles", inmuebles);
      model.addAttribute("isDisabled", true);
      return viewEdit;

    } catch (ErrorServiceException e) {
      model.addAttribute("msgError", e.getMessage());
      return viewList;
    }
  }

  @GetMapping("/listarHabitante")
  public String listarHabitante(Model model) {
    try {
      List<PersonaDTO> listaHabitante = habitanteService.listar();
      model.addAttribute("listaHabitante", listaHabitante);

    } catch (ErrorServiceException e) {
      model.addAttribute("msgError", e.getMessage());
    } catch (Exception e) {
      model.addAttribute("msgError", "Error de Sistema");
    }
    return viewList;
  }

  @PostMapping("/aceptarEditHabitante")
  public String aceptarEdit(@RequestParam(required = false, defaultValue = "0") String id,
      @RequestParam String nombre,
      @RequestParam String apellido,
      @RequestParam String inmuebleId,
      RedirectAttributes attributes, Model model) {

    try {

      if ("0".equals(id)) {
        habitanteService.crear(nombre, apellido, inmuebleId);
      } else {
        habitanteService.modificar(id, nombre, apellido, inmuebleId);
      }

      attributes.addFlashAttribute("msgExito", "La acción fue realizada correctamente.");
      return redirectList;

    } catch (ErrorServiceException e) {
      return error(e.getMessage(), model, id, nombre, apellido, inmuebleId);
    } catch (Exception e) {
      return error("Error de Sistema", model, id, nombre, apellido, inmuebleId);
    }

  }

  @GetMapping("/cancelarEditHabitante")
  public String cancelarEdit() {

    return redirectList;
  }

  private String error(String mensaje, Model model, String id, String nombre, String apellido, String inmuebleId) {
    try {
      model.addAttribute("msgError", mensaje);

      PersonaDTO habitante = new PersonaDTO();

      if (id != "0") {
        habitante = habitanteService.buscar(id);
      } else {
        habitante.setNombre(nombre);
        habitante.setApellido(apellido);
        habitante.setInmuebleId(inmuebleId);
      }

      model.addAttribute("habitante", habitante);

    } catch (Exception e) {
      e.printStackTrace();
      model.addAttribute("msgError", "Error inesperado al procesar la solicitud.");
    }

    return viewEdit;
  }

}

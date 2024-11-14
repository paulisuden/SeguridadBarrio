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

import com.is2.seguridad_barrio_cliente.dto.NegocioDTO;
import com.is2.seguridad_barrio_cliente.dto.PersonaDTO;
import com.is2.seguridad_barrio_cliente.enumeration.TipoEmpleado;
import com.is2.seguridad_barrio_cliente.error.ErrorServiceException;
import com.is2.seguridad_barrio_cliente.service.EmpleadoService;
import com.is2.seguridad_barrio_cliente.service.NegocioService;

@Controller
@RequestMapping("/empleado")
public class EmpleadoController {

  @Autowired
  private EmpleadoService empleadoService;
  @Autowired
  private NegocioService negocioService;

  private String viewList = "persona/listarEmpleado.html";
  private String redirectList = "redirect:/empleado/listarEmpleado";
  private String viewEdit = "persona/editarEmpleado.html";

  @GetMapping("/altaEmpleado")
  public String alta(PersonaDTO empleado, Model model) throws ErrorServiceException {

    empleado = new PersonaDTO();

    List<NegocioDTO> negocios = negocioService.listar();

    model.addAttribute("empleado", empleado);
    model.addAttribute("tipoEmpleados", TipoEmpleado.values());
    model.addAttribute("negocios", negocios);
    model.addAttribute("isDisabled", false);

    return viewEdit;
  }

  @PostMapping("/baja")
  public String baja(@RequestParam("id") String id, RedirectAttributes redirectAttributes, Model model) {

    try {

      empleadoService.eliminar(id);
      redirectAttributes.addFlashAttribute("msgExito", "Empleado #" + id + " eliminado correctamente.");
      return redirectList;

    } catch (ErrorServiceException e) {
      model.addAttribute("msgError", e.getMessage());
      return redirectList;
    }
  }

  @GetMapping("/modificar")
  public String modificar(@RequestParam String id, Model model) {

    try {

      PersonaDTO empleado = empleadoService.buscar(id);

      List<NegocioDTO> negocios = negocioService.listar();

      model.addAttribute("empleado", empleado);
      model.addAttribute("tipoEmpleados", TipoEmpleado.values());
      model.addAttribute("negocios", negocios);
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

      PersonaDTO empleado = empleadoService.buscar(id);

      List<NegocioDTO> negocios = negocioService.listar();

      model.addAttribute("empleado", empleado);
      model.addAttribute("tipoEmpleados", TipoEmpleado.values());
      model.addAttribute("negocios", negocios);
      model.addAttribute("isDisabled", true);
      return viewEdit;

    } catch (ErrorServiceException e) {
      model.addAttribute("msgError", e.getMessage());
      return viewList;
    }
  }

  @GetMapping("/listarEmpleado")
  public String listarEmpleado(Model model) {
    try {
      List<PersonaDTO> listaEmpleado = empleadoService.listar();
      model.addAttribute("listaEmpleado", listaEmpleado);

    } catch (ErrorServiceException e) {
      model.addAttribute("msgError", e.getMessage());
    } catch (Exception e) {
      e.printStackTrace();
      model.addAttribute("msgError", "Error de Sistema");
    }
    return viewList;
  }

  @PostMapping("/aceptarEditEmpleado")
  public String aceptarEdit(@RequestParam(required = false, defaultValue = "0") String id,
      @RequestParam String nombre,
      @RequestParam String apellido,
      @RequestParam String legajo,
      @RequestParam TipoEmpleado tipoEmpleado,
      @RequestParam String[] negociosId,
      RedirectAttributes attributes, Model model) {

    try {

      if ("0".equals(id)) {
        empleadoService.crear(nombre, apellido, legajo, tipoEmpleado, negociosId);
      } else {
        empleadoService.modificar(id, nombre, apellido, legajo, tipoEmpleado, negociosId);
      }

      attributes.addFlashAttribute("msgExito", "La acción fue realizada correctamente.");
      return redirectList;

    } catch (ErrorServiceException e) {
      return error(e.getMessage(), model, id, nombre, apellido, legajo, tipoEmpleado, negociosId);
    } catch (Exception e) {
      return error("Error de Sistema", model, id, nombre, apellido, legajo, tipoEmpleado, negociosId);
    }

  }

  @GetMapping("/cancelar")
  public String cancelarEdit() {

    return redirectList;
  }

  private String error(String mensaje, Model model, String id, String nombre, String apellido, String legajo,
      TipoEmpleado tipoEmpleado, String[] negociosId) {
    try {
      model.addAttribute("msgError", mensaje);

      PersonaDTO empleado = new PersonaDTO();

      if (id != "0") {
        empleado = empleadoService.buscar(id);
      } else {
        empleado.setNombre(nombre);
        empleado.setApellido(apellido);
        empleado.setLegajo(legajo);
        empleado.setTipoEmpleado(tipoEmpleado);
        empleado.setNegociosId(negociosId);
      }

      model.addAttribute("empleado", empleado);

    } catch (Exception e) {
      e.printStackTrace();
      model.addAttribute("msgError", "Error inesperado al procesar la solicitud.");
    }

    return viewEdit;
  }

}

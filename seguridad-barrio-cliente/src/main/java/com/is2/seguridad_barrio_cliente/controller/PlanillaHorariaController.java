package com.is2.seguridad_barrio_cliente.controller;

//import com.is2.seguridad_barrio_cliente.dto.EmpleadoDTO;
import com.is2.seguridad_barrio_cliente.dto.PlanillaHorariaDTO;
import com.is2.seguridad_barrio_cliente.enumeration.EstadoAsistencia;
import com.is2.seguridad_barrio_cliente.error.ErrorServiceException;
//import com.is2.seguridad_barrio_cliente.service.EmpleadoService;
import com.is2.seguridad_barrio_cliente.service.PlanillaHorariaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/planillaHoraria")
public class PlanillaHorariaController {

    @Autowired
    private PlanillaHorariaService planillaHorariaService;
    //@Autowired private EmpleadoService empleadoService;

    private String viewList = "horarios/listarPlanillaHoraria.html";
    private String redirectList = "redirect:/planillaHoraria/listarPlanillaHoraria";
    private String viewEdit = "horarios/editarPlanillaHoraria.html";

    @GetMapping("/altaPlanillaHoraria")
    public String alta(PlanillaHorariaDTO planillaHoraria, Model model) throws ErrorServiceException {

        planillaHoraria = new PlanillaHorariaDTO();

        //List<EmpleadoDTO> empleados = empleadoService.listar();
        model.addAttribute("planillaHoraria", planillaHoraria);
        //model.addAttribute("empleados", empleados);
        model.addAttribute("isDisabled", false);

        return viewEdit;
    }

    @GetMapping("/baja")
    public String baja(@RequestParam Long id, RedirectAttributes attributes, Model model) {

        try {

            planillaHorariaService.eliminar(id);
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

            PlanillaHorariaDTO planillaHoraria = planillaHorariaService.buscar(id);
            //List<EmpleadoDTO> empleados = empleadoService.listar();
            model.addAttribute("planillaHoraria", planillaHoraria);
            //model.addAttribute("empleados", empleados);
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

            PlanillaHorariaDTO planillaHoraria = planillaHorariaService.buscar(id);
            //List<EmpleadoDTO> empleados = empleadoService.listar();
            model.addAttribute("planillaHoraria", planillaHoraria);
            //model.addAttribute("empleados", empleados);
            model.addAttribute("isDisabled", true);

            return viewEdit;

        } catch (ErrorServiceException e) {
            model.addAttribute("msgError", e.getMessage());
            return viewList;
        }
    }

    @GetMapping("/listarPlanillaHoraria")
    public String listarPlanillaHoraria(Model model) {
        try {
            List<PlanillaHorariaDTO> listaPlanillaHoraria = planillaHorariaService.listar();
            model.addAttribute("listaPlanillaHoraria", listaPlanillaHoraria);
            //List<EmpleadoDTO> listaEmpleado = empleadoService.listar();
            //model.addAttribute("listaEmpleado", listaEmpleado);
        } catch (ErrorServiceException e) {
            model.addAttribute("msgError", e.getMessage());
        } catch (Exception e) {
            model.addAttribute("msgError", "Error de Sistema");
        }
        return viewList;
    }

    @PostMapping("/aceptarEditPlanillaHoraria")
    public String aceptarEdit(
        @RequestParam(required = false, defaultValue = "0") Long id, @RequestParam("entrada") String entradaStr,
        @RequestParam("salida") String salidaStr, @RequestParam EstadoAsistencia estadoAsistencia,
        @RequestParam String observacion, RedirectAttributes attributes, Model model) {

        LocalDateTime entrada = null;
        LocalDateTime salida = null;

        try {
            // Convertir las cadenas a LocalDateTime
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            entrada = LocalDateTime.parse(entradaStr, formatter);
            salida = LocalDateTime.parse(salidaStr, formatter);

            
            if (id == 0) {
                planillaHorariaService.crear(entrada, salida, estadoAsistencia, observacion);
            } else {
                planillaHorariaService.modificar(id, entrada, salida, estadoAsistencia, observacion);
            }

            attributes.addFlashAttribute("msgExito", "La acción fue realizada correctamente.");
            return redirectList;

        } catch (ErrorServiceException e) {
            return error(e.getMessage(), model, id, entrada, salida, estadoAsistencia, observacion);
        } catch (Exception e) {
            return error("Error de Sistema", model, id, entrada, salida, estadoAsistencia, observacion);
        }
    }


    @GetMapping("/cancelarEditPlanillaHoraria")
    public String cancelarEdit() {

        return redirectList;
    }

    private String error(String mensaje, Model model, Long id, LocalDateTime entrada, LocalDateTime salida, EstadoAsistencia estadoAsistencia, String observacion) {
        
        try {
            model.addAttribute("msgError", mensaje);

            PlanillaHorariaDTO planillaHoraria = new PlanillaHorariaDTO();

            if (id != 0) {
                planillaHoraria = planillaHorariaService.buscar(id);
            } else {
                planillaHoraria.setEntrada(entrada);
                planillaHoraria.setEstadoAsistencia(estadoAsistencia);
                planillaHoraria.setObservacionAsistencia(observacion);
                planillaHoraria.setSalida(salida);
                //planillaHoraria.setEmpleadoId(idEmpleado);
            }

            model.addAttribute("planillaHoraria", planillaHoraria);

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("msgError", "Error inesperado al procesar la solicitud.");
        }

        return viewEdit;
    }
}

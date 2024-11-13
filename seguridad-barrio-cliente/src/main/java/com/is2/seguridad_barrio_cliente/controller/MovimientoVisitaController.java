package com.is2.seguridad_barrio_cliente.controller;

import com.is2.seguridad_barrio_cliente.dto.InmuebleDTO;
import com.is2.seguridad_barrio_cliente.dto.MovimientoVisitaDTO;
import com.is2.seguridad_barrio_cliente.dto.VisitanteDTO;
import com.is2.seguridad_barrio_cliente.enumeration.EstadoMovimiento;
import com.is2.seguridad_barrio_cliente.enumeration.TipoMovilidad;
import com.is2.seguridad_barrio_cliente.error.ErrorServiceException;
import com.is2.seguridad_barrio_cliente.service.InmuebleService;
import com.is2.seguridad_barrio_cliente.service.MovimientoVisitaService;
import com.is2.seguridad_barrio_cliente.service.VisitanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Date;
import java.util.List;

@Controller
@RequestMapping("/movimientoVisita")
public class MovimientoVisitaController {

    @Autowired
    private MovimientoVisitaService movimientoVisitaService;
    @Autowired
    private VisitanteService visitanteServie;
    @Autowired
    private InmuebleService inmuebleService;

    private String viewList = "visita/listarMovimientoVisita.html";
    private String redirectList = "redirect:/movimientoVisita/listarMovimientoVisita";
    private String viewEdit = "visita/editarMovimientoVisita.html";

    @GetMapping("/altaMovimientoVisita")
    public String alta(MovimientoVisitaDTO movimientoVisita, Model model) throws ErrorServiceException {

        movimientoVisita = new MovimientoVisitaDTO();

        List<VisitanteDTO> visitantes = visitanteServie.listar();
        model.addAttribute("visitantes", visitantes);

        List<InmuebleDTO> inmuebles = inmuebleService.listar();
        model.addAttribute("inmuebles", inmuebles);

        model.addAttribute("movimientoVisita", movimientoVisita);
        model.addAttribute("isDisabled", false);

        return viewEdit;
    }

    @PostMapping("/baja")
    public String baja(@RequestParam("id") String id, RedirectAttributes redirectAttributes, Model model) {


        try {

            movimientoVisitaService.eliminar(id);
            redirectAttributes.addFlashAttribute("msgExito", "Movimiento #" + id + " eliminado correctamente.");
            return redirectList;

        } catch (ErrorServiceException e) {
            model.addAttribute("msgError", e.getMessage());
            return redirectList;
        }
    }

    @GetMapping("/modificar")
    public String modificar(@RequestParam String id, Model model) {

        try {

            MovimientoVisitaDTO movimientoVisita = movimientoVisitaService.buscar(id);
            model.addAttribute("movimientoVisita", movimientoVisita);

            List<VisitanteDTO> visitantes = visitanteServie.listar();
            model.addAttribute("visitantes", visitantes);

            List<InmuebleDTO> inmuebles = inmuebleService.listar();
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

            MovimientoVisitaDTO movimientoVisita = movimientoVisitaService.buscar(id);
            model.addAttribute("movimientoVisita", movimientoVisita);
            model.addAttribute("isDisabled", true);

            return viewEdit;

        } catch (ErrorServiceException e) {
            model.addAttribute("msgError", e.getMessage());
            return viewList;
        }
    }

    @GetMapping("/listarMovimientoVisita")
    public String listarMovimientoVisita(Model model) {
        try {
            List<MovimientoVisitaDTO> listaMovimientoVisita = movimientoVisitaService.listar();
            model.addAttribute("listaMovimientoVisita", listaMovimientoVisita);
        } catch (ErrorServiceException e) {
            model.addAttribute("msgError", e.getMessage());
        } catch (Exception e) {
            model.addAttribute("msgError", "Error de Sistema");
        }
        return viewList;
    }

    ///////// FALTA AGREGAR EL INMUEBLE
    @PostMapping("/aceptarEditMovimientoVisita")
    public String aceptarEdit(@RequestParam(required = false, defaultValue = "0") String id,
            @RequestParam Date fechasMovimiento, @RequestParam String observacion,
            @RequestParam EstadoMovimiento estadoMovimiento, @RequestParam TipoMovilidad tipoMovilidad,
            @RequestParam String descripcionMovilidad, @RequestParam String idVisitante,
            @RequestParam String idInmueble,
            RedirectAttributes attributes, Model model) {
        try {

            if ("0".equals(id)) {

                movimientoVisitaService.crear(fechasMovimiento, observacion, estadoMovimiento,
                        tipoMovilidad, descripcionMovilidad, idVisitante, idInmueble);
                attributes.addFlashAttribute("msgExito", "Movimiento creado correctamente.");

            }

            else {
                movimientoVisitaService.modificar(id, fechasMovimiento, observacion, estadoMovimiento,
                        tipoMovilidad, descripcionMovilidad, idVisitante, idInmueble);
                attributes.addFlashAttribute("msgExito", "Movimiento #" + id + " editado correctamente.");

            }

            return redirectList;

        } catch (ErrorServiceException e) {
            return error(e.getMessage(), model, id, fechasMovimiento, observacion, estadoMovimiento,
                    tipoMovilidad, descripcionMovilidad, idVisitante, idInmueble);

        } catch (Exception e) {
            return error("Error de Sistema", model, id, fechasMovimiento, observacion, estadoMovimiento,
                    tipoMovilidad, descripcionMovilidad, idVisitante, idInmueble);
        }

    }

    @GetMapping("/cancelarEditMovimientoVisita")
    public String cancelarEdit() {

        return redirectList;
    }

    private String error(String mensaje, Model model, String id, Date fechasMovimiento, String observacion,
            EstadoMovimiento estadoMovimiento,
            TipoMovilidad tipoMovilidad, String descripcionMovilidad, String idVisitante, String idInmueble) {

        try {

            model.addAttribute("msgError", mensaje);
            if ("0".equals(id)) {
                model.addAttribute("movimientoVisita", movimientoVisitaService.buscar(id));
            } else {
                VisitanteDTO visitante = visitanteServie.buscar(idVisitante);
                InmuebleDTO inmueble = inmuebleService.buscar(idInmueble);
                MovimientoVisitaDTO movimiento = new MovimientoVisitaDTO();
                movimiento.setFechasMovimiento(fechasMovimiento);
                movimiento.setObservacion(observacion);
                movimiento.setEstadoMovimiento(estadoMovimiento);
                movimiento.setTipoMovilidad(tipoMovilidad);
                movimiento.setDescripcionMovilidad(descripcionMovilidad);
                movimiento.setVisitante(visitante);
                movimiento.setInmueble(inmueble);
                model.addAttribute("movimientoVisita", movimiento);
            }

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("msgError", "Error inesperado al procesar la solicitud.");
        }
        return viewEdit;
    }

}

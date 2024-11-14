package com.is2.seguridad_barrio_cliente.controller;

import com.is2.seguridad_barrio_cliente.dto.InmuebleDTO;
import com.is2.seguridad_barrio_cliente.dto.MovimientoVisitaDTO;
import com.is2.seguridad_barrio_cliente.dto.PersonaDTO;
import com.is2.seguridad_barrio_cliente.dto.UsuarioDTO;
import com.is2.seguridad_barrio_cliente.dto.VisitanteDTO;
import com.is2.seguridad_barrio_cliente.enumeration.EstadoMovimiento;
import com.is2.seguridad_barrio_cliente.enumeration.TipoMovilidad;
import com.is2.seguridad_barrio_cliente.error.ErrorServiceException;
import com.is2.seguridad_barrio_cliente.service.HabitanteService;
import com.is2.seguridad_barrio_cliente.service.InmuebleService;
import com.is2.seguridad_barrio_cliente.service.MovimientoVisitaService;
import com.is2.seguridad_barrio_cliente.service.UsuarioService;
import com.is2.seguridad_barrio_cliente.service.VisitanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/movimientoVisita")
public class MovimientoVisitaController {

    @Autowired private MovimientoVisitaService movimientoVisitaService;

    @Autowired private VisitanteService visitanteServie;

    @Autowired private InmuebleService inmuebleService;

    @Autowired private UsuarioService usuarioService;

    @Autowired private HabitanteService habitanteService;

    private String viewList = "visita/listarMovimientoVisita.html";
    private String redirectList = "redirect:/movimientoVisita/listarMovimientoVisita";
    private String viewEdit = "visita/editarMovimientoVisita.html";

    @GetMapping("/altaMovimientoVisita")
    public String alta(MovimientoVisitaDTO movimientoVisita, Model model, Authentication authentication) throws ErrorServiceException {

        movimientoVisita = new MovimientoVisitaDTO();

        if (authentication != null) {
            boolean hasHabitanteRole = authentication.getAuthorities().stream()
                    .anyMatch(authority -> authority.getAuthority().equals("ROLE_HABITANTE"));
            if (hasHabitanteRole){
                String name = authentication.getName(); //email
                UsuarioDTO usuarioDTO = usuarioService.buscarCuenta(name);
                //busco la persona que tenga ese idUsuario
                PersonaDTO habitante = habitanteService.buscarPorUsuarioId(usuarioDTO.getId());
                //busco el inmueble que corresponda a esa persona (habitante)
                InmuebleDTO inmueble = habitante.getInmueble();
                model.addAttribute("inmueble", inmueble);
                //traigo una lista de los movimientos que se hayan realizado en ese inmueble
                List<MovimientoVisitaDTO> movimientos = movimientoVisitaService.listarPorInmuebleId(inmueble.getId());
                //traigo los visitantes vinculados con ese inmueble
                List<VisitanteDTO> listaVisitantes = listarVisitantes(movimientos);
                model.addAttribute("movimientos", movimientos);
                model.addAttribute("listaVisitantes", listaVisitantes);
                //para que el inmueble quede preseleccionado sin ninguna otra opcion
                movimientoVisita.setInmueble(inmueble);
                model.addAttribute("movimientoVisita", movimientoVisita);

                return "habitante/editarMovimientoVisita";

            } else { //PERSONAL O ADMIN

                List<VisitanteDTO> visitantes = visitanteServie.listar();
                List<InmuebleDTO> inmuebles = inmuebleService.listar();
                model.addAttribute("visitantes", visitantes);
                model.addAttribute("inmuebles", inmuebles);
                model.addAttribute("movimientoVisita", movimientoVisita);
                model.addAttribute("isDisabled", false);

                return viewEdit;
            }
        }
        return "inicio.html";

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
    public String listarMovimientoVisita(Model model, Authentication authentication) {
        try {
            if (authentication != null) {
                boolean hasHabitanteRole = authentication.getAuthorities().stream()
                        .anyMatch(authority -> authority.getAuthority().equals("ROLE_HABITANTE"));
                if (hasHabitanteRole) {
                    String name = authentication.getName(); //email
                    UsuarioDTO usuarioDTO = usuarioService.buscarCuenta(name);
                    PersonaDTO habitante = habitanteService.buscarPorUsuarioId(usuarioDTO.getId());
                    InmuebleDTO inmueble = habitante.getInmueble();
                    model.addAttribute("inmueble", inmueble);
                    List<MovimientoVisitaDTO> movimientos = movimientoVisitaService.listarPorInmuebleId(inmueble.getId());
                    model.addAttribute("movimientos", movimientos);
                    return "habitante/listarMovimientoVisita";
                } else { //ADMIN O PERSONAL
                List<MovimientoVisitaDTO> listaMovimientoVisita = movimientoVisitaService.listar();
                model.addAttribute("listaMovimientoVisita", listaMovimientoVisita);
                }
            }
        } catch (ErrorServiceException e) {
            model.addAttribute("msgError", e.getMessage());
        } catch (Exception e) {
            model.addAttribute("msgError", "Error de SistemaAAAA");
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


    public List<VisitanteDTO> listarVisitantes(List<MovimientoVisitaDTO> movimientos) {
        List<VisitanteDTO> visitantesUnicos = movimientos.stream()
        .map(MovimientoVisitaDTO::getVisitante) // Extraer el visitante de cada movimiento
        .filter(visitante -> visitante != null && visitante.getId() != null) // Evitar visitantes nulos
        .collect(Collectors.toMap(
            VisitanteDTO::getId, // Usar el ID del visitante como clave
            visitante -> visitante, // Mantener el visitante como valor
            (v1, v2) -> v1 // En caso de duplicados, conservar el primero
        ))
        .values()
        .stream()
        .collect(Collectors.toList());
        return visitantesUnicos;
    }

}

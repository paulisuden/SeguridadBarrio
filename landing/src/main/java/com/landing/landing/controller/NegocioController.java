package com.landing.landing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.landing.landing.dto.ServicioDTO;
import com.landing.landing.error.ErrorServiceException;
import com.landing.landing.service.NegocioService;
import com.landing.landing.service.ServicioService;

@Controller
@RequestMapping("/negocio")
public class NegocioController {

    @Autowired
    private NegocioService negocioService;

    @Autowired
    private ServicioService servicioService;

    @GetMapping("/{id}")
    public String unidadDeNegocio(
            Model model,
            @PathVariable("id") Long id) {
        try {
            var negocio = negocioService.buscar(id);
            var servicios = negocio.getServicios();
            model.addAttribute("negocio", negocio);
            model.addAttribute("servicios", servicios);
        } catch (ErrorServiceException e) {
            System.out.println(e.toString());
            return "error.html";
        }
        return "consultaUnidadDeNegocio.html";
    }

    @GetMapping("/servicio/imagen/{id}")
    public ResponseEntity<byte[]> fotoServicio(
            Model model,
            @PathVariable Long id) {

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

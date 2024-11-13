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

import com.landing.landing.dto.ImagenDTO;
import com.landing.landing.service.ImagenService;

@Controller
@RequestMapping("/imagen")
public class ImagenController {

    @Autowired
    private ImagenService imagenService;

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> fotoServicio(
            @PathVariable String id,
            Model model) {

        try {
            ImagenDTO imagen = imagenService.buscar(id);
            byte[] imgContenido = imagen.getContenido();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity<>(imgContenido, headers, HttpStatus.OK);

        } catch (Exception ex) {
            model.addAttribute("msgError", "Error inesperado al procesar la solicitud.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

}

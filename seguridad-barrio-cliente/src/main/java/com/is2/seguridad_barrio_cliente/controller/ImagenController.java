package com.is2.seguridad_barrio_cliente.controller;

import com.is2.seguridad_barrio_cliente.dto.ImagenDTO;
import com.is2.seguridad_barrio_cliente.dto.UsuarioDTO;
import com.is2.seguridad_barrio_cliente.service.ImagenService;
import com.is2.seguridad_barrio_cliente.service.UsuarioService;

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

@Controller
@RequestMapping("/imagen")
public class ImagenController {

    @Autowired
    private ImagenService imagenService;
    @Autowired
    private UsuarioService usuarioService;

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

    @GetMapping("/usuario/{email}")
    public ResponseEntity<byte[]> imagenUsuario(
            @PathVariable String email,
            Model model) {

        try {
            UsuarioDTO usuario = usuarioService.buscarCuenta(email);

            ImagenDTO imagen = imagenService.buscar(usuario.getImagen().getId());
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

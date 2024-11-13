package com.is.servidor_barrio.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.is.servidor_barrio.business.domain.dto.usuario.UsuarioCreateDTO;
import com.is.servidor_barrio.business.domain.dto.usuario.UsuarioDTO;
import com.is.servidor_barrio.business.domain.entity.Usuario;
import com.is.servidor_barrio.business.facade.impl.UsuarioFacade;
import com.is.servidor_barrio.business.logic.service.UsuarioServiceImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/usuario")
public class UsuarioController extends BaseControllerImpl<Usuario, UsuarioDTO, UsuarioCreateDTO, UsuarioCreateDTO, UsuarioFacade>{

    @Autowired
    protected UsuarioFacade facade;

    @PostMapping("/crear")
    public ResponseEntity<?> crearUsuario (@RequestBody UsuarioDTO usuarioDto){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(facade.);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\""+e.getMessage()+"\"}");
        }
    }

    @GetMapping("/buscar/{nombre}")
    public ResponseEntity<?> getOne(@PathVariable String nombre) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.convertToDto(service.searchByCuenta(nombre)));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }

    @GetMapping("/buscar/per/{idUsuario}")
    public ResponseEntity<?> getPorPersonaId(@PathVariable String idUsuario) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.convertToDto(service.searchByIdPersona(idUsuario)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }

}
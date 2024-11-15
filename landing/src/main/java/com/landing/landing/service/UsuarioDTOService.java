package com.landing.landing.service;

import org.springframework.stereotype.Service;

@Service
public class UsuarioDTOService {
    public com.landing.landing.dto.UsuarioDTO crear(String cuenta, String clave) {
        com.landing.landing.dto.UsuarioDTO usuario = new com.landing.landing.dto.UsuarioDTO();
        usuario.setClave(clave);
        usuario.setEmail(cuenta);
        return usuario;
    }
}
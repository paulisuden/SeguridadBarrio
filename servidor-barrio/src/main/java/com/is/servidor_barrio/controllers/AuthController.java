package com.is.servidor_barrio.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.is.servidor_barrio.business.domain.entity.Usuario;
import com.is.servidor_barrio.business.domain.entity.authentication.LoginResponse;
import com.is.servidor_barrio.business.logic.service.AuthService;


@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/auth/login")
    public LoginResponse login(@RequestBody @Validated Usuario request) {
        return authService.attemptLogin(request.getEmail(), request.getClave());
    }
}

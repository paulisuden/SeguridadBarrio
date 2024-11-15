package com.is2.seguridad_barrio_cliente.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.is2.seguridad_barrio_cliente.dto.UsuarioDTO;
import com.is2.seguridad_barrio_cliente.error.ErrorServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthDAORest {

    @Autowired
    private RestTemplate restTemplate;

    public String authenticate(UsuarioDTO usuario) throws ErrorServiceException {
        try {

            String uri = "http://localhost:8081/auth/login";
            System.out.println(uri);
            HttpHeaders headers = new HttpHeaders();
            headers.add("No-Token", "true");
            headers.setContentType(MediaType.APPLICATION_JSON); // Asumimos que la API espera JSON

            String usuarioJson = new ObjectMapper().writeValueAsString(usuario);

            HttpEntity<String> entity = new HttpEntity<>(usuarioJson, headers);
            ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);
            String token = response.getBody();
            return token;
        } catch (Exception ex) {
            throw new ErrorServiceException("Error al autenticar");
        }
    }

}

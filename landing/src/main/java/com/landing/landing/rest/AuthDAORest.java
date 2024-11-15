package com.landing.landing.rest;

    import com.fasterxml.jackson.databind.ObjectMapper;
import com.landing.landing.dto.UsuarioDTO;
import com.landing.landing.error.ErrorServiceException;

import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.*;
    import org.springframework.stereotype.Service;
    import org.springframework.web.client.RestTemplate;
    
@Service
public class AuthDAORest {
    
        @Autowired
        private RestTemplate restTemplate;
        /* 
        public String authenticate(UsuarioDTO usuario) throws ErrorDAOException {
            try {
    
                String uri = "http://localhost:8081/auth/login";
                HttpHeaders headers = new HttpHeaders();
                headers.add("No-Token", "true");
                headers.setContentType(MediaType.APPLICATION_JSON);
    
                String usuarioJson = new ObjectMapper().writeValueAsString(usuario);
    
                HttpEntity<String> entity = new HttpEntity<>(usuarioJson, headers);
    
                ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);
                String token = response.getBody();
    
                return token;
            } catch (Exception ex) {
                ex.printStackTrace();
                throw new ErrorDAOException("Error al autenticar");
            }
        } */
    public String authenticate(UsuarioDTO usuario) throws ErrorServiceException {
        try {

            String uri = "http://localhost:8081/auth/login";
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

    

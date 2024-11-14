package com.is2.seguridad_barrio_cliente.rest;

import com.is2.seguridad_barrio_cliente.dto.UsuarioDTO;
import com.is2.seguridad_barrio_cliente.error.ErrorServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class UsuarioDAORest {

    @Autowired
    protected RestTemplate restTemplate;

    public void crear(UsuarioDTO usuarioDTO) throws ErrorServiceException {

        try {

            String uri = "http://localhost:8081/api/usuario";
            restTemplate.postForEntity(uri, usuarioDTO, UsuarioDTO.class);
        } catch (HttpClientErrorException e) {
            String error = e.getMessage();
            error = error.replace("404 :", "").trim();
            error = error.replace("{\"error\":\"", "").replace("\"}", "");
            error = error.replace("\"", "");
            throw new ErrorServiceException(error);
        } catch (RestClientException ex) {
            //
        } catch (Exception ex) {
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public void actualizar(UsuarioDTO usuarioDTO) throws ErrorServiceException {

        try {
            String uri = "http://localhost:8081/api/usuario/" + usuarioDTO.getId();
            restTemplate.put(uri, usuarioDTO);

        } catch (HttpClientErrorException e) {
            String error = e.getMessage();
            error = error.replace("404 :", "").trim();
            error = error.replace("{\"error\":\"", "").replace("\"}", "");
            error = error.replace("\"", "");
            throw new ErrorServiceException(error);
        } catch (Exception ex) {
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public void eliminar(String id) throws ErrorServiceException {

        try {
            String uri = "http://localhost:8081/api/usuario/" + id;
            restTemplate.delete(uri);

        } catch (HttpClientErrorException e) {
            String error = e.getMessage();
            error = error.replace("404 :", "").trim();
            error = error.replace("{\"error\":\"", "").replace("\"}", "");
            error = error.replace("\"", "");
            throw new ErrorServiceException(error);
        } catch (Exception ex) {
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public UsuarioDTO buscar(String id) throws ErrorServiceException {

        try {

            String uri = "http://localhost:8081/api/usuario/buscarId/" + id;
            ResponseEntity<UsuarioDTO> response = restTemplate.getForEntity(uri, UsuarioDTO.class);
            UsuarioDTO usuarioDTO = response.getBody();
            return usuarioDTO;

        } catch (HttpClientErrorException e) {
            String error = e.getMessage();
            error = error.replace("404 :", "").trim();
            error = error.replace("{\"error\":\"", "").replace("\"}", "");
            error = error.replace("\"", "");
            throw new ErrorServiceException(error);
        } catch (Exception e) {
            throw new ErrorServiceException("Error inesperado");
        }
    }

    public List<UsuarioDTO> listar() throws ErrorServiceException {

        try {

            String uri = "http://localhost:8081/api/usuario";
            ResponseEntity<UsuarioDTO[]> response = restTemplate.getForEntity(uri, UsuarioDTO[].class);
            UsuarioDTO[] usuarios = response.getBody();
            List<UsuarioDTO> listaUsuarios = Arrays.asList(usuarios);
            return listaUsuarios;

        } catch (HttpClientErrorException e) {
            String error = e.getMessage();
            error = error.replace("404 :", "").trim();
            error = error.replace("{\"error\":\"", "").replace("\"}", "");
            error = error.replace("\"", "");
            throw new ErrorServiceException(error);
        } catch (Exception ex) {
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public void registrar(UsuarioDTO usuario) throws ErrorServiceException {
        try {
            String uri = "http://localhost:8081/api/usuario/crear";
            restTemplate.postForEntity(uri, usuario, UsuarioDTO.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public UsuarioDTO buscarCuenta(String cuenta) throws ErrorServiceException {

        try {
            String uri = "http://localhost:8081/api/usuario/buscar/" + cuenta;

            ResponseEntity<UsuarioDTO> response = restTemplate.getForEntity(uri, UsuarioDTO.class);
            UsuarioDTO entity = response.getBody();
            return entity;

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public UsuarioDTO buscarPorIdUsuario(String idPersona) throws ErrorServiceException {
        try {
            String uri = "http://localhost:8081/api/usuario/buscar/per/" + idPersona;

            ResponseEntity<UsuarioDTO> response = restTemplate.getForEntity(uri, UsuarioDTO.class, idPersona);
            UsuarioDTO entity = response.getBody();

            return entity;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }
}

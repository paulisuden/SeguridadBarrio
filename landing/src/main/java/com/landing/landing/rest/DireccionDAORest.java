package com.landing.landing.rest;

import com.landing.landing.dto.DireccionDTO;
import com.landing.landing.error.ErrorServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class DireccionDAORest {

    @Autowired
    private RestTemplate restTemplate;

    public void crear(DireccionDTO direccion) throws ErrorServiceException {
        try {
            String uri = "http://localhost:8081/api/direccion";
            restTemplate.postForEntity(uri, direccion, DireccionDTO.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public DireccionDTO buscar(String id) throws ErrorServiceException {

        try {

            String uri = "http://localhost:8081/api/direccion/" + id;

            ResponseEntity<DireccionDTO> response = restTemplate.getForEntity(uri, DireccionDTO.class);
            DireccionDTO direccion = response.getBody();

            return direccion;

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public void actualizar(DireccionDTO direccion) throws ErrorServiceException {

        try {

            String uri = "http://localhost:8081/api/direccion/" + direccion.getId();
            restTemplate.put(uri, direccion);

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public void eliminar(String id) throws ErrorServiceException {

        try {

            String uri = "http://localhost:8081/api/direccion/" + id;
            restTemplate.delete(uri);

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public List<DireccionDTO> listar() throws ErrorServiceException {
        try {
            String uri = "http://localhost:8081/api/direccion";

            ResponseEntity<DireccionDTO[]> response = restTemplate.getForEntity(uri, DireccionDTO[].class);
            DireccionDTO[] direcciones = response.getBody();
            List<DireccionDTO> listaDirecciones = Arrays.asList(direcciones);
            return listaDirecciones;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public DireccionDTO buscarDireccionPorLocalidadYNombre(String idLocalidad, String nombre)
            throws ErrorServiceException {
        try {

            String uri = "http://localhost:8081/api/direccion/?localidadId=" + idLocalidad + "&nombre=" + nombre;

            ResponseEntity<DireccionDTO> response = restTemplate.getForEntity(uri, DireccionDTO.class);

            DireccionDTO direccion = response.getBody();

            return direccion;

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

}

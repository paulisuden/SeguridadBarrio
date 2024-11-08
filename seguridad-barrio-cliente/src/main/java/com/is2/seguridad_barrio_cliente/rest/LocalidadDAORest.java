package com.is2.seguridad_barrio_cliente.rest;

import com.is2.seguridad_barrio_cliente.dto.LocalidadDTO;
import com.is2.seguridad_barrio_cliente.error.ErrorServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class LocalidadDAORest {

    @Autowired
    private RestTemplate restTemplate;

    public void crear(LocalidadDTO localidad) throws ErrorServiceException {
        try {
            String uri = "http://localhost:8081/api/localidad";
            restTemplate.postForEntity(uri, localidad, LocalidadDTO.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public LocalidadDTO buscar(Long id) throws ErrorServiceException {

        try {

            String uri = "http://localhost:8081/api/localidad/" + id;

            ResponseEntity<LocalidadDTO> response = restTemplate.getForEntity(uri, LocalidadDTO.class);
            LocalidadDTO localidad = response.getBody();

            return localidad;

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public void actualizar(LocalidadDTO localidad) throws ErrorServiceException {

        try {

            String uri = "http://localhost:8081/api/localidad/" + localidad.getId();
            restTemplate.put(uri, localidad);

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public void eliminar(Long id) throws ErrorServiceException {

        try {

            String uri = "http://localhost:8081/api/localidad/" + id;
            restTemplate.delete(uri);

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public List<LocalidadDTO> listar() throws ErrorServiceException {
        try {
            String uri = "http://localhost:8081/api/localidad";

            ResponseEntity<LocalidadDTO[]> response = restTemplate.getForEntity(uri, LocalidadDTO[].class);
            LocalidadDTO[] localidades = response.getBody();
            List<LocalidadDTO> listaLocalidades = Arrays.asList(localidades);
            return listaLocalidades;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public LocalidadDTO buscarLocalidadPorDepartamentoYNombre(Long idDepartamento, String nombre) throws ErrorServiceException {
        try {

            String uri = "http://localhost:8081/api/localidad/?departamentoId=" + idDepartamento + "&nombre=" + nombre;

            ResponseEntity<LocalidadDTO> response = restTemplate.getForEntity(uri, LocalidadDTO.class);

            LocalidadDTO localidad = response.getBody();

            return localidad;

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

}

package com.is2.seguridad_barrio_cliente.rest;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.is2.seguridad_barrio_cliente.dto.InmuebleDTO;
import com.is2.seguridad_barrio_cliente.error.ErrorServiceException;

@Service
public class InmuebleDAORest {

    @Autowired
    private RestTemplate restTemplate;

    public void crear(InmuebleDTO inmueble) throws ErrorServiceException {
        try {
            System.out.println(inmueble);
            String uri = "http://localhost:8081/api/inmueble";
            restTemplate.postForEntity(uri, inmueble, InmuebleDTO.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public InmuebleDTO buscar(String id) throws ErrorServiceException {

        try {
            String uri = "http://localhost:8081/api/inmueble/" + id;

            ResponseEntity<InmuebleDTO> response = restTemplate.getForEntity(uri, InmuebleDTO.class);
            InmuebleDTO inmueble = response.getBody();

            return inmueble;

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public void actualizar(InmuebleDTO inmueble) throws ErrorServiceException {

        try {
            String uri = "http://localhost:8081/api/inmueble/" + inmueble.getId();
            restTemplate.put(uri, inmueble);

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public void eliminar(String id) throws ErrorServiceException {

        try {
            String uri = "http://localhost:8081/api/inmueble/" + id;
            restTemplate.delete(uri);

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public List<InmuebleDTO> listar() throws ErrorServiceException {
        try {
            String uri = "http://localhost:8081/api/inmueble";

            ResponseEntity<InmuebleDTO[]> response = restTemplate.getForEntity(uri, InmuebleDTO[].class);
            InmuebleDTO[] inmuebles = response.getBody();
            List<InmuebleDTO> listaInmuebles = Arrays.asList(inmuebles);
            return listaInmuebles;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }
}

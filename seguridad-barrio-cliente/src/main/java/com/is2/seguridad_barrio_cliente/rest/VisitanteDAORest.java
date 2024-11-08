package com.is2.seguridad_barrio_cliente.rest;

import com.is2.seguridad_barrio_cliente.dto.VisitanteDTO;
import com.is2.seguridad_barrio_cliente.error.ErrorServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class VisitanteDAORest {

    @Autowired
    private RestTemplate restTemplate;

    public void crear(VisitanteDTO visitante) throws ErrorServiceException {
        try {
            String uri = "http://localhost:8081/api/visitante";
            restTemplate.postForEntity(uri, visitante, VisitanteDTO.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public VisitanteDTO buscar(Long id) throws ErrorServiceException {

        try {

            String uri = "http://localhost:8081/api/visitante/" + id;

            ResponseEntity<VisitanteDTO> response = restTemplate.getForEntity(uri, VisitanteDTO.class);
            VisitanteDTO visitante = response.getBody();

            return visitante;

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public void actualizar(VisitanteDTO visitante) throws ErrorServiceException {

        try {

            String uri = "http://localhost:8081/api/visitante/" + visitante.getId();
            restTemplate.put(uri, visitante);

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public void eliminar(Long id) throws ErrorServiceException {

        try {

            String uri = "http://localhost:8081/api/visitante/" + id;
            restTemplate.delete(uri);

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public List<VisitanteDTO> listar() throws ErrorServiceException {
        try {
            String uri = "http://localhost:8081/api/visitante";

            ResponseEntity<VisitanteDTO[]> response = restTemplate.getForEntity(uri, VisitanteDTO[].class);
            VisitanteDTO[] visitantes = response.getBody();
            List<VisitanteDTO> listaVisitantes = Arrays.asList(visitantes);
            return listaVisitantes;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }


}

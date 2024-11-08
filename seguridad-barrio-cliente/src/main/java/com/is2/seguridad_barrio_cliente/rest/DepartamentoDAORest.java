package com.is2.seguridad_barrio_cliente.rest;

import com.is2.seguridad_barrio_cliente.dto.DepartamentoDTO;
import com.is2.seguridad_barrio_cliente.error.ErrorServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class DepartamentoDAORest {

    @Autowired
    private RestTemplate restTemplate;

    public void crear(DepartamentoDTO departamento) throws ErrorServiceException {
        try {
            String uri = "http://localhost:8081/api/departamento";
            restTemplate.postForEntity(uri, departamento, DepartamentoDTO.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public DepartamentoDTO buscar(Long id) throws ErrorServiceException {

        try {

            String uri = "http://localhost:8081/api/departamento/" + id;

            ResponseEntity<DepartamentoDTO> response = restTemplate.getForEntity(uri, DepartamentoDTO.class);
            DepartamentoDTO departamento = response.getBody();

            return departamento;

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public void actualizar(DepartamentoDTO departamento) throws ErrorServiceException {

        try {

            String uri = "http://localhost:8081/api/departamento/" + departamento.getId();
            restTemplate.put(uri, departamento);

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public void eliminar(Long id) throws ErrorServiceException {

        try {

            String uri = "http://localhost:8081/api/departamento/" + id;
            restTemplate.delete(uri);

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public List<DepartamentoDTO> listar() throws ErrorServiceException {
        try {
            String uri = "http://localhost:8081/api/departamento";

            ResponseEntity<DepartamentoDTO[]> response = restTemplate.getForEntity(uri, DepartamentoDTO[].class);
            DepartamentoDTO[] departamentos = response.getBody();
            List<DepartamentoDTO> listaDepartamentos = Arrays.asList(departamentos);
            return listaDepartamentos;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }


    public DepartamentoDTO buscarDepartamentoPorProvinciaYNombre(Long idProvincia, String nombre) throws ErrorServiceException {
        try {

            String uri = "http://localhost:8081/api/departamento/?provinciaId=" + idProvincia + "&nombre=" + nombre;

            ResponseEntity<DepartamentoDTO> response = restTemplate.getForEntity(uri, DepartamentoDTO.class);

            DepartamentoDTO departamento = response.getBody();

            return departamento;

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

}

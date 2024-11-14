package com.is2.seguridad_barrio_cliente.rest;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.is2.seguridad_barrio_cliente.dto.EmpresaDTO;
import com.is2.seguridad_barrio_cliente.error.ErrorServiceException;

@Service
public class EmpresaDAORest {

    @Autowired
    private RestTemplate restTemplate;

    static final String API_PATH = "http://localhost:8081/api/empresa";

    public EmpresaDTO crear(EmpresaDTO empresa) throws ErrorServiceException {
        try {
            var res = restTemplate.postForEntity(API_PATH, empresa, EmpresaDTO.class);
            return res.getBody();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public EmpresaDTO buscar(String id) throws ErrorServiceException {

        try {

            String uri = API_PATH + "/" + id;

            ResponseEntity<EmpresaDTO> response = restTemplate.getForEntity(uri, EmpresaDTO.class);
            EmpresaDTO empresa = response.getBody();

            return empresa;

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas. " + ex.toString());
        }
    }

    public void actualizar(EmpresaDTO empresa) throws ErrorServiceException {

        try {

            String uri = API_PATH + "/" + empresa.getId();
            restTemplate.put(uri, empresa);

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public void eliminar(String id) throws ErrorServiceException {

        try {
            String uri = API_PATH + "/" + id;
            restTemplate.delete(uri);

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public List<EmpresaDTO> listar() throws ErrorServiceException {
        try {

            ResponseEntity<EmpresaDTO[]> response = restTemplate.getForEntity(API_PATH, EmpresaDTO[].class);
            EmpresaDTO[] empresaes = response.getBody();
            List<EmpresaDTO> listaempresaes = Arrays.asList(empresaes);
            return listaempresaes;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }
}
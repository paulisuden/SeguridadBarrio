package com.landing.landing.rest;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.landing.landing.dto.EmpresaDTO;
import com.landing.landing.error.ErrorServiceException;

@Service
public class EmpresaDAORest {

    @Autowired
    private RestTemplate restTemplate;

    static final String API_PATH = "${URL}/api/empresa";

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
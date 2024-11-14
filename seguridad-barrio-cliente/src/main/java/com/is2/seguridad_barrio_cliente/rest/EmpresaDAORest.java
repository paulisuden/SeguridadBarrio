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

    public EmpresaDTO crear(EmpresaDTO imagen) throws ErrorServiceException {
        try {
            var res = restTemplate.postForEntity(API_PATH, imagen, EmpresaDTO.class);
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
            EmpresaDTO imagen = response.getBody();

            return imagen;

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public void actualizar(EmpresaDTO imagen) throws ErrorServiceException {

        try {

            String uri = API_PATH + "/" + imagen.getId();
            restTemplate.put(uri, imagen);

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
            EmpresaDTO[] imagenes = response.getBody();
            List<EmpresaDTO> listaimagenes = Arrays.asList(imagenes);
            return listaimagenes;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }
}
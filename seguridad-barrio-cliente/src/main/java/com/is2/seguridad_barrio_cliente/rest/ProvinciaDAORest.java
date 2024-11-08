package com.is2.seguridad_barrio_cliente.rest;

import com.is2.seguridad_barrio_cliente.dto.ProvinciaDTO;
import com.is2.seguridad_barrio_cliente.error.ErrorServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class ProvinciaDAORest {

    @Autowired
    private RestTemplate restTemplate;

    public void crear(ProvinciaDTO provincia) throws ErrorServiceException {
        try {
            System.out.println(provincia.getPaisDTO().getNombre());
            System.out.println(provincia);
            String uri = "http://localhost:8081/api/provincia";
            restTemplate.postForEntity(uri, provincia, ProvinciaDTO.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public ProvinciaDTO buscar(Long id) throws ErrorServiceException {

        try {

            String uri = "http://localhost:8081/api/provincia/" + id;

            ResponseEntity<ProvinciaDTO> response = restTemplate.getForEntity(uri, ProvinciaDTO.class);
            ProvinciaDTO provincia = response.getBody();

            return provincia;

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public void actualizar(ProvinciaDTO provincia) throws ErrorServiceException {

        try {

            String uri = "http://localhost:8081/api/provincia/" + provincia.getId();
            restTemplate.put(uri, provincia);

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public void eliminar(Long id) throws ErrorServiceException {

        try {

            String uri = "http://localhost:8081/api/provincia/" + id;
            restTemplate.delete(uri);

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public List<ProvinciaDTO> listar() throws ErrorServiceException {
        try {
            String uri = "http://localhost:8081/api/provincia";

            ResponseEntity<ProvinciaDTO[]> response = restTemplate.getForEntity(uri, ProvinciaDTO[].class);
            ProvinciaDTO[] provincias = response.getBody();
            List<ProvinciaDTO> listaProvincias = Arrays.asList(provincias);
            return listaProvincias;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }


}

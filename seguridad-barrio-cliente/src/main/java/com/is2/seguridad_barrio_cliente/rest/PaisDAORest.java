package com.is2.seguridad_barrio_cliente.rest;

import com.is2.seguridad_barrio_cliente.dto.PaisDTO;
import com.is2.seguridad_barrio_cliente.error.ErrorServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class PaisDAORest {

    @Autowired
    private RestTemplate restTemplate;

    public void crear(PaisDTO pais) throws ErrorServiceException {
        try {
            String uri = "http://localhost:8081/api/pais";
            restTemplate.postForEntity(uri, pais, PaisDTO.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public PaisDTO buscar(Long id)  throws ErrorServiceException {

        try {

            String uri = "http://localhost:8081/api/pais/" + id;

            ResponseEntity<PaisDTO> response = restTemplate.getForEntity(uri,PaisDTO.class);
            PaisDTO pais  = response.getBody();

            return  pais;

        } catch (Exception ex){
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public void actualizar(PaisDTO pais) throws ErrorServiceException {

        try {

            String uri = "http://localhost:8081/api/pais/"+ pais.getId();
            restTemplate.put(uri, pais);

        } catch (Exception ex){
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public void eliminar(Long id)  throws ErrorServiceException {

        try {

            String uri = "http://localhost:8081/api/pais/" + id;
            restTemplate.delete(uri);

        } catch (Exception ex){
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public List<PaisDTO> listar() throws ErrorServiceException {
        try {
            String uri = "http://localhost:8081/api/pais";

            ResponseEntity<PaisDTO[]> response = restTemplate.getForEntity(uri, PaisDTO[].class);
            PaisDTO[] paises = response.getBody();
            List<PaisDTO> listaPaises = Arrays.asList(paises);
            return listaPaises;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }



}

package com.is2.seguridad_barrio_cliente.rest;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.is2.seguridad_barrio_cliente.dto.ContactoDTO;
import com.is2.seguridad_barrio_cliente.error.ErrorServiceException;

@Service
public class ContactoDAORest {

    @Autowired
    private RestTemplate restTemplate;

    static final String API_PATH = "http://localhost:8081/api/contacto";

    public ContactoDTO crear(ContactoDTO contacto) throws ErrorServiceException {
        try {
            var res = restTemplate.postForEntity(API_PATH, contacto, ContactoDTO.class);
            return res.getBody();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas al crear ContactoDTO");
        }
    }

    public ContactoDTO buscar(String id) throws ErrorServiceException {

        try {

            String uri = API_PATH + "/" + id;
            ResponseEntity<ContactoDTO> response = restTemplate.getForEntity(uri, ContactoDTO.class);
            return response.getBody();

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public void actualizar(ContactoDTO contacto) throws ErrorServiceException {

        try {

            String uri = API_PATH + "/" + contacto.getId();
            restTemplate.put(uri, contacto);
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

    public List<ContactoDTO> listar() throws ErrorServiceException {
        try {

            ResponseEntity<ContactoDTO[]> response = restTemplate.getForEntity(API_PATH, ContactoDTO[].class);
            ContactoDTO[] contactos = response.getBody();
            List<ContactoDTO> listacontactos = Arrays.asList(contactos);
            return listacontactos;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

}

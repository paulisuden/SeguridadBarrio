package com.is2.seguridad_barrio_cliente.rest;

import com.is2.seguridad_barrio_cliente.dto.ImagenDTO;
import com.is2.seguridad_barrio_cliente.error.ErrorServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class ImagenDAORest {

    @Autowired
    private RestTemplate restTemplate;

    static final String API_PATH = "http://localhost:8081/api/imagen";

    public ImagenDTO crear(ImagenDTO imagen) throws ErrorServiceException {
        try {
            var res = restTemplate.postForEntity(API_PATH, imagen, ImagenDTO.class);
            return res.getBody();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public ImagenDTO buscar(String id) throws ErrorServiceException {

        try {

            String uri = API_PATH + "/" + id;

            ResponseEntity<ImagenDTO> response = restTemplate.getForEntity(uri, ImagenDTO.class);
            ImagenDTO imagen = response.getBody();

            return imagen;

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public void actualizar(ImagenDTO imagen) throws ErrorServiceException {

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

    public List<ImagenDTO> listar() throws ErrorServiceException {
        try {

            ResponseEntity<ImagenDTO[]> response = restTemplate.getForEntity(API_PATH, ImagenDTO[].class);
            ImagenDTO[] imagenes = response.getBody();
            List<ImagenDTO> listaimagenes = Arrays.asList(imagenes);
            return listaimagenes;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

}

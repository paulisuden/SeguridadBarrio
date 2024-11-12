package com.landing.landing.rest;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.landing.landing.dto.ServicioDTO;
import com.landing.landing.error.ErrorServiceException;

@Service
public class ServicioDAORest {

  @Autowired
  private RestTemplate restTemplate;

  public void crear(ServicioDTO servicio) throws ErrorServiceException {
    try {
      String uri = "http://localhost:8081/api/servicio";
      restTemplate.postForEntity(uri, servicio, ServicioDTO.class);
    } catch (Exception ex) {
      ex.printStackTrace();
      throw new ErrorServiceException("Error de Sistemas");
    }
  }

  public ServicioDTO buscar(Long id) throws ErrorServiceException {

    try {

      String uri = "http://localhost:8081/api/servicio/" + id;

      ResponseEntity<ServicioDTO> response = restTemplate.getForEntity(uri, ServicioDTO.class);
      ServicioDTO servicio = response.getBody();

      return servicio;

    } catch (Exception ex) {
      ex.printStackTrace();
      throw new ErrorServiceException("Error de Sistemas");
    }
  }

  public void actualizar(ServicioDTO servicio) throws ErrorServiceException {

    try {

      String uri = "http://localhost:8081/api/servicio/" + servicio.getId();
      restTemplate.put(uri, servicio);

    } catch (Exception ex) {
      ex.printStackTrace();
      throw new ErrorServiceException("Error de Sistemas");
    }
  }

  public void eliminar(Long id) throws ErrorServiceException {

    try {

      String uri = "http://localhost:8081/api/servicio/" + id;
      restTemplate.delete(uri);

    } catch (Exception ex) {
      ex.printStackTrace();
      throw new ErrorServiceException("Error de Sistemas");
    }
  }

  public List<ServicioDTO> listar() throws ErrorServiceException {
    try {
      String uri = "http://localhost:8081/api/servicio";

      ResponseEntity<ServicioDTO[]> response = restTemplate.getForEntity(uri, ServicioDTO[].class);
      ServicioDTO[] servicioes = response.getBody();
      List<ServicioDTO> listaservicioes = Arrays.asList(servicioes);
      return listaservicioes;
    } catch (Exception ex) {
      ex.printStackTrace();
      throw new ErrorServiceException("Error de Sistemas");
    }
  }

}

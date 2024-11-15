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

  public ServicioDTO buscar(String id) throws ErrorServiceException {

    try {

      String uri = "${URL}/api/servicio/" + id;

      ResponseEntity<ServicioDTO> response = restTemplate.getForEntity(uri, ServicioDTO.class);
      ServicioDTO servicio = response.getBody();

      return servicio;

    } catch (Exception ex) {
      ex.printStackTrace();
      throw new ErrorServiceException("Error de Sistemas");
    }
  }

  public List<ServicioDTO> listar() throws ErrorServiceException {
    try {
      String uri = "${URL}/api/servicio";

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

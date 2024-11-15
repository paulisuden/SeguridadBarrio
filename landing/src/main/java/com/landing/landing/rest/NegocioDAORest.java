package com.landing.landing.rest;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.landing.landing.dto.NegocioDTO;
import com.landing.landing.error.ErrorServiceException;

@Service
public class NegocioDAORest {

  @Autowired
  private RestTemplate restTemplate;

  public NegocioDTO buscar(String id) throws ErrorServiceException {

    try {

      String uri = "${URL}/api/negocio/" + id;

      ResponseEntity<NegocioDTO> response = restTemplate.getForEntity(uri, NegocioDTO.class);
      NegocioDTO negocio = response.getBody();

      return negocio;

    } catch (Exception ex) {
      ex.printStackTrace();
      throw new ErrorServiceException("Error de Sistemas");
    }
  }

  public List<NegocioDTO> listar() throws ErrorServiceException {
    try {
      String uri = "${URL}/api/negocio";

      ResponseEntity<NegocioDTO[]> response = restTemplate.getForEntity(uri, NegocioDTO[].class);
      NegocioDTO[] negocios = response.getBody();
      List<NegocioDTO> listaNegocios = Arrays.asList(negocios);
      return listaNegocios;
    } catch (Exception ex) {
      ex.printStackTrace();
      throw new ErrorServiceException("Error de Sistemas");
    }
  }

}

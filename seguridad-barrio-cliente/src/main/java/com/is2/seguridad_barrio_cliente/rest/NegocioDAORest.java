package com.is2.seguridad_barrio_cliente.rest;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.is2.seguridad_barrio_cliente.dto.NegocioDTO;
import com.is2.seguridad_barrio_cliente.error.ErrorServiceException;

@Service
public class NegocioDAORest {

  @Autowired
  private RestTemplate restTemplate;

  public void crear(NegocioDTO negocio) throws ErrorServiceException {
    try {
      String uri = "http://localhost:8081/api/negocio";
      restTemplate.postForEntity(uri, negocio, NegocioDTO.class);
    } catch (Exception ex) {
      ex.printStackTrace();
      throw new ErrorServiceException("Error de Sistemas");
    }
  }

  public NegocioDTO buscar(Long id) throws ErrorServiceException {

    try {

      String uri = "http://localhost:8081/api/negocio/" + id;

      ResponseEntity<NegocioDTO> response = restTemplate.getForEntity(uri, NegocioDTO.class);
      NegocioDTO negocio = response.getBody();

      return negocio;

    } catch (Exception ex) {
      ex.printStackTrace();
      throw new ErrorServiceException("Error de Sistemas");
    }
  }

  public void actualizar(NegocioDTO negocio) throws ErrorServiceException {

    try {

      String uri = "http://localhost:8081/api/negocio/" + negocio.getId();
      restTemplate.put(uri, negocio);

    } catch (Exception ex) {
      ex.printStackTrace();
      throw new ErrorServiceException("Error de Sistemas");
    }
  }

  public void eliminar(Long id) throws ErrorServiceException {

    try {

      String uri = "http://localhost:8081/api/negocio/" + id;
      restTemplate.delete(uri);

    } catch (Exception ex) {
      ex.printStackTrace();
      throw new ErrorServiceException("Error de Sistemas");
    }
  }

  public List<NegocioDTO> listar() throws ErrorServiceException {
    try {
      String uri = "http://localhost:8081/api/negocio";

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

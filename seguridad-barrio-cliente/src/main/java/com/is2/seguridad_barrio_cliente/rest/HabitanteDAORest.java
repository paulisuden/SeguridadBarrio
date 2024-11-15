package com.is2.seguridad_barrio_cliente.rest;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.is2.seguridad_barrio_cliente.dto.PersonaDTO;
import com.is2.seguridad_barrio_cliente.error.ErrorServiceException;

@Service
public class HabitanteDAORest {

  @Autowired
  private RestTemplate restTemplate;

  public void crear(PersonaDTO habitante) throws ErrorServiceException {
    try {

      String uri = "http://localhost:8081/api/persona";
      restTemplate.postForEntity(uri, habitante, PersonaDTO.class);
    } catch (Exception ex) {
      ex.printStackTrace();
      throw new ErrorServiceException("Error de Sistemas");
    }
  }

  public PersonaDTO buscar(String id) throws ErrorServiceException {

    try {

      String uri = "http://localhost:8081/api/persona/" + id;

      ResponseEntity<PersonaDTO> response = restTemplate.getForEntity(uri, PersonaDTO.class);
      PersonaDTO habitante = response.getBody();

      return habitante;

    } catch (Exception ex) {
      ex.printStackTrace();
      throw new ErrorServiceException("Error de Sistemas");
    }
  }

  public void actualizar(PersonaDTO habitante) throws ErrorServiceException {

    try {

      String uri = "http://localhost:8081/api/persona/" + habitante.getId();
      restTemplate.put(uri, habitante);

    } catch (Exception ex) {
      ex.printStackTrace();
      throw new ErrorServiceException("Error de Sistemas");
    }
  }

  public void eliminar(String id) throws ErrorServiceException {

    try {

      String uri = "http://localhost:8081/api/persona/" + id;
      restTemplate.delete(uri);

    } catch (Exception ex) {
      ex.printStackTrace();
      throw new ErrorServiceException("Error de Sistemas");
    }
  }

  public List<PersonaDTO> listar() throws ErrorServiceException {
    try {
      String uri = "http://localhost:8081/api/persona/habitantes";

      ResponseEntity<PersonaDTO[]> response = restTemplate.getForEntity(uri, PersonaDTO[].class);
      PersonaDTO[] habitantes = response.getBody();
      List<PersonaDTO> listahabitantes = Arrays.asList(habitantes);
      return listahabitantes;
    } catch (Exception ex) {
      ex.printStackTrace();
      throw new ErrorServiceException("Error de Sistemas");
    }
  }

  public PersonaDTO buscarPorUsuarioId(String id) throws ErrorServiceException {
    try {

      String uri = "http://localhost:8081/api/persona/usuario/" + id;

      ResponseEntity<PersonaDTO> response = restTemplate.getForEntity(uri, PersonaDTO.class);
      PersonaDTO habitante = response.getBody();

      return habitante;

    } catch (Exception ex) {
      ex.printStackTrace();
      throw new ErrorServiceException("Error de Sistemas");
    }

  }

}

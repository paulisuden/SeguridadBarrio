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
public class EmpleadoDAORest {

  @Autowired
  private RestTemplate restTemplate;

  public void crear(PersonaDTO empleado) throws ErrorServiceException {
    try {

      String uri = "http://localhost:8081/api/persona";
      restTemplate.postForEntity(uri, empleado, PersonaDTO.class);
    } catch (Exception ex) {
      ex.printStackTrace();
      throw new ErrorServiceException("Error de Sistemas");
    }
  }

  public PersonaDTO buscar(Long id) throws ErrorServiceException {

    try {

      String uri = "http://localhost:8081/api/persona/" + id;

      ResponseEntity<PersonaDTO> response = restTemplate.getForEntity(uri, PersonaDTO.class);
      PersonaDTO empleado = response.getBody();

      return empleado;

    } catch (Exception ex) {
      ex.printStackTrace();
      throw new ErrorServiceException("Error de Sistemas");
    }
  }

  public void actualizar(PersonaDTO empleado) throws ErrorServiceException {

    try {

      String uri = "http://localhost:8081/api/persona/" + empleado.getId();
      restTemplate.put(uri, empleado);

    } catch (Exception ex) {
      ex.printStackTrace();
      throw new ErrorServiceException("Error de Sistemas");
    }
  }

  public void eliminar(Long id) throws ErrorServiceException {

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
      String uri = "http://localhost:8081/api/persona";

      ResponseEntity<PersonaDTO[]> response = restTemplate.getForEntity(uri, PersonaDTO[].class);
      PersonaDTO[] empleados = response.getBody();
      List<PersonaDTO> listaEmpleados = Arrays.asList(empleados);
      return listaEmpleados;
    } catch (Exception ex) {
      ex.printStackTrace();
      throw new ErrorServiceException("Error de Sistemas");
    }
  }

}

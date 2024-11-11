package com.is2.seguridad_barrio_cliente.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.is2.seguridad_barrio_cliente.dto.ServicioDTO;
import com.is2.seguridad_barrio_cliente.error.ErrorServiceException;
import com.is2.seguridad_barrio_cliente.rest.ServicioDAORest;

@Service
public class ServicioService {

  @Autowired
  private ServicioDAORest dao;

  public void crear(String nombre) throws ErrorServiceException {

    try {

      ServicioDTO servicio = new ServicioDTO();
      servicio.setNombre(nombre);

      dao.crear(servicio);

    } catch (ErrorServiceException e) {
      throw e;

    } catch (Exception ex) {
      ex.printStackTrace();
      throw new ErrorServiceException("Error de Sistemas");
    }
  }

  public ServicioDTO buscar(Long id) throws ErrorServiceException {

    try {

      if (id == 0) {
        throw new ErrorServiceException("Debe indicar el autor");
      }

      ServicioDTO servicio = dao.buscar(id);

      return servicio;

    } catch (ErrorServiceException ex) {
      throw ex;
    } catch (Exception ex) {
      ex.printStackTrace();
      throw new ErrorServiceException("Error de sistema");
    }
  }

  public void modificar(Long id, String nombre) throws ErrorServiceException {

    try {

      ServicioDTO servicio = new ServicioDTO();
      servicio.setId(id);
      servicio.setNombre(nombre);

      dao.actualizar(servicio);

    } catch (ErrorServiceException e) {
      throw e;
    } catch (Exception ex) {
      ex.printStackTrace();
      throw new ErrorServiceException("Error de Sistemas");
    }
  }

  public void eliminar(Long id) throws ErrorServiceException {

    try {

      if (id == 0) {
        throw new ErrorServiceException("Debe indicar el país");
      }

      dao.eliminar(id);

    } catch (ErrorServiceException ex) {
      throw ex;

    } catch (Exception ex) {
      ex.printStackTrace();
      throw new ErrorServiceException("Error de sistema");
    }

  }

  public List<ServicioDTO> listar() throws ErrorServiceException {
    try {
      return dao.listar();
    } catch (Exception ex) {
      ex.printStackTrace();
      throw new ErrorServiceException("Error de sistema");
    }
  }

}

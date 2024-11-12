package com.landing.landing.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.landing.landing.dto.ServicioDTO;
import com.landing.landing.error.ErrorServiceException;
import com.landing.landing.rest.ServicioDAORest;

@Service
public class ServicioService {

  @Autowired
  private ServicioDAORest dao;

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

  public List<ServicioDTO> listar() throws ErrorServiceException {
    try {
      return dao.listar();
    } catch (Exception ex) {
      ex.printStackTrace();
      throw new ErrorServiceException("Error de sistema");
    }
  }

}

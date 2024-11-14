package com.landing.landing.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.landing.landing.dto.NegocioDTO;
import com.landing.landing.error.ErrorServiceException;
import com.landing.landing.rest.NegocioDAORest;

@Service
public class NegocioService {

  @Autowired
  private NegocioDAORest dao;

  public NegocioDTO buscar(String id) throws ErrorServiceException {

    try {

      if (id == null || id.isEmpty()) {
        throw new ErrorServiceException("Debe indicar el Negocio");
      }

      NegocioDTO negocio = dao.buscar(id);

      return negocio;

    } catch (ErrorServiceException ex) {
      throw ex;
    } catch (Exception ex) {
      ex.printStackTrace();
      throw new ErrorServiceException("Error de sistema");
    }
  }

  public List<NegocioDTO> listar() throws ErrorServiceException {
    try {
      return dao.listar();
    } catch (Exception ex) {
      ex.printStackTrace();
      throw new ErrorServiceException("Error de sistema");
    }
  }
}

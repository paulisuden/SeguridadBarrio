package com.is2.seguridad_barrio_cliente.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.is2.seguridad_barrio_cliente.dto.NegocioDTO;
import com.is2.seguridad_barrio_cliente.error.ErrorServiceException;
import com.is2.seguridad_barrio_cliente.rest.NegocioDAORest;

@Service
public class NegocioService {

  @Autowired
  private NegocioDAORest dao;

  public void crear(String nombre, String idDireccion, List<String> idServicios) throws ErrorServiceException {

    try {

      NegocioDTO negocio = new NegocioDTO();
      negocio.setNombre(nombre);
      negocio.setServiciosId(idServicios);
      negocio.setDireccionId(idDireccion);

      dao.crear(negocio);

    } catch (ErrorServiceException e) {
      throw e;

    } catch (Exception ex) {
      ex.printStackTrace();
      throw new ErrorServiceException("Error de Sistemas");
    }
  }

  public NegocioDTO buscar(String id) throws ErrorServiceException {

    try {

      if ("0".equals(id)) {
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

  public void modificar(String id, String nombre, String idDireccion, List<String> idServicios)
      throws ErrorServiceException {

    try {

      NegocioDTO negocio = new NegocioDTO();
      negocio.setId(id);
      negocio.setNombre(nombre);
      negocio.setServiciosId(idServicios);
      negocio.setDireccionId(idDireccion);

      dao.actualizar(negocio);

    } catch (ErrorServiceException e) {
      throw e;
    } catch (Exception ex) {
      ex.printStackTrace();
      throw new ErrorServiceException("Error de Sistemas");
    }
  }

  public void eliminar(String id) throws ErrorServiceException {

    try {

      dao.eliminar(id);

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

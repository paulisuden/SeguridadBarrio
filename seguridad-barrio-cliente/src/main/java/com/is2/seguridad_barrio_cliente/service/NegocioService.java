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

  public void crear(String nombre, Long idDireccion, List<Long> idServicios) throws ErrorServiceException {

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

  public NegocioDTO buscar(Long id) throws ErrorServiceException {

    try {

      if (id == 0) {
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

  public void modificar(Long id, String nombre, Long idDireccion, List<Long> idServicios) throws ErrorServiceException {

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

  public void eliminar(Long id) throws ErrorServiceException {

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

package com.is2.seguridad_barrio_cliente.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.is2.seguridad_barrio_cliente.dto.ImagenDTO;
import com.is2.seguridad_barrio_cliente.dto.NegocioDTO;
import com.is2.seguridad_barrio_cliente.error.ErrorServiceException;
import com.is2.seguridad_barrio_cliente.rest.NegocioDAORest;

@Service
public class NegocioService {

  @Autowired
  private NegocioDAORest dao;

  @Autowired
  private ImagenService imagenService;

  public void crear(
      String nombre,
      String idDireccion,
      List<String> idServicios,
      MultipartFile archivoImagen) throws ErrorServiceException {

    try {

      NegocioDTO negocio = new NegocioDTO();
      negocio.setNombre(nombre);
      negocio.setServiciosId(idServicios);
      negocio.setDireccionId(idDireccion);

      if (archivoImagen != null && archivoImagen.getSize() > 0) {
        ImagenDTO img = imagenService.crear(archivoImagen);
        if (img != null) {
          negocio.setImagen(img);
          negocio.setImagenId(img.getId());
        }
      }
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

  public void modificar(
      String id,
      String nombre,
      String idDireccion,
      List<String> idServicios,
      MultipartFile archivoImagen) throws ErrorServiceException {

    try {
      NegocioDTO negocio = dao.buscar(id);
      negocio.setId(id);
      negocio.setNombre(nombre);
      negocio.setServiciosId(idServicios);
      negocio.setDireccionId(idDireccion);
      if (archivoImagen != null && archivoImagen.getSize() > 0) {
        imagenService.modificar(negocio.getImagen().getId(), archivoImagen);
      }
      negocio.setImagenId(negocio.getImagen().getId());
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

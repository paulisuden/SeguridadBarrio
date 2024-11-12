package com.is2.seguridad_barrio_cliente.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.is2.seguridad_barrio_cliente.dto.ImagenDTO;
import com.is2.seguridad_barrio_cliente.dto.ServicioDTO;
import com.is2.seguridad_barrio_cliente.error.ErrorServiceException;
import com.is2.seguridad_barrio_cliente.rest.ServicioDAORest;

@Service
public class ServicioService {

  @Autowired
  private ServicioDAORest dao;

  @Autowired
  private ImagenService imagenService;

  public void crear(String nombre, MultipartFile archivoImagen) throws ErrorServiceException {

    try {

      ServicioDTO servicio = new ServicioDTO();
      servicio.setNombre(nombre);
      servicio.setImagenId(0l);

      if (archivoImagen != null && archivoImagen.getSize() > 0) {
        ImagenDTO img = imagenService.crear(archivoImagen);
        if (img != null) {
          servicio.setImagen(img);
          servicio.setImagenId(img.getId());
        }
      }
      dao.crear(servicio);

    } catch (ErrorServiceException e) {
      throw e;

    } catch (Exception ex) {
      ex.printStackTrace();
      throw new ErrorServiceException("Error de Sistemas. " + ex.toString());
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

  public void modificar(Long id, String nombre, MultipartFile archivoImagen) throws ErrorServiceException {

    try {

      ServicioDTO servicio = dao.buscar(id);
      servicio.setNombre(nombre);

      if (archivoImagen != null && archivoImagen.getSize() > 0) {

        ImagenDTO img = imagenService.crear(archivoImagen);

        if (img != null) {

          // Elimina las imagen anterior
          if (servicio.getImagen() != null)
            imagenService.eliminar(servicio.getImagen().getId());

          servicio.setImagen(img);
          servicio.setImagenId(img.getId());
        }

      }
      dao.actualizar(servicio);

    } catch (ErrorServiceException e) {
      throw e;
    } catch (Exception ex) {
      ex.printStackTrace();
      throw new ErrorServiceException("Error de Sistemas. " + ex.toString());
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

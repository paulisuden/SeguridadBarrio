package com.is2.seguridad_barrio_cliente.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.is2.seguridad_barrio_cliente.dto.ImagenDTO;
import com.is2.seguridad_barrio_cliente.error.ErrorServiceException;
import com.is2.seguridad_barrio_cliente.rest.ImagenDAORest;

@Service
public class ImagenService {

    @Autowired
    private ImagenDAORest dao;

    public ImagenDTO crear(MultipartFile archivo) throws ErrorServiceException {

        try {

            if (archivo.getSize() == 0)
                throw new ErrorServiceException("El archivo no puede ser nulo");

            ImagenDTO imagen = new ImagenDTO();
            imagen.setName(archivo.getOriginalFilename());
            imagen.setContenido(archivo.getBytes());
            imagen.setMime(archivo.getContentType());
            return dao.crear(imagen);
        } catch (ErrorServiceException e) {
            throw e;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas. " + ex.toString());
        }
    }

    public ImagenDTO buscar(String id) throws ErrorServiceException {

        try {
            ImagenDTO imagen = dao.buscar(id);

            return imagen;

        } catch (ErrorServiceException ex) {
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de sistema");
        }
    }

    public void modificar(String id, MultipartFile archivo) throws ErrorServiceException {

        try {
            ImagenDTO imagen = new ImagenDTO();
            imagen.setId(id);
            imagen.setName(archivo.getOriginalFilename());
            imagen.setContenido(archivo.getBytes());
            imagen.setMime(archivo.getContentType());
            System.out.println(archivo.getBytes().length);
            dao.actualizar(imagen);

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

    public List<ImagenDTO> listar() throws ErrorServiceException {
        try {
            return dao.listar();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de sistema");
        }
    }

}

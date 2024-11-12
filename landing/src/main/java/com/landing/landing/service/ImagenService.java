package com.landing.landing.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.landing.landing.dto.ImagenDTO;
import com.landing.landing.error.ErrorServiceException;
import com.landing.landing.rest.ImagenDAORest;

@Service
public class ImagenService {

    @Autowired
    private ImagenDAORest dao;

    public ImagenDTO buscar(Long id) throws ErrorServiceException {

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

    public List<ImagenDTO> listar() throws ErrorServiceException {
        try {
            return dao.listar();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de sistema");
        }
    }

}

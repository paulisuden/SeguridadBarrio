package com.is2.seguridad_barrio_cliente.service;

import com.is2.seguridad_barrio_cliente.dto.PaisDTO;
import com.is2.seguridad_barrio_cliente.error.ErrorServiceException;
import com.is2.seguridad_barrio_cliente.rest.PaisDAORest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaisService {

    @Autowired
    private PaisDAORest dao;

    public void crear(String nombre) throws ErrorServiceException {

        try {

            PaisDTO pais = new PaisDTO();
            pais.setNombre(nombre);

            dao.crear(pais);

        } catch (ErrorServiceException e) {
            throw e;

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public PaisDTO buscar(String id) throws ErrorServiceException {

        try {

            if ("0".equals(id)) {
                throw new ErrorServiceException("Debe indicar el autor");
            }

            PaisDTO pais = dao.buscar(id);

            return pais;

        } catch (ErrorServiceException ex) {
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de sistema");
        }
    }

    public void modificar(String id, String nombre) throws ErrorServiceException {

        try {

            PaisDTO pais = new PaisDTO();
            pais.setId(id);
            pais.setNombre(nombre);

            dao.actualizar(pais);

        } catch (ErrorServiceException e) {
            throw e;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public void eliminar(String id) throws ErrorServiceException {

        try {

            if ("0".equals(id)) {
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

    public List<PaisDTO> listar() throws ErrorServiceException {
        try {
            return dao.listar();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de sistema");
        }
    }

}

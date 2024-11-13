package com.is2.seguridad_barrio_cliente.service;

import com.is2.seguridad_barrio_cliente.dto.LocalidadDTO;
import com.is2.seguridad_barrio_cliente.error.ErrorServiceException;
import com.is2.seguridad_barrio_cliente.rest.LocalidadDAORest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocalidadService {
    @Autowired
    private LocalidadDAORest dao;

    public void crear(String nombre, String codigoPostal, String idDepartamento) throws ErrorServiceException {

        try {

            LocalidadDTO localidad = new LocalidadDTO();
            localidad.setNombre(nombre);
            localidad.setCodigoPostal(codigoPostal);
            localidad.setDepartamentoId(idDepartamento);

            dao.crear(localidad);

        } catch (ErrorServiceException e) {
            throw e;

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public LocalidadDTO buscar(String id) throws ErrorServiceException {

        try {

            if ("0".equals(id)) {
                throw new ErrorServiceException("Debe indicar la localidad");
            }

            LocalidadDTO localidad = dao.buscar(id);

            return localidad;

        } catch (ErrorServiceException ex) {
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de sistema");
        }
    }

    public void modificar(String id, String nombre, String codigoPostal, String idDepartamento)
            throws ErrorServiceException {

        try {

            LocalidadDTO localidad = new LocalidadDTO();
            localidad.setId(id);
            localidad.setNombre(nombre);
            localidad.setCodigoPostal(codigoPostal);
            localidad.setDepartamentoId(idDepartamento);

            dao.actualizar(localidad);

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

    public List<LocalidadDTO> listar() throws ErrorServiceException {
        try {
            return dao.listar();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de sistema");
        }
    }
}

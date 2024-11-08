package com.is2.seguridad_barrio_cliente.service;

import com.is2.seguridad_barrio_cliente.dto.LocalidadDTO;
import com.is2.seguridad_barrio_cliente.dto.DireccionDTO;
import com.is2.seguridad_barrio_cliente.error.ErrorServiceException;
import com.is2.seguridad_barrio_cliente.rest.LocalidadDAORest;
import com.is2.seguridad_barrio_cliente.rest.DireccionDAORest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DireccionService {

    @Autowired
    private LocalidadService daoLocalidad;
    @Autowired
    private DireccionDAORest dao;

    public void crear(String calle, String numeracion, String barrio, String observacion, Long idLocalidad) throws ErrorServiceException {

        try {

            LocalidadDTO localidad = daoLocalidad.buscar(idLocalidad);
            if (localidad == null) {
                throw new ErrorServiceException("Localidad no encontrada");
            }

            DireccionDTO direccion = new DireccionDTO();
            direccion.setCalle(calle);
            direccion.setBarrio(barrio);
            direccion.setNumeracion(numeracion);
            direccion.setObservacion(observacion);
            direccion.setLocalidad(localidad);

            dao.crear(direccion);

        } catch (ErrorServiceException e) {
            throw e;

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public DireccionDTO buscar(Long id) throws ErrorServiceException {

        try {

            if (id == 0) {
                throw new ErrorServiceException("Debe indicar la direccion");
            }

            DireccionDTO direccion = dao.buscar(id);

            return direccion;

        } catch (ErrorServiceException ex) {
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de sistema");
        }
    }

    public void modificar(Long id, String calle, String numeracion, String barrio, String observacion, Long idLocalidad) throws ErrorServiceException {

        try {
            LocalidadDTO localidad = daoLocalidad.buscar(idLocalidad);

            DireccionDTO direccion = new DireccionDTO();
            direccion.setId(id);
            direccion.setCalle(calle);
            direccion.setBarrio(barrio);
            direccion.setNumeracion(numeracion);
            direccion.setObservacion(observacion);
            direccion.setLocalidad(localidad);

            dao.actualizar(direccion);

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

    public List<DireccionDTO> listar() throws ErrorServiceException {
        try {
            return dao.listar();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de sistema");
        }
    }
}

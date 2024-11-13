package com.is2.seguridad_barrio_cliente.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.is2.seguridad_barrio_cliente.dto.InmuebleDTO;
import com.is2.seguridad_barrio_cliente.error.ErrorServiceException;
import com.is2.seguridad_barrio_cliente.rest.InmuebleDAORest;

@Service
public class InmuebleService {
    @Autowired
    private InmuebleDAORest dao;

    public void crear(
            String numeracion,
            String calle,
            String manzana,
            String idNegocio) throws ErrorServiceException {

        try {

            InmuebleDTO inmueble = new InmuebleDTO();
            inmueble.setCalle(calle);
            inmueble.setManzana(manzana);
            inmueble.setNumeracion(numeracion);
            inmueble.setIdUnidadDeNegocio(idNegocio);
            dao.crear(inmueble);

        } catch (ErrorServiceException e) {
            throw e;

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public InmuebleDTO buscar(String id) throws ErrorServiceException {

        try {

            if ("0".equals(id)) {
                throw new ErrorServiceException("Debe indicar la localidad");
            }

            InmuebleDTO inmueble = dao.buscar(id);

            return inmueble;

        } catch (ErrorServiceException ex) {
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de sistema");
        }
    }

    public void modificar(
            String id,
            String numeracion,
            String calle,
            String manzana,
            String idNegocio) throws ErrorServiceException {

        try {

            InmuebleDTO inmueble = new InmuebleDTO();
            inmueble.setId(id);
            inmueble.setCalle(calle);
            inmueble.setManzana(manzana);
            inmueble.setNumeracion(numeracion);
            inmueble.setIdUnidadDeNegocio(idNegocio);
            dao.actualizar(inmueble);

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

    public List<InmuebleDTO> listar() throws ErrorServiceException {
        try {
            return dao.listar();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de sistema");
        }
    }

}

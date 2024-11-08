package com.is2.seguridad_barrio_cliente.service;

import com.is2.seguridad_barrio_cliente.dto.VisitanteDTO;
import com.is2.seguridad_barrio_cliente.enumeration.TipoVisita;
import com.is2.seguridad_barrio_cliente.error.ErrorServiceException;
import com.is2.seguridad_barrio_cliente.rest.VisitanteDAORest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitanteService {

    @Autowired
    private VisitanteDAORest dao;

    public void crear(String nombre, String apellido, String numeroDeDocumento, TipoVisita tipoVisita) throws ErrorServiceException {

        try {

            VisitanteDTO visitante = new VisitanteDTO();
            visitante.setNombre(nombre);
            visitante.setApellido(apellido);
            visitante.setNumeroDeDocumento(numeroDeDocumento);
            visitante.setTipoVisita(tipoVisita);

            dao.crear(visitante);

        } catch (ErrorServiceException e) {
            throw e;

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public VisitanteDTO buscar(Long id) throws ErrorServiceException {

        try {

            if (id == 0) {
                throw new ErrorServiceException("Debe indicar el visitante");
            }

            VisitanteDTO visitante = dao.buscar(id);

            return visitante;

        } catch (ErrorServiceException ex) {
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de sistema");
        }
    }

    public void modificar(Long id, String nombre, String apellido, String numeroDeDocumento, TipoVisita tipoVisita) throws ErrorServiceException {

        try {

            VisitanteDTO visitante = new VisitanteDTO();
            visitante.setId(id);
            visitante.setNombre(nombre);
            visitante.setApellido(apellido);
            visitante.setNumeroDeDocumento(numeroDeDocumento);
            visitante.setTipoVisita(tipoVisita);

            dao.actualizar(visitante);

        } catch (ErrorServiceException e) {
            throw e;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public void eliminar(Long id) throws ErrorServiceException {

        try {

            if (id == 0) {
                throw new ErrorServiceException("Debe indicar el visitante");
            }

            dao.eliminar(id);

        } catch (ErrorServiceException ex) {
            throw ex;

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de sistema");
        }

    }

    public List<VisitanteDTO> listar() throws ErrorServiceException {
        try {
            return dao.listar();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de sistema");
        }
    }

}

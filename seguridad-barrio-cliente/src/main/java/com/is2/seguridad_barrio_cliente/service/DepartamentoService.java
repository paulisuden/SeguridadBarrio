package com.is2.seguridad_barrio_cliente.service;

import com.is2.seguridad_barrio_cliente.dto.DepartamentoDTO;
import com.is2.seguridad_barrio_cliente.error.ErrorServiceException;
import com.is2.seguridad_barrio_cliente.rest.DepartamentoDAORest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartamentoService {

    @Autowired
    private DepartamentoDAORest dao;

    public void crear(String nombre, String idProvincia) throws ErrorServiceException {

        try {

            DepartamentoDTO departamento = new DepartamentoDTO();
            departamento.setNombre(nombre);
            departamento.setProvinciaId(idProvincia);

            dao.crear(departamento);

        } catch (ErrorServiceException e) {
            throw e;

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public DepartamentoDTO buscar(String id) throws ErrorServiceException {

        try {

            if ("0".equals(id)) {
                throw new ErrorServiceException("Debe indicar el departamento");
            }

            DepartamentoDTO departamento = dao.buscar(id);

            return departamento;

        } catch (ErrorServiceException ex) {
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de sistema");
        }
    }

    public void modificar(String id, String nombre, String idProvincia) throws ErrorServiceException {

        try {

            DepartamentoDTO departamento = new DepartamentoDTO();
            departamento.setId(id);
            departamento.setNombre(nombre);
            departamento.setProvinciaId(idProvincia);

            dao.actualizar(departamento);

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

    public List<DepartamentoDTO> listar() throws ErrorServiceException {
        try {
            return dao.listar();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de sistema");
        }
    }
}

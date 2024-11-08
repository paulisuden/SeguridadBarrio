package com.is2.seguridad_barrio_cliente.service;

import com.is2.seguridad_barrio_cliente.dto.ProvinciaDTO;
import com.is2.seguridad_barrio_cliente.dto.DepartamentoDTO;
import com.is2.seguridad_barrio_cliente.error.ErrorServiceException;
import com.is2.seguridad_barrio_cliente.rest.DepartamentoDAORest;
import com.is2.seguridad_barrio_cliente.rest.ProvinciaDAORest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartamentoService {

    @Autowired
    private DepartamentoDAORest dao;
    @Autowired
    private ProvinciaDAORest daoProvincia;

    public void crear(String nombre, Long idProvincia) throws ErrorServiceException {

        try {

            ProvinciaDTO provincia = daoProvincia.buscar(idProvincia);
            if (provincia == null) {
                throw new ErrorServiceException("Provincia no encontrada");
            }

            DepartamentoDTO departamento = new DepartamentoDTO();
            departamento.setNombre(nombre);
            departamento.setProvincia(provincia);

            dao.crear(departamento);

        } catch (ErrorServiceException e) {
            throw e;

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public DepartamentoDTO buscar(Long id) throws ErrorServiceException {

        try {

            if (id == 0) {
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

    public void modificar(Long id, String nombre, Long idProvincia) throws ErrorServiceException {

        try {

            ProvinciaDTO provincia = daoProvincia.buscar(idProvincia);

            DepartamentoDTO departamento = new DepartamentoDTO();
            departamento.setId(id);
            departamento.setNombre(nombre);
            departamento.setProvincia(provincia);

            dao.actualizar(departamento);

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

    public List<DepartamentoDTO> listar() throws ErrorServiceException {
        try {
            return dao.listar();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de sistema");
        }
    }
}

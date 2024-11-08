package com.is2.seguridad_barrio_cliente.service;

import com.is2.seguridad_barrio_cliente.dto.DepartamentoDTO;
import com.is2.seguridad_barrio_cliente.dto.LocalidadDTO;
import com.is2.seguridad_barrio_cliente.error.ErrorServiceException;
import com.is2.seguridad_barrio_cliente.rest.DepartamentoDAORest;
import com.is2.seguridad_barrio_cliente.rest.LocalidadDAORest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocalidadService {

    @Autowired
    private DepartamentoDAORest daoDepartamento;
    @Autowired
    private LocalidadDAORest dao;

    public void crear(String nombre, String codigoPostal, Long idDepartamento) throws ErrorServiceException {

        try {

            DepartamentoDTO departamento = daoDepartamento.buscar(idDepartamento);
            if (departamento == null) {
                throw new ErrorServiceException("Departamento no encontrado");
            }

            LocalidadDTO localidad = new LocalidadDTO();
            localidad.setNombre(nombre);
            localidad.setCodigoPostal(codigoPostal);
            localidad.setDepartamentoDTO(departamento);

            dao.crear(localidad);

        } catch (ErrorServiceException e) {
            throw e;

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public LocalidadDTO buscar(Long id) throws ErrorServiceException {

        try {

            if (id == 0) {
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

    public void modificar(Long id, String nombre, String codigoPostal, Long idDepartamento) throws ErrorServiceException {

        try {

            DepartamentoDTO departamento = daoDepartamento.buscar(idDepartamento);

            LocalidadDTO localidad = new LocalidadDTO();
            localidad.setId(id);
            localidad.setNombre(nombre);
            localidad.setCodigoPostal(codigoPostal);
            localidad.setDepartamentoDTO(departamento);

            dao.actualizar(localidad);

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

    public List<LocalidadDTO> listar() throws ErrorServiceException {
        try {
            return dao.listar();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de sistema");
        }
    }
}

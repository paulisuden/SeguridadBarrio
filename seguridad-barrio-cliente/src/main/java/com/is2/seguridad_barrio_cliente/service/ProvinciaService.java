package com.is2.seguridad_barrio_cliente.service;

import com.is2.seguridad_barrio_cliente.dto.PaisDTO;
import com.is2.seguridad_barrio_cliente.dto.ProvinciaDTO;
import com.is2.seguridad_barrio_cliente.error.ErrorServiceException;
import com.is2.seguridad_barrio_cliente.rest.PaisDAORest;
import com.is2.seguridad_barrio_cliente.rest.ProvinciaDAORest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinciaService {

    @Autowired
    private ProvinciaDAORest dao;
    @Autowired
    private PaisDAORest daoPais;

    public void crear(String nombre, Long idPais) throws ErrorServiceException {

        try {

            ProvinciaDTO provincia = new ProvinciaDTO();
            provincia.setNombre(nombre);
            provincia.setPaisId(idPais);

            dao.crear(provincia);

        } catch (ErrorServiceException e) {
            throw e;

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public ProvinciaDTO buscar(Long id) throws ErrorServiceException {

        try {

            if (id == 0) {
                throw new ErrorServiceException("Debe indicar la provincia");
            }

            ProvinciaDTO provincia = dao.buscar(id);

            return provincia;

        } catch (ErrorServiceException ex) {
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de sistema");
        }
    }

    public void modificar(Long id, String nombre, Long idPais) throws ErrorServiceException {

        try {

            ProvinciaDTO provincia = new ProvinciaDTO();
            provincia.setId(id);
            provincia.setNombre(nombre);
            provincia.setPaisId(idPais);

            dao.actualizar(provincia);

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

    public List<ProvinciaDTO> listar() throws ErrorServiceException {
        try {
            return dao.listar();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de sistema");
        }
    }
}

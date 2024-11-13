package com.is2.seguridad_barrio_cliente.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.is2.seguridad_barrio_cliente.dto.ContactoDTO;
import com.is2.seguridad_barrio_cliente.enumeration.TipoContacto;
import com.is2.seguridad_barrio_cliente.enumeration.TipoTelefono;
import com.is2.seguridad_barrio_cliente.error.ErrorServiceException;
import com.is2.seguridad_barrio_cliente.rest.ContactoDAORest;

@Service
public class ContactoService {

    @Autowired
    private ContactoDAORest dao;

    public ContactoDTO crear(
            TipoContacto tipo,
            String observacion,
            String email,
            String telefono,
            TipoTelefono tipoTelefono) throws ErrorServiceException {

        try {
            ContactoDTO contacto = new ContactoDTO();
            contacto.setTipoContacto(tipo);
            contacto.setObservacion(observacion);
            contacto.setEmail(email);
            contacto.setTelefono(telefono);
            contacto.setTipoTelefono(tipoTelefono);
            return dao.crear(contacto);
        } catch (ErrorServiceException e) {
            throw e;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas. " + ex.toString());
        }
    }

    public ContactoDTO buscar(String id) throws ErrorServiceException {


        try {
            return dao.buscar(id);
        } catch (ErrorServiceException ex) {
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de sistema");
        }
    }

    public void modificar(
            String id,

            TipoContacto tipo,
            String observacion,
            String email,
            String telefono,
            TipoTelefono tipoTelefono) throws ErrorServiceException {

        try {
            ContactoDTO contacto = new ContactoDTO();
            contacto.setId(id);
            contacto.setTipoContacto(tipo);
            contacto.setObservacion(observacion);
            contacto.setEmail(email);
            contacto.setTelefono(telefono);
            contacto.setTipoTelefono(tipoTelefono);
            dao.actualizar(contacto);
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

    public List<ContactoDTO> listar() throws ErrorServiceException {
        try {
            return dao.listar();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de sistema");
        }
    }

}

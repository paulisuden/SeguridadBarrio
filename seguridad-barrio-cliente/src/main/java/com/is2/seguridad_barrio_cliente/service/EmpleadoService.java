package com.is2.seguridad_barrio_cliente.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.is2.seguridad_barrio_cliente.dto.PersonaDTO;
import com.is2.seguridad_barrio_cliente.enumeration.TipoEmpleado;
import com.is2.seguridad_barrio_cliente.error.ErrorServiceException;
import com.is2.seguridad_barrio_cliente.rest.EmpleadoDAORest;

@Service
public class EmpleadoService {

  @Autowired
  private EmpleadoDAORest dao;

  public void crear(
      String nombre,
      String apellido,
      String legajo,
      TipoEmpleado tipoEmpelado,
      String[] negociosId,
      String usuarioId)
      throws ErrorServiceException {

    try {

      PersonaDTO empleado = new PersonaDTO();
      empleado.setNombre(nombre);
      empleado.setApellido(apellido);
      empleado.setLegajo(legajo);
      empleado.setTipoEmpleado(tipoEmpelado);
      empleado.setNegociosId(negociosId);
      empleado.setUsuarioId(usuarioId);

      dao.crear(empleado);

    } catch (ErrorServiceException e) {
      throw e;

    } catch (Exception ex) {
      ex.printStackTrace();
      throw new ErrorServiceException("Error de Sistemas");
    }
  }

  public PersonaDTO buscar(String id) throws ErrorServiceException {

    try {

      if ("0".equals(id)) {
        throw new ErrorServiceException("Debe indicar la Empleado");
      }

      PersonaDTO empleado = dao.buscar(id);

      return empleado;

    } catch (ErrorServiceException ex) {
      throw ex;
    } catch (Exception ex) {
      ex.printStackTrace();
      throw new ErrorServiceException("Error de sistema");
    }
  }

  public void modificar(
      String id,
      String nombre,
      String apellido,
      String legajo,
      TipoEmpleado tipoEmpelado,
      String[] negociosId,
      String usuarioId)
      throws ErrorServiceException {

    try {

      PersonaDTO empleado = new PersonaDTO();
      empleado.setId(id);
      empleado.setNombre(nombre);
      empleado.setApellido(apellido);
      empleado.setLegajo(legajo);
      empleado.setTipoEmpleado(tipoEmpelado);
      empleado.setNegociosId(negociosId);
      empleado.setUsuarioId(usuarioId);

      dao.actualizar(empleado);

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

  public List<PersonaDTO> listar() throws ErrorServiceException {
    try {
      return dao.listar();
    } catch (Exception ex) {
      ex.printStackTrace();
      throw new ErrorServiceException("Error de sistema");
    }
  }
}

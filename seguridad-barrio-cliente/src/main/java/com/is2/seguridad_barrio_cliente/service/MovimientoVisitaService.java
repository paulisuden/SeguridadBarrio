package com.is2.seguridad_barrio_cliente.service;

import com.is2.seguridad_barrio_cliente.dto.MovimientoVisitaDTO;
import com.is2.seguridad_barrio_cliente.enumeration.EstadoMovimiento;
import com.is2.seguridad_barrio_cliente.enumeration.TipoMovilidad;
import com.is2.seguridad_barrio_cliente.error.ErrorServiceException;
import com.is2.seguridad_barrio_cliente.rest.MovimientoVisitaDAORest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class MovimientoVisitaService {

    @Autowired
    private MovimientoVisitaDAORest dao;

    public void crear(Date fechasMovimiento, String observacion, EstadoMovimiento estadoMovimiento,
            TipoMovilidad tipoMovilidad, String descripcionMovilidad, String idVisitante, String idInmueble)
            throws ErrorServiceException {

        try {

            MovimientoVisitaDTO movimiento = new MovimientoVisitaDTO();
            movimiento.setFechasMovimiento(fechasMovimiento);
            movimiento.setObservacion(observacion);
            movimiento.setEstadoMovimiento(estadoMovimiento);
            movimiento.setTipoMovilidad(tipoMovilidad);
            movimiento.setDescripcionMovilidad(descripcionMovilidad);
            movimiento.setIdVisitante(idVisitante);
            movimiento.setIdInmueble(idInmueble);
            System.out.println("ENTRO A SERVICE");
            dao.crear(movimiento);

        } catch (ErrorServiceException e) {
            throw e;

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public MovimientoVisitaDTO buscar(String id) throws ErrorServiceException {

        try {

            if ("0".equals(id)) {
                throw new ErrorServiceException("Debe indicar la localidad");
            }

            MovimientoVisitaDTO movimiento = dao.buscar(id);

            return movimiento;

        } catch (ErrorServiceException ex) {
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de sistema");
        }
    }

    public void modificar(String id, Date fechasMovimiento, String observacion, EstadoMovimiento estadoMovimiento,
            TipoMovilidad tipoMovilidad, String descripcionMovilidad, String idVisitante, String idInmueble)
            throws ErrorServiceException {

        try {

            MovimientoVisitaDTO movimiento = new MovimientoVisitaDTO();
            movimiento.setId(id);
            movimiento.setFechasMovimiento(fechasMovimiento);
            movimiento.setObservacion(observacion);
            movimiento.setEstadoMovimiento(estadoMovimiento);
            movimiento.setTipoMovilidad(tipoMovilidad);
            movimiento.setDescripcionMovilidad(descripcionMovilidad);
            movimiento.setIdVisitante(idVisitante);
            movimiento.setIdInmueble(idInmueble);

            dao.actualizar(movimiento);

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

    public List<MovimientoVisitaDTO> listar() throws ErrorServiceException {
        try {
            return dao.listar();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de sistema");
        }
    }

    public List<MovimientoVisitaDTO> listarPorInmuebleId(String id) throws ErrorServiceException {
        try {
            return dao.listarPorInmuebleId(id);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de sistema");
        }
    }
}

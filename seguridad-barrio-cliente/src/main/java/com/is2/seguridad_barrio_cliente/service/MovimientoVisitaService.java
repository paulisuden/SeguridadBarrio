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
                    TipoMovilidad tipoMovilidad, String descripcionMovilidad, Long  idVisitante, Long idInmueble) throws ErrorServiceException {

        try {

            MovimientoVisitaDTO movimiento = new MovimientoVisitaDTO();
            movimiento.setFechasMovimiento(fechasMovimiento);
            movimiento.setObservacion(observacion);
            movimiento.setEstadoMovimiento(estadoMovimiento);
            movimiento.setTipoMovilidad(tipoMovilidad);
            movimiento.setDescripcionMovilidad(descripcionMovilidad);
            movimiento.setIdVisitante(idVisitante);
            movimiento.setIdInmueble(idInmueble);

            dao.crear(movimiento);

        } catch (ErrorServiceException e) {
            throw e;

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public MovimientoVisitaDTO buscar(Long id) throws ErrorServiceException {

        try {

            if (id == 0) {
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

    public void modificar(Long id, Date fechasMovimiento, String observacion, EstadoMovimiento estadoMovimiento,
                        TipoMovilidad tipoMovilidad, String descripcionMovilidad, Long  idVisitante, Long idInmueble) throws ErrorServiceException {

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

    public List<MovimientoVisitaDTO> listar() throws ErrorServiceException {
        try {
            return dao.listar();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de sistema");
        }
    }
}

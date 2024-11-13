package com.is2.seguridad_barrio_cliente.service;

import com.is2.seguridad_barrio_cliente.dto.PlanillaHorariaDTO;
import com.is2.seguridad_barrio_cliente.enumeration.EstadoAsistencia;
import com.is2.seguridad_barrio_cliente.error.ErrorServiceException;
//import com.is2.seguridad_barrio_cliente.rest.EmpleadoDAORest;
import com.is2.seguridad_barrio_cliente.rest.PlanillaHorariaDAORest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PlanillaHorariaService {

    @Autowired
    private PlanillaHorariaDAORest dao;

    public void crear(LocalDateTime entrada, LocalDateTime salida, EstadoAsistencia estadoAsistencia,
            String observacion) throws ErrorServiceException {

        try {

            PlanillaHorariaDTO planillaHoraria = new PlanillaHorariaDTO();
            planillaHoraria.setEntrada(entrada);
            planillaHoraria.setEstadoAsistencia(estadoAsistencia);
            planillaHoraria.setObservacionAsistencia(observacion);
            planillaHoraria.setSalida(salida);
            // planillaHoraria.setEmpleadoId(idEmpleado);

            dao.crear(planillaHoraria);

        } catch (ErrorServiceException e) {
            throw e;

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public PlanillaHorariaDTO buscar(String id) throws ErrorServiceException {

        try {

            if ("0".equals(id)) {
                throw new ErrorServiceException("Debe indicar la planilla horaria");
            }

            PlanillaHorariaDTO planillaHoraria = dao.buscar(id);

            return planillaHoraria;

        } catch (ErrorServiceException ex) {
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de sistema");
        }
    }

    public void modificar(String id, LocalDateTime entrada, LocalDateTime salida, EstadoAsistencia estadoAsistencia,
            String observacion) throws ErrorServiceException {

        try {

            PlanillaHorariaDTO planillaHoraria = new PlanillaHorariaDTO();
            planillaHoraria.setId(id);
            planillaHoraria.setEntrada(entrada);
            planillaHoraria.setEstadoAsistencia(estadoAsistencia);
            planillaHoraria.setObservacionAsistencia(observacion);
            planillaHoraria.setSalida(salida);
            // planillaHoraria.setEmpleadoId(idEmpleado);

            dao.actualizar(planillaHoraria);

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

    public List<PlanillaHorariaDTO> listar() throws ErrorServiceException {
        try {
            return dao.listar();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de sistema");
        }
    }
}

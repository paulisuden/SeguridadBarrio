package com.is2.seguridad_barrio_cliente.dto;

import com.is2.seguridad_barrio_cliente.enumeration.EstadoMovimiento;
import com.is2.seguridad_barrio_cliente.enumeration.TipoMovilidad;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Data
public class MovimientoVisitaDTO implements Serializable {
    private Long id;
    private Date fechasMovimiento;
    private String observacion;
    private EstadoMovimiento estadoMovimiento;
    private TipoMovilidad tipoMovilidad;
    private String descripcionMovilidad;
    private VisitanteDTO visitante;
    private Long idVisitante;
    private InmuebleDTO inmueble;
    private Long idInmueble;
}

package com.is2.seguridad_barrio_cliente.dto;

import com.is2.seguridad_barrio_cliente.enumeration.EstadoMovimiento;
import com.is2.seguridad_barrio_cliente.enumeration.TipoMovilidad;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class MovimientoVisitaDTO implements Serializable {
    private String id;
    private LocalDateTime fechasMovimiento;
    private String observacion;
    private EstadoMovimiento estadoMovimiento;
    private TipoMovilidad tipoMovilidad;
    private String descripcionMovilidad;
    private VisitanteDTO visitante;
    private String idVisitante;
    private InmuebleDTO inmueble;
    private String idInmueble;

    public String getFechasMovimientoAsString() {
        return fechasMovimiento != null ? fechasMovimiento.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) : "";
    }
}

package com.is.servidor_barrio.business.domain.dto.movimientoVisita;

import com.is.servidor_barrio.business.domain.enumeration.EstadoMovimiento;
import com.is.servidor_barrio.business.domain.enumeration.TipoMovilidad;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MovimientoVisitaCreateDTO {

    private LocalDateTime fechasMovimiento;
    private String observacion;
    private EstadoMovimiento estadoMovimiento;
    private TipoMovilidad tipoMovilidad;
    private String descripcionMovilidad;
    private String idVisitante;
    private String idInmueble;
}

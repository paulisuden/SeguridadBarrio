package com.is.servidor_barrio.business.domain.dto.movimientoVisita;

import com.is.servidor_barrio.business.domain.dto.BaseDto;
import com.is.servidor_barrio.business.domain.dto.inmueble.InmuebleDto;
import com.is.servidor_barrio.business.domain.dto.visitante.VisitanteDto;
import com.is.servidor_barrio.business.domain.enumeration.EstadoMovimiento;
import com.is.servidor_barrio.business.domain.enumeration.TipoMovilidad;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MovimientoVisitaDTO extends BaseDto {

    private Date fechasMovimiento;
    private String observacion;
    private EstadoMovimiento estadoMovimiento;
    private TipoMovilidad tipoMovilidad;
    private String descripcionMovilidad;
    private Long idVisitante;
    private Long idInmuebles;
    private VisitanteDto visitante;
    private InmuebleDto inmueble;
}

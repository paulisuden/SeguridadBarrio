package com.is2.seguridad_barrio_cliente.dto;

import com.is2.seguridad_barrio_cliente.enumeration.TipoVisita;
import lombok.Data;

@Data
public class VisitanteDTO {
    private Long id;
    private boolean eliminado;
    private String nombre;
    private String apellido;
    private String numeroDeDocumento;
    private TipoVisita tipoVisita;
}

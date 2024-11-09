package com.is2.seguridad_barrio_cliente.dto;

import com.is2.seguridad_barrio_cliente.enumeration.TipoVisita;
import lombok.Data;

import java.io.Serializable;

@Data
public class VisitanteDTO implements Serializable {
    private Long id;
    private String nombre;
    private String apellido;
    private String numeroDeDocumento;
    private TipoVisita tipoVisita;
}

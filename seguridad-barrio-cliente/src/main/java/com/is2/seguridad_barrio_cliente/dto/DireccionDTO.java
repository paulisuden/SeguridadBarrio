package com.is2.seguridad_barrio_cliente.dto;

import lombok.Data;

@Data
public class DireccionDTO {
    private Long id;
    private String calle;
    private String numeracion;
    private String barrio;
    private String observacion;
    private Long localidadId;
    private LocalidadDTO localidad;

}

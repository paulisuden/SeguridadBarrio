package com.is2.seguridad_barrio_cliente.dto;

import lombok.Data;

@Data
public class DireccionDTO {
    private String calle;
    private String numeracion;
    private String barrio;
    private String observacion;
    private LocalidadDTO localidad;
    private Long id;
    private boolean eliminado;

}

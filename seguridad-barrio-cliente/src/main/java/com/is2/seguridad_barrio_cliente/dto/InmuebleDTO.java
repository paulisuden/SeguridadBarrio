package com.is2.seguridad_barrio_cliente.dto;

import lombok.Data;

@Data
public class InmuebleDTO {
    private Long id;
    private String numeracion;
    private String calle;
    private String manzana;
    // private Long idNegocio;
    // private UnidadDeNegocioDTO negocio;
}

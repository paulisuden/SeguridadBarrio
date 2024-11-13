package com.is2.seguridad_barrio_cliente.dto;

import lombok.Data;

@Data
public class InmuebleDTO {
    private String id;
    private String numeracion;
    private String calle;
    private String manzana;
    private String idUnidadDeNegocio;

    private NegocioDTO unidadDeNegocio;
}

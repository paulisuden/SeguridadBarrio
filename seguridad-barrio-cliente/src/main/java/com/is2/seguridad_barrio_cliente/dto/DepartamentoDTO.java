package com.is2.seguridad_barrio_cliente.dto;

import lombok.Data;

@Data
public class DepartamentoDTO {
    private Long id;
    private String nombre;
    private boolean eliminado;
    private ProvinciaDTO provincia;

}

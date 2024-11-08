package com.is2.seguridad_barrio_cliente.dto;

import lombok.Data;

@Data
public class LocalidadDTO {
    private Long id;
    private String nombre;
    private boolean eliminado;
    private String codigoPostal;
    private DepartamentoDTO departamentoDTO;


}
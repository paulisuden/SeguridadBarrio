package com.is2.seguridad_barrio_cliente.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class LocalidadDTO implements Serializable {
    private Long id;
    private String nombre;
    private String codigoPostal;
    private DepartamentoDTO departamentoDTO;


}
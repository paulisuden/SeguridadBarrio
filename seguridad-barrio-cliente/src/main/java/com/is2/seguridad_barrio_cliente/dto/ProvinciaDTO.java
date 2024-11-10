package com.is2.seguridad_barrio_cliente.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProvinciaDTO implements Serializable {
    private Long id;
    private String nombre;
    private Long paisId;
    private PaisDTO pais;

}

package com.is2.seguridad_barrio_cliente.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProvinciaDTO implements Serializable {
    private String id;
    private String nombre;
    private String paisId;
    private PaisDTO pais;

}
